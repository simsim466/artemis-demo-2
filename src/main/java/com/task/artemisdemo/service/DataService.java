package com.task.artemisdemo.service;

import com.task.artemisdemo.model.Message;
import com.task.artemisdemo.repository.ReportRepository;
import com.task.artemisdemo.util.QueuesUtil;
import com.task.artemisdemo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.task.artemisdemo.util.QueuesUtil.*;
import static com.task.artemisdemo.util.ValidationUtil.checkNotFoundWithId;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

@Service
public class DataService {
    @Autowired
    private ReportRepository repository;

    public Message save(Message message) {
        notNull(message, "report entity must not be null");
        Message created = repository.save(message);
        Integer index = message.getId();
        if (index != null) {
            notNull(created, "the entity with id=" + index + " already exists");
        }
        return created;
    }

    public Message getById(int reportId) {
        return checkNotFoundWithId(repository.getById(reportId), reportId);
    }

    public List<Message> getByQueue(String queue) {
        isTrue(getAll().contains(queue), "the queue is absent in the list of available queues");
        return repository.getByQueue(queue);
    }

    public List<Message> getByDate(LocalDate start, LocalDate end) {
        return repository.getByDate(start, end);
    }
}
