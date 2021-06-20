package com.task.artemisdemo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.artemisdemo.util.MessageUtil;
import com.task.artemisdemo.model.Message;
import com.task.artemisdemo.model.MessageTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.TextMessage;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/send")
public class DispatcherController {
    @Autowired
    private final JmsTemplate jmsTemplate;

    @PostMapping()
    public String send(@RequestBody Message message) {
        MessageTo messageTo = MessageUtil.convertTo(message);
        String currentQueue = message.getQueue();
        log.info("Send message {} to jms {}", messageTo, currentQueue);
        jmsTemplate.send(currentQueue, to -> {
            try {
                TextMessage tm = to.createTextMessage(new ObjectMapper().writeValueAsString(messageTo));
                tm.setJMSType(MessageTo.class.getTypeName());
                tm.setStringProperty("typeId", MessageTo.class.getTypeName());
                return tm;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        });
        return "Message sent " + messageTo;
    }
}
