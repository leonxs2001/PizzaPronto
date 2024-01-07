package de.thb.pizzaPronto.menu.model;

import de.thb.pizzaPronto.menu.data.DishVO;
import de.thb.pizzaPronto.menu.data.MenuVO;

public interface IMenuObserver {
    void updateMenu(MenuVO menu);
}
