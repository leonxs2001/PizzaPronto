package de.thb.pizzaPronto.menu.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PizzaVO extends DishVO{
    private int size;

    public PizzaVO(String name, float price, float timeToMake, ArrayList<IngredientComponent> ingredients, int size) {
        super(name, price, timeToMake, ingredients);
        this.size = size;
    }

    public String toString() {
        String result = super.toString();

        return result + (size != 0 ? " and is Pizza, Size: " + size : " and is Pizza");
    }
}
