package de.thb.pizzaPronto.menu.rest;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuVO {
	private static int nextId = 1;

	private int id;
	private List<Object> dishes;

	public MenuVO(int id, ArrayList<DishVO> dishes) {
		this.id = id;
		List<Object> temp_dishes = new ArrayList<Object>();
		for (DishVO dish : dishes){
			Object o = (Object) dish;
			temp_dishes.add(o);
		}
		this.dishes = temp_dishes;

	}
	public MenuVO(ArrayList<DishVO> dishes) {
		this(nextId++, dishes);
	}

	/**
	 * Defaultkonstruktor
	 * 
	 */
	public MenuVO(int id) {
		this.id = id;
	}
	public MenuVO() {
		this(nextId++);
		initMenu();
	}

	/**
	 * Method to initialize the menu and create all objects of dishes.
	 * 
	 */

	public void resetId(){
		setId(nextId++);
	}

	private void initMenu() {
		this.dishes = new ArrayList<Object>();
		ArrayList<IngredientComponent> zutatenMargherita = new ArrayList<IngredientComponent>();
		ArrayList<IngredientComponent> zutatenBolognese1 = new ArrayList<IngredientComponent>();
		ArrayList<IngredientComponent> zutatenBolognese2 = new ArrayList<IngredientComponent>();
		ArrayList<IngredientComponent> zutatenObstsalat = new ArrayList<IngredientComponent>();


		IngredientLeaf il;
		IngredientComposite ic;
		
		ic = new IngredientComposite("Belag");
		il = new IngredientLeaf("Kaese");
		ic.add(il);
		il = new IngredientLeaf("Basillikum");
		ic.add(il);
		il = new IngredientLeaf("Knoblauch");
		ic.add(il);
		il = new IngredientLeaf("Oel");
		ic.add(il);
		zutatenMargherita.add(ic);
		
		il = new IngredientLeaf("Tomatensauce",10f);
		zutatenMargherita.add(il);
		
		ic = new IngredientComposite("Teig");
		il = new IngredientLeaf("Mehl");
		ic.add(il);
		il = new IngredientLeaf("Hefe");
		ic.add(il);
		il = new IngredientLeaf("Salz",0.01f);
		ic.add(il);
		il = new IngredientLeaf("Oel");
		ic.add(il);
		zutatenMargherita.add(ic);
		
		
		ic = new IngredientComposite("Sauce");
		il = new IngredientLeaf("Rindfleisch");
		ic.add(il);
		il = new IngredientLeaf("Tomaten");
		ic.add(il);
		il = new IngredientLeaf("Knoblauch");
		ic.add(il);
		il = new IngredientLeaf("Oel");
		ic.add(il);
		zutatenBolognese1.add(ic);
		
		ic = new IngredientComposite("Teig");
		il = new IngredientLeaf("Mehl");
		ic.add(il);
		il = new IngredientLeaf("Salz",0.01f);
		ic.add(il);
		il = new IngredientLeaf("Eier");
		ic.add(il);
		zutatenBolognese1.add(ic);
		
		
		
		ic = new IngredientComposite("Sauce");
		il = new IngredientLeaf("Rindfleisch");
		ic.add(il);
		il = new IngredientLeaf("Tomaten");
		ic.add(il);
		il = new IngredientLeaf("Knoblauch");
		ic.add(il);
		il = new IngredientLeaf("Oel");
		ic.add(il);
		zutatenBolognese2.add(ic);
		
		ic = new IngredientComposite("Teig");
		il = new IngredientLeaf("Kartoffel", 60f);
		ic.add(il);
		il = new IngredientLeaf("Salz",0.01f);
		ic.add(il);
		il = new IngredientLeaf("Eier");
		ic.add(il);
		zutatenBolognese2.add(ic);	

		il = new IngredientLeaf("Zucker",20f);
		zutatenObstsalat.add(il);
		il = new IngredientLeaf("Banane",20f);
		zutatenObstsalat.add(il);
		il = new IngredientLeaf("Ananas",20f);
		zutatenObstsalat.add(il);
		il = new IngredientLeaf("Apfel",20);
		zutatenObstsalat.add(il);
		il = new IngredientLeaf("Mango",20);
		zutatenObstsalat.add(il);
		
		dishes.add( new PizzaVO(30, "Margherita", zutatenMargherita, 10.00f, 1));
		dishes.add( new PizzaVO(30, "Margherita",zutatenMargherita, 12.90f, 2));
		dishes.add( new PastaVO(11, "Bolognese",zutatenBolognese1,9.60f,4));
		dishes.add( new PastaVO(11, "Bolognese",zutatenBolognese1, 9.60f,5));
		dishes.add( new PastaVO(11, "Bolognese",zutatenBolognese2, 9.60f, 6));
		dishes.add( new DessertVO(21, "Hausgemachter Obstsalat",zutatenObstsalat, 8.30f));
	}

	/**
	 * Returns the object in human-readable form. Calls for getter of the individual courts. 
	 * Is based on the initialization sequence: pizza, pasta, dessert
	 * @return - complete String
	 * 
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Menu:");
		if (dishes != null && !dishes.isEmpty()) {
			for (Object o : dishes) {
				try {
					PizzaVO dish = (PizzaVO) o;
					result.append("\t-").append(dish.toString()).append(" \n");
				}
				catch(ClassCastException e1) {
					try {
						PastaVO dish = (PastaVO) o;
						result.append("\t-").append(dish.toString()).append(" \n");
					}
					catch(ClassCastException e2) {
						try {
							DessertVO dish = (DessertVO) o;
							result.append("\t-").append(dish.toString()).append(" \n");
						}
						catch(ClassCastException e3) {
							DishVO dish = (DishVO) o;
							result.append("\t-").append(dish.toString()).append(" \n");
						}
					}
				}
			}
			result.setLength(result.length() - 2);
		}
		return result.toString();
	}

	
	
	// /
	// / Getter und Setter
	// /
	public DishVO getDish(int index) {
			return (DishVO) dishes.get(index);
	}

	public int getNumberOfDishes() {
		return dishes.size();
	}

} 

