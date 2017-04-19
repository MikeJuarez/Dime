package com.mlapps.dime;


import com.mlapps.dime.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;


import android.os.Bundle;


import android.R.anim;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.attr.fragment;

public class MainActivity extends FragmentActivity implements LoginFragment.loginListener, SignupFragment.signupUser {

    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    LoginFragment loginFrag = new LoginFragment();
    SignupFragment signupFrag = new SignupFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        fragmentAdapter(loginFrag);

        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Starting");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signupPressed() {

        fragmentAdapter(signupFrag);
    }

    public void loginPressed(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(getApplicationContext(), "User was signed in successfully.", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "User was not signed in successfully.", Toast.LENGTH_SHORT).show();
                        // ...
                    }
                });

        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
    }

    public void signupUser(String email, String password) {
        Toast.makeText(getApplicationContext(), "email: " + email + ", pass: " + password, Toast.LENGTH_SHORT).show();
        createAccount(email, password);
    }

    public void createAccount(String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(getApplicationContext(), "User was added successfully.", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "User not added successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {

        if(!loginFrag.isVisible())
            super.onBackPressed();
    }


    public void fragmentAdapter(Fragment fragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_in, R.animator.fade_out);
        transaction.replace(R.id.fragment_placeholder, fragment, fragment.getTag());

        transaction.addToBackStack(null);
        transaction.commit();
    }

}
