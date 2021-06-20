package com.task.artemisdemo.util;

import com.task.artemisdemo.model.Message;
import com.task.artemisdemo.model.MessageTo;

import java.time.LocalDate;

public class MessageUtil {
    public static MessageTo convertTo(Message message) {
        return new MessageTo(message.getId(), message.getText());
    }

    public static Message convertFromTo(MessageTo to, String messageQueue) {
        return new Message(to.getId(), to.getText(), LocalDate.now(), messageQueue);
    }
}
