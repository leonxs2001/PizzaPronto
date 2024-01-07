package de.thb.pizzaPronto.menu.data;

import de.thb.pizzaPronto.Exception.IdNotFoundException;

import java.util.List;

public interface IDiscountDAO {
    void saveDiscount(DiscountVO discount);
    List<DiscountVO> getAllDiscounts();
    void deleteDiscountById(int discountId) throws IdNotFoundException;
}
