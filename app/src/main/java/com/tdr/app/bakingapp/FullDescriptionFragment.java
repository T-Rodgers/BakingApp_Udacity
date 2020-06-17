package com.tdr.app.bakingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tdr.app.bakingapp.model.Step;

import java.util.List;

public class FullDescriptionFragment extends Fragment {

    public static final String STEP_ID_LIST = "step_index";
    public static final String STEP_INDEX = "step_index";

    private List<Step> steps;
    private int mStepIndex;

    public FullDescriptionFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {

        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
