package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;

import com.parse.starter.Fragments.SignUpFragment;

/**
 * Created by richardpingree on 7/9/15.
 */
public class SignUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new SignUpFragment()).commit();
        }
    }
}
