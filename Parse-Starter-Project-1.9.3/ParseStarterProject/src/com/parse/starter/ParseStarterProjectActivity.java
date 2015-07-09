package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseAnalytics;
import com.parse.starter.Fragments.LoginFragment;

public class ParseStarterProjectActivity extends Activity implements LoginFragment.LoginListener{
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpenedInBackground(getIntent());

//		ParseObject testObject = new ParseObject("TestObject");
//		testObject.put("foo", "bar");
//		testObject.saveInBackground();

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).commit();
        }
	}

    @Override
    public void signUp() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }
}
