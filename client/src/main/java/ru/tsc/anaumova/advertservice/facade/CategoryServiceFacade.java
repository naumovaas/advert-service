package ru.tsc.anaumova.advertservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.CategoryDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.model.Category;
import ru.tsc.anaumova.advertservice.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceFacade {

    private final CategoryService categoryService;

    private final MapperDto<Category, CategoryDto> categoryMapperDto;

    @Autowired
    public CategoryServiceFacade(CategoryService categoryService) {
        this.categoryService = categoryService;
        this.categoryMapperDto = new MapperDto<>(CategoryDto.class, Category.class);
    }

    public List<CategoryDto> findByParentCategoryId(Integer parentCategoryId) {
        return categoryService.findByParentCategoryId(parentCategoryId)
                .stream()
                .map(categoryMapperDto::toDto)
                .collect(Collectors.toList());
    }

    public void save(CategoryDto categoryDto) {
        final Category category = categoryMapperDto.toEntity(categoryDto);
        categoryService.save(category);
    }

    public void update(CategoryDto categoryDto) throws EntityNotFoundException {
        final Category category = categoryMapperDto.toEntity(categoryDto);
        categoryService.update(category);
    }

    public void delete(Long categoryId) {
        categoryService.delete(categoryId);
    }

}
