package ru.tsc.anaumova.advertservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.tsc.anaumova.advertservice.model.Advert;

@Repository
public interface AdvertRepository  extends PagingAndSortingRepository<Advert, Long> {

    Page<Advert> findByUserId(Integer userId, Pageable pageable);

}