package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CategoryDto> showCategoryList(
            @RequestParam(name = "parentCategoryId", required = false) final Integer parentCategoryId
    ) {
        List<CategoryDto> categories = categoryService.findByParentCategoryId(parentCategoryId);
        //if (categories.size() <= 0) return "redirect:/categories/" + parentCategoryId + "/adverts";
        return categories;
    }

    @PostMapping
    @Operation(summary = "Добавить категорию")
    public String addCategory(@RequestBody CategoryDto categoryDto){
        categoryService.save(categoryDto);
        return "redirect:/categories" + categoryDto.getCategoryId();
    }

    @PutMapping
    @Operation(summary = "Редактировать категорию")
    public String updateCategory(@RequestBody CategoryDto categoryDto) throws EntityNotFoundException {
        categoryService.update(categoryDto);
        return "redirect:/categories" + categoryDto.getCategoryId();
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Удалить категорию")
    public String deleteCategory(@PathVariable Long categoryId){
        categoryService.delete(categoryId);
        return "redirect:/categories";
    }

}
