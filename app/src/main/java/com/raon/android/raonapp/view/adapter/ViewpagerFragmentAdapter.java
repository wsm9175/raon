package com.raon.android.raonapp.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.raon.android.raonapp.view.findadopt.FindAdoptFragment;
import com.raon.android.raonapp.view.findtemporary.FindTemporaryFragment;
import com.raon.android.raonapp.view.home.HomeFragment;
import com.raon.android.raonapp.view.review.ReviewFragment;

import java.util.Arrays;
import java.util.List;

public class ViewpagerFragmentAdapter extends FragmentStateAdapter {
    List<Fragment> fragmentList = Arrays.asList(new HomeFragment(), new FindAdoptFragment(), new FindTemporaryFragment(), new ReviewFragment());

    public ViewpagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
