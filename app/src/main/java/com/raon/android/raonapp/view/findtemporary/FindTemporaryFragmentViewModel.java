package com.raon.android.raonapp.view.findtemporary;

import android.location.Location;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.raon.android.raonapp.callback.GetBoardAdoptListListener;
import com.raon.android.raonapp.domain.BoardAdopt;
import com.raon.android.raonapp.domain.BoardTemporaryProtect;
import com.raon.android.raonapp.repository.FirebaseRealTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindTemporaryFragmentViewModel extends ViewModel {
    private final String TAG = FindTemporaryFragmentViewModel.class.getSimpleName();
    List<BoardTemporaryProtect> boardAdoptList = new ArrayList<>();
    private MutableLiveData<List<BoardTemporaryProtect>> boardAdoptListML = new MutableLiveData<>();
    private double lat;
    private double lon;

    public void getBoardAdoptList() {
        FirebaseRealTime.getInstance().getBoardTemporaryProtectListByMyLocation(getBoardAdoptListListener());
    }

    public GetBoardAdoptListListener getBoardAdoptListListener() {
        return new GetBoardAdoptListListener() {
            @Override
            public void onSuccess(DataSnapshot doc) {
                Log.d(TAG, doc.getKey());
                Location locationMy = new Location("MyLocation");
                locationMy.setLatitude(lat);
                locationMy.setLongitude(lon);

                boardAdoptList = new ArrayList<>();

                for (DataSnapshot child : doc.getChildren()) {
                    BoardTemporaryProtect board = child.getValue(BoardTemporaryProtect.class);
                    boardAdoptList.add(board);
                    Log.d(TAG, board.toString());
                }
                //거리에 따른 게시판 필터링
                boardAdoptList = boardAdoptList.stream()
                        .filter(boardAdopt -> {
                            Location locationBoard = new Location("board");
                            locationBoard.setLatitude(boardAdopt.getLat());
                            locationBoard.setLongitude(boardAdopt.getLon());
                            float distance = locationMy.distanceTo(locationBoard);
                            Log.d(TAG, "distance : " + distance);
                            if (distance <= 50000) {
                                return true;
                            }
                            return false;
                        }).collect(Collectors.toList());

                FindTemporaryFragmentViewModel.this.boardAdoptListML.setValue(boardAdoptList);
            }

            @Override
            public void onFailed(Exception e) {
                Log.d(TAG, e.getMessage());
            }
        };
    }

    public MutableLiveData<List<BoardTemporaryProtect>> getBoardAdoptLiveData() {
        return this.boardAdoptListML;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
