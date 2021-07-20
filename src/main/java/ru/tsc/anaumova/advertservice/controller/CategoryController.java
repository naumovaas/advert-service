package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.model.Comment;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @PostMapping("/add")
    @Operation(summary = "Добавить категорию")
    public void addCategory(@RequestBody Comment comment){
        //return redirect /category/{parentCategory}
    }

    @PostMapping("/update")
    @Operation(summary = "Редактировать категорию")
    public void updateCategory(@RequestBody Comment comment){
        //return redirect /category/{parentCategory}
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить категорию")
    public void deleteCategory(@PathVariable String id){
        //return redirect category/{parentCategory}
    }

    @GetMapping("/{parentCategory}")
    @Operation(summary = "Просмотр категорий")
    public void showCategoryList(@PathVariable(required = false) String parentCategory){
        //если нет подкатегорий для параметра - return redirect /advert/filter
    }

}
