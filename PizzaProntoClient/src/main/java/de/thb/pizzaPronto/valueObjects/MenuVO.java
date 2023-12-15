package de.thb.pizzaPronto.valueObjects;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MenuVO {
	private List<DishVO> dishes;
	
	public MenuVO(ArrayList<DishVO> dishes) {
		this.dishes = dishes;
	}

	/**
	 * Defaultkonstruktor
	 * 
	 */
	public MenuVO() {
		initMenu();
	}

	/**
	 * Method to initialize the menu and create all objects of dishes.
	 * 
	 */
	private void initMenu() {
		this.dishes = new ArrayList<DishVO>();
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
		StringBuffer sb = new StringBuffer();
		DecimalFormat dFormat = new DecimalFormat(".00"); //Format the price ...

		sb.append("MENU PIZZA PRONTO\n\n");
		// Pizzas
		sb.append("Prima special pizzas: \n   1 normal (Diameter approx. 26 cm) and \n   2 grande (Diameter approx. 32 cm)\n");
		int i = 0;
		do {
			sb.append(dishes.get(i).getNumber() + "\t");
			sb.append(dishes.get(i).getName() + "\t");
			sb.append(dishes.get(i).ingredientsToString());
			sb.append("\n\tPrice:\t\t\t"
					+ dFormat.format(dishes.get(i).getPrice()) + " Euro");
			if(dishes.get(i).getNumber() == dishes.get(i+1).getNumber()){
				sb.append("\n\tPrice:\t\t\t"
						+ dFormat.format(dishes.get(i+1).getPrice()) + " Euro");
				sb.append("\n");
				i+=2;
			}
			else i++;
		} while (i < dishes.size() && dishes.get(i) instanceof PizzaVO );
		
		//Pasta 
		
		sb.append("\nPrima special pastas: \n4  Spaghetti\n5  Tortellini\n6  Gnocchi\n");
		do {
			sb.append(" " + dishes.get(i).getNumber() + "\t");
			sb.append(dishes.get(i).getName() + "\t");

			sb.append(dishes.get(i).ingredientsToString());

			sb.append("\n\tPrice:\t\t\t"
					+ dFormat.format(dishes.get(i).getPrice()) + " Euro");
			if(dishes.get(i).getNumber() == dishes.get(i+1).getNumber() && dishes.get(i).getNumber() == dishes.get(i+2).getNumber()){
				i+=3;
			} else {
			if(dishes.get(i).getNumber() == dishes.get(i+1).getNumber()){
				i+=2;
			}
			else
				i++;
			}
			sb.append("\n");
		} while (i < dishes.size() && dishes.get(i) instanceof PastaVO);
		
		sb.append("\nPrima desserts\n");
		do {
			sb.append(dishes.get(i).getNumber() + "\t");
			sb.append(dishes.get(i).getName() + "\t");

			sb.append(dishes.get(i).ingredientsToString());

			sb.append("\n\tPrice:\t\t\t"
					+ dFormat.format(dishes.get(i).getPrice()) + " Euro");
			sb.append("\n");
			i++;
		} while (i < dishes.size() && dishes.get(i) instanceof DessertVO);

		return sb.toString();
	}

	
	
	// /
	// / Getter und Setter
	// /
	public DishVO getDish(int index) {
			return dishes.get(index);
	}

	public int getNumberOfDishes() {
		return dishes.size();
	}

} 

