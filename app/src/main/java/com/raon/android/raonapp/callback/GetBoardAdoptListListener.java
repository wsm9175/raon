package com.raon.android.raonapp.callback;

import com.google.firebase.database.DataSnapshot;

public interface GetBoardAdoptListListener {
    public void onSuccess(DataSnapshot doc);
    public void onFailed(Exception e);
}
