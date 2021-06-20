package com.task.artemisdemo.api.listeners;

import com.task.artemisdemo.model.MessageTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Controller;

import javax.jms.ConnectionFactory;

import static com.task.artemisdemo.util.QueuesUtil.*;

@Slf4j
@Controller
@Configuration
@EnableJms
public class Listeners extends ListenersProto {

    @JmsListener(destination = firstQueue, containerFactory = "messageFactory")
    public void receiveFirst(MessageTo messageTo) {
        super.save(messageTo, firstQueue);
    }

    @JmsListener(destination = secondQueue, containerFactory = "messageFactory")
    public void receiveSecond(MessageTo messageTo) {
        super.save(messageTo, secondQueue);
    }

    @JmsListener(destination = thirdQueue, containerFactory = "messageFactory")
    public void receiveThird(MessageTo messageTo) {
        super.save(messageTo, thirdQueue);
    }

    @Bean("messageFactory")
    public JmsListenerContainerFactory<?> messageFactory(ConnectionFactory connectionFactory,
                                                         DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("typeId");
        factory.setMessageConverter(converter);
        return factory;
    }
}
