package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.enumerated.Status;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.MapperDto;
import ru.tsc.anaumova.advertservice.model.Advert;
import ru.tsc.anaumova.advertservice.repository.AdvertRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertService {

    private final AdvertRepository advertRepository;

    private final MapperDto<Advert, AdvertDto> advertMapperDto;

    @Autowired
    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
        this.advertMapperDto = new MapperDto<>(AdvertDto.class, Advert.class);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Page<AdvertDto> findByUserId(final Integer userId, Pageable pageable) {
        List<AdvertDto> adverts = advertRepository.findByUserId(userId, pageable)
                .stream()
                .map(advertMapperDto::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(adverts);
    }

    public Page<AdvertDto> findAllByFilter(Integer categoryId, String status, Pageable pageable) {
        List<AdvertDto> adverts = advertRepository.findByCategoryId(categoryId, pageable)
                .stream()
                .filter(a -> (status == null || status.isEmpty()) || a.getStatus().equals(status))
                .map(advertMapperDto::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(adverts);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public AdvertDto findById(Long advertId) throws EntityNotFoundException {
        return advertMapperDto.toDto(
                advertRepository
                        .findById(advertId)
                        .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void save(AdvertDto advertDto) {
        final Advert advert = advertMapperDto.toEntity(advertDto);
        advert.setDate(new Timestamp(new Date().getTime()));
        advert.setPriorityFlag(false);
        advert.setStatus(Status.OPEN.getDisplayName());
        advertRepository.save(advert);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void update(AdvertDto advertDto) throws EntityNotFoundException {
        final Advert advert = advertRepository.findById(advertDto.getAdvertId()).orElseThrow(EntityNotFoundException::new);
        advert.setCategoryId(advertDto.getCategoryId());
        advert.setPriorityFlag(advertDto.getPriorityFlag());
        advert.setStatus(advertDto.getStatus());
        advert.setText(advertDto.getText());
        advertRepository.save(advert);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void delete(Long advertId) {
        advertRepository.deleteById(advertId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void setPriorityFlag(Long advertId) throws EntityNotFoundException {
        final Advert advert = advertRepository.findById(advertId).orElseThrow(EntityNotFoundException::new);
        advert.setPriorityFlag(true);
        advertRepository.save(advert);
    }

}