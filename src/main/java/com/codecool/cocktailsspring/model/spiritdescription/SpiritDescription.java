package com.codecool.cocktailsspring.model.spiritdescription;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpiritDescription{

	@JsonProperty("ingredients")
	private List<IngredientsItem> ingredients;

	public List<IngredientsItem> getIngredients(){
		return ingredients;
	}
}