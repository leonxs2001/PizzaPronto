package de.thb.pizzaPronto.menu.model;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import de.thb.pizzaPronto.menu.data.DiscountVO;
import de.thb.pizzaPronto.menu.data.IDiscountDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiscountService extends DiscountObservable implements IDiscountService {
    private final IDiscountDAO discountDAO;


    @Override
    public void addDiscount(DiscountVO discount) {
        discountDAO.saveDiscount(discount);
        notifyDiscountObservers(discount);
    }

    @Override
    public void deleteDiscount(int discountId) throws IdNotFoundException {
        discountDAO.deleteDiscountById(discountId);
    }

    @Override
    public List<DiscountVO> getAllDiscounts() {
        return discountDAO.getAllDiscounts();
    }
}
