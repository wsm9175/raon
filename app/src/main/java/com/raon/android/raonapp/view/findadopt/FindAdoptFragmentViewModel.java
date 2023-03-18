package com.raon.android.raonapp.view.findadopt;

import android.location.Location;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.raon.android.raonapp.callback.GetBoardAdoptListListener;
import com.raon.android.raonapp.domain.BoardAdopt;
import com.raon.android.raonapp.repository.FirebaseRealTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindAdoptFragmentViewModel extends ViewModel {
    private final String TAG = FindAdoptFragmentViewModel.class.getSimpleName();
    List<BoardAdopt> boardAdoptList = new ArrayList<>();
    private MutableLiveData<List<BoardAdopt>> boardAdoptListML = new MutableLiveData<>();
    private double lat;
    private double lon;

    public void getBoardAdoptList() {
        FirebaseRealTime.getInstance().getBoardAdoptListByMyLocation(getBoardAdoptListListener());
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
                    BoardAdopt boardAdopt = child.getValue(BoardAdopt.class);
                    boardAdoptList.add(boardAdopt);
                    Log.d(TAG, boardAdopt.toString());
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

                FindAdoptFragmentViewModel.this.boardAdoptListML.setValue(boardAdoptList);
            }

            @Override
            public void onFailed(Exception e) {
                Log.d(TAG, e.getMessage());
            }
        };
    }

    public MutableLiveData<List<BoardAdopt>> getBoardAdoptLiveData() {
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
