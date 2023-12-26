package de.thb.pizzaPronto.menu.viewmodel;

import de.thb.pizzaPronto.menu.data.DiscountVO;
import de.thb.pizzaPronto.menu.model.IDiscountService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@Controller
@AllArgsConstructor
public class DiscountViewModel {
    private final IDiscountService discountService;

    @GetMapping("/test")
    public String test(){//TODO delete (is only for test cases
        discountService.addDiscount(new DiscountVO("Test", "Test", LocalDate.now(), LocalDate.now(), LocalTime.now(), LocalTime.now()));
        return "jaaa";
    }
}
