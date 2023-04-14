package com.raon.android.raonapp.view.findtemporary;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.raon.android.raonapp.databinding.FragmentFindTemporaryBinding;
import com.raon.android.raonapp.domain.BoardTemporaryProtect;
import com.raon.android.raonapp.geocoder.GeocoderModule;
import com.raon.android.raonapp.view.adapter.BoardTemporaryListAdapter;
import com.raon.android.raonapp.view.adapter.RecyclerViewDecoration;
import com.raon.android.raonapp.view.addressweb.WebViewActivity;

import java.util.ArrayList;

public class FindTemporaryFragment extends Fragment {
    private final String TAG = FindTemporaryFragment.class.getSimpleName();
    private FragmentFindTemporaryBinding binding;
    private FindTemporaryFragmentViewModel viewModel;
    private LocationManager locationManager;
    private BoardTemporaryListAdapter boardAdoptAdapter;
    private ActivityResultLauncher<Intent> getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_OK){
            Intent intent = result.getData();
            Bundle bundle = intent.getExtras();
            String address = bundle.getString("data");
            Log.d(TAG, address);

            Location location = GeocoderModule.getLocation(address);
            getBoardByLocation(location.getLatitude(), location.getLongitude());
        }
    });
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView");
        Log.d(TAG, "onCreateView");
        binding = FragmentFindTemporaryBinding.inflate(inflater);
        binding.setFragment(this);
        viewModel = new ViewModelProvider(this).get(FindTemporaryFragmentViewModel.class);
        boardAdoptAdapter = new BoardTemporaryListAdapter(getItemClickListener());

        binding.recyclerviewBoard.setAdapter(boardAdoptAdapter);
        binding.recyclerviewBoard.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.btnMyLocation.setOnClickListener(view -> {
            checkPermission();
            getMyLocation(getLocationListener());
        });

        binding.btnDesignate.setOnClickListener(view -> {

        });

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        viewModel.getBoardAdoptLiveData().observe(getViewLifecycleOwner(), boardAdopts -> {
            Log.d(TAG, "get board size : " + boardAdopts.size());
            binding.progressBar.setVisibility(View.INVISIBLE);
            boardAdoptAdapter.setBoardAdoptList(boardAdopts);
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(binding.linearBoard.getVisibility() == View.VISIBLE){
                    binding.linearBoard.setVisibility(View.INVISIBLE);
                    binding.constraintSelectMode.setVisibility(View.VISIBLE);
                    boardAdoptAdapter.setBoardAdoptList(new ArrayList<>());
                }
            }
        });
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                return;
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            }
        }
    }

    private void getMyLocation(GetLocationListener getLocationListener) {
        double lat = 0;
        double lon = 0;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "permission not granted");
            return;
        }
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(lastKnownLocation != null){
            lat = Math.abs(lastKnownLocation.getLatitude());
            lon = Math.abs(lastKnownLocation.getLongitude());
            Log.d(TAG, "lat : " + lat);
            Log.d(TAG, "lon : " + lon);
        }

        if(lat != 0 && lon != 0) getLocationListener.onSuccess(lat, lon);
        else getLocationListener.onFailed();
    }

    public void moveMap(){
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        getResult.launch(intent);
    }

    public GetLocationListener getLocationListener(){
        return new GetLocationListener() {
            @Override
            public void onSuccess(double lat, double lon) {
                getBoardByLocation(lat, lon);
            }

            @Override
            public void onFailed() {
                Toast.makeText(getContext(), "위치 정보를 가져오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public ItemClickListener getItemClickListener(){
        return boardTemporaryProtect -> {
            Intent intent = new Intent(getActivity(), FindTemporaryDetailActivity.class);
            intent.putExtra("data", boardTemporaryProtect);
            startActivity(intent);
        };
    }

    private void getBoardByLocation(double lat, double lon){
        binding.linearBoard.setVisibility(View.VISIBLE);
        binding.constraintSelectMode.setVisibility(View.INVISIBLE);
        viewModel.setLat(lat);
        viewModel.setLon(lon);
        viewModel.getBoardAdoptList();
    }
}

interface GetLocationListener{
    public void onSuccess(double lat, double lon);
    public void onFailed();
}

