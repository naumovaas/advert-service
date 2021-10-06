package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.model.Category;
import ru.tsc.anaumova.advertservice.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findByParentCategoryId(Integer parentCategoryId) {
        return categoryRepository.findByParentCategoryId(parentCategoryId);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void update(Category category) throws EntityNotFoundException {
        final Category categoryFromDb = categoryRepository
                .findById(category.getCategoryId())
                .orElseThrow(EntityNotFoundException::new);
        category.setParentCategoryId(category.getParentCategoryId());
        category.setTitle(category.getTitle());
        categoryRepository.save(categoryFromDb);
    }

    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}