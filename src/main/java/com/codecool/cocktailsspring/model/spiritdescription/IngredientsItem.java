package com.codecool.cocktailsspring.model.spiritdescription;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientsItem{

	@JsonProperty("strDescription")
	private String strDescription;

	@JsonProperty("strAlcohol")
	private String strAlcohol;

	@JsonProperty("strABV")
	private String strABV;

	@JsonProperty("strIngredient")
	private String strIngredient;

	@JsonProperty("strType")
	private String strType;

	@JsonProperty("idIngredient")
	private String idIngredient;

	public String getStrDescription(){
		return strDescription;
	}

	public String getStrAlcohol(){
		return strAlcohol;
	}

	public String getStrABV(){
		return strABV;
	}

	public String getStrIngredient(){
		return strIngredient;
	}

	public String getStrType(){
		return strType;
	}

	public String getIdIngredient(){
		return idIngredient;
	}
}