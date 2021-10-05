package ru.tsc.anaumova.advertservice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.tsc.anaumova.advertservice.model.Category;
import ru.tsc.anaumova.advertservice.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private final List<Category> categories = new ArrayList<>();

    private CategoryService categoryService;

    @Before
    public void init() {
        initTestData();
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findByParentCategoryId(any(Integer.class))).thenReturn(categories);

        categoryService = new CategoryService(categoryRepository);
    }

    private void initTestData() {
        categories.add(new Category());
        categories.add(new Category());
    }

    @Test
    public void findByParentCategoryIdTest() {
        List<Category> resultCategoryList = categoryService.findByParentCategoryId(1);
        Assert.assertNotNull(resultCategoryList);
        Assert.assertEquals(2, resultCategoryList.size());
        Assert.assertNotNull(resultCategoryList.get(0));
    }

}