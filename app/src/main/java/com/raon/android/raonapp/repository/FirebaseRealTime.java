package com.raon.android.raonapp.repository;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.raon.android.raonapp.callback.GetBoardAdoptCountListener;
import com.raon.android.raonapp.callback.GetBoardAdoptListListener;
import com.raon.android.raonapp.callback.GetReviewAdoptListener;

public class FirebaseRealTime {
    private static FirebaseRealTime instance;
    private final String BOARD_ADOPT_PATH = "board_adopt";
    private final String BOARD_TEMPORARY_PROTECT_PATH = "board_temporary_protect";
    private final String REVIEW_ADOPT = "review_adopt";
    private FirebaseDatabase database;
    private FirebaseRealTime() {
        database = FirebaseDatabase.getInstance();
    }

    public static FirebaseRealTime getInstance() {
        if (instance == null) instance = new FirebaseRealTime();
        return instance;
    }

    public void getBoardAdoptListCount(GetBoardAdoptCountListener getBoardAdoptCountListener){
        DatabaseReference myRef = database.getReference();
        myRef.child(BOARD_ADOPT_PATH).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                getBoardAdoptCountListener.onSuccess(task.getResult().getChildrenCount());
            }else{
                getBoardAdoptCountListener.onFailed(task.getException());
            }
        });
    }

    public void getBoardAdoptList(GetBoardAdoptListListener getBoardAdoptListListener, int page, int upsize){
        DatabaseReference myRef = database.getReference();
        myRef.child(BOARD_ADOPT_PATH).orderByChild("createAt").limitToFirst(page).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                getBoardAdoptListListener.onSuccess(task.getResult());
            }else{
                Log.e("firebase", "Error getting data", task.getException());
                getBoardAdoptListListener.onFailed(task.getException());
            }
        });
    }

    public void getBoardAdoptListByMyLocation(GetBoardAdoptListListener getBoardAdoptListListener){
        DatabaseReference myRef = database.getReference();
        myRef.child(BOARD_ADOPT_PATH).orderByChild("createAt").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                getBoardAdoptListListener.onSuccess(task.getResult());
            }else{
                Log.e("firebase", "Error getting data", task.getException());
                getBoardAdoptListListener.onFailed(task.getException());
            }
        });
    }

    public void getBoardTemporaryProtectListByMyLocation(GetBoardAdoptListListener getBoardAdoptListListener){
        DatabaseReference myRef = database.getReference();
        myRef.child(BOARD_TEMPORARY_PROTECT_PATH).orderByChild("createAt").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                getBoardAdoptListListener.onSuccess(task.getResult());
            }else{
                Log.e("firebase", "Error getting data", task.getException());
                getBoardAdoptListListener.onFailed(task.getException());
            }
        });
    }

    public void getReviewAdopt(GetReviewAdoptListener reviewAdoptListener){
        DatabaseReference mRef = database.getReference();
        mRef.child(REVIEW_ADOPT).orderByChild("createAt").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                reviewAdoptListener.onSuccess(task.getResult());
            }else{
                Log.e("firebase", "Error getting data", task.getException());
            }
        });
    }
}

