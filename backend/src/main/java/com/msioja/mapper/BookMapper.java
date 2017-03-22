package com.msioja.mapper;

import com.msioja.core.model.Book;
import com.msioja.core.repository.BookRepository;
import com.msioja.core.repository.UserRepository;
import com.msioja.web.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements GenericMapper<Book, BookDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public BookDTO toDto(Book entity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(entity.getBookId());
        bookDTO.setAuthor(entity.getAuthor());
        bookDTO.setSerialNumber(entity.getSerialNumber());
        bookDTO.setTitle(entity.getTitle());
        bookDTO.setCategory(entity.getCategory());
        bookDTO.setUserId(entity.getUser().getUserId());
        return bookDTO;
    }

    @Override
    public Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setBookId(dto.getBookId());
        book.setSerialNumber(dto.getSerialNumber());
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setCategory(dto.getCategory());
        book.setUser(userRepository.findByUserId(dto.getUserId()));
        return book;
    }
}
