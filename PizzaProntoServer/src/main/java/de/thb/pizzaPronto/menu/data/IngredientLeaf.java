package de.thb.pizzaPronto.menu.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class IngredientLeaf extends IngredientComponent{

    public String toString() {
        String s = "";
        if (percentage > 0f) {
            s += String.format("%.2f%% ", percentage);
        }
        s += name;

        return s;
    }
}
