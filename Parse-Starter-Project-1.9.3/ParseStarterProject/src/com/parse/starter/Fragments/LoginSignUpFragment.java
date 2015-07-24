package com.parse.starter.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.ListActivtiy;
import com.parse.starter.R;

/**
 * Created by Richard Pingree CPM1507 on 7/9/15.
 */

public class LoginSignUpFragment extends Fragment {

    public EditText userName, pass;
    public Button LogIn, SignUp;
    public String userNameTxt, passwordTxt;
    private LoginListener mListener;


    public interface LoginListener{
        public boolean isOnline();

    }

    public LoginSignUpFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loginsignup, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userName = (EditText)getView().findViewById(R.id.username);
        pass = (EditText)getView().findViewById(R.id.password);

        LogIn = (Button)getView().findViewById(R.id.loginBtn);
        SignUp = (Button)getView().findViewById(R.id.signUpBtn);

//        ParseUser currentUser = ParseUser.getCurrentUser();
//        if (currentUser != null){
//            loginMethod();
//        }else{
//
//        }

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userNameTxt = userName.getText().toString();
                passwordTxt = pass.getText().toString();

//                if (mListener.isOnline()){
                    ParseUser.logInInBackground(userNameTxt, passwordTxt, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if (parseUser != null){
//                            Intent loginIntent = new Intent(getActivity(), ListActivtiy.class);
//                            startActivity(loginIntent);
                                loginMethod();

                                Toast.makeText(getActivity(),"Successfully Logged In", Toast.LENGTH_LONG).show();
                                userName.setText("");
                                pass.setText("");
                            } else {
                                Toast.makeText(getActivity(), "No such user exist, please signup", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
//                } else{
//                    Toast.makeText(getActivity(), "No Network Connection!", Toast.LENGTH_SHORT).show();
//                }



            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userNameTxt = userName.getText().toString();
                passwordTxt = pass.getText().toString();

                Log.i("User/Password", userNameTxt + " " + passwordTxt);

                if (userNameTxt.equals("") && passwordTxt.equals("")){
                    Toast.makeText(getActivity(), "Please fill in sign up form", Toast.LENGTH_LONG).show();
                } else {
                    ParseUser user = new ParseUser();
                    user.setUsername(userNameTxt);
                    user.setPassword(passwordTxt);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {
                            if (e == null){
                                Toast.makeText(getActivity(), "Successfully Signed Up, please log in.", Toast.LENGTH_LONG).show();
                            } else{
                                Toast.makeText(getActivity(), "Sign up Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void loginMethod(){
        Intent loginIntent = new Intent(getActivity(), ListActivtiy.class);
        startActivity(loginIntent);
    }

    public ParseUser currentUser(){
        return ParseUser.getCurrentUser();
    }
}
