package com.parse.starter.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;

import java.io.IOException;
import java.util.List;

/**
 * Created by Richard Pingree CPM1507 on 7/10/15.
 */
public class ListFragment extends Fragment{

    private HeroListener mListener;
    TextView userTxt;
    Button logoutBtn, addBtn;
    ListView heroListView;


    public interface HeroListener{
        public void viewHero(int position);
        public void deleteHero(int position);
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

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hero");
        query.getInBackground("knqg3xwjhR", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object will be your game score
                    String name = object.getString("Name");
                    String id = object.getString("Id");
                    String user = object.getString("User");

                    Log.i("Object", name + " " + id + " " + user);
                } else {
                    // something went wrong
                }
            }
        });

        ParseQuery<ParseObject> listQuery = ParseQuery.getQuery("Hero");

        listQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    Log.i("List", "Retrieved " + list.size() + " heroes");

                    class CustomAdapter extends BaseAdapter{

                        List<ParseObject> list;

                        Context context;

                        public CustomAdapter(FindCallback findCallback, List<ParseObject> list, Context context){
                            super();
                            this.context = getActivity().getApplicationContext();
                            this.list = list;
                        }

                        @Override
                        public int getCount() {
                            if (list != null){
                                return list.size();
                            }else{
                                return 0;
                            }

                        }

                        @Override
                        public Object getItem(int position) {
                            return list.get(position);
                        }

                        @Override
                        public long getItemId(int position) {
                            return 0;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            if (convertView == null){
                                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
                            }
                            TextView heroNameText = (TextView)convertView.findViewById(R.id.item1);
                            TextView heroYearText = (TextView)convertView.findViewById(R.id.item2);

                            if (list != null){
                                if (list.get(position).getString("Name") != null){
                                    heroNameText.setText("Name: " + list.get(position).getString("Name").toString());
                                    heroYearText.setText("Birth Year: " + Integer.valueOf(list.get(position).getInt("Year")).toString());
                                }
                            }
                            return convertView;
                        }
                    }

                    heroListView.setAdapter(new CustomAdapter(this, list, null));

                    heroListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            mListener.viewHero(position);
                        }
                    });
                } else {
                    Log.i("List", "Error: " + e.getMessage());
                }
            }
        });

        addBtn = (Button)getView().findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent addIntent = new Intent(getActivity(), HeroFormActivity.class);
//                startActivity(addIntent);
                mListener.addHero();

            }
        });
    }

    public void updateList() throws IOException{
        ListView heroList = (ListView)getView().findViewById(R.id.heroList);
        BaseAdapter adapter = (BaseAdapter)heroList.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
