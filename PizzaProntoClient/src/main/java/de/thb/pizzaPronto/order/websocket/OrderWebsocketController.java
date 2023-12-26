package de.thb.pizzaPronto.order.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.thb.pizzaPronto.menu.rest.DiscountVO;
import de.thb.pizzaPronto.menu.rest.MenuVO;
import de.thb.pizzaPronto.websockets.WebSocketClient;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
public class OrderWebsocketController implements IOrderWebsocketController{
    private static String API_URL = "ws://localhost:8080";
    private final IOrderWebsocketGUIController orderWebsocketGUIController;

    @Override
    public void updateDiscount(DiscountVO discount) {
        orderWebsocketGUIController.updateDiscount(discount);
    }

    @Override
    public void updateMenu(MenuVO menu) {
        orderWebsocketGUIController.updateMenu(menu);
    }

    private void connect() throws ExecutionException, InterruptedException {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        stompClient.setMessageConverter(converter);

        /*WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("Authorization" + "Bearer " + authenticatedUser.getToken());*/
        stompClient.connectAsync(API_URL + "/ws", new MyStompSessionHandler()).get();
    }

    @Override
    public void update() {
        try {
            connect();
        } catch (ExecutionException | InterruptedException e) {
            orderWebsocketGUIController.showException(e);
        }
    }

    private class MyStompDiscountFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders headers) {
            return DiscountVO.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            DiscountVO discount = (DiscountVO) payload;
            updateDiscount(discount);
        }
    }

    private class MyStompMenuFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders headers) {
            return MenuVO.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            MenuVO menu = (MenuVO) payload;
            updateMenu(menu);
        }
    }

    private class MyStompSessionHandler extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            session.subscribe("/topic/discount", new MyStompDiscountFrameHandler());
            session.subscribe("/topic/menu", new MyStompMenuFrameHandler());
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
