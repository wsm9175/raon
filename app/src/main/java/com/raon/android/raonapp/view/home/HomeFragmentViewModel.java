package com.raon.android.raonapp.view.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.raon.android.raonapp.callback.GetBoardAdoptCountListener;
import com.raon.android.raonapp.callback.GetBoardAdoptListListener;
import com.raon.android.raonapp.domain.BoardAdopt;
import com.raon.android.raonapp.repository.FirebaseRealTime;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentViewModel extends ViewModel {
    private final String TAG = HomeFragmentViewModel.class.getSimpleName();
    List<BoardAdopt> boardAdoptList = new ArrayList<>();
    private MutableLiveData<Long> boardCount = new MutableLiveData<>();
    private MutableLiveData<List<BoardAdopt>> boardAdoptListML = new MutableLiveData<>();

    public void getBoardCount(){
        FirebaseRealTime.getInstance().getBoardAdoptListCount(getBoardAdoptCountListener());
    }

    public void getBoardAdoptList(int page, int upsize){
        FirebaseRealTime.getInstance().getBoardAdoptList(getBoardAdoptListListener(), page, upsize);
    }

    public GetBoardAdoptCountListener getBoardAdoptCountListener(){
        return new GetBoardAdoptCountListener() {
            @Override
            public void onSuccess(long count) {
                boardCount.setValue(count);
            }

            @Override
            public void onFailed(Exception e) {
                Log.d(TAG, e.getMessage());
            }
        };
    }

    public GetBoardAdoptListListener getBoardAdoptListListener(){
        return new GetBoardAdoptListListener() {
            @Override
            public void onSuccess(DataSnapshot doc) {
                Log.d(TAG, doc.getKey());
                boardAdoptList = new ArrayList<>();
                for(DataSnapshot child : doc.getChildren()){
                    BoardAdopt boardAdopt = child.getValue(BoardAdopt.class);
                    boardAdoptList.add(boardAdopt);
                    Log.d(TAG, boardAdopt.toString());
                }
                HomeFragmentViewModel.this.boardAdoptListML.setValue(boardAdoptList);
            }
            @Override
            public void onFailed(Exception e) {
                Log.d(TAG, e.getMessage());
            }
        };
    }

    public MutableLiveData<Long> getBoardCountLiveData(){
        return this.boardCount;
    }
    public MutableLiveData<List<BoardAdopt>> getBoardAdoptLiveData(){
        return this.boardAdoptListML;
    }
}

