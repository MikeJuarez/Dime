package com.mlapps.dime;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.mlapps.dime.R.id.txtUserPass;

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

        btn_Login = (Button) view.findViewById(R.id.btn_fragLogin_login);
        btn_Login.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        EditText username = (EditText) getView().findViewById(R.id.txtUserName);
        EditText password = (EditText) getView().findViewById(R.id.txtUserPass);

        switch (v.getId()) {

            case R.id.btn_fragLogin_signup:
                ((MainActivity)getActivity()).signupPressed();
                break;

            case R.id.btn_fragLogin_login:
                ((MainActivity)getActivity()).loginPressed(username.getText().toString(), password.getText().toString());
                break;

            default:
                break;
        }
    }

    public interface loginListener {
        public void signupPressed();
        public void loginPressed(String email, String password);
    }

  }


