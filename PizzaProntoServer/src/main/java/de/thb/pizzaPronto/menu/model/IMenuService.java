package de.thb.pizzaPronto.menu.model;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import de.thb.pizzaPronto.menu.data.DishVO;
import de.thb.pizzaPronto.menu.data.MenuVO;

import java.io.InputStream;
import java.io.OutputStream;

public interface IMenuService {
    public void importFromInputStream(InputStream fileInputStream);
    public DishVO add(DishVO dishVO);
    public void removeDishById(int dishId) throws IdNotFoundException;
    public MenuVO getMenu();
    public OutputStream exportAsOutputStream();
}
