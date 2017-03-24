package com.msioja.core.service;

import com.msioja.core.model.Role;
import com.msioja.core.model.User;
import com.msioja.core.repository.RoleRepository;
import com.msioja.core.repository.UserRepository;
import com.msioja.mapper.UserMapper;
import com.msioja.security.PasswordEncoderGenerator;
import com.msioja.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.toDto(userRepository.findByUserId(id));
    }

    @Override
    public UserDTO addUser(User user) {
        user.setPassword(PasswordEncoderGenerator
                .generate(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        Role roleUser = roleRepository.findByRole("ROLE_USER");
        roles.add(roleUser);
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO editUser(User user, Long id) {
        user.setPassword(PasswordEncoderGenerator
                .generate(user.getPassword()));
        user.setUserId(id);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findByUserId(id));
    }
}
