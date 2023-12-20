package de.thb.pizzaPronto.menu.rest;

import java.util.ArrayList;
import java.util.List;

public class IngredientComposite extends IngredientComponent {
	
	
	private List<IngredientComponent> parts;
	
	public IngredientComposite(String name, float prozentAnteil) {
		super(name,prozentAnteil );
		parts = new ArrayList<IngredientComponent>();
	}
	
	public IngredientComposite(String name) {
		this(name, 0f);
		
	}
	
	public IngredientComposite() {
		this(null, 0f);
	}

	public  String toString() {
		StringBuilder sb = new StringBuilder();
		if (percentage > 0f) {
			sb.append(String.format("%.2f%% ", percentage));
		}
		sb.append(name);
		sb.append(" (");
		for(IngredientComponent p : parts) {
			sb.append(p + ", ");
		}
		sb = new StringBuilder(sb.substring(0, sb.length() - 2)); // Komma am Ende entfernen ...
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
	public List<IngredientComponent> getParts() {
		return parts;
	}

	public void setParts(List<IngredientComponent> parts) {
		this.parts = parts;
	}
}
