package com.tdr.app.bakingapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.tdr.app.bakingapp.R;
import com.tdr.app.bakingapp.model.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tdr.app.bakingapp.utils.Constants.EXTRA_STEP;

public class FullDescriptionFragment extends Fragment {

    private static final String TAG = FullDescriptionFragment.class.getSimpleName();

    private Step step;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private String videoUrl;
    private String fullDescription;

    @BindView(R.id.full_description_text_view)
    TextView fullDescriptionTextView;

    @BindView(R.id.emptyView)
    TextView emptyView;
    public FullDescriptionFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_full_step_description, container, false);

        ButterKnife.bind(this, rootView);

        mPlayerView = rootView.findViewById(R.id.playerView);

        Intent stepData = getActivity().getIntent();
        if (stepData != null) {
            step = stepData.getParcelableExtra(EXTRA_STEP);
        }

        if (step != null) {
            videoUrl = step.getVideoURL();
            fullDescription = step.getDescription();
        }
        fullDescriptionTextView.setText(fullDescription);

        if (TextUtils.isEmpty(videoUrl)) {
            emptyView.setVisibility(View.VISIBLE);
            mPlayerView.setVisibility(View.GONE);
        } else {
        initializePlayer(Uri.parse(videoUrl));
        }

        return rootView;
    }


    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(getContext(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);

        }
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}
