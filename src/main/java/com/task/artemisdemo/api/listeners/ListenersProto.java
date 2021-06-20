package com.task.artemisdemo.api.listeners;

import com.task.artemisdemo.model.MessageTo;
import com.task.artemisdemo.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.task.artemisdemo.util.MessageUtil.convertFromTo;

@Slf4j
public abstract class ListenersProto {
    @Autowired
    private DataService dataService;

    public void save(MessageTo messageTo, String queue) {
        log.info("Received message: {} via queue {}", messageTo, queue);
        dataService.save(convertFromTo(messageTo, queue));
    }
}
