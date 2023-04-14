package com.raon.android.raonapp.view.home;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.raon.android.raonapp.databinding.ActivityBoardAdoptBinding;
import com.raon.android.raonapp.domain.BoardAdopt;
import com.raon.android.raonapp.domain.Comment;
import com.raon.android.raonapp.view.adapter.CommentListAdapter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardAdoptActivity extends AppCompatActivity {
    private ActivityBoardAdoptBinding binding;
    private CommentListAdapter commentListAdapter;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardAdoptBinding.inflate(getLayoutInflater());
        binding.setActivity(this);
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        BoardAdopt selectBoardAdopt = intent.getSerializableExtra("data", BoardAdopt.class);

        commentListAdapter = new CommentListAdapter();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerview.setAdapter(commentListAdapter);

        settingBoard(selectBoardAdopt);
    }

    private void settingBoard(BoardAdopt boardAdopt) {
        Glide.with(binding.getRoot()).load(boardAdopt.getImagePath()).into(binding.imgAnimal);
        binding.txtVariety.setText(boardAdopt.getVariety());
        binding.txtSex.setText(boardAdopt.getSex());
        LocalDate date =
                Instant.ofEpochMilli(boardAdopt.getCreateAt()).atZone(ZoneId.systemDefault()).toLocalDate();
        binding.txtCreateAt.setText(date.toString());
        binding.txtLocation.setText(boardAdopt.getLocation());
        binding.txtRescueLocation.setText(boardAdopt.getRescueSite());

        List<Comment> comments = new ArrayList<>();
        for(String key : boardAdopt.getComments().keySet()){
            comments.add(boardAdopt.getComments().get(key));
        }

        List<Comment> commentList = comments.stream().filter(Comment::isRoot).collect(Collectors.toList());
        List<Comment> replyList = comments.stream().filter(comment -> !comment.isRoot()).collect(Collectors.toList());

        commentListAdapter.setCommentList(commentList, replyList);

    }
}