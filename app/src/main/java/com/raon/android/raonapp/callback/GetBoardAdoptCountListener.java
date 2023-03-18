package com.raon.android.raonapp.callback;

public interface GetBoardAdoptCountListener {
    public void onSuccess(long count);
    public void onFailed(Exception e);
}
