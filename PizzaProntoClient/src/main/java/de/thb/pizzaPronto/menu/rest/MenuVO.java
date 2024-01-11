package de.thb.pizzaPronto.menu.rest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public List<DishVO> getDishes(){
        return dishes;
    }

    public void setDishes(List<DishVO> dishes){
        this.dishes = (ArrayList<DishVO>) dishes;
    }

    public DishVO getDish(int i){
        return dishes.get(i);
    }

    public int getNumberOfDishes(){
        if(dishes == null){
            return 0;
        }
        return dishes.size();
    }



} 

