package com.raon.android.raonapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.raon.android.raonapp.MainApplication;
import com.raon.android.raonapp.R;
import com.raon.android.raonapp.domain.Comment;
import com.raon.android.raonapp.domain.ReviewAdopt;
import com.raon.android.raonapp.view.review.ReviewItemClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {
    private final String TAG = CommentListAdapter.class.getSimpleName();
    private List<Comment> commentList;
    private List<Comment> replyList;
    private ReplyListAdapter adapter;


    public void setCommentList(List<Comment> commentList, List<Comment> replyList) {
        this.commentList = commentList;
        this.replyList = replyList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView writerName, content, addComment;
        private RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            writerName = itemView.findViewById(R.id.writerName);
            content = itemView.findViewById(R.id.content);
            addComment = itemView.findViewById(R.id.addComment);
            recyclerView = itemView.findViewById(R.id.recyclerview);
        }

        public void onBind(Comment comment) {
            writerName.setText(comment.getWriterName());
            content.setText(comment.getContent());

            addComment.setOnClickListener(view -> {

            });
            List<Comment> reply = CommentListAdapter.this.replyList.stream()
                    .filter(comment1 -> comment1.getCommentGroup().equals(comment.getCommentGroup()))
                    .collect(Collectors.toList());

            if(Optional.ofNullable(reply).orElseGet(ArrayList::new).size()==0){
                recyclerView.setVisibility(View.INVISIBLE);
            }else{
                adapter = new ReplyListAdapter();
                reply.sort((comment12, t1) -> (int) (comment.getCreateAt() - t1.getCreateAt()));
                adapter.setCommentList(reply);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainApplication.getInstance().getApplicationContext()));
                recyclerView.setAdapter(adapter);
            }
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
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
