package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.CategoryDto;
import ru.tsc.anaumova.advertservice.mapper.Mapper;
import ru.tsc.anaumova.advertservice.model.Category;
import ru.tsc.anaumova.advertservice.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final Mapper<Category, CategoryDto> categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = new Mapper<>(CategoryDto.class, Category.class);
    }

}
