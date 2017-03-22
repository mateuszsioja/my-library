package com.msioja.core.service;

import com.msioja.core.model.User;
import com.msioja.web.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO addBook(User user);

    UserDTO editUser(User user, Long id);

    void deleteUser(Long id);
}
