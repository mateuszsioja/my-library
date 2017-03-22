package com.msioja.core.service;

import com.msioja.core.model.User;
import com.msioja.core.repository.UserRepository;
import com.msioja.mapper.UserMapper;
import com.msioja.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.toDto(userRepository.findByUserId(id));
    }

    @Override
    public UserDTO addBook(User user) {
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO editUser(User user, Long id) {
        user.setUserId(id);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findByUserId(id));
    }
}
