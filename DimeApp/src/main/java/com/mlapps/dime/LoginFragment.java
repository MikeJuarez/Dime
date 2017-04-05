package com.mlapps.dime;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment implements View.OnClickListener {

    TextView btn_Signup;
    Button btn_Password;
    Button btn_Login;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btn_Signup = (TextView) view.findViewById(R.id.btn_fragLogin_signup);
        btn_Signup.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        ((MainActivity)getActivity()).signupPressed();
    }

    public interface OnLoginSelectedListener {
        public void signupPressed();
    }
}


