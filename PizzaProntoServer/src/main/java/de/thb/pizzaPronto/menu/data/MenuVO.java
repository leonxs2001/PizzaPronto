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
public class MenuVO {
    private static int nextId = 1;

    private int id;
    private ArrayList<DishVO> dishes;

    public MenuVO(ArrayList<DishVO> dishes) {
        this(nextId++, dishes);
    }
    public MenuVO(int id){
        setId(id);
    }
    public void resetId(){
        setId(nextId++);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Menu:");
        if (dishes != null && !dishes.isEmpty()) {
            for (DishVO dish : dishes) {
                result.append("\t-").append(dish.toString()).append(" \n");
            }
            result.setLength(result.length() - 2);
        }
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