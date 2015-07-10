package com.parse.starter.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;
import com.parse.starter.HeroFormActivity;
import com.parse.starter.R;

/**
 * Created by richardpingree on 7/10/15.
 */
public class ListFragment extends Fragment{

    TextView userTxt;
    Button logoutBtn, addBtn;

    public ListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ParseUser currentUser = ParseUser.getCurrentUser();

        String userString = currentUser.getUsername().toString();

        userTxt = (TextView)getView().findViewById(R.id.currentUserId);

        userTxt.setText("Currently Logged in as " + userString);

        logoutBtn = (Button)getView().findViewById(R.id.logoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                getActivity().finish();
            }
        });

        addBtn = (Button)getView().findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(getActivity(), HeroFormActivity.class);
                startActivity(addIntent);
            }
        });
    }
}
