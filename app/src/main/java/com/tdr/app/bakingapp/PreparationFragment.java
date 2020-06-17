package com.tdr.app.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tdr.app.bakingapp.model.Recipe;
import com.tdr.app.bakingapp.model.Step;

import java.util.List;

import static com.tdr.app.bakingapp.utils.Constants.EXTRA_RECIPE;

public class PreparationFragment extends Fragment {

    private static final String TAG = PreparationFragment.class.getSimpleName();

    private StepsAdapter mAdapter;
    private Recipe recipe;
    private List<Step> steps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_steps, container, false);

        Intent recipeIntent = getActivity().getIntent();
        recipe = recipeIntent.getParcelableExtra(EXTRA_RECIPE);
        if (recipe != null) {
            steps = recipe.getSteps();
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.steps_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter = new StepsAdapter();
        recyclerView.setAdapter(mAdapter);

        mAdapter.setRecipeSteps(steps);

        return rootView;
    }

}