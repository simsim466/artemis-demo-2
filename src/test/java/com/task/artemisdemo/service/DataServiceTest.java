package com.task.artemisdemo.service;

import com.task.artemisdemo.AbstractTest;
import com.task.artemisdemo.TestData;
import com.task.artemisdemo.TimingExtension;
import com.task.artemisdemo.model.Message;
import com.task.artemisdemo.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.task.artemisdemo.TestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@ExtendWith(TimingExtension.class)
class DataServiceTest extends AbstractTest {

    @Autowired
    private DataService service;

    @Test
    void save() {
        Message message = service.save(getNew());
        MATCHER.assertMatch(message, getNew());
    }

    @Test
    void duplicateIdSave() {
        Message message = getOld();
        assertThrows(IllegalArgumentException.class, () -> service.save(message));
    }

    @Test
    void getById() {
        Message message = service.getById(FOUND_222);
        MATCHER.assertMatch(message, TestData.getWithId_222());
    }

    @Test
    void getByIdNotFound() {
        assertThrows(NotFoundException.class, () -> service.getById(NOT_FOUND));
    }

    @Test
    void getByQueue() {
        List<Message> list = service.getByQueue("mythirdqueue");
        MATCHER.assertMatch(thirdQueueMes, list);
    }

    @Test
    void getByFakeQueue() {
        assertThrows(IllegalArgumentException.class, () -> service.getByQueue(FAKE_QUEUE));
    }

    @Test
    void getByDate() {
        List<Message> list = service.getByDate(START_INTERVAL, END_INTERVAL);
        MATCHER.assertMatch(list, messagesByDate);
    }

    @Test
    void getByDateNotFound() {
        List<Message> list = service.getByDate(END_INTERVAL, START_INTERVAL);
        MATCHER.assertMatch(emptyList, list);
    }
}