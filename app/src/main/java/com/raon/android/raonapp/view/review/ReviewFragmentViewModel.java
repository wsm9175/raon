package com.raon.android.raonapp.view.review;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.raon.android.raonapp.callback.GetReviewAdoptListener;
import com.raon.android.raonapp.domain.ReviewAdopt;
import com.raon.android.raonapp.repository.FirebaseRealTime;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragmentViewModel extends ViewModel {
    private FirebaseRealTime firebaseRealTime = FirebaseRealTime.getInstance();
    private MutableLiveData<List<ReviewAdopt>> reviewAdoptListML = new MutableLiveData<>();

    public void getReviewAdoptList(){
        firebaseRealTime.getReviewAdopt(getReviewAdoptListener());
    }

    public MutableLiveData<List<ReviewAdopt>> getReviewAdoptListML() {
        return reviewAdoptListML;
    }

    private GetReviewAdoptListener getReviewAdoptListener(){
        return new GetReviewAdoptListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                List<ReviewAdopt> reviewAdopts = new ArrayList<>();
                for(DataSnapshot child : snapshot.getChildren()){
                    reviewAdopts.add(child.getValue(ReviewAdopt.class));
                }
                ReviewFragmentViewModel.this.reviewAdoptListML.setValue(reviewAdopts);
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
            }
        };
    }
}
