package com.raon.android.raonapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raon.android.raonapp.R;
import com.raon.android.raonapp.domain.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReplyListAdapter extends RecyclerView.Adapter<ReplyListAdapter.ViewHolder> {
    private final String TAG = ReplyListAdapter.class.getSimpleName();
    private List<Comment> commentList;


    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView writerName, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            writerName = itemView.findViewById(R.id.writerName);
            content = itemView.findViewById(R.id.content);
        }

        public void onBind(Comment comment) {
            writerName.setText(comment.getWriterName());
            content.setText(comment.getContent());
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_2, parent, false);
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



    public void clearList(){
        commentList = new ArrayList<>();
    }

}
