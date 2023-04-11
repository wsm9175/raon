package com.raon.android.raonapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.raon.android.raonapp.R;
import com.raon.android.raonapp.domain.BoardAdopt;
import com.raon.android.raonapp.domain.Comment;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReviewAdoptCommentAdapter extends RecyclerView.Adapter<ReviewAdoptCommentAdapter.ViewHolder> {
    private final String TAG = ReviewAdoptCommentAdapter.class.getSimpleName();
    private List<Comment> commentList;

    public ReviewAdoptCommentAdapter(){}

    public void setCommentMap(List<Comment> comments) {
        this.commentList = comments;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtVariety, txtSex, txtCreateAt, txtLocation, txtRescueLocation;
        private ImageView imgAnimal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void onBind(Comment comment) {

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board_adopt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(this.commentList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentList == null ? 0 : commentList.size();
    }

}
