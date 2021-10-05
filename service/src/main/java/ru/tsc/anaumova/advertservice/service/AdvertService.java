package ru.tsc.anaumova.advertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.tsc.anaumova.advertservice.comparator.AdvertPriorityFlagComparator;
import ru.tsc.anaumova.advertservice.enumerated.Status;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.exception.IncorrectStatusException;
import ru.tsc.anaumova.advertservice.model.Advert;
import ru.tsc.anaumova.advertservice.repository.AdvertRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertService {

    private final AdvertRepository advertRepository;

    @Autowired
    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public Page<Advert> findAllByFilter(Integer categoryId, Integer userId, String status, Pageable pageable) {
        List<Advert> adverts = advertRepository.findByCategoryId(categoryId, pageable)
                .stream()
                .filter(a -> (status == null || status.isEmpty()) || status.equals(a.getStatus()))
                .filter(a -> (categoryId == null || categoryId.equals(a.getCategoryId())))
                .filter(a -> (userId == null || userId.equals(a.getUserId())))
                .sorted(new AdvertPriorityFlagComparator().reversed())
                .collect(Collectors.toList());
        return new PageImpl<>(adverts, pageable, adverts.size());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Advert findById(Long advertId) throws EntityNotFoundException {
        return advertRepository.findById(advertId).orElseThrow(EntityNotFoundException::new);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void save(Advert advert) {
        advert.setDate(new Timestamp(new Date().getTime()));
        advert.setPriorityFlag(false);
        advert.setStatus(Status.OPEN.toString());
        advertRepository.save(advert);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void update(Advert advert) throws EntityNotFoundException, IncorrectStatusException {
        validateStatus(advert);
        Advert advertFromDb = advertRepository.findById(advert.getAdvertId()).orElseThrow(EntityNotFoundException::new);
        advertFromDb.setCategoryId(advert.getCategoryId());
        advertFromDb.setPriorityFlag(advert.getPriorityFlag());
        advertFromDb.setStatus(advert.getStatus());
        advertFromDb.setText(advert.getText());
        advertRepository.save(advertFromDb);
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

    private void validateStatus(Advert advert) throws IncorrectStatusException {
        if (!Status.checkStatus(advert.getStatus())) {
            throw new IncorrectStatusException();
        }
    }

}