package com.task.artemisdemo;

import com.task.artemisdemo.model.Message;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.task.artemisdemo.util.QueuesUtil.*;

public class TestData {
    public static final TestMatcher<Message> MATCHER = TestMatcher.usingEqualsComparator(Message.class);

    public static final int FOUND_222 = 222;
    public static final int NOT_FOUND = 3;
    public static final String FAKE_QUEUE = "unexistqueue";

    public static final LocalDate START_INTERVAL = LocalDate.of(2021, 5, 2);
    public static final LocalDate END_INTERVAL = LocalDate.of(2021, 5, 24);

    public static final Message mes1 = new Message(4, "привет", LocalDate.of(2021, 5, 1), firstQueue);
    public static final Message mes2 = new Message(22, "как дела", LocalDate.of(2021, 5, 1), firstQueue);
    public static final Message mes3 = new Message(222, "поздравляю", LocalDate.of(2021, 5, 2), secondQueue);
    public static final Message mes4 = new Message(12, "давай", LocalDate.of(2021, 5, 2), secondQueue);
    public static final Message mes5 = new Message(78, "до свидания", LocalDate.of(2021, 5, 3), thirdQueue);
    public static final Message mes6 = new Message(999, "чудо", LocalDate.of(2021, 5, 3), thirdQueue);
    public static final Message mes7 = new Message(234, "пока", LocalDate.of(2021, 5, 3), thirdQueue);
    public static final Message mes8 = new Message(95, "чао", LocalDate.of(2021, 5, 25), firstQueue);

    public static final List<Message> firstQueueMes = List.of(mes1, mes2, mes8);
    public static final List<Message> secondQueueMes = List.of(mes3, mes4);
    public static final List<Message> thirdQueueMes = List.of(mes5, mes7, mes6);

    public static final List<Message> messagesByDate = List.of(mes4, mes5, mes3, mes7, mes6);
    public static final List<Message> emptyList = Collections.emptyList();

    public static Message getNew() {
        return new Message(1000, "хорошего дня, любимый-дорогой!", LocalDate.now(), firstQueue);
    }

    public static Message getOld() {
        return new Message(FOUND_222, "этого текста не должно быть", LocalDate.now(), secondQueue);
    }

    public static Message getWithId_222() {
        return mes3;
    }
}
