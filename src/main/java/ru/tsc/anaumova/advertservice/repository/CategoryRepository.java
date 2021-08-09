package ru.tsc.anaumova.advertservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.tsc.anaumova.advertservice.model.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

}