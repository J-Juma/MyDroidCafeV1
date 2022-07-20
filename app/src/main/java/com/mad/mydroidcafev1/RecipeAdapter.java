package com.mad.mydroidcafev1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Step 1
//Create a Recipe adapter that extends RecyclerView.Adapter and use the RecyclerView.ViewHolder class
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    // Step 3 Declare the private member variables Recipe Data and the context
    private ArrayList<Recipe> recipeData;
    private Context myContext;

//    Step 3.1 Create constructor to pass in the recipe data and the context

    RecipeAdapter(ArrayList<Recipe> mRecipeData, Context context){
        //initialize the objects
        this.myContext = context;
        this.recipeData = mRecipeData;
    }

//    Step 1.1
//    Implement the method onCreateViewHolder for creating viewHolder objects
//    @param parent the view group of which the view object will be added after it is bound to the adapter position
//    @param viewType of the new view
//    @return the newly created viewHolder
    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        // Step 4.1 Create a new view holder
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.recipe_list_item, parent, false));

    }

    /*

    * Step 1.1 Required for binding the view to the data
    * @holder viewHolder which the data should be put
    * @position the adapter position
    *
    * */

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
//  Step 5 Get the current view object using its position and populate it with data
        Recipe currentRecipe = recipeData.get(position);
//  Step 5.1 Populate the current View with data
        holder.bindTo(currentRecipe);
    }

    /*
    * Step 1.1 Required for getting the size of the data set
    * @return the size of the dataset
    * */

    @Override
    public int getItemCount() {
        // Step 4.0
        return recipeData.size();
    }

    /* Step 2 Create the ViewHolder class that represents each and every row in the recycler view
    *
    * */

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* Step 2.1 Create a constructor for the ViewHolder used in onCreateViewHolder() method
        @param itemView rootView of the recipe_list_item layout
        * */

//        Step 2.2 Declare the private member variables that the viewHolder will hold
        private ImageView myRecipeImage;
        private TextView myRecipeTitle;
        private TextView myRecipeDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myRecipeImage = itemView.findViewById(R.id.recipe_image);
            myRecipeTitle = itemView.findViewById(R.id.recipe_title);
            myRecipeDescription = itemView.findViewById(R.id.recipe_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int dessertPosition = getAdapterPosition();
                    Recipe currentDessert = recipeData.get(dessertPosition);
                    if(dessertPosition == 0){
                        Intent donutIntent = new Intent(myContext, DonutActivity.class);
                        donutIntent.putExtra("dTitle", currentDessert.getRecipeTitle());
                        donutIntent.putExtra("dImage", currentDessert.getRecipeImage());
                        donutIntent.putExtra("dDescription", currentDessert.getRecipeDescription());
                        myContext.startActivity(donutIntent);
                    }else{
                        Toast.makeText(myContext, "Create an activity for the dessert", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

//      Step 6  create a method to bind the current view with data (Image, title and description)
        public void bindTo(Recipe currentRecipe) {
//            Step 6.1 populate the view with the data
//            For loading the image, use the Glide Library so as to prevent problems of the app crashing
//            as a result of loading images of different resolutions
//            Implement the Glide library first in your gradle module(App) level

            Glide.with(myContext).load(currentRecipe.getRecipeImage()).into(myRecipeImage);
            myRecipeTitle.setText(currentRecipe.getRecipeTitle());
            myRecipeDescription.setText(currentRecipe.getRecipeDescription());
        }
    }
}
