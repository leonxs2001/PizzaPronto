package de.thb.pizzaPronto.menu.data;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class DishDAO implements IDishDAO {
    private List<DishVO> dishes;

    public DishDAO() {
        dishes = new ArrayList<>();
    }

    @Override
    public DishVO saveDish(DishVO dish) {
        if(dish == null){
            throw new NullPointerException("Dish should not be null.");
        }
        if(dish.getNumber() == 0){
            dish.resetNumber();
            dishes.add(dish);
        }else{
            int appearanceIndex = dishes.indexOf(dish);
            if(appearanceIndex != -1){
                this.dishes.set(appearanceIndex, dish);
            }else{
                dishes.add(dish);
            }
        }
        return dish;
    }

    @Override
    public void saveAllDishes(List<DishVO> dishes) {
        this.dishes.addAll(dishes);
    }

    @Override
    public void deleteAllDishes(List<DishVO> dishes) {
        this.dishes.removeAll(dishes);

    }

    @Override
    public List<DishVO> getAllDishes() {
        return dishes;
    }

    @Override
    public void deleteDishByNumber(int dishNumber) throws IdNotFoundException {
        int size_before = dishes.size();
        dishes.removeIf(dish -> dish.getNumber() == dishNumber);
        //TODO: dish.getNumberOfDish()
        if (size_before <= dishes.size()){
            throw new IdNotFoundException("There is no dish with the given id");
        }

    }
}
