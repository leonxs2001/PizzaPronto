package de.thb.pizzaPronto.menu.model;

import de.thb.pizzaPronto.menu.data.DiscountVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService extends DiscountObservable implements IDiscountService {
    public DiscountService() {
        super();
    }

    @Override
    public void addDiscount(DiscountVO discount) {
        notifyDiscountObservers(discount);
        //TODO add everythng else
    }

    @Override
    public void deleteDiscount(DiscountVO discount) {
        //TODO add everythng else
    }

    @Override
    public List<DiscountVO> getAllDiscounts() {
        //TODO add everythng else
        return null;
    }
}
