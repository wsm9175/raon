package com.raon.android.raonapp.view.findtemporary;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.raon.android.raonapp.R;
import com.raon.android.raonapp.databinding.ActivityFindTemporaryDetailBinding;
import com.raon.android.raonapp.domain.BoardTemporaryProtect;

public class FindTemporaryDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private final String TAG = FindTemporaryDetailActivity.class.getSimpleName();
    private ActivityFindTemporaryDetailBinding binding;

    private GoogleMap mMap;

    private BoardTemporaryProtect protect;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindTemporaryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        protect = intent.getSerializableExtra("data", BoardTemporaryProtect.class);
        Log.d(TAG, protect.getLat() + " " + protect.getLon());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.back.setOnClickListener(view -> finish());
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(protect.getLat(), protect.getLon());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(protect.getName());
        markerOptions.snippet("위치");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }
}