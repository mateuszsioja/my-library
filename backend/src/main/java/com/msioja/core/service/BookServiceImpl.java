package com.msioja.core.service;

import com.msioja.core.model.Book;
import com.msioja.core.model.User;
import com.msioja.core.repository.BookRepository;
import com.msioja.core.repository.UserRepository;
import com.msioja.mapper.BookMapper;
import com.msioja.web.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private PrincipalService principalService;

    @Override
    public List<BookDTO> findAll() {
        return bookMapper.toDtoList(bookRepository.findAll());
    }

    @Override
    public List<BookDTO> findBooksForLoggedInUser() {
        User user = userRepository.findByLogin(principalService.getCurrentlyLoggedInUserLogin());
        List<Book> books = bookRepository.findBooksForUserById(user.getUserId());
        return bookMapper.toDtoList(books);
    }

    @Override
    public BookDTO findById(Long id) {
        return bookMapper.toDto(bookRepository.findByBookId(id));
    }

    @Override
    public BookDTO addBook(Book book) {
        User user = userRepository.findByLogin(principalService.getCurrentlyLoggedInUserLogin());
        book.setUser(user);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public BookDTO editBook(Book book, Long id) {
        book.setBookId(id);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(bookRepository.findByBookId(id));
    }

}
