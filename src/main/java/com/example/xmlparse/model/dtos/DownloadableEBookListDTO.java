package com.example.xmlparse.model.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "downloadables")
@XmlAccessorType(XmlAccessType.FIELD)
public class DownloadableEBookListDTO {
    @XmlElement(name = "downloadable-ebook")
    private List<DownloadableEBookDTO> downloadableEBookDTOS;

    public List<DownloadableEBookDTO> getDownloadableEBookDTOS(){
        return Collections.unmodifiableList(this.downloadableEBookDTOS);
    }
}
