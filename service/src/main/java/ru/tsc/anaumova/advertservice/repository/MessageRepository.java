package ru.tsc.anaumova.advertservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.tsc.anaumova.advertservice.model.Message;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    Page<Message> findByDialogId(Integer dialogId, Pageable pageable);
}