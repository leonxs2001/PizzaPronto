package de.thb.pizzaPronto.menu.data;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class DiscountDAO implements IDiscountDAO {
    private List<DiscountVO> discounts;

    public DiscountDAO() {
        discounts = new ArrayList<>();
    }

    @Override
    public void saveDiscount(DiscountVO discount) {
        if(discount == null){
            throw new NullPointerException("Discount should not be null.");
        }
        if(discount.getId() == 0){
            discount.resetId();
            discounts.add(discount);
        }else{
            int appearanceIndex = discounts.indexOf(discount);
            if(appearanceIndex != -1){
                this.discounts.set(appearanceIndex, discount);
            }else{
                discounts.add(discount);
            }
        }
    }

    @Override
    public List<DiscountVO> getAllDiscounts() {
        return discounts;
    }

    @Override
    public void deleteDiscountById(int discountId) throws IdNotFoundException {
        if(!discounts.remove(new DiscountVO(discountId))){
            throw new IdNotFoundException("There is no discount with the given id");
        }
    }
}
