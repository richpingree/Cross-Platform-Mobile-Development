package com.parse.starter.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;

/**
 * Created by Richard Pingree CPM1507 on 7/10/15.
 */
public class DetailFragment extends Fragment {

    private DetailListener mListener;

    EditText heroNameText, heroIdText, heroYearText;
    Button updateBtn, deleteBtn;

    public interface DetailListener{
        public String getHeroName();
        public String getHeroId();
        public String getHeroYear();
        public String getCurrentObjectId();
        public void deleteEntry();
    }

    public DetailFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof DetailListener){
            mListener = (DetailListener)activity;
        } else{
            throw new IllegalArgumentException("Containing activtiy must implement DetailListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        heroNameText = (EditText)getView().findViewById(R.id.detailName);
        heroNameText.setText(mListener.getHeroName());

        heroIdText = (EditText)getView().findViewById(R.id.detailId);
        heroIdText.setText(mListener.getHeroId());

        heroYearText = (EditText)getView().findViewById(R.id.detailYear);
        heroYearText.setText(mListener.getHeroYear());

        updateBtn = (Button)getView().findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Hero");

                // Retrieve the object by id
                query.getInBackground(mListener.getCurrentObjectId(), new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, com.parse.ParseException e) {
                        if (e == null){
                            parseObject.put("Name", heroNameText.getText().toString());
                            parseObject.put("Id", heroIdText.getText().toString());
                            parseObject.put("Year", Integer.valueOf(heroYearText.getText().toString()));
                            parseObject.saveEventually();

                        }
                    }

                });

                getActivity().finish();


            }
        });

        deleteBtn = (Button)getView().findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.deleteEntry();
            }
        });


    }
}
