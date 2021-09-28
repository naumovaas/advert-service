package ru.tsc.anaumova.advertservice.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.enumerated.Status;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.model.Advert;
import ru.tsc.anaumova.advertservice.repository.AdvertRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdvertServiceTest {

    private final List<Advert> adverts = new ArrayList<>();

    private AdvertService advertService;

    @Before
    public void init() {
        initTestData();
        AdvertRepository advertRepository = mock(AdvertRepository.class);
        when(advertRepository.findByUserId(any(Integer.class), any(Pageable.class))).thenReturn(new PageImpl<>(adverts));
        when(advertRepository.findByCategoryId(any(Integer.class), any(Pageable.class))).thenReturn(new PageImpl<>(adverts));
        when(advertRepository.findById(any(Long.class))).thenReturn(Optional.of(new Advert()));

        advertService = new AdvertService(advertRepository);
    }

    private void initTestData() {
        Advert testAdvert1 = new Advert();
        testAdvert1.setUserId(1);
        testAdvert1.setCategoryId(1);
        testAdvert1.setStatus("Открыто");
        Advert testAdvert2 = new Advert();
        testAdvert1.setUserId(1);
        testAdvert1.setCategoryId(1);
        Advert testAdvert3 = new Advert();
        testAdvert1.setUserId(2);
        testAdvert1.setCategoryId(2);
        adverts.add(testAdvert1);
        adverts.add(testAdvert2);
        adverts.add(testAdvert3);
    }

    @Test
    public void findByUserIdTest() {
        Page<AdvertDto> resultFindByUserId = advertService.findByUserId(1, PageRequest.of(1, 2));
        Assert.assertNotNull(resultFindByUserId);
        Assert.assertNotNull(resultFindByUserId.getContent());
        Assert.assertEquals(3, resultFindByUserId.getContent().size());
    }

    @Test
    public void findAllByFilterTest() {
        Page<AdvertDto> resultFindAllByFilter = advertService.findAllByFilter(1, "Открыто", PageRequest.of(1, 2));
        Assert.assertNotNull(resultFindAllByFilter);
        Assert.assertNotNull(resultFindAllByFilter.getContent());
        Assert.assertEquals(1, resultFindAllByFilter.getContent().size());

        Page<AdvertDto> nullResultFindAllByFilter = advertService.findAllByFilter(1, "Закрыто", PageRequest.of(1, 2));
        Assert.assertNotNull(nullResultFindAllByFilter);
        Assert.assertEquals(0, nullResultFindAllByFilter.getContent().size());
    }

    @Test
    public void findByIdTest() throws EntityNotFoundException {
        AdvertDto resultAdvert = advertService.findById(1L);
        Assert.assertNotNull(resultAdvert);
    }

//    @Test
//    public void saveTest() {
//
//    }
//
//    @Test
//    public void setPriorityFlagTest() throws EntityNotFoundException {
//
//    }

    @Test
    public void checkStatusTest() {
        Assert.assertTrue(Status.checkStatus("Открыто"));
        Assert.assertTrue(Status.checkStatus("Закрыто"));
        Assert.assertTrue(Status.checkStatus("Забронировано"));
        Assert.assertFalse(Status.checkStatus("111"));
        Assert.assertFalse(Status.checkStatus(""));
        Assert.assertFalse(Status.checkStatus(null));
    }

}