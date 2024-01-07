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
}
