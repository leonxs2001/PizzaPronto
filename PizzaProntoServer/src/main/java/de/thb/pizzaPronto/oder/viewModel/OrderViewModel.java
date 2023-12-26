package de.thb.pizzaPronto.oder.viewModel;

import de.thb.pizzaPronto.menu.data.DiscountVO;
import de.thb.pizzaPronto.menu.model.DiscountObservable;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RestController
public class OrderViewModel implements IOrderViewModel{
    private final SimpMessagingTemplate messagingTemplate;

    public OrderViewModel(SimpMessagingTemplate messagingTemplate, DiscountObservable discountObservable){
        this.messagingTemplate = messagingTemplate;
        discountObservable.attachDiscountObserver(this);
    }

    @Override
    public void updateDiscount(DiscountVO discount) {
        //new DiscountVO("Test", "Test", LocalDate.now(), LocalDate.now(), LocalTime.now(), LocalTime.now())
        messagingTemplate.convertAndSend("/topic/discount", discount);
    }
}
