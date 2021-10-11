package ru.tsc.anaumova.advertservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.exception.IncorrectStatusException;
import ru.tsc.anaumova.advertservice.facade.AdvertServiceFacade;

@RestController
@RequestMapping("/adverts")
public class AdvertController {

    private final AdvertServiceFacade advertServiceFacade;

    @Autowired
    public AdvertController(AdvertServiceFacade advertServiceFacade) {
        this.advertServiceFacade = advertServiceFacade;
    }

    @GetMapping
    @Operation(summary = "Просмотр списка объявлений")
    @Parameter(name = "categoryId", description = "Ид категории")
    @Parameter(name = "status", description = "Статус, по которому фильтруем", required = false)
    @Parameter(name = "userId", description = "Ид автора объявления", required = false)
    @Parameter(name = "categoryId", description = "Ид категории", required = false)
    public ResponseEntity<Page<AdvertDto>> showAdverts(
            @RequestParam(name = "status", required = false) final String status,
            @RequestParam(name = "userId", required = false) final Integer userId,
            @RequestParam(name = "categoryId", required = false) final Integer categoryId,
            Pageable pageable
    ) {
        return new ResponseEntity<>(advertServiceFacade.findAllByFilter(categoryId, userId, status, pageable), HttpStatus.OK);
    }

    @GetMapping("/{advertId}")
    @Operation(summary = "Просмотр объявления")
    @Parameter(name = "categoryId", description = "Ид категории")
    @Parameter(name = "advertId", description = "Ид объявления")
    public ResponseEntity<AdvertDto> showAdvertDetails(@PathVariable Long advertId) throws EntityNotFoundException {
        return new ResponseEntity<>(advertServiceFacade.findById(advertId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Добавить объявление")
    @Parameter(name = "categoryId", description = "Ид категории")
    public void add(@RequestBody AdvertDto advertDto) {
        advertServiceFacade.save(advertDto);
    }

    @PutMapping
    @Operation(summary = "Редактировать объявление")
    @Parameter(name = "categoryId", description = "Ид категории")
    public void update(@RequestBody AdvertDto advertDto, @AuthenticationPrincipal UserDetails userDetails) throws EntityNotFoundException, IncorrectStatusException {
        advertServiceFacade.update(advertDto, userDetails);
    }

    @DeleteMapping("/{advertId}")
    @Operation(summary = "Удалить объявление")
    @Parameter(name = "categoryId", description = "Ид категории")
    @Parameter(name = "advertId", description = "Ид удаляемого объявления")
    public void delete(@PathVariable Long advertId, @AuthenticationPrincipal UserDetails userDetails) {
        advertServiceFacade.delete(advertId, userDetails);
    }

    @PatchMapping
    @Operation(summary = "Установить флаг приоритета для объявления")
    @Parameter(name = "categoryId", description = "Ид категории")
    @Parameter(name = "advertId", description = "Ид объявления")
    public void updateAdvertPriority(@RequestParam Long advertId) throws EntityNotFoundException {
        advertServiceFacade.setPriorityFlag(advertId);
    }

}
