package com.tdr.app.bakingapp.fragments;

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

import com.tdr.app.bakingapp.PreparationInstructionsActivity;
import com.tdr.app.bakingapp.R;
import com.tdr.app.bakingapp.StepsAdapter;
import com.tdr.app.bakingapp.model.Recipe;
import com.tdr.app.bakingapp.model.Step;

import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;

import static com.tdr.app.bakingapp.utils.Constants.EXTRA_RECIPE;
import static com.tdr.app.bakingapp.utils.Constants.EXTRA_STEP;

public class PreparationFragment extends Fragment
implements StepsAdapter.StepOnClickHandler {

    private static final String TAG = PreparationFragment.class.getSimpleName();

    private StepsAdapter mAdapter;
    private List<Step> steps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_preparation, container, false);
        ButterKnife.bind(this, rootView);

        Intent recipeIntent = Objects.requireNonNull(getActivity()).getIntent();
        Recipe recipe = recipeIntent.getParcelableExtra(EXTRA_RECIPE);
        if (recipe != null) {
            steps = recipe.getSteps();
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.steps_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter = new StepsAdapter(this);
        recyclerView.setAdapter(mAdapter);

        loadData();

        return rootView;
    }

    public void loadData() {
        mAdapter.setRecipeSteps(steps);
    }

    @Override
    public void onClick(Step stepData) {
        Intent fullDescriptionIntent = new Intent(getContext(), PreparationInstructionsActivity.class);
        fullDescriptionIntent.putExtra(EXTRA_STEP, stepData);
        startActivity(fullDescriptionIntent);
    }

}