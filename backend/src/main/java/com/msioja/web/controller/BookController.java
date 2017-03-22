package com.msioja.web.controller;

import com.msioja.core.service.BookService;
import com.msioja.mapper.BookMapper;
import com.msioja.web.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<BookDTO> getBookById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.addBook(bookMapper.toEntity(bookDTO)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<BookDTO> editBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(bookService.editBook(bookMapper.toEntity(bookDTO), id), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBookById(@PathVariable(name = "id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
