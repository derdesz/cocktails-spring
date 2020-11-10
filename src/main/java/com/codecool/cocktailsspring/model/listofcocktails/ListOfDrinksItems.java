package com.codecool.cocktailsspring.model.listofcocktails;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListOfDrinksItems {

	@JsonProperty("drinks")
	private List<DrinksItem> drinks;

	public List<DrinksItem> getDrinks(){
		return drinks;
	}
}