package com.example.xmlparse.bean;

import com.example.xmlparse.model.book.impl.DownloadableEBook;
import com.example.xmlparse.model.dtos.DownloadableEBookDTO;
import com.example.xmlparse.model.dtos.DownloadableEBookListDTO;
import com.example.xmlparse.repository.jpa.DownloadableEBookRepository;
import lombok.AllArgsConstructor;
import org.apache.camel.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PersistBookInMySQLDB {
    @Autowired
    private final DownloadableEBookRepository downloadableEBookRepository;

    public String persistBookInDB(@Body DownloadableEBookListDTO downloadableEBookListDTO) throws JAXBException {
        String ISBNlist = "";
        if ("DownloadableEBookListDTO".equals(downloadableEBookListDTO.getClass().getSimpleName())) {
            List<DownloadableEBookDTO> downloadableEBookList = downloadableEBookListDTO.getDownloadableEBookDTOS();

            ISBNlist = downloadableEBookList
                    .stream()
                    .peek(book -> {
                        DownloadableEBook downloadableEBook = new DownloadableEBook(
                                book.getISBN(),
                                book.getTitle(),
                                book.getSummary(),
                                book.getGenre(),
                                book.getTags(),
                                book.getOnlineLink(),
                                book.getDownloadLink()
                        );
                        this.downloadableEBookRepository.saveAndFlush(downloadableEBook);
                    })
                    .map(DownloadableEBookDTO::getISBN)
                    .collect(Collectors.joining(", "));
        }
        return ISBNlist;
    }
}
