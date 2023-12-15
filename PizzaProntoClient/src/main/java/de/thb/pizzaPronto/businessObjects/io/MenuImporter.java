package de.thb.pizzaPronto.businessObjects.io;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.thb.pizzaPronto.valueObjects.DessertVO;
import de.thb.pizzaPronto.valueObjects.DishVO;
import de.thb.pizzaPronto.valueObjects.MenuVO;
import de.thb.pizzaPronto.valueObjects.PastaVO;
import de.thb.pizzaPronto.valueObjects.PizzaVO;
import de.thb.pizzaPronto.valueObjects.IngredientComponent;
import de.thb.pizzaPronto.valueObjects.IngredientComposite;
import de.thb.pizzaPronto.valueObjects.IngredientLeaf;

public class MenuImporter {

	public MenuImporter() {
	}

	public MenuVO readMenu(String fileName) throws IOException, FileNotFoundException, JSONException {
		List<DishVO> dishes;
		MenuVO menu = null;
		DishVO currentDish = null;
		IngredientComponent currentIngredient = null;
		dishes = new LinkedList<DishVO>();
		
		
		
		File f = new File(fileName);
        FileReader fr = new FileReader(f);
        char [] iArray = new char[(int)f.length()+2];
        fr.read(iArray);        
		JSONObject jsonMenu = new JSONObject(new String(iArray));
		fr.close();
		JSONArray jsonDishes = jsonMenu.getJSONArray("dishes");
		for (int i = 0; i < jsonDishes.length(); i++) {
			JSONObject dish = jsonDishes.getJSONObject(i);
			String type =dish.getString("type");
			if(type.equals("Pizza")) {
				currentDish = new PizzaVO();
				((PizzaVO) currentDish).setSize(dish.getInt("size"));
			}
			if(type.equals("Pasta")) {
				currentDish = new PastaVO();
				((PastaVO) currentDish).setTypeOfPasta(dish.getInt("typeOfPasta"));
			
			}
			if(type.equals("Dessert")) {
				currentDish = new DessertVO();
			}
			currentDish.setNumber(dish.getInt("nr"));
			currentDish.setName(dish.getString("name"));
			currentDish.setPrice(dish.getFloat("price"));
			currentDish.setTimeToMake(dish.getFloat("timeToMake"));
			
			JSONArray jsonArrayIngredient = dish.getJSONArray("ingredients");
			
			List<IngredientComponent> incredientArrayList = new ArrayList<IngredientComponent>();
			for (int j = 0; j < jsonArrayIngredient.length(); j++) {
				
				JSONObject jsonCurrentIngredient = jsonArrayIngredient.getJSONObject(j);
				currentIngredient = readIngredients(jsonCurrentIngredient );
				incredientArrayList.add(currentIngredient);
			}
			if(incredientArrayList.size()>0)
				currentDish.setIngredients(( ArrayList<IngredientComponent>)incredientArrayList);
			dishes.add(currentDish);
			
		}
		
		menu = new MenuVO(new ArrayList<DishVO>(dishes));
		

		return menu;
	}
	private IngredientComponent readIngredients(JSONObject jsonCurrentIngredient) {
		IngredientComponent currentIngredient = null;
		String itype = jsonCurrentIngredient.getString("type");
		if(itype.equals("Leaf")){
			currentIngredient = new IngredientLeaf();
			currentIngredient.setName(jsonCurrentIngredient.getString("name"));
			currentIngredient.setPercentage(jsonCurrentIngredient.getFloat("percentage"));
		}
		if(itype.equals("Composite")){
			currentIngredient = new IngredientComposite();
			currentIngredient.setName(jsonCurrentIngredient.getString("name"));
			currentIngredient.setPercentage(jsonCurrentIngredient.getFloat("percentage"));
			JSONArray currentJsonIngredientsParts = jsonCurrentIngredient.getJSONArray("parts");
			for (int j = 0; j < currentJsonIngredientsParts.length(); ++j) {
				IngredientComponent currentIngredientPart;
				currentIngredientPart = readIngredients(currentJsonIngredientsParts.getJSONObject(j));
				((IngredientComposite) currentIngredient).add(currentIngredientPart);
			}

			
		}
		return currentIngredient;
	}
}
