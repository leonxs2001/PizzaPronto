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
        ArrayList<DishVO> dishes = new ArrayList<>();
        for(DishVO dish : menu.getDishes()){
            DishVO new_dish = new DishVO(dish.getNumber(), dish.getName(), dish.getPrice(), dish.getTimeToMake(), dish.getIngredients());
            dishes.add(new_dish);
        }
        observers.forEach(observer -> observer.updateMenu(new MenuVO(dishes)));
    }
}
