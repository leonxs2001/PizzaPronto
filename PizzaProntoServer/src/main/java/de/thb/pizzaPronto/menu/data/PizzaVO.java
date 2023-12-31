package de.thb.pizzaPronto.menu.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonTypeName("pizza")
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
    @Override
    public String toStringForMenu(){
        DecimalFormat dFormat = new DecimalFormat(".00");
        StringBuilder result = new StringBuilder();
        result.append(number != 0 ? number + " - " : "");
        result.append(name != null ? name : "");
        result.append(size != 0 ? " (Size: " + size + ") " : "");
        result.append(price != 0.0 ? "\t€ " + dFormat.format(price) : "");

        if (ingredients != null && !ingredients.isEmpty()) {
            result.append("\nIngredients: ");
            for (IngredientComponent ingredient : ingredients) {
                result.append(ingredient.toString()).append(", ");
            }
            result.setLength(result.length() - 2);
        }
        return result.toString();
    }
}
