package ru.tsc.anaumova.advertservice.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.tsc.anaumova.advertservice.dto.UserDto;
import ru.tsc.anaumova.advertservice.model.User;

public class MapperDtoTest {

    private User user;

    private UserDto userDto;

    private MapperDto<User, UserDto> userMapperDto;

    @Before
    public void init() {
        user = new User();
        user.setUserId(1L);
        user.setUsername("test");
        user.setRating(10);
        user.setFirstName("first");
        user.setLastName("last");

        userDto = new UserDto();
        userDto.setUserId(1L);
        userDto.setUsername("test");
        userDto.setRating(10);
        userDto.setFirstName("first");
        userDto.setLastName("last");

        userMapperDto = new MapperDto<>(UserDto.class, User.class);
    }

    @Test
    public void toDtoTest() {
        UserDto userDto = userMapperDto.toDto(user);
        Assert.assertEquals(user.getUserId(), userDto.getUserId());
        Assert.assertEquals(user.getUsername(), userDto.getUsername());
        Assert.assertEquals(user.getRating(), userDto.getRating());
        Assert.assertEquals(user.getFirstName(), userDto.getFirstName());
        Assert.assertEquals(user.getLastName(), userDto.getLastName());
    }

    @Test
    public void toEntityTest() {
        User user = userMapperDto.toEntity(userDto);
        Assert.assertEquals(userDto.getUserId(), user.getUserId());
        Assert.assertEquals(userDto.getUsername(), user.getUsername());
        Assert.assertEquals(userDto.getRating(), user.getRating());
        Assert.assertEquals(userDto.getFirstName(), user.getFirstName());
        Assert.assertEquals(userDto.getLastName(), user.getLastName());
    }

}
