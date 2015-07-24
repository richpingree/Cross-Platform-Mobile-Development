package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseObject;
import com.parse.starter.Fragments.ListFragment;

import java.io.IOException;
import java.util.List;

/**
 * Created by Richard Pingree CPM1507 on 7/10/15.
 */
public class ListActivtiy extends Activity implements ListFragment.HeroListener{

    private static final int ADDREQUEST = 0;
    public static int DELETEREQUEST = 1;

    List<ParseObject> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new ListFragment()).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == ADDREQUEST){
            ListFragment lf = (ListFragment)getFragmentManager().findFragmentById(R.id.container);
            try {
                lf.updateList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void addHero() {
        Intent addIntent = new Intent(this, HeroFormActivity.class);
        startActivityForResult(addIntent, ADDREQUEST);

    }
}
