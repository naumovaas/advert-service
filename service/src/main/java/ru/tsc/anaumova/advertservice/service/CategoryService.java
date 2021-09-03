package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.CategoryDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.mapper.MapperJson;
import ru.tsc.anaumova.advertservice.model.Category;
import ru.tsc.anaumova.advertservice.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final MapperDto<Category, CategoryDto> categoryMapperDto;

    private final MapperJson<CategoryDto> categoryDtoMapperJson;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapperDto = new MapperDto<>(CategoryDto.class, Category.class);
        this.categoryDtoMapperJson = new MapperJson<>(CategoryDto.class);
    }

    public List<CategoryDto> findByParentCategoryId(Integer parentCategoryId) {
        return categoryRepository.findByParentCategoryId(parentCategoryId)
                .stream()
                .map(categoryMapperDto::toDto)
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    public void save(String jsonString) {
        final CategoryDto categoryDto = categoryDtoMapperJson.toEntity(jsonString);
        final Category category = categoryMapperDto.toEntity(categoryDto);
        categoryRepository.save(category);
    }

    @Secured("ROLE_ADMIN")
    public void update(String jsonString) throws EntityNotFoundException {
        final CategoryDto categoryDto = categoryDtoMapperJson.toEntity(jsonString);
        final Category category = categoryRepository
                .findById(categoryDto.getCategoryId())
                .orElseThrow(EntityNotFoundException::new);
        category.setParentCategoryId(categoryDto.getParentCategoryId());
        category.setTitle(categoryDto.getTitle());
        categoryRepository.save(category);
    }

    @Secured("ROLE_ADMIN")
    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}