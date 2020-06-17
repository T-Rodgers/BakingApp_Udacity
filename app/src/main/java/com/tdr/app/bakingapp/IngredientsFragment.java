package com.tdr.app.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tdr.app.bakingapp.model.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tdr.app.bakingapp.utils.Constants.EXTRA_RECIPE;

public class IngredientsFragment extends Fragment {

    private static final String TAG = IngredientsFragment.class.getSimpleName();

    private IngredientsAdapter mAdapter;
    private Recipe recipe;
    @BindView(R.id.servings_text_view)
    TextView servingsTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, rootView);

        Intent recipeIntent = getActivity().getIntent();
        if (recipeIntent != null) {
            recipe = recipeIntent.getParcelableExtra(EXTRA_RECIPE);
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.ingredients_recycler_view);
        mAdapter = new IngredientsAdapter();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mAdapter);


        loadRecipeDataIntoViews();


        return rootView;
    }


    public void loadRecipeDataIntoViews() {
        String servings = String.valueOf(recipe.getServings());
        servingsTextView.setText(servings);
        mAdapter.setIngredients(recipe.getIngredients());

        Log.d(TAG, "loadRecipeDataIntoViews: " + mAdapter.getItemCount());

    }

}