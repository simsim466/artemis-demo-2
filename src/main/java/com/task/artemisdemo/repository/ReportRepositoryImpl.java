package com.task.artemisdemo.repository;

import com.task.artemisdemo.model.Message;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {
    @Autowired
    private final DataJpaRepository repository;

    @Override
    public Message save(Message message) {
        if (!repository.existsById(message.getId())) {
            return repository.save(message);
        }
        return null;
    }

    @Override
    public Message getById(int reportId) {
        return repository.findById(reportId).orElse(null);
    }

    @Override
    public List<Message> getByDate(LocalDate start, LocalDate end) {
        return repository.findByDateBetween(start, end);
    }

    @Override
    public List<Message> getByQueue(String queue) {
        return repository.findByQueueOrderById(queue);
    }
}
