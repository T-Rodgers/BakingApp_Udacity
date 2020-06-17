package com.tdr.app.bakingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdr.app.bakingapp.model.Step;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private List<Step> mStepsList;

    private final StepOnClickHandler mClickHandler;

    public interface StepOnClickHandler {
        void onClick(Step stepData);
    }


    public StepsAdapter(StepOnClickHandler handler) {
        mClickHandler = handler;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_list_item, parent, false);

        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        Step step = mStepsList.get(position);

        holder.shortDescriptionTextView.setText(step.getShortDescription());

    }

    @Override
    public int getItemCount() {
        if (mStepsList != null) {
            return mStepsList.size();
        }
        return 0;
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView shortDescriptionTextView;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);

            shortDescriptionTextView = itemView.findViewById(R.id.short_description_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Step step = mStepsList.get(position);
            mClickHandler.onClick(step);
        }
    }

    public void setRecipeSteps(List<Step> steps) {
        mStepsList = steps;
        notifyDataSetChanged();
    }
}
