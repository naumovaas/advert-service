package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.MessageDto;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.Mapper;
import ru.tsc.anaumova.advertservice.model.Message;
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

    public void save(UserDto userDto) {
        final User user = userMapper.toEntity(userDto);
        userRepository.save(user);
    }

    public void update(UserDto userDto) throws EntityNotFoundException {
        final User user = userRepository
                .findById(userDto.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setRating(userDto.getRating());
        userRepository.save(user);
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

}
