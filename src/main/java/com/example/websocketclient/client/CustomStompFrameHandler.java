package com.example.websocketclient.client;

import com.example.websocketclient.client.entities.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;

public class CustomStompFrameHandler implements StompFrameHandler {

    Logger logger = LoggerFactory.getLogger(CustomStompFrameHandler.class);


    @Override
    public Type getPayloadType(StompHeaders headers) {
        logger.info("getPayloadType()");
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Greeting msg = (Greeting) payload;
        logger.info("Received : {}", msg.getContent());
    }


}
