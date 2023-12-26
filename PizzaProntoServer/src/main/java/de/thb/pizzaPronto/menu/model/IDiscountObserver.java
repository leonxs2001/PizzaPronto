package de.thb.pizzaPronto.menu.model;

import de.thb.pizzaPronto.menu.data.DiscountVO;

public interface IDiscountObserver {
    void updateDiscount(DiscountVO discount);
}
