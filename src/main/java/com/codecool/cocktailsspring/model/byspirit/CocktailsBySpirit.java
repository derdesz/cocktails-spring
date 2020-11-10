package com.codecool.cocktailsspring.model.byspirit;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CocktailsBySpirit {

	@JsonProperty("drinks")
	private List<DrinksItem> drinks;

	public List<DrinksItem> getDrinks(){
		return drinks;
	}
}