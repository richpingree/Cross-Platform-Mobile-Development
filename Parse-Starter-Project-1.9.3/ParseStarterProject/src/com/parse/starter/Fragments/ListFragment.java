package com.parse.starter.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.starter.DetailActivity;
import com.parse.starter.R;

import java.io.IOException;

//import com.parse.starter.CustomAdapter;

/**
 * Created by Richard Pingree CPM1507 on 7/10/15.
 */
public class ListFragment extends Fragment{

    public static final String HERONAME = "Hero Name";
    public static final String HEROID = "Hero Id";
    public static final String HEROYEAR = "Hero Year";
    public static final String OBJECTID = "Object ID";
    private HeroListener mListener;
    TextView userTxt;
    Button logoutBtn, addBtn;
    ListView heroListView;
    ParseQueryAdapter<ParseObject> mainAdapter;
    ParseObject selectedObject;


    public interface HeroListener{
        public void addHero();
    }

    public ListFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof HeroListener){
            mListener = (HeroListener) activity;
        } else{
            throw new IllegalArgumentException("Containing activity must implement HeroListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ParseUser currentUser = ParseUser.getCurrentUser();

        final String userString = currentUser.getUsername().toString();

        heroListView = (ListView)getView().findViewById(R.id.heroList);

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

        try {
            updateList();
        } catch (IOException e) {
            e.printStackTrace();
        }




        addBtn = (Button)getView().findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.addHero();

            }
        });
    }

    public void updateList() throws IOException{
        mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), "Hero");
        mainAdapter.setTextKey("Name");

        heroListView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();

        heroListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedObject = mainAdapter.getItem(position);
                String currentName = selectedObject.getString("Name");
                String currentId = selectedObject.getString("Id");
                int currentYear = selectedObject.getInt("Year");
                String currentObjectId = selectedObject.getObjectId();

                Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
                detailIntent.putExtra(HERONAME, currentName);
                detailIntent.putExtra(HEROID, currentId);
                detailIntent.putExtra(HEROYEAR, currentYear);
                detailIntent.putExtra(OBJECTID, currentObjectId);
                startActivity(detailIntent);

            }
        });

    }
}
