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
@JsonTypeName("pasta")
public class PastaVO extends DishVO{
    private int typeOfPasta;

    public PastaVO(String name, float price, float timeToMake, ArrayList<IngredientComponent> ingredients, int typeOfPasta) {
        super(name, price, timeToMake, ingredients);
        this.typeOfPasta = typeOfPasta;
    }

    public String toString() {
        String result = super.toString();

        return result + (typeOfPasta != 0 ? " and is Pasta, Type: " + typeOfPasta : " and is Pasta");
    }
    @Override
    public String toStringForMenu(){
        DecimalFormat dFormat = new DecimalFormat(".00");
        StringBuilder result = new StringBuilder();
        result.append(number != 0 ? number + " - " : "");
        result.append(name != null ? name : "");
        result.append(typeOfPasta != 0 ? " (Type: " + typeOfPasta + ") " : "");
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
