package com.example.xmlparse.bean;

import com.example.xmlparse.model.dtos.DownloadableEBookDTO;
import org.apache.camel.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

@Converter(generateLoader = true)
public class ParseXmlFileUsingCamelConverter {

    @Autowired
    private ApplicationContext applicationContext;

    @Converter
    public DownloadableEBookDTO convertToDownloadableEBookDTO(File file) throws JAXBException {
        applicationContext.getBeanDefinitionNames();


        return (DownloadableEBookDTO) JAXBContext
                .newInstance(DownloadableEBookDTO.class)
                .createUnmarshaller()
                .unmarshal(file);
    }
}
