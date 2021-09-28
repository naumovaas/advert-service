package ru.tsc.anaumova.advertservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import ru.tsc.anaumova.advertservice.dto.AdvertDto;
import ru.tsc.anaumova.advertservice.exception.EntityNotFoundException;
import ru.tsc.anaumova.advertservice.service.AdvertService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AdvertControllerTest {

    @Autowired
    MockMvc mockMvc;

    private AdvertService advertService;

//    @Before
//    public void init() throws EntityNotFoundException {
//        AdvertDto advertDtoResult = new AdvertDto();
//        List<AdvertDto> advertsResult = new ArrayList<>();
//        advertService = mock(AdvertService.class);
//        doReturn(new PageImpl<>(advertsResult)).when(advertService).findAllByFilter(any(), any(), any());
//        doReturn(advertDtoResult).when(advertService).findById(any(Long.class));
//    }

//    @Test
//    public void findAllByFilterTest() throws Exception {
//        mockMvc.perform(get("/categories/2/adverts")
//                .param("categoryId", "2")
//                .param("status", "Открыто"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void showAdvertDetails() throws Exception {
//        mockMvc.perform(get("/categories/2/adverts")
//                .param("categoryId", "2")
//                .param("advertId", "1"))
//                .andExpect(status().isOk());
//    }

}