package ru.tsc.anaumova.advertservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.exception.IncorrectStatusException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.model.Advert;
import ru.tsc.anaumova.advertservice.service.AdvertService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertServiceFacade {

    private final AdvertService advertService;

    private final MapperDto<Advert, AdvertDto> advertMapperDto;

    @Autowired
    public AdvertServiceFacade(AdvertService advertService) {
        this.advertService = advertService;
        this.advertMapperDto = new MapperDto<>(AdvertDto.class, Advert.class);
    }

    public Page<AdvertDto> findAllByFilter(Integer categoryId, Integer userId, String status, Pageable pageable) {
        List<AdvertDto> adverts = advertService.findAllByFilter(categoryId, userId, status, pageable)
                .stream()
                .map(advertMapperDto::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(adverts, pageable, adverts.size());
    }

    public AdvertDto findById(Long advertId) throws EntityNotFoundException {
        return advertMapperDto.toDto(advertService.findById(advertId));
    }

    public void save(AdvertDto advertDto) {
        final Advert advert = advertMapperDto.toEntity(advertDto);
        advertService.save(advert);
    }

    public void update(AdvertDto advertDto) throws EntityNotFoundException, IncorrectStatusException {
        final Advert advert = advertMapperDto.toEntity(advertDto);
        advertService.update(advert);
    }

    public void delete(Long advertId) {
        advertService.delete(advertId);
    }

    public void setPriorityFlag(Long advertId) throws EntityNotFoundException {
        advertService.setPriorityFlag(advertId);
    }

}
