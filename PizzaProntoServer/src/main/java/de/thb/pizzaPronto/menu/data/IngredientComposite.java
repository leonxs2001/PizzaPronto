package de.thb.pizzaPronto.menu.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class IngredientComposite extends IngredientComponent {

    private List<IngredientComponent> parts;

    public IngredientComposite(String name, float percentage) {
        super(name, percentage);
        parts = new ArrayList<>();
    }

    public IngredientComposite(String name) {
        this(name, 0f);

    }
    public  String toString() {
        StringBuilder sb = new StringBuilder();
        if (percentage > 0f) {
            sb.append(String.format("%.2f%% ", percentage));
        }
        sb.append(name);
        sb.append(" (");
        for(IngredientComponent p : parts) {
            sb.append(p).append(", ");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 2));
        sb.append(")");
        return sb.toString();
    }



    public  void add(IngredientComponent ic) {
        parts.add(ic);
    }
    public  void remove(IngredientComponent ic) {
        parts.remove(ic);
    }
    public  IngredientComponent getPart(int index) {
        return parts.get(index);
    }
}
