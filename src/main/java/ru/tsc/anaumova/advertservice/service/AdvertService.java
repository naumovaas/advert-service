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

    public Page<AdvertDto> findAllByFilter(String filter, Pageable pageable) {
        List<AdvertDto> adverts = advertRepository.findAll(pageable)
                .stream()
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

}