package de.thb.pizzaPronto.menu.model;

import de.thb.pizzaPronto.menu.data.DishVO;
import de.thb.pizzaPronto.menu.data.MenuVO;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenuObservable {
    private List<IMenuObserver> observers;
    public AbstractMenuObservable(){
        observers = new ArrayList<>();
    }

    public void attachMenuObserver(IMenuObserver menuObserver) {
        observers.add(menuObserver);
    }

    public void detachMenuObserver(IMenuObserver menuObserver) {
        observers.remove(menuObserver);
    }

    public void notifyMenuObserversMenu(MenuVO menu) {
        observers.forEach(observer -> observer.updateMenu(menu));
    }
}
