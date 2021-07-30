package ru.tsc.anaumova.advertservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.tsc.anaumova.advertservice.model.Advert;

@Repository
public interface AdvertRepository  extends PagingAndSortingRepository<Advert, Long> {

    Iterable<Advert> findByUserId(Integer userId);

}