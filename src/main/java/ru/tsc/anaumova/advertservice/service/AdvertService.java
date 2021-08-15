package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.mapper.Mapper;
import ru.tsc.anaumova.advertservice.model.Advert;
import ru.tsc.anaumova.advertservice.repository.AdvertRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertService {

    private final AdvertRepository advertRepository;

    private final Mapper<Advert, AdvertDto> advertMapper;

    @Autowired
    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
        advertMapper = new Mapper<>(AdvertDto.class, Advert.class);
    }

    public Page<AdvertDto> findByUserId(final Integer userId, Pageable pageable) {
        List<AdvertDto> adverts = advertRepository.findByUserId(userId, pageable)
                .stream()
                .map(advertMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(adverts);
    }

    public Page<AdvertDto> findAllByFilter(Integer categoryId, String status, Pageable pageable) {
        List<AdvertDto> adverts = advertRepository.findByCategoryId(categoryId, pageable)
                .stream()
                .filter(a -> (status == null || status.isEmpty()) || a.getStatus().equals(status))
                .map(advertMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(adverts);

    }

    public AdvertDto findById(Long advertId) throws EntityNotFoundException {
        return advertMapper.toDto(
                advertRepository
                        .findById(advertId)
                        .orElseThrow(EntityNotFoundException::new)
        );
    }

    public void save(AdvertDto advertDto) {
        final Advert advert = advertMapper.toEntity(advertDto);
        advertRepository.save(advert);
    }

    public void update(AdvertDto advertDto) throws EntityNotFoundException {
        final Advert advert = advertRepository.findById(advertDto.getAdvertId()).orElseThrow(EntityNotFoundException::new);
        advert.setCategoryId(advertDto.getCategoryId());
        advert.setPriorityFlag(advertDto.isPriorityFlag());
        advert.setStatus(advertDto.getStatus());
        advert.setText(advertDto.getText());
        advertRepository.save(advert);
    }

    public void delete(Long advertId) {
        advertRepository.deleteById(advertId);
    }

}