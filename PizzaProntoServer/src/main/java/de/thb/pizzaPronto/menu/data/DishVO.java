package de.thb.pizzaPronto.menu.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishVO {
    protected static int nextNumber = 1;

    protected int number;
    protected String name;
    protected float price;
    protected float timeToMake;           // time in minutes
    protected ArrayList<IngredientComponent> ingredients;

    public DishVO(String name, float price, float timeToMake, ArrayList<IngredientComponent> ingredients) {
        this(nextNumber++, name, price, timeToMake, ingredients);
    }
    public DishVO(int number){
        setNumber(number);
    }
    public void resetNumber(){
        setNumber(nextNumber++);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(number != 0 ? number + " - " : "");
        result.append(name != null ? name : "");
        result.append(price != 0.0 ? " for a price of " + price : "");
        result.append(timeToMake != 0.0 ? " takes " + timeToMake + " minutes to make" : "");

        if (ingredients != null && !ingredients.isEmpty()) {
            result.append(" with ingredients: ");
            for (IngredientComponent ingredient : ingredients) {
                result.append(ingredient.toString()).append(", ");
            }
            result.setLength(result.length() - 2);
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishVO that = (DishVO) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
