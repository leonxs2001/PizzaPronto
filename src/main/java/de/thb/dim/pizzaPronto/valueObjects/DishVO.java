package de.thb.dim.pizzaPronto.valueObjects;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;


public abstract class DishVO implements  Comparable<DishVO>, Cloneable, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int number;
	protected String name;
	protected float price;
	protected float timeToMake;
	protected ArrayList<IngredientComponent>  ingredients;

	
	public DishVO(int number, String name, ArrayList<IngredientComponent>  ingredients, float price) {
		setNumber(number);
		setName(name);
		setPrice(price);
		setIngredients(ingredients);
		setTimeToMake(0.3f);
	}
	
	public DishVO(int number, String name, ArrayList<IngredientComponent>  ingredients, float price, float time) {
		setNumber(number);
		setName(name);
		setPrice(price);
		setIngredients(ingredients);
		setTimeToMake(time);
	}
	
	public DishVO(int number, String name, float price) {
		this(number, name, null, price);
	}
	
	public DishVO(int number, String name, float price, float time) {
		this(number, name, null, price, time);
	}
	
	public DishVO() {
		this(0, null, null, 0.00f);
	}
	
	@Override
	public int compareTo(DishVO d){
		return getNameOfDish().compareTo(d.getNameOfDish());
	}

	/**
	 * Method creates a copy of the object 
	 * implementation corresponding to Java-API see interface Cloneable
	 */
	public Object clone()  {
		
		DishVO dish = null;
		try {
			dish = (DishVO) super.clone();
		} catch (CloneNotSupportedException e) {
		       // Should not happen because of Cloneable      
			throw new InternalError();
		}
		return dish;
	}

	
	public String toString() {
		DecimalFormat dFormat = new DecimalFormat(".00"); // Formatierung der
															// Preisangabe ...
		StringBuffer sb = new StringBuffer(); // R�ckgabewert ...

		sb.append(getNumberOfDish() + " - ");
		sb.append(getNameOfDish() + "\t");

		sb.append(ingredientsToString());

		sb.append("\n\tPrice:\t\t\t" + dFormat.format(getPrice()) + " Euro");
		sb.append("\n");

		return sb.toString();
	}

	
	public String ingredientsToString() {
		StringBuilder sb = new StringBuilder();
		
		if (getIngredients() != null) { // Wenn Zutaten Array nicht
			sb.append("\n\tZutaten: ");
			
			for (IngredientComponent currentIngredient : getIngredients()) { // Fuer jede
				// Zutat ...
				sb.append(currentIngredient.toString() + ", "); // aktuelle Zutat und Komma an
				// Rueckgabestring anhaengen ...
			}
			sb = new StringBuilder(sb.substring(0, sb.length() - 2)); // Komma am Ende entfernen ...
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + Float.floatToIntBits(price);
		if(ingredients != null)
			result = prime * result + ingredients.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DishVO other = (DishVO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!Objects.deepEquals(ingredients, ingredients))
			return false;
		return true;
	}

	// /
	// / Setter und Getter
	// /
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<IngredientComponent>  getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<IngredientComponent>  zutaten) {
		this.ingredients = zutaten;
	}
	

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = (price > 0f) ? price : 0f;
	}
	
	public void setTimeToMake(float ttm) {
		this.timeToMake = ttm;
	}
	
	public float getTimeToMake() {
		return this.timeToMake;
	}

	/**
	 * Abstract method of returning the name of the dish as in menu. Must be overridden by the derived classes.
	 * 
	 * @return - name of the dish
	 */
	public abstract String getNameOfDish();

	/**
	  * Abstract method of returning the number of the dish as in menu. Must be overridden by the derived classes.
	 * 
	 * @return - number of the dish
	 */
	public abstract int getNumberOfDish();

} 
