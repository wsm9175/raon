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

import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class BoardAdoptAdapter extends RecyclerView.Adapter<BoardAdoptAdapter.ViewHolder> {
    private final String TAG = BoardAdoptAdapter.class.getSimpleName();
    private List<BoardAdopt> boardAdoptList;

    public BoardAdoptAdapter(){}

    public void setBoardAdoptList(List<BoardAdopt> boardAdoptList) {
        this.boardAdoptList = boardAdoptList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtVariety, txtSex, txtCreateAt, txtLocation, txtRescueLocation;
        private ImageView imgAnimal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVariety = itemView.findViewById(R.id.txt_variety);
            txtSex = itemView.findViewById(R.id.txt_sex);
            txtCreateAt = itemView.findViewById(R.id.txt_create_at);
            txtLocation = itemView.findViewById(R.id.txt_location);
            txtRescueLocation = itemView.findViewById(R.id.txt_rescue_location);
            imgAnimal = itemView.findViewById(R.id.img_animal);
        }

        public void onBind(BoardAdopt boardAdopt) {
            txtVariety.setText(boardAdopt.getVariety());
            txtSex.setText(boardAdopt.getSex());
            LocalDate date =
                    Instant.ofEpochMilli(boardAdopt.getCreateAt()).atZone(ZoneId.systemDefault()).toLocalDate();
            txtCreateAt.setText(date.toString());
            txtLocation.setText(boardAdopt.getLocation());
            txtRescueLocation.setText(boardAdopt.getRescueSite());
            Glide.with(itemView).load(boardAdopt.getImagePath()).into(imgAnimal);
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
        holder.onBind(this.boardAdoptList.get(position));
    }

    @Override
    public int getItemCount() {
        return boardAdoptList == null ? 0 : boardAdoptList.size();
    }

    public void clearList(){
        boardAdoptList = new ArrayList<>();
    }

}
