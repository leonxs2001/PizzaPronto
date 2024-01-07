package de.thb.pizzaPronto.menu.model;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import de.thb.pizzaPronto.menu.data.DishVO;
import de.thb.pizzaPronto.menu.data.IDishDAO;
import de.thb.pizzaPronto.menu.data.MenuVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MenuService extends AbstractMenuObservable implements IMenuService {
    private final IDishDAO dishDAO;

    @Override
    public void importFromInputStream(InputStream fileInputStream) {
        // not implemented in prototype
        // the menu-observers would be notified with a new menu
    }

    @Override
    public DishVO add(DishVO dishVO) {
        DishVO createdDish = dishDAO.saveDish(dishVO);
        notifyMenuObserversMenu(getMenu());
        return createdDish;
    }

    @Override
    public void removeDishById(int dishId) throws IdNotFoundException {
        dishDAO.deleteDishByNumber(dishId);
    }

    @Override
    public MenuVO getMenu() {
        ArrayList<DishVO> dishes = (ArrayList<DishVO>) dishDAO.getAllDishes();
        return new MenuVO(dishes);
    }

    @Override
    public OutputStream exportAsOutputStream() {
        // not implemented in prototype
        return null;
    }
}
