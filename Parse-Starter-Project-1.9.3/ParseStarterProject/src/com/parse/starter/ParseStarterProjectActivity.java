package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;

import com.parse.ParseAnalytics;
import com.parse.starter.Fragments.LoginSignUpFragment;

public class ParseStarterProjectActivity extends Activity implements LoginSignUpFragment.LoginListener{
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpenedInBackground(getIntent());

//		ParseObject testObject = new ParseObject("TestObject");
//		testObject.put("foo", "bar");
//		testObject.saveInBackground();

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new LoginSignUpFragment()).commit();
        }
	}


}
