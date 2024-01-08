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
        ArrayList<IngredientComponent> zustatenPizza = new ArrayList<>();
        ArrayList<IngredientComponent> zutatenPasta = new ArrayList<>();

        IngredientLeaf il;
        IngredientComposite ic;

        ic = new IngredientComposite("Teig");
        il = new IngredientLeaf("Mehl");
        ic.add(il);
        il = new IngredientLeaf("Salz",0.01f);
        ic.add(il);
        il = new IngredientLeaf("Hefe");
        ic.add(il);
        il = new IngredientLeaf("Wasser");
        ic.add(il);
        zustatenPizza.add(ic);

        ic = new IngredientComposite("Belag Pizza Spezial");
        il = new IngredientLeaf("Pizzasoße");
        ic.add(il);
        il = new IngredientLeaf("Salami");
        ic.add(il);
        il = new IngredientLeaf("Peparoni");
        ic.add(il);
        il = new IngredientLeaf("Champignons");
        ic.add(il);
        zustatenPizza.add(ic);

        ic = new IngredientComposite("Teig");
        il = new IngredientLeaf("Mehl");
        ic.add(il);
        il = new IngredientLeaf("Salz", 0.01f);
        ic.add(il);
        il = new IngredientLeaf("Eier");
        ic.add(il);
        zutatenPasta.add(ic);
        ic = new IngredientComposite("Soße Pasta Spezial");
        il = new IngredientLeaf("Sahne");
        ic.add(il);
        il = new IngredientLeaf("Spinat", 0.1f);
        ic.add(il);
        il = new IngredientLeaf("Käse");
        ic.add(il);
        zutatenPasta.add(ic);


        addDish(new PizzaVO("Pizza spezial", 12f, 25f, zustatenPizza, 1));
        addDish(new PastaVO("Pasta spezial", 12f, 25f, zutatenPasta, 1));
    }
}
