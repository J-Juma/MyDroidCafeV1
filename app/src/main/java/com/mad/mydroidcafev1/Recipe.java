package com.mad.mydroidcafev1;

public class Recipe {
    //Declare private member variable
    private final int recipeImage;
    private String recipeTitle;
    private String recipeDescription;
    //create a constructor for recipe data model
//    pass the parameters recipeImage, recipeTitle, recipeDescription

    Recipe(int recipeImage,String recipeTitle,String recipeDescription){
        this.recipeImage = recipeImage;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
    }

//    Create the getters and return the specific object

    public int getRecipeImage(){
        return recipeImage;
    }
    public String getRecipeTitle(){
        return recipeTitle;
    }
    public String getRecipeDescription(){
        return recipeDescription;
    }
}
