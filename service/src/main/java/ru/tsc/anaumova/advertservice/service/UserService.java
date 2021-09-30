package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.exception.IncorrectPasswordException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MapperDto<User, UserDto> userMapperDto;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapperDto = new MapperDto<>(UserDto.class, User.class);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Page<UserDto> findAll(Pageable pageable) {
        List<UserDto> users = userRepository.findAll(pageable)
                .stream()
                .map(userMapperDto::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(users);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public UserDto findById(final Long userId) throws EntityNotFoundException {
        return userMapperDto.toDto(
                userRepository
                        .findById(userId)
                        .orElseThrow(EntityNotFoundException::new)
        );
    }

    public void save(User user) {
        final String password = user.getPassword();
        final String passwordEncoded = bCryptPasswordEncoder.encode(password);
        user.setPassword(passwordEncoded);
        userRepository.save(user);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void update(UserDto userDto) throws EntityNotFoundException {
        final User userFromDb = userRepository
                .findById(userDto.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        userFromDb.setFirstName(userDto.getFirstName());
        userFromDb.setLastName(userDto.getLastName());
        userFromDb.setUsername(userDto.getUsername());
        userFromDb.setRating(userDto.getRating());
        userRepository.save(userFromDb);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void updatePassword(
            Long userId,
            String oldPassword,
            String newPassword
    ) throws EntityNotFoundException, IncorrectPasswordException {
        final User userFromDb = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        if (!checkOldPasswordEquals(userFromDb, oldPassword)) throw new IncorrectPasswordException();
        final String newPasswordEncoded = bCryptPasswordEncoder.encode(newPassword);
        userFromDb.setPassword(newPasswordEncoded);
        userRepository.save(userFromDb);
    }

    private boolean checkOldPasswordEquals(User user, String oldPassword) {
        final String currentPassword = user.getPassword();
        return bCryptPasswordEncoder.matches(oldPassword, currentPassword);
    }

}