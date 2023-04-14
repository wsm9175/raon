package com.raon.android.raonapp.view.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raon.android.raonapp.R;
import com.raon.android.raonapp.callback.BoardClickListener;
import com.raon.android.raonapp.databinding.FragmentHomeBinding;
import com.raon.android.raonapp.domain.BoardAdopt;
import com.raon.android.raonapp.view.adapter.BoardAdoptAdapter;
import com.raon.android.raonapp.view.adapter.RecyclerViewDecoration;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private BoardAdoptAdapter boardAdoptAdapter;
    private final String TAG = HomeFragment.class.getSimpleName();
    private HomeFragmentViewModel viewModel;
    private int page = 0;
    private int upSize = 5;
    private long totalDataCount;

    private Parcelable recyclerViewState;
    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        boardAdoptAdapter = new BoardAdoptAdapter(getBoardClickListener());
        binding.recyclerviewBoard.setAdapter(boardAdoptAdapter);
        binding.recyclerviewBoard.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerviewBoard.addItemDecoration(new RecyclerViewDecoration(15));

        recyclerViewState = binding.recyclerviewBoard.getLayoutManager().onSaveInstanceState();


        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.recyclerviewBoard.getLayoutManager();
        binding.recyclerviewBoard.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = linearLayoutManager.findLastVisibleItemPosition();
                Log.d(TAG, String.valueOf(lastItem == boardAdoptAdapter.getItemCount() - 1));
                Log.d("onScrolled", String.valueOf(lastItem));
                Log.d("onScrolled", String.valueOf(boardAdoptAdapter.getItemCount()-1));
                if(!isLoading && page < totalDataCount){
                    if (linearLayoutManager != null && lastItem == boardAdoptAdapter.getItemCount() - 1) {
                        binding.progressBar.setVisibility(View.VISIBLE);
                        isLoading = true;
                        page += upSize;
                        Log.d("onScrolled", "page : "+ page);
                        viewModel.getBoardAdoptList(page, upSize);
                    }
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getBoardCountLiveData().observe(getViewLifecycleOwner(), aLong -> {
            totalDataCount = aLong;
            viewModel.getBoardAdoptList(page, upSize);
        });
        viewModel.getBoardAdoptLiveData().observe(getViewLifecycleOwner(), boardAdopts -> {
            boardAdoptAdapter.setBoardAdoptList(boardAdopts);
            Objects.requireNonNull(binding.recyclerviewBoard.getLayoutManager()).onRestoreInstanceState(recyclerViewState);
            binding.progressBar.setVisibility(View.INVISIBLE);
            isLoading = false;
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        page = upSize;
        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel.getBoardCount();
    }

    public BoardClickListener getBoardClickListener(){
        return boardAdopt -> {
            Intent intent = new Intent(getContext(), BoardAdoptActivity.class);
            intent.putExtra("data", boardAdopt);
            startActivity(intent);
        };
    }
}