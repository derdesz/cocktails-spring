package com.codecool.cocktailsspring.model.byspirit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrinksItem{

	@JsonProperty("strDrink")
	private String strDrink;

	@JsonProperty("strDrinkThumb")
	private String strDrinkThumb;

	@JsonProperty("idDrink")
	private String idDrink;

	public String getStrDrink(){
		return strDrink;
	}

	public String getStrDrinkThumb(){
		return strDrinkThumb;
	}

	public String getIdDrink(){
		return idDrink;
	}
}