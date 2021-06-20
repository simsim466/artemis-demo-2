package com.task.artemisdemo.api;

import com.task.artemisdemo.model.Message;
import com.task.artemisdemo.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/message-data", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("/{id}")
    public Message get(@PathVariable int id) {
        log.info("get message with id={}", id);
        return dataService.getById(id);
    }

    @GetMapping("/")
    public List<Message> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate end) {
        log.info("get messages from {} to {}", start, end);
        return dataService.getByDate(start, end);
    }

    @GetMapping("byQueue/{queue}")
    public List<Message> getByQueue(@PathVariable String queue) {
        log.info("get messages by queue \"{}\"", queue);
        return dataService.getByQueue(queue);
    }
}
