package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParseObject;
import com.parse.starter.Fragments.DetailFragment;
import com.parse.starter.Fragments.ListFragment;

/**
 * Created by Richard Pingree CPM1507 on 7/10/15.
 */
public class DetailActivity extends Activity implements DetailFragment.DetailListener{

    private String heroName;
    private String heroId;
    private int heroYear;
    private String objectID;

    public static final String HEROEXTRA = "Hero";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.container, new DetailFragment()).commit();
        }

        Intent detailIntent = getIntent();
        if (detailIntent != null) {
            heroName = detailIntent.getStringExtra(ListFragment.HERONAME);
            heroId = detailIntent.getStringExtra(ListFragment.HEROID);
            //heroYear = detailIntent.getStringExtra(ListFragment.HEROYEAR);
            heroYear = detailIntent.getIntExtra(ListFragment.HEROYEAR, 0);
            objectID = detailIntent.getStringExtra(ListFragment.OBJECTID);



            Log.i("details", heroName + " " + heroId + " " + heroYear);

        }


    }

    @Override
    public String getHeroName() {
        return heroName;
    }

    @Override
    public String getHeroId() {
        return heroId;
    }

    @Override
    public String getHeroYear() {
        return String.valueOf(heroYear);
    }

    @Override
    public String getCurrentObjectId() {
        return objectID;
    }


    @Override
    public void deleteEntry() {
        ParseObject.createWithoutData("Hero", objectID).deleteInBackground();
        finish();

    }
}
