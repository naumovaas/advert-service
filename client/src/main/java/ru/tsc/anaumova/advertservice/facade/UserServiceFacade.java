package ru.tsc.anaumova.advertservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.dto.UserRegistryDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.exception.ExistUsernameException;
import ru.tsc.anaumova.advertservice.exception.IncorrectPasswordException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceFacade {

    private final UserService userService;

    private final MapperDto<User, UserDto> userMapperDto;

    private final MapperDto<User, UserRegistryDto> userRegistryMapperDto;

    @Autowired
    public UserServiceFacade(UserService userService) {
        this.userService = userService;
        this.userMapperDto = new MapperDto<>(UserDto.class, User.class);
        this.userRegistryMapperDto = new MapperDto<>(UserRegistryDto.class, User.class);
    }

    public Page<UserDto> findAll(Pageable pageable) {
        List<UserDto> users = userService.findAll(pageable)
                .stream()
                .map(userMapperDto::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(users, pageable, users.size());
    }

    public UserDto findById(final Long userId) throws EntityNotFoundException {
        return userMapperDto.toDto(userService.findById(userId));
    }

    public void save(UserRegistryDto userRegistryDto) throws ExistUsernameException {
        final User user = userRegistryMapperDto.toEntity(userRegistryDto);
        userService.save(user);
    }

    public void update(UserDto userDto) throws EntityNotFoundException, ExistUsernameException {
        final User user = userMapperDto.toEntity(userDto);
        userService.update(user);
    }

    public void delete(Long userId) {
        userService.delete(userId);
    }

    public void updatePassword(Long userId, String oldPassword, String newPassword)
            throws EntityNotFoundException, IncorrectPasswordException {
        userService.updatePassword(userId, oldPassword, newPassword);
    }

}
