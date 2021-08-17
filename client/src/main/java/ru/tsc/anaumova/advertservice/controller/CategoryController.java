package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<List<CategoryDto>> showCategoryList(
            @RequestParam(name = "parentCategoryId", required = false) final Integer parentCategoryId
    ) {
        List<CategoryDto> categories = categoryService.findByParentCategoryId(parentCategoryId);
        //if (categories.size() <= 0) return "redirect:/categories/" + parentCategoryId + "/adverts";
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Добавить категорию")
    public String addCategory(@RequestParam String categoryJson){
        categoryService.save(categoryJson);
        return "redirect:/categories";
    }

    @PutMapping
    @Operation(summary = "Редактировать категорию")
    public String updateCategory(@RequestParam String categoryJson) throws EntityNotFoundException {
        categoryService.update(categoryJson);
        return "redirect:/categories";
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Удалить категорию")
    public String deleteCategory(@PathVariable Long categoryId){
        categoryService.delete(categoryId);
        return "redirect:/categories";
    }

}
