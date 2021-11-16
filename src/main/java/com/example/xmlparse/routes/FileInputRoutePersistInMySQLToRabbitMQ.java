package com.example.xmlparse.routes;

import com.example.xmlparse.model.dtos.DownloadableEBookDTO;
import com.example.xmlparse.utils.IsXmlFile;
import com.example.xmlparse.bean.ParseXmlFileUsingJAXB;
import com.example.xmlparse.bean.PersistBookInMySQLDB;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Slf4j
@AllArgsConstructor
@Component
public class FileInputRoutePersistInMySQLToRabbitMQ extends RouteBuilder {
    @Autowired
    private final IsXmlFile isXmlFile;

    private final Logger myLogger = Logger.getLogger("mylogger");
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(myLogger.getName());

    @Override
    public void configure() {
        from("file:/Users/simeonatanasov/WebstormProjects/input/input")
                .multicast()
                .parallelProcessing()
                .to("direct:archive-file", "direct:parse-jaxb-save-db");

        from("direct:archive-file")
                .to("file:/Users/simeonatanasov/WebstormProjects/input/xmls");

        from("direct:parse-jaxb-save-db")
                .choice()
                    .when(isXmlFile)
                        .log(LoggingLevel.INFO, logger,"File is xml format. Parsing with JAXB")
                        .bean(ParseXmlFileUsingJAXB.class)
                        .log(LoggingLevel.INFO, logger, "File parsed with JAXB. Persisting entity in DB")
                        .bean(PersistBookInMySQLDB.class)
                        .log(LoggingLevel.INFO, logger, "Entities persisted.")
                        .to("rabbitmq:xmlparser?hostname=localhost&portNumber=5672&exchangeType=fanout&queue=parsedxml")
                    .endChoice()
                .end();

        from("direct:parse-converter-save-db")
                .choice()
                    .when(isXmlFile)
                        .log(LoggingLevel.INFO, logger, "File is xml format. Parsing with Camel converter")
                        .convertBodyTo(DownloadableEBookDTO.class)
                        .log(LoggingLevel.INFO, logger, "File parsed with Camel converter. Persisting entity in DB")
                        .bean(PersistBookInMySQLDB.class)
                        .log(LoggingLevel.INFO, logger, "Entities persisted.")
                    .endChoice()
                .end();

        JaxbDataFormat dataFormat = new JaxbDataFormat(DownloadableEBookDTO.class.getName());
        dataFormat.setContextPathIsClassName(true);

        from("direct:parse-camel-jaxb-save-db")
                .choice()
                .when(isXmlFile)
                .log(LoggingLevel.WARN, logger, "File is xml format. Parsing with Camel JAXB")
                .unmarshal(dataFormat)
                .log(LoggingLevel.WARN, logger, "File parsed with Camel JAXB. Persisting entity in DB")
                .bean(PersistBookInMySQLDB.class)
                .log(LoggingLevel.WARN, logger, "Entities persisted.")
                .endChoice()
                .end();
    }
}
