package ru.tsc.anaumova.advertservice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.exception.IncorrectPasswordException;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private final List<User> users = new ArrayList<>();

    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();;

    @Before
    public void init() {
        initTestData();
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(users));
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(new User()));

        userService = new UserService(userRepository, bCryptPasswordEncoder);

    }

    private void initTestData() {
        User user1 = new User();
        user1.setPassword(bCryptPasswordEncoder.encode("12345"));
        users.add(user1);
        User user2 = new User();
        users.add(user2);
    }

    @Test
    public void findAllTest() {
        Page<UserDto> resultFindAll = userService.findAll(PageRequest.of(1, 2));
        Assert.assertNotNull(resultFindAll);
        Assert.assertNotNull(resultFindAll.getContent());
        Assert.assertEquals(2, resultFindAll.getContent().size());
    }

    @Test
    public void findByIdTest() throws EntityNotFoundException {
        UserDto resultUser = userService.findById(1L);
        Assert.assertNotNull(resultUser);
    }

}
