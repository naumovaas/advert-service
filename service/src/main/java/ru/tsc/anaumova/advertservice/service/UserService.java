package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.exception.ExistUsernameException;
import ru.tsc.anaumova.advertservice.exception.IncorrectPasswordException;
import ru.tsc.anaumova.advertservice.model.User;
import ru.tsc.anaumova.advertservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Page<User> findAll(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable)
                .stream()
                .collect(Collectors.toList());
        return new PageImpl<>(users, pageable, users.size());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public User findById(final Long userId) throws EntityNotFoundException {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    public void save(User user) throws ExistUsernameException {
        if (isUsernameExist(user.getUsername())) {
            throw new ExistUsernameException();
        }
        final String password = user.getPassword();
        final String passwordEncoded = bCryptPasswordEncoder.encode(password);
        user.setPassword(passwordEncoded);
        userRepository.save(user);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void update(User user) throws EntityNotFoundException, ExistUsernameException {
        if (isUsernameExist(user.getUsername())) {
            throw new ExistUsernameException();
        }
        final User userFromDb = userRepository
                .findById(user.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setUsername(user.getUsername());
        userFromDb.setRating(user.getRating());
        userRepository.save(userFromDb);
    }

    @Secured({"ROLE_ADMIN"})
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void updatePassword(Long userId, String oldPassword, String newPassword)
            throws EntityNotFoundException, IncorrectPasswordException {
        final User userFromDb = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        if (!checkOldPasswordEquals(userFromDb, oldPassword)) {
            throw new IncorrectPasswordException();
        }
        final String newPasswordEncoded = bCryptPasswordEncoder.encode(newPassword);
        userFromDb.setPassword(newPasswordEncoded);
        userRepository.save(userFromDb);
    }

    private boolean checkOldPasswordEquals(User user, String oldPassword) {
        final String currentPassword = user.getPassword();
        return bCryptPasswordEncoder.matches(oldPassword, currentPassword);
    }

    private boolean isUsernameExist(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return (userOptional.isPresent());
    }

}