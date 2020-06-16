package com.tdr.app.bakingapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdr.app.bakingapp.model.Step;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private List<Step> mStepsList;

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_list_item, parent, false);

        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {

        Step step = mStepsList.get(position);
        holder.descriptionTextView.setText(step.getId());

        Log.d("BindView", "onBindViewHolder: " + step.getShortDescription());

    }



    public class StepsViewHolder extends RecyclerView.ViewHolder {

        private TextView descriptionTextView;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);

            descriptionTextView = itemView.findViewById(R.id.description_text_view);
        }

    }


    @Override
    public int getItemCount() {
        if (mStepsList != null) {
            mStepsList.size();
        }
        return 0;
    }


    public void setRecipeSteps(List<Step> stepsList) {
        mStepsList = stepsList;
        notifyDataSetChanged();
    }
}
