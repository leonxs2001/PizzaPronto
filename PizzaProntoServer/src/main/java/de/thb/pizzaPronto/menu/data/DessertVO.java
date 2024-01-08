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

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonTypeName("dessert")
public class DessertVO extends DishVO {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DessertVO(String name, List<IngredientComponent> ingredients, float price) {
        this(name, ingredients, price, 0);
    }


    public DessertVO(String name, List<IngredientComponent> ingredients, float price, float time) {
        super(name, price, time, ingredients);
    }

    public DessertVO(String name, float price) {
        this(name, Collections.emptyList(), price, 0);
    }

    public DessertVO(int number, String name, float price, float time) {
        this(name, Collections.emptyList(), price, time);
    }

    /**
     * Defaultkonstruktor
     * Ruft den Initialisierungskonstruktor auf, um Standardwerte f�r Instanzattribute zu setzen.
     */
    public DessertVO() {
        this("", Collections.emptyList(), 0.00f);
    }

}
