package com.parse.starter.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.starter.R;

/**
 * Created by Richard Pingree CPM1507 on 7/10/15.
 */
public class DetailFragment extends Fragment {

    private DetailListener mListener;

    public interface DetailListener{
        public ParseObject getHero();
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

        TextView textView = (TextView)getView().findViewById(R.id.detailName);
        textView.setText(mListener.getHero().getString("Name"));
    }
}
