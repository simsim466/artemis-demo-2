package com.task.artemisdemo.api;

import com.task.artemisdemo.TestData;
import com.task.artemisdemo.model.Message;
import com.task.artemisdemo.service.DataService;
import com.task.artemisdemo.util.json.JsonUtil;
import org.apache.activemq.artemis.junit.EmbeddedActiveMQResource;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DispatcherControllerTest extends AbstractControllerTest {
    private static final String DISPATCHER_URI = "/send/";

    @Rule
    public static EmbeddedActiveMQResource embeddedActiveMQResource = new EmbeddedActiveMQResource();

    @Autowired
    private DataService service;

    @Test
    void send() throws Exception {
        Message message = TestData.getNew();
        message.setDate(null);

        perform(MockMvcRequestBuilders.post(DISPATCHER_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(message)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
        JmsListenerEndpointRegistry jmsListenerEndpointRegistry = this.webApplicationContext.getBean(JmsListenerEndpointRegistry.class);
        jmsListenerEndpointRegistry.stop();
        Thread.sleep(1000);
        TestData.MATCHER.assertMatch(service.getById(message.getId()), TestData.getNew());

    }
}