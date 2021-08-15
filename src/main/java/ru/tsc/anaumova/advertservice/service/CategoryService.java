package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.CategoryDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.Mapper;
import ru.tsc.anaumova.advertservice.model.Category;
import ru.tsc.anaumova.advertservice.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final Mapper<Category, CategoryDto> categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = new Mapper<>(CategoryDto.class, Category.class);
    }

    public List<CategoryDto> findByParentCategoryId(Integer parentCategoryId) {
        List<CategoryDto> categories = categoryRepository.findByParentCategoryId(parentCategoryId)
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
        return categories;
    }

    public void save(CategoryDto categoryDto) {
        final Category category = categoryMapper.toEntity(categoryDto);
        categoryRepository.save(category);
    }

    public void update(CategoryDto categoryDto) throws EntityNotFoundException {
        final Category category = categoryRepository
                .findById(categoryDto.getCategoryId())
                .orElseThrow(EntityNotFoundException::new);
        category.setParentCategoryId(categoryDto.getParentCategoryId());
        category.setTitle(categoryDto.getTitle());
        categoryRepository.save(category);
    }

    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}