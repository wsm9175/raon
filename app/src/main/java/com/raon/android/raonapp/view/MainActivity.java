package com.raon.android.raonapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.raon.android.raonapp.R;
import com.raon.android.raonapp.databinding.ActivityMainBinding;
import com.raon.android.raonapp.view.adapter.ViewpagerFragmentAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private final List<String> TABELEMENT = Arrays.asList("홈", "분양 찾기", "임시 보호 찾기", "입양 후기");
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewpagerFragmentAdapter viewpagerFragmentAdapter = new ViewpagerFragmentAdapter(this);
        binding.pager.setAdapter(viewpagerFragmentAdapter);

        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {

        }).attach();
    }

}