package com.raon.android.raonapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class BoardAdoptAdapter extends RecyclerView.Adapter<BoardAdoptAdapter.ViewHolder> {
    private final String TAG = MemberManageAdapter.class.getSimpleName();
    private ArrayList<> memberList;
    private NumberFormat nf = NumberFormat.getInstance();
    private MemberManageViewModel.MemberClickListener listener;
    public BoardAdoptAdapter(MemberManageViewModel.MemberClickListener addPointListener){
        this.listener = addPointListener;
    }

    public void setMemberList(ArrayList<Member> memberList) {
        this.memberList = memberList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtPhoneNumber;
        private ImageView imgProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtPhoneNumber = itemView.findViewById(R.id.txt_phone_number);
            imgProfile = itemView.findViewById(R.id.img_profile);
        }

        public void onBind(Member member) {
            String name = member.getName();
            String cellPhoneNumber = member.getCellPhoneNumber();
            //이미지는 추가 개발 예정

            txtName.setText(name);
            txtPhoneNumber.setText(cellPhoneNumber);

            itemView.setOnClickListener(view -> listener.onClick(member));
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member_manage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(this.memberList.get(position));
    }

    @Override
    public int getItemCount() {
        return memberList == null ? 0 : memberList.size();
    }

}
