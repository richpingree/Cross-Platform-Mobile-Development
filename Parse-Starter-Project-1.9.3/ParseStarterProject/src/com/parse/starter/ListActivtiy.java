package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;

import com.parse.starter.Fragments.ListFragment;

/**
 * Created by richardpingree on 7/10/15.
 */
public class ListActivtiy extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new ListFragment()).commit();
        }
    }
}