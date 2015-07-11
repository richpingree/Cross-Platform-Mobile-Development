package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseObject;
import com.parse.starter.Fragments.DetailFragment;

/**
 * Created by Richard Pingree CPM1507 on 7/10/15.
 */
public class DetailActivity extends Activity implements DetailFragment.DetailListener{

    private ParseObject hero;

    public static final String HEROEXTRA = "Hero";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new DetailFragment()).commit();
        }

        Intent detailIntent = getIntent();
        if (detailIntent != null){
            hero = (ParseObject)detailIntent.getSerializableExtra(HEROEXTRA);
        }
    }

    @Override
    public ParseObject getHero() {
        return hero;
    }

    @Override
    public int getDelete() {
        return 0;
    }

    @Override
    public void deleteEntry() {

    }
}
