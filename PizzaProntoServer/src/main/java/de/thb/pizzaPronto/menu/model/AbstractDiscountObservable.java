package de.thb.pizzaPronto.menu.model;

import de.thb.pizzaPronto.menu.data.DiscountVO;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDiscountObservable {

    List<IDiscountObserver> observers;

    public AbstractDiscountObservable(){
        observers = new ArrayList<>();
    }

    public void attachDiscountObserver(IDiscountObserver discountObserver){
        observers.add(discountObserver);
    }

    public void detachDiscountObserver(IDiscountObserver discountObserver){
        observers.remove(discountObserver);
    }

    public void notifyDiscountObservers(DiscountVO discount){
        observers.forEach(observer -> observer.updateDiscount(discount));
    }
}
