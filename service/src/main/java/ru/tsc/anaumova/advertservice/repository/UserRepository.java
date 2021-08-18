package ru.tsc.anaumova.advertservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.tsc.anaumova.advertservice.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

}