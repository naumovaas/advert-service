package ru.tsc.anaumova.advertservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.tsc.anaumova.advertservice.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    List<Message> findByDialogId(Integer dialogId, Pageable pageable);
}