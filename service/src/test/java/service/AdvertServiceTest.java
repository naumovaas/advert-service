package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.model.Advert;
import ru.tsc.anaumova.advertservice.repository.AdvertRepository;
import ru.tsc.anaumova.advertservice.service.AdvertService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdvertServiceTest {

    private final List<Advert> adverts = new ArrayList<>();

    private AdvertService advertService;

    @Before
    public void init() {
        initTestData();
        AdvertRepository advertRepository = mock(AdvertRepository.class);
//        doNothing().when(advertRepository.save(any(Advert.class)));
        when(advertRepository.findByUserId(any(Integer.class), any(Pageable.class))).thenReturn(new PageImpl<>(adverts));
        when(advertRepository.findByCategoryId(any(Integer.class), any(Pageable.class))).thenReturn(new PageImpl<>(adverts));

        advertService = new AdvertService(advertRepository);
    }

    private void initTestData() {
        Advert testAdvert1 = new Advert();
        testAdvert1.setUserId(1);
        testAdvert1.setCategoryId(1);
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

}