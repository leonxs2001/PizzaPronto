package de.thb.pizzaPronto.menu.viewmodel;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import de.thb.pizzaPronto.menu.data.*;
import de.thb.pizzaPronto.menu.model.IMenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@AllArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuViewModel {
    private final IMenuService menuService;

    // import and export not implemented in prototype

    @GetMapping
    public MenuVO getMenu() {
        return menuService.getMenu();
    }

    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable int id) throws IdNotFoundException {
        menuService.removeDishById(id);
    }

    @PostMapping
    public DishVO addDish(@RequestBody DishVO dish) {
        return menuService.add(dish);
    }

    @GetMapping("/test")
    public void test(){//TODO delete (is only for test cases
        System.out.print("Line");
        addDish(new PastaVO("Pasta spezial", 12f, 25f, new ArrayList<>(), 1));
        addDish(new PizzaVO("Pizza spezial", 12f, 25f, new ArrayList<>(), 1));
    }
}
