package com.raon.android.raonapp.callback;

import com.google.firebase.database.DataSnapshot;
import com.raon.android.raonapp.domain.ReviewAdopt;

import java.util.List;

public interface GetReviewAdoptListener {
    public void onSuccess(DataSnapshot doc);
    public void onFailed(Exception e);
}
