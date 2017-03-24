package com.msioja.core.service;

import com.msioja.core.model.Book;
import com.msioja.core.model.Role;
import com.msioja.core.model.User;
import com.msioja.core.repository.RoleRepository;
import com.msioja.core.repository.UserRepository;
import com.msioja.mapper.UserMapper;
import com.msioja.web.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final Long TEST_ID = 10L;
    private static final String TEST_NAME = "Name";
    private static final String TEST_SURNAME = "Surname";
    private static final String TEST_LOGIN = "login";
    private static final String TEST_PASSWORD = "password";
    private static final String TEST_ROLE = "ROLE_USER";

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void addUser() {
        User user = dummyUser();
        Book book = BookServiceImplTest.dummyBook();
        Role role = dummyRole();
        book.setUser(user);
        List<Book> books = new ArrayList<>();
        books.add(book);
        user.setBooks(books);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        List<User> users = new ArrayList<>();
        users.add(user);
        role.setUsers(users);

        UserDTO userDTO = userToDTO(user);

        when(roleRepository.findByRole(TEST_ROLE)).thenReturn(role);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO testUser = userService.addUser(user);

        assertEquals(user.getBooks(), books);
        assertEquals(user.getRoles(), roles);
        assertEquals(user.getLogin(), TEST_LOGIN);
        assertNotEquals(user.getPassword(), TEST_PASSWORD);

        assertEquals(testUser.getBookIds().get(0), books.get(0).getBookId());
        assertEquals(testUser.getLogin(), TEST_LOGIN);
        assertEquals(testUser.getPassword(), TEST_PASSWORD);
        assertEquals(testUser.getName(), TEST_NAME);
        assertEquals(testUser.getSurname(), TEST_SURNAME);
    }

    @Test
    public void findById() {
        User user = dummyUser();
        Book book = BookServiceImplTest.dummyBook();
        book.setUser(user);
        List<Book> books = new ArrayList<>();
        books.add(book);
        user.setBooks(books);

        UserDTO userDTO = userToDTO(user);

        when(userRepository.findByUserId(TEST_ID)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        userDTO = userService.findById(TEST_ID);

        assertNotNull(userDTO);
        Long userId = userDTO.getUserId();
        assertEquals(userId, TEST_ID);
        assertEquals(userDTO.getLogin(), TEST_LOGIN);
        assertEquals(userDTO.getPassword(), TEST_PASSWORD);
        assertEquals(userDTO.getBookIds().get(0), book.getBookId());
        verify(userRepository, times(1)).findByUserId(any());
        verify(userMapper, times(1)).toDto(any());
        verifyNoMoreInteractions(userRepository);
    }

    static User dummyUser() {
        User user = new User();
        user.setUserId(TEST_ID);
        user.setName(TEST_NAME);
        user.setSurname(TEST_SURNAME);
        user.setLogin(TEST_LOGIN);
        user.setPassword(TEST_PASSWORD);
        return user;
    }

    private Role dummyRole() {
        Role role = new Role();
        role.setRoleId(TEST_ID);
        role.setRole(TEST_ROLE);
        return role;
    }

    private UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setBookIds(user.getBooks().stream()
                .map(book -> book.getBookId()).collect(Collectors.toList()));
        return userDTO;
    }
}
