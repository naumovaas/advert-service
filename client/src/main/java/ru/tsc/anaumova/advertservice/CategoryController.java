package ru.tsc.anaumova.advertservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.CategoryDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.facade.CategoryServiceFacade;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceFacade categoryServiceFacade;

    @Autowired
    public CategoryController(CategoryServiceFacade categoryServiceFacade) {
        this.categoryServiceFacade = categoryServiceFacade;
    }

    @GetMapping
    @Operation(summary = "Просмотр категорий")
    @Parameter(name = "parentCategoryId", description = "Ид родительской категории", required = false)
    public ResponseEntity<List<CategoryDto>> showList(
            @RequestParam(name = "parentCategoryId", required = false) final Integer parentCategoryId
    ) {
        List<CategoryDto> categories = categoryServiceFacade.findByParentCategoryId(parentCategoryId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Добавить категорию")
    public void add(@RequestBody CategoryDto categoryDto) {
        categoryServiceFacade.save(categoryDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать категорию")
    public void update(@RequestBody CategoryDto categoryDto) throws EntityNotFoundException {
        categoryServiceFacade.update(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Удалить категорию")
    @Parameter(name = "categoryId", description = "Ид удаляемой категории")
    public void delete(@PathVariable Long categoryId) {
        categoryServiceFacade.delete(categoryId);
    }

}
