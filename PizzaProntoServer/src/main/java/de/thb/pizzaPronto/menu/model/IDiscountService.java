package de.thb.pizzaPronto.menu.model;

import de.thb.pizzaPronto.menu.data.DiscountVO;

import java.util.List;

public interface IDiscountService {
    void addDiscount(DiscountVO discount);
    void deleteDiscount(DiscountVO discount);
    List<DiscountVO> getAllDiscounts();
}
