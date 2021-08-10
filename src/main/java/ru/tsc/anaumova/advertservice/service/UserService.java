package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.Mapper;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final Mapper<User, UserDto> userMapper;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new Mapper<>(UserDto.class, User.class);
    }

    public Page<UserDto> findAll(Pageable pageable){
        List<UserDto> users = userRepository.findAll(pageable)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(users);
    }

    public UserDto findById(final long userId) throws EntityNotFoundException {
        return userMapper.toDto(
                userRepository
                        .findById(userId)
                        .orElseThrow(EntityNotFoundException::new)
        );
    }

    public void deleteById(final Long id) {
        userRepository.deleteById(id);
    }

}
