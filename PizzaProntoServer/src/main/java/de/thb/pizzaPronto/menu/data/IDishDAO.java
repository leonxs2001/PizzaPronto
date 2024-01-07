package de.thb.pizzaPronto.menu.data;

import de.thb.pizzaPronto.Exception.IdNotFoundException;

import java.util.List;

public interface IDishDAO {
    DishVO saveDish(DishVO dish);
    void saveAllDishes(List<DishVO> dishes);
    void deleteAllDishes(List<DishVO> dishes) throws IdNotFoundException;
    List<DishVO> getAllDishes();
    void deleteDishByNumber(int dishNumber) throws IdNotFoundException;
}
