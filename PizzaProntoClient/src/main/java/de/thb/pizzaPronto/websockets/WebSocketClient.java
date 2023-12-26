package de.thb.pizzaPronto.websockets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.thb.pizzaPronto.menu.rest.DiscountVO;
import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class WebSocketClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        stompClient.setMessageConverter(converter);

        /*WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("Authorization" + "Bearer " + authenticatedUser.getToken());*/
        stompClient.connectAsync("ws://localhost:8080/ws", new MyStompSessionHandler()).get();



        while (true) {
        }
    }

    private static class MyStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders headers) {
            return DiscountVO.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            DiscountVO discount = (DiscountVO) payload;
            System.out.println(discount);
        }
    }

    private static class MyStompSessionHandler extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            System.out.println("test");
            session.subscribe("/topic/discount", new MyStompFrameHandler());
        }

        @Override
        public void handleException(StompSession session, @Nullable StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            System.out.println("test");
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            System.out.println("test");
        }
    }
}