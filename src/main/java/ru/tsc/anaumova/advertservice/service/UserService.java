package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.model.Advert;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(final long id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ошибка. Не найден пользователь с ID - " + id));
    }

//    public List<Advert> findUserAdverts(final Long userId) throws EntityNotFoundException {
//        return findById(userId).getAdverts();
//    }

}
