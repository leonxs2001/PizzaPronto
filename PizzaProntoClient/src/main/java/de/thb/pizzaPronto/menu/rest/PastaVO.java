package de.thb.pizzaPronto.menu.rest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Robert Fischer, Gabriele Schmidt
 * @version 0.1
 * @since 27.05.2013
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonTypeName("pasta")
public class PastaVO extends DishVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int typeOfPasta;
	
	
	public PastaVO(int number, String name, ArrayList<IngredientComponent> ingredients, float price, int pastaType) {
		super(number, name, ingredients, price);
		setTypeOfPasta(pastaType);
	}
	
	public PastaVO(int number, String name, float price, int pastaType) {
		super(number, name,null, price,0.1f);
		setTypeOfPasta(pastaType);
	}
	
	public PastaVO(int number, String name, ArrayList<IngredientComponent> ingredients, float price, float time, int pastaType) {
		super(number, name, ingredients, price, time);
		setTypeOfPasta(pastaType);
	}
	
	/**
	 * Defaultkonstruktor 
	 * Ruft den Initialisierungskonstruktor auf, um Standardwerte f�r Instanzattribute zu setzen.
	 * 
	 */
	public PastaVO() {
		this(0, null, null, 0.00f, 0);
	}

	public String toStringForMenu(){
		DecimalFormat dFormat = new DecimalFormat(".00");
		StringBuilder result = new StringBuilder();
		result.append(number != 0 ? number + " - " : "");
		result.append(name != null ? name : "");
		result.append(typeOfPasta != 0 ? " (Type: " + typeOfPasta + ") " : "");
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
	
	
	///
	/// Getter und Setter
	///
	public int getTypeOfPasta() {
		return typeOfPasta;
	}

	public void setTypeOfPasta(int pastaType) {
		this.typeOfPasta = pastaType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + typeOfPasta;
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
		PastaVO other = (PastaVO) obj;
		if (typeOfPasta != other.typeOfPasta)
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "PastaVO [" + super.toString() + "\t typeOfPasta =" + typeOfPasta  + "]\n";
//	}
	
	@Override
	public String getNameOfDish() {
		StringBuffer sb = new StringBuffer();
		sb.append("Pasta ");
		
		switch(typeOfPasta) {
			case 4 : 
				sb.append(getName() + " - Spaghetti");
				break;
			case 5 : 
				sb.append(getName() + " - Tortellini");
				break;
			case 6 : 
				sb.append(getName() + " - Gnocchi");
				break;
			default : 
				sb.append(getName());
				break;
		}
		
		return sb.toString();
	}

	@Override
	public int getNumberOfDish() {
		return this.typeOfPasta * 100 + this.number;
	}
		
	
	
} 
