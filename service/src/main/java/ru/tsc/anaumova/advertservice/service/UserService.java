package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.mapper.MapperJson;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final MapperDto<User, UserDto> userMapperDto;

    private final MapperJson<UserDto> userDtoMapperJson;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapperDto = new MapperDto<>(UserDto.class, User.class);
        this.userDtoMapperJson = new MapperJson<>(UserDto.class);
    }

    public Page<UserDto> findAll(Pageable pageable){
        List<UserDto> users = userRepository.findAll(pageable)
                .stream()
                .map(userMapperDto::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(users);
    }

    public String findById(final long userId) throws EntityNotFoundException {
        return userDtoMapperJson.toJson(userMapperDto.toDto(
                userRepository
                        .findById(userId)
                        .orElseThrow(EntityNotFoundException::new)
        ));
    }

    public void save(String jsonString) {
        final UserDto userDto = userDtoMapperJson.toEntity(jsonString);
        final User user = userMapperDto.toEntity(userDto);
        userRepository.save(user);
    }

    public void update(String jsonString) throws EntityNotFoundException {
        final UserDto userDto = userDtoMapperJson.toEntity(jsonString);
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
