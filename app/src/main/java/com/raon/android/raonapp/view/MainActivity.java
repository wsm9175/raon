package com.raon.android.raonapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.raon.android.raonapp.R;
import com.raon.android.raonapp.databinding.ActivityMainBinding;
import com.raon.android.raonapp.domain.LoginUser;
import com.raon.android.raonapp.view.adapter.ViewpagerFragmentAdapter;

import java.util.Arrays;
import java.util.List;

/*
 * set fragment
 * connect fragment and Tablayout
 * set firebase google login
 * */
public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private final List<String> TAB_ELEMENT = Arrays.asList("홈", "입양 찾기", "임시 보호\n 찾기", "입양 후기");
    private ActivityMainBinding binding;
    private ImageView imgMenu;
    private TextView txtLogin, txtNameDrawer;

    private SignInClient oneTapClient;
    private BeginSignInRequest signUpRequest;
    private static final int REQ_ONE_TAP = 2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // Setting Fragment
        ViewpagerFragmentAdapter viewpagerFragmentAdapter = new ViewpagerFragmentAdapter(this);
        binding.pager.setAdapter(viewpagerFragmentAdapter);

        // Setting Tab layout
        new TabLayoutMediator(binding.tabLayout, binding.pager, (tab, position) -> {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(TAB_ELEMENT.get(position));
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setTextColor(Color.parseColor("#000000"));
            tab.setCustomView(textView);
        }).attach();

        imgMenu = findViewById(R.id.img_menu);
        imgMenu.setOnClickListener(view -> {
            binding.drawer.openDrawer(GravityCompat.START);
        });

        txtLogin = binding.naviationView.getHeaderView(0).findViewById(R.id.txt_login);
        txtNameDrawer = binding.naviationView.getHeaderView(0).findViewById(R.id.txt_name_drawer);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Login
        oneTapClient = Identity.getSignInClient(this);
        signUpRequest = BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId(getString(R.string.default_web_client_id))
                // Show all accounts on the device.
                .setFilterByAuthorizedAccounts(false).build()).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            txtLogin.setText("로그아웃");
            loginSuccess();
            txtLogin.setOnClickListener(view -> logout());
        } else {
            txtLogin.setOnClickListener(getTxtLoginClickListener());
        }
    }

    private void loginSuccess() {
        FirebaseUser user = mAuth.getCurrentUser();
        LoginUser loginUser = LoginUser.getInstance();
        loginUser.setId(user.getUid());
        loginUser.setEmail(user.getEmail());
        Log.d(TAG, "login info :" + loginUser);
        txtNameDrawer.setText(loginUser.getEmail());

        txtLogin.setText("로그아웃");
        txtLogin.setOnClickListener(view -> logout());
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        LoginUser.getInstance().clear();
        txtNameDrawer.setText("");
        Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();

        txtLogin.setText("로그인");
        txtLogin.setOnClickListener(getTxtLoginClickListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    String username = credential.getId();
                    String password = credential.getPassword();
                    if (idToken != null) {
                        // Got an ID token from Google. Use it to authenticate
                        // with Firebase.
                        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                        mAuth.signInWithCredential(firebaseCredential).addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential:success");
                                //updateUI
                                loginSuccess();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                //updateUI
                                Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (ApiException e) {
                    // ...
                }
                break;
        }
    }

    private View.OnClickListener getTxtLoginClickListener() {
        return view -> {
            oneTapClient.beginSignIn(signUpRequest).addOnSuccessListener(this, result -> {
                try {
                    startIntentSenderForResult(result.getPendingIntent().getIntentSender(), REQ_ONE_TAP, null, 0, 0, 0);
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Couldn't start One Tap UI: " + e.getLocalizedMessage());
                }
            }).addOnFailureListener(this, e -> {
                // No Google Accounts found. Just continue presenting the signed-out UI.
                Log.d(TAG, "oneTap error: " + e.getLocalizedMessage());
            });
        };
    }
}