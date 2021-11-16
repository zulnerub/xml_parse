package com.example.xmlparse.bean;

import com.example.xmlparse.model.dtos.DownloadableEBookListDTO;
import org.apache.camel.Body;
import org.apache.camel.component.file.GenericFile;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

@Component
public class ParseXmlFileUsingJAXB {
    public static DownloadableEBookListDTO convertXmlToBook(@Body GenericFile genericFile) throws JAXBException {
        File file = (File) genericFile.getFile();

        return  (DownloadableEBookListDTO) JAXBContext
                .newInstance(DownloadableEBookListDTO.class)
                .createUnmarshaller()
                .unmarshal(file);
    }
}
