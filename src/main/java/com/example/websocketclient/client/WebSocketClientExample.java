package com.example.websocketclient.client;

import com.example.websocketclient.client.entities.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

public class WebSocketClientExample {
    private StompSession stompSession;

    Logger logger = LoggerFactory.getLogger(WebSocketClientExample.class);

    private void setConnected(boolean connected) {
        // do nothing
    }


    public void connect() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        String urlForHandShake = "ws://localhost:8080/gs-guide-websocket";
        StompSessionHandler sessionHandler = new MyStompSessionHandler();

        stompClient.connect(urlForHandShake, sessionHandler);
    }

    public void disconnect() {
        if (stompSession != null) {
            stompSession.disconnect();
        }
        setConnected(false);
        logger.info("Disconnected");
    }

    public void sendName(String name) {
        if (stompSession != null) {
            stompSession.send("/app/hello", new HelloMessage(name));
        }
    }


    private class MyStompSessionHandler extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            stompSession = session;
            setConnected(true);
            logger.info("Connected");
            session.subscribe("/topic/greetings",  new CustomStompFrameHandler());
        }
    }

}
