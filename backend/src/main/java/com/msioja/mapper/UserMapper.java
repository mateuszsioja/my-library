package com.msioja.mapper;

import com.msioja.core.model.Book;
import com.msioja.core.model.User;
import com.msioja.core.repository.BookRepository;
import com.msioja.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements GenericMapper<User, UserDTO> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public UserDTO toDto(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(entity.getUserId());
        userDTO.setName(entity.getName());
        userDTO.setSurname(entity.getSurname());
        userDTO.setLogin(entity.getLogin());
        userDTO.setPassword(entity.getPassword());
        //userDTO.setRoles(entity.getRoles().stream()
        //        .map(Role::getRole).collect(Collectors.toList()));
        userDTO.setBookIds(entity.getBooks().stream()
                .map(Book::getBookId).collect(Collectors.toList()));
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setBooks(dto.getBookIds().stream()
                .map(bookId -> bookRepository.findByBookId(bookId)).collect(Collectors.toList()));
        return user;
    }
}
