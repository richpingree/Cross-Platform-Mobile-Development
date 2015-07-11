package com.parse.starter.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.R;

/**
 * Created by richardpingree on 7/10/15.
 */
public class HeroFormFragment extends Fragment {

    EditText heroName, heroId, heroYear;
    Button cancelBtn, saveBtn;

    public HeroFormFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        heroName = (EditText)getView().findViewById(R.id.heroName);
        heroId = (EditText)getView().findViewById(R.id.heroId);
        heroYear = (EditText)getView().findViewById(R.id.heroYear);


        cancelBtn = (Button)getView().findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        saveBtn = (Button)getView().findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String heroNameString = heroName.getText().toString();
                String heroIdString = heroId.getText().toString();
                int heroBirthYear = Integer.valueOf(heroYear.getText().toString());

                ParseObject hero = new ParseObject("Hero");
                hero.put("User", ParseUser.getCurrentUser().getUsername().toString());
                hero.put("Name", heroNameString);
                hero.put("Id", heroIdString);
                hero.put("Year", heroBirthYear);
                hero.saveInBackground();
                Intent returnIntent = new Intent();
                getActivity().setResult(Activity.RESULT_OK, returnIntent);
                getActivity().finish();
            }
        });
    }
}
