package com.raon.android.raonapp.view.review;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raon.android.raonapp.R;
import com.raon.android.raonapp.databinding.FragmentReviewBinding;
import com.raon.android.raonapp.domain.ReviewAdopt;
import com.raon.android.raonapp.view.adapter.ReviewAdoptAdapter;

import java.util.List;
import java.util.Optional;

public class ReviewFragment extends Fragment {
    private final String TAG = ReviewFragment.class.getSimpleName();
    private FragmentReviewBinding binding;
    private ReviewAdoptAdapter adapter;
    private ReviewFragmentViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView");
        binding = FragmentReviewBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(ReviewFragmentViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ReviewAdoptAdapter(getReviewItemClickListener());
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerview.setAdapter(adapter);

        viewModel.getReviewAdoptList();
        viewModel.getReviewAdoptListML().observe(getViewLifecycleOwner(), reviewAdopts -> {
            Log.d(TAG, "get review adopt list observe");
            if(reviewAdopts != null){
                Log.d(TAG, "size : " + reviewAdopts.size());
                adapter.setReviewAdoptList(reviewAdopts);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public ReviewItemClickListener getReviewItemClickListener(){
        return reviewAdopt -> {
            Intent intent = new Intent(getContext(), ReviewCommentActivity.class);
            intent.putExtra("data", reviewAdopt);
            startActivity(intent);
        };
    }
}