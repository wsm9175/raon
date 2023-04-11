package com.raon.android.raonapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raon.android.raonapp.R;
import com.raon.android.raonapp.domain.BoardTemporaryProtect;
import com.raon.android.raonapp.view.findtemporary.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class BoardTemporaryListAdapter extends RecyclerView.Adapter<BoardTemporaryListAdapter.ViewHolder> {
    private final String TAG = BoardTemporaryListAdapter.class.getSimpleName();
    private List<BoardTemporaryProtect> boardAdoptList;
    private ItemClickListener itemClickListener;
    public BoardTemporaryListAdapter(){}

    public void setBoardAdoptList(List<BoardTemporaryProtect> boardAdoptList) {
        this.boardAdoptList = boardAdoptList;
        notifyDataSetChanged();
    }

    public BoardTemporaryListAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name);
            txtLocation = itemView.findViewById(R.id.location);
        }

        public void onBind(BoardTemporaryProtect boardAdopt) {
            txtName.setText(boardAdopt.getName());
            txtLocation.setText(boardAdopt.getLocation());
            itemView.setOnClickListener(view -> itemClickListener.onClick(boardAdopt));
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board_temp_protect, parent, false);
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
