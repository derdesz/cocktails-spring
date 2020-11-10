package com.codecool.cocktailsspring.model.detailedcocktail;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailedCocktail{

	@JsonProperty("drinks")
	private List<DrinksItem> drinks;

	public List<DrinksItem> getDrinks(){
		return drinks;
	}
}