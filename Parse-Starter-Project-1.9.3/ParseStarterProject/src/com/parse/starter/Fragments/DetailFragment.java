package com.parse.starter.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.parse.starter.R;

/**
 * Created by Richard Pingree CPM1507 on 7/10/15.
 */
public class DetailFragment extends Fragment {

    private DetailListener mListener;

    EditText heroNameText, heroIdText, heroYearText;

    public interface DetailListener{
        public String getHeroName();
        public String getHeroId();
        public String getHeroYear();
        public int getDelete();
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


    }
}
