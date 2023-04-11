package com.raon.android.raonapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.raon.android.raonapp.MainApplication;
import com.raon.android.raonapp.R;
import com.raon.android.raonapp.domain.BoardAdopt;
import com.raon.android.raonapp.domain.ReviewAdopt;
import com.raon.android.raonapp.view.review.ReviewItemClickListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ReviewAdoptAdapter extends RecyclerView.Adapter<ReviewAdoptAdapter.ViewHolder> {
    private final String TAG = ReviewAdoptAdapter.class.getSimpleName();
    private List<ReviewAdopt> reviewAdoptList;
    private ReviewItemClickListener reviewItemClickListener;
    public ReviewAdoptAdapter(ReviewItemClickListener reviewItemClickListener){
        this.reviewItemClickListener = reviewItemClickListener;
    }

    public void setReviewAdoptList(List<ReviewAdopt> reviewAdoptList) {
        this.reviewAdoptList = reviewAdoptList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtWriter, txtContent, txtComment;
        private ViewPager2 viewPager2;
        private LinearLayout layoutIndicators;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWriter = itemView.findViewById(R.id.writer);
            viewPager2 = itemView.findViewById(R.id.viewPager);
            txtContent = itemView.findViewById(R.id.content);
            layoutIndicators = itemView.findViewById(R.id.layoutIndicators);
            txtComment = itemView.findViewById(R.id.txt_comment);
            viewPager2.setOffscreenPageLimit(1);
        }

        public void onBind(ReviewAdopt reviewAdopt) {
            txtWriter.setText(reviewAdopt.getWriterName());
            viewPager2.setAdapter(new ImageSliderAdapter(MainApplication.getInstance().getApplicationContext(), reviewAdopt.getImagePath()));
            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    setCurrentIndicator(position, layoutIndicators);
                }
            });
            txtContent.setText(reviewAdopt.getText());

            setupIndicators(reviewAdopt.getImagePath().size(), layoutIndicators);

            if(reviewAdopt.getComments() != null){
                if(reviewAdopt.getComments().size() >= 1){
                    txtComment.setText("댓글 보러 가기");
                    txtComment.setOnClickListener(view -> reviewItemClickListener.onClick(reviewAdopt));
                }
            }
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(this.reviewAdoptList.get(position));

    }

    @Override
    public int getItemCount() {
        return reviewAdoptList == null ? 0 : reviewAdoptList.size();
    }

    private void setupIndicators(int count, LinearLayout layoutIndicator) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(MainApplication.getInstance().getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(MainApplication.getInstance().getApplicationContext(),
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0, layoutIndicator);
    }

    private void setCurrentIndicator(int position, LinearLayout layoutIndicator) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        MainApplication.getInstance().getApplicationContext(),
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        MainApplication.getInstance().getApplicationContext(),
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }

    public void clearList(){
        reviewAdoptList = new ArrayList<>();
    }

}
