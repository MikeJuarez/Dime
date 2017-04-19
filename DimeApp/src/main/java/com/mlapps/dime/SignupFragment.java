package com.mlapps.dime;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mlapps.dime.MainActivity;
import com.mlapps.dime.R;

import static com.mlapps.dime.R.id.btn_fragSignup_signup;


public class SignupFragment extends Fragment implements View.OnClickListener {
    EditText txt_Email;
    EditText txt_DisplayName;
    EditText txt_Password;

    Button btn_Signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        txt_Email = (EditText) view.findViewById(R.id.txtEmailAddress);
        txt_DisplayName = (EditText) view.findViewById(R.id.txtDisplayName);;
        txt_Password = (EditText) view.findViewById(R.id.txtPassword);;
        btn_Signup = (Button) view.findViewById(R.id.btn_fragSignup_signup);

        btn_Signup.setOnClickListener(this);

        return view;
    }

    public void onClick(View v) {
        ((MainActivity)getActivity()).signupUser(txt_Email.getText().toString().trim(), txt_Password.getText().toString().trim());
    }

    public interface signupUser {
        public void signupUser(String email, String password);
    }
}
