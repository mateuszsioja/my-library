package com.msioja.core.service;

import com.msioja.core.model.Book;
import com.msioja.web.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> findAll();

    BookDTO findById(Long id);

    BookDTO addBook(Book book);

    BookDTO editBook(Book book, Long id);

    void deleteBook(Long id);
}
