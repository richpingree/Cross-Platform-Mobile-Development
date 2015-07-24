package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.parse.ParseAnalytics;
import com.parse.starter.Fragments.LoginSignUpFragment;

public class ParseStarterProjectActivity extends Activity implements LoginSignUpFragment.LoginListener{
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpenedInBackground(getIntent());


        if(savedInstanceState == null){
			LoginSignUpFragment loginFrag = new LoginSignUpFragment();
            getFragmentManager().beginTransaction().replace(R.id.container, loginFrag).commit();


        }
	}

    @Override
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else {
            return false;
        }
    }
}
