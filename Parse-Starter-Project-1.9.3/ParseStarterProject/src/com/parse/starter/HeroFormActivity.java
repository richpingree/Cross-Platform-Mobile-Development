package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;

import com.parse.starter.Fragments.HeroFormFragment;

/**
 * Created by richardpingree on 7/10/15.
 */
public class HeroFormActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);

        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new HeroFormFragment()).commit();
        }
    }
}
