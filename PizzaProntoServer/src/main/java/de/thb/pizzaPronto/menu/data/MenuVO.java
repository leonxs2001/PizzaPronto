package de.thb.pizzaPronto.menu.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonTypeName("menu")
public class MenuVO {
    private static int nextId = 1;

    private int id;
    private ArrayList<DishVO> dishes;

    public MenuVO(ArrayList<DishVO> dishes) {
        this(nextId++, dishes);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("PizzaPronto Spezial Menu:\n");
        if (dishes != null && !dishes.isEmpty()) {
            for (DishVO dish : dishes) {
                result.append(dish.toStringForMenu()).append(" \n\n");
            }
            result.setLength(result.length() - 2);
        }
        result.append("\n");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuVO that = (MenuVO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}