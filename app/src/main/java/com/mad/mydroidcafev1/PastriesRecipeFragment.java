package com.mad.mydroidcafev1;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PastriesRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastriesRecipeFragment extends Fragment {



    private RecyclerView pastryRecyclerView;
    private ArrayList<Recipe> pastryRecipeData;
    private RecipeAdapter pastryAdapter;

    public PastriesRecipeFragment() {
        // Required empty public constructor
    }


    public static PastriesRecipeFragment newInstance(String param1, String param2) {
        PastriesRecipeFragment fragment = new PastriesRecipeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_pastries_recipe, container, false);

        //3. Initialize the recycler view
        pastryRecyclerView = rootView.findViewById(R.id.recycler_pastry);
        //4. Set a layout for the recycler view
        pastryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //5. Initialize the array list that will contain data
        pastryRecipeData = new ArrayList<>();
        //6. Initialize the recipe adapter
        pastryAdapter = new RecipeAdapter(pastryRecipeData, getContext());
        //7. Set the adapter
        pastryRecyclerView.setAdapter(pastryAdapter);
        //8. Get data
        initializeData();
//        9. Implement swiping and moving of card items
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull  RecyclerView.ViewHolder viewHolder, @NonNull  RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(pastryRecipeData, from, to);
                pastryAdapter.notifyItemMoved(from, to);

                return true;
            }
            @Override
            public void onSwiped(@NonNull  RecyclerView.ViewHolder viewHolder, int direction) {
                pastryRecipeData.remove(viewHolder.getAdapterPosition());
                pastryAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(pastryRecyclerView);

        return rootView;
    }

    private void initializeData() {
        //8.1 Get the data you created in the resource file strings.xml
        String[] pastryTitles = getResources().getStringArray(R.array.pastry_titles);
        String[] pastryDescription = getResources().getStringArray(R.array.pastry_description);
        TypedArray pastryImages = getResources().obtainTypedArray(R.array.pastry_images);

        //8.2 Clear existing data to avoid duplication
        pastryRecipeData.clear();

        //8.3  Create an array list of dessert recipes with title, description, images
        for(int i=0;i<pastryTitles.length;i++){
            pastryRecipeData.add(new Recipe(pastryImages.getResourceId(i,0),pastryTitles[i],pastryDescription[i]));

        }

        //8.4 Clean up the data in the typed array
        pastryImages.recycle();
        //8.5 Notify the adapter of the change in the data set
        pastryAdapter.notifyDataSetChanged();
    }

}
