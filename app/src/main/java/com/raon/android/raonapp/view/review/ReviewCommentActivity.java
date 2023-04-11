package com.raon.android.raonapp.view.review;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.raon.android.raonapp.R;
import com.raon.android.raonapp.databinding.ActivityReviewCommentBinding;
import com.raon.android.raonapp.domain.ReviewAdopt;

public class ReviewCommentActivity extends AppCompatActivity {
    private ActivityReviewCommentBinding binding;
    private ReviewAdopt reviewAdopt;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setActivity(this);
        Intent intent = getIntent();
        reviewAdopt = intent.getSerializableExtra("data", ReviewAdopt.class);

    }

    public void close(){
        finish();
    }
}