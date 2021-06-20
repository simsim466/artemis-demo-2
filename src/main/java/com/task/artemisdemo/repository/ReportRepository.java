package com.task.artemisdemo.repository;

import com.task.artemisdemo.model.Message;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository {
    Message save(Message message);
    Message getById(int reportId);
    List<Message> getByDate(LocalDate start, LocalDate end);
    List<Message> getByQueue(String queue);
}
