package de.thb.pizzaPronto.menu.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PizzaVO.class),
        @JsonSubTypes.Type(value = PastaVO.class),
        @JsonSubTypes.Type(value = PastaVO.class),
})
public abstract class DishVO {
    protected static int nextNumber = 1;

    protected int number;
    protected String name;
    protected float price;
    protected float timeToMake;           // time in minutes
    protected ArrayList<IngredientComponent> ingredients;

    public DishVO(String name, float price, float timeToMake, List<IngredientComponent> ingredients) {
        this.setNumber(nextNumber++);
        this.setName(name);
        this.setPrice(price);
        this.setTimeToMake(timeToMake);
        this.setIngredients((ArrayList<IngredientComponent>) ingredients);
    }


    public DishVO(int number){
        setNumber(number);
    }
    public void resetNumber(){
        setNumber(nextNumber++);
    }

    public String toString() {
        DecimalFormat dFormat = new DecimalFormat(".00");
        StringBuilder result = new StringBuilder();
        result.append(number != 0 ? number + " - " : "");
        result.append(name != null ? name : "");
        result.append(price != 0.0 ? " for a price of " + dFormat.format(price) : "");
        result.append(timeToMake != 0.0 ? " takes " + dFormat.format(timeToMake) + " minutes to make" : "");

        if (ingredients != null && !ingredients.isEmpty()) {
            result.append(" with ingredients: ");
            for (IngredientComponent ingredient : ingredients) {
                result.append(ingredient.toString()).append(", ");
            }
            result.setLength(result.length() - 2);
        }
        return result.toString();
    }

    public String toStringForMenu(){
        DecimalFormat dFormat = new DecimalFormat(".00");
        return (number != 0 ? number + " - " : "") +
                (name != null ? name : "") +
                (price != 0.0 ? "\t€ " + dFormat.format(price) : "");
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
