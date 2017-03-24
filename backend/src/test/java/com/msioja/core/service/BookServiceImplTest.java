package com.msioja.core.service;

import com.msioja.core.model.Book;
import com.msioja.core.model.User;
import com.msioja.core.repository.BookRepository;
import com.msioja.core.repository.UserRepository;
import com.msioja.mapper.BookMapper;
import com.msioja.web.dto.BookDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    private static final Long TEST_ID = 10L;
    private static final String TEST_TITLE = "title";
    private static final String TEST_AUTHOR = "author";
    private static final String TEST_SERIAL_NUMBER = "serial number";
    private static final String TEST_CATEGORY = "category";

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private PrincipalService principalService;

    @InjectMocks
    private BookServiceImpl bookService;

    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void findBooksForLoggedInUser() {
        Book book = dummyBook();
        User user = UserServiceImplTest.dummyUser();
        book.setUser(user);
        List<Book> books = new ArrayList<>();
        books.add(book);
        user.setBooks(books);

        List<BookDTO> bookDTOList = booksToListDTO(books);

        when(principalService.getCurrentlyLoggedInUserLogin()).thenReturn(user.getLogin());
        when(userRepository.findByLogin(user.getLogin())).thenReturn(user);
        when(bookRepository.findBooksForUserById(user.getUserId())).thenReturn(books);
        when(bookMapper.toDtoList(books)).thenReturn(bookDTOList);

        List<BookDTO> testBookDTOList = bookService.findBooksForLoggedInUser();

        assertEquals(testBookDTOList.get(0).getAuthor(), book.getAuthor());
        assertEquals(testBookDTOList.get(0).getUserId(), book.getUser().getUserId());
        assertEquals(testBookDTOList.get(0).getTitle(), book.getTitle());
        assertEquals(testBookDTOList.get(0).getCategory(), bookDTOList.get(0).getCategory());
        assertEquals(testBookDTOList.size(), bookDTOList.size());
    }

    static Book dummyBook() {
        Book book = new Book();
        book.setBookId(TEST_ID);
        book.setTitle(TEST_TITLE);
        book.setAuthor(TEST_AUTHOR);
        book.setSerialNumber(TEST_SERIAL_NUMBER);
        book.setCategory(TEST_CATEGORY);
        return book;
    }

    private BookDTO bookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getBookId());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setSerialNumber(book.getSerialNumber());
        bookDTO.setUserId(book.getUser().getUserId());
        return bookDTO;
    }

    private List<BookDTO> booksToListDTO(List<Book> books) {
        return books.stream().map(this::bookToDTO).collect(Collectors.toList());
    }
}
