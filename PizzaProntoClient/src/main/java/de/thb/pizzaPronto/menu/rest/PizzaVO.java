package de.thb.pizzaPronto.menu.rest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The methods of the class PizzaVO are tested.
 * 
 * Special assert statements are used for testing <br>
 * 
 * @author Gabriele Schmidt
 * @version 4.0 16.03.2020
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonTypeName("pizza")
public class PizzaVO extends DishVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size;
	
	/**
	 * initializing constructor
	 * Initialize all instance attributes with values. 
	 * 
	 * @param name        - Name of the pizza
	 * @param ingredients - ingredients of the pizza
	 * @param price       - price of the pizza
	 * 
	 */
	public PizzaVO(int number, String name, ArrayList<IngredientComponent> ingredients, float price, int size) {
		super(number, name, ingredients, price);
		setSize(size);
	}
	
	public PizzaVO(int number, String name, ArrayList<IngredientComponent> ingredients, float price, float time, int size) {
		super(number, name, ingredients, price,time);
		setSize(size);
	}
	
	public PizzaVO(int number, String name, float price, int size) {
		super(number, name, null, price,0.1f);
		setSize(size);
	}

	
	public PizzaVO(int number, String name, float price, float time, int size) {
		super(number, name, null, price,time);
		setSize(size);
	}

	public String toStringForMenu(){
		DecimalFormat dFormat = new DecimalFormat(".00");
		StringBuilder result = new StringBuilder();
		result.append(number != 0 ? number + " - " : "");
		result.append(name != null ? name : "");
		result.append(size != 0 ? " (Size: " + size + ") " : "");
		result.append(price != 0.0 ? "\t€ " + dFormat.format(price) : "");

		if (ingredients != null && !ingredients.isEmpty()) {
			result.append("\nIngredients: ");
			for (IngredientComponent ingredient : ingredients) {
				result.append(ingredient.toString()).append(", ");
			}
			result.setLength(result.length() - 2);
		}
		return result.toString();
	}

	/**
	 * default constructor calls initializing constructor with default values for
	 * instance attributes
	 * 
	 */
	public PizzaVO() {
		this(0,null, null, 0.0f,1);
	}

	// Standard methods of Java

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PizzaVO other = (PizzaVO) obj;
		if (size != other.size)
			return false;
		return true;
	}


	@Override
	public String getNameOfDish() {
		StringBuffer sb = new StringBuffer();
		sb.append("Pizza ");

		if (size == 1) sb.append(getName() + " - Normal");
		else sb.append(getName() + " - Grande");

		return sb.toString();
	}

	@Override
	public int getNumberOfDish() {
		return this.number * 10 + size;
	}
		
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}


} 
