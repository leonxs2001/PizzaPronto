package de.thb.pizzaPronto.menu.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonTypeName("dessert")
public class DessertVO extends DishVO {

    private static final long serialVersionUID = 1L;

    public DessertVO(String name, float price, float timeToMake, ArrayList<IngredientComponent> ingredients) {
        super(name, price, timeToMake, ingredients);
    }
}
