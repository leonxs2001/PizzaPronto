package de.thb.dim.pizzaPronto.valueObjects;

import java.io.Serializable;

public abstract class IngredientComponent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected float percentage;
	
	public IngredientComponent(String name, float precentage) {
		super();
		this.name = name;
		this.percentage = precentage;
	}
	
	public IngredientComponent(String name) {
		this(name, 0f);
		
	}
	
	public IngredientComponent() {
		this(null, 0f);
	}

	public abstract String toString();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	

}
