package com.task.artemisdemo.api;

import com.task.artemisdemo.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static com.task.artemisdemo.TestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DataControllerTest extends AbstractControllerTest {
    private static final String DATA_URI = "/message-data/";

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(DATA_URI + FOUND_222))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentJson(getWithId_222()));
    }

    @Test
    void getNotFound() {
        Throwable throwable = Assertions.assertThrows(NestedServletException.class, () ->
                perform(MockMvcRequestBuilders.get(DATA_URI + NOT_FOUND)));

        Assertions.assertEquals(throwable.getCause().getClass(), NotFoundException.class);
        Assertions.assertEquals(throwable.getCause().getMessage(), "Not found entity with id=" + NOT_FOUND);
    }

    @Test
    void getByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(DATA_URI)
                .param("start", "2021-05-02")
                .param("end", "2021-05-24"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentJson(messagesByDate))
                .andDo(print());
    }

    @Test
    void getByQueue() throws Exception {
        perform(MockMvcRequestBuilders.get(DATA_URI + "byQueue/" + "mythirdqueue"))
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MATCHER.contentJson(thirdQueueMes))
        .andDo(print());
    }

    @Test
    void getByQueueNotFound() {
        Throwable throwable = Assertions.assertThrows(NestedServletException.class, () ->
                perform(MockMvcRequestBuilders.get(DATA_URI + "byQueue/" + FAKE_QUEUE)));

        Assertions.assertEquals(throwable.getCause().getClass(), IllegalArgumentException.class);
        Assertions.assertEquals(throwable.getCause().getMessage(), "the queue is absent in the list of available queues");
    }
}