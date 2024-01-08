package de.thb.pizzaPronto.menu.rest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonTypeName("ingredientLeaf")
public class IngredientLeaf extends IngredientComponent {



	public IngredientLeaf(String name, float prozentAnteil) {
		super(name, prozentAnteil);
	}

	public IngredientLeaf(String name) {
		this(name, 0f);

	}

	public IngredientLeaf() {
		this(null, 0f);
	}

	public String toString() {
		String s = "";
		if (percentage > 0f) {
			s += String.format("%.2f%% ", percentage);
		}
		s += name;

		return s;
	}

}
