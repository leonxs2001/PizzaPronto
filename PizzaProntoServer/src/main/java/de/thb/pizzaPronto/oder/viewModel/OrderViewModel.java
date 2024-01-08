package de.thb.pizzaPronto.oder.viewModel;

import de.thb.pizzaPronto.menu.data.DiscountVO;
import de.thb.pizzaPronto.menu.data.DishVO;
import de.thb.pizzaPronto.menu.data.MenuVO;
import de.thb.pizzaPronto.menu.model.AbstractDiscountObservable;
import de.thb.pizzaPronto.menu.model.AbstractMenuObservable;
import de.thb.pizzaPronto.menu.model.IDiscountObserver;
import de.thb.pizzaPronto.menu.model.IMenuObserver;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class OrderViewModel implements IDiscountObserver, IMenuObserver {
    private final SimpMessagingTemplate messagingTemplate;

    public OrderViewModel(SimpMessagingTemplate messagingTemplate, AbstractDiscountObservable discountObservable, AbstractMenuObservable menuObservable){
        this.messagingTemplate = messagingTemplate;
        discountObservable.attachDiscountObserver(this);
        menuObservable.attachMenuObserver(this);
    }

    @Override
    public void updateDiscount(DiscountVO discount) {
        messagingTemplate.convertAndSend("/topic/discount", discount);
    }

    @Override
    public void updateMenu(MenuVO menu) {
        messagingTemplate.convertAndSend("/topic/menu", menu);
    }

}
