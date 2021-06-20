package com.task.artemisdemo.repository;

import com.task.artemisdemo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DataJpaRepository extends JpaRepository<Message, Integer> {
    List<Message> findByDateBetween(LocalDate start, LocalDate end);

    List<Message> findByQueueOrderById(String queue);
}
