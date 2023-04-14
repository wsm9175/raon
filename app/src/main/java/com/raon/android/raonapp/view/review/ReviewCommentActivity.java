package com.raon.android.raonapp.view.review;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.raon.android.raonapp.R;
import com.raon.android.raonapp.databinding.ActivityReviewCommentBinding;
import com.raon.android.raonapp.domain.Comment;
import com.raon.android.raonapp.domain.ReviewAdopt;
import com.raon.android.raonapp.view.adapter.CommentListAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewCommentActivity extends AppCompatActivity {
    private ActivityReviewCommentBinding binding;
    private CommentListAdapter commentListAdapter;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setActivity(this);
        Intent intent = getIntent();
        ReviewAdopt reviewAdopt = intent.getSerializableExtra("data", ReviewAdopt.class);

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        commentListAdapter = new CommentListAdapter();
        binding.recyclerview.setAdapter(commentListAdapter);

        settingCommentList(reviewAdopt);
    }

    private void settingCommentList(ReviewAdopt reviewAdopt){
        List<Comment> comments = new ArrayList<>();
        for(String key : reviewAdopt.getComments().keySet()){
            Comment comment = reviewAdopt.getComments().get(key);
            comments.add(comment);
        }
        comments.sort((comment, t1) -> (int) (comment.getCreateAt() - t1.getCreateAt()));

        List<Comment> commentList = comments.stream().filter(comment -> comment.isRoot()).collect(Collectors.toList());
        List<Comment> replyList = comments.stream().filter(comment -> !comment.isRoot()).collect(Collectors.toList());
        commentListAdapter.setCommentList(commentList, replyList);
    }

    public void close(){
        finish();
    }
}