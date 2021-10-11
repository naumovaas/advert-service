package ru.tsc.anaumova.advertservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.dto.UserRegistryDto;
import ru.tsc.anaumova.advertservice.exception.AccessDeniedException;
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

    public void update(UserDto userDto, Long userId, UserDetails userDetails) throws EntityNotFoundException, ExistUsernameException, AccessDeniedException {
        final User user = userMapperDto.toEntity(userDto);
        userService.update(user, userId, userDetails);
    }

    public void delete(Long userId) {
        userService.delete(userId);
    }

    public void updatePassword(Long userId, String oldPassword, String newPassword, UserDetails userDetails)
            throws EntityNotFoundException, IncorrectPasswordException, AccessDeniedException {
        userService.updatePassword(userId, oldPassword, newPassword, userDetails);
    }

}
