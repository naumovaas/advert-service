package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.CategoryDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Просмотр категорий")
    @Parameter(name = "parentCategoryId", description = "Ид родительской категории", required = false)
    public ResponseEntity<List<CategoryDto>> showCategoryList(
            @RequestParam(name = "parentCategoryId", required = false) final Integer parentCategoryId
    ) {
        List<CategoryDto> categories = categoryService.findByParentCategoryId(parentCategoryId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Добавить категорию")
    public void addCategory(@RequestBody CategoryDto categoryDto){
        categoryService.save(categoryDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать категорию")
    public void updateCategory(@RequestBody CategoryDto categoryDto) throws EntityNotFoundException {
        categoryService.update(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Удалить категорию")
    @Parameter(name = "categoryId", description = "Ид удаляемой категории")
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.delete(categoryId);
    }

}
