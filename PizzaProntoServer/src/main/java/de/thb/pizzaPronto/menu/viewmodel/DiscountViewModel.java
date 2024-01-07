package de.thb.pizzaPronto.menu.viewmodel;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import de.thb.pizzaPronto.menu.data.DiscountVO;
import de.thb.pizzaPronto.menu.model.IDiscountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/discount")
public class DiscountViewModel {
    private final IDiscountService discountService;

    @GetMapping
    public List<DiscountVO> getAllDiscounts(){
        return discountService.getAllDiscounts();
    }

    @DeleteMapping("/{id}")
    public void deleteDiscount(@PathVariable int id) throws IdNotFoundException {
        discountService.deleteDiscount(id);
    }

    @PostMapping
    public void addDiscount(@RequestBody DiscountVO discountVO){
        discountService.addDiscount(discountVO);
    }
}
