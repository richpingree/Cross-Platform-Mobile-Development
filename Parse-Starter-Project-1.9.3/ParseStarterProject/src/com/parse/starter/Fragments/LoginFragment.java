package com.parse.starter.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.starter.R;

/**
 * Created by richardpingree on 7/9/15.
 */

public class LoginFragment extends Fragment {

    public EditText userName, pass;
    public Button LogIn, SignUp;
    private LoginListener mListener;


    public interface LoginListener{
        public void signUp();
    }

    public LoginFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userName = (EditText)getView().findViewById(R.id.username);
        pass = (EditText)getView().findViewById(R.id.password);

        LogIn = (Button)getView().findViewById(R.id.loginBtn);
        SignUp = (Button)getView().findViewById(R.id.signUpBtn);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.signUp();

            }
        });
    }
}
