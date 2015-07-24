package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.starter.Fragments.DetailFragment;
import com.parse.starter.Fragments.ListFragment;

/**
 * Created by Richard Pingree CPM1507 on 7/10/15.
 */
public class DetailActivity extends Activity implements DetailFragment.DetailListener{

    private String heroName;
    private String heroId;
    private int heroYear;
    //private int heroYear;

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
    public int getDelete() {
        return 0;
    }

    @Override
    public void deleteEntry() {

    }
}
