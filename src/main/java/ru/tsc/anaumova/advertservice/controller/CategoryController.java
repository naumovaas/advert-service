package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.Comment;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    @Operation(summary = "Просмотр категорий")
    public void showCategoryList(@RequestParam(name = "parentCategoryId", required = false) final String parentCategoryId) {
        //если нет подкатегорий для параметра - return redirect /advert/filter
    }

    @PostMapping
    @Operation(summary = "Добавить категорию")
    public void addCategory(@RequestBody Comment comment){
        //return redirect /category/{parentCategory}
    }

    @PutMapping
    @Operation(summary = "Редактировать категорию")
    public void updateCategory(@RequestBody Comment comment){
        //return redirect /category/{parentCategory}
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Удалить категорию")
    public void deleteCategory(@PathVariable String categoryId){
        //return redirect category/{parentCategory}
    }

}
