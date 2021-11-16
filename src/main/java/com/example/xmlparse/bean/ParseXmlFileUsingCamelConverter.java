package com.example.xmlparse.bean;

import com.example.xmlparse.model.dtos.DownloadableEBookDTO;
import org.apache.camel.Converter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

@Converter(generateLoader = true)
public class ParseXmlFileUsingCamelConverter {
    @Converter
    public DownloadableEBookDTO convertToDownloadableEBookDTO(File file) throws JAXBException {
        return (DownloadableEBookDTO) JAXBContext
                .newInstance(DownloadableEBookDTO.class)
                .createUnmarshaller()
                .unmarshal(file);
    }
}
