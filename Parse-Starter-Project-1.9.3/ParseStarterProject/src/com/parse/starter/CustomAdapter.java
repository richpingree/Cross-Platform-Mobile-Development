package com.parse.starter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by Richard Pingree on 7/22/15.
 */
public class CustomAdapter extends ParseQueryAdapter<ParseObject> {


    public CustomAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>(){

            @Override
            public ParseQuery<ParseObject> create() {
                ParseQuery query = new ParseQuery("Hero");

                return query;
            }
        });
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null){
            v = View.inflate(getContext(), R.layout.list_item, null);
        }

        super.getItemView(object, v, parent);

        TextView heroNameText = (TextView) v.findViewById(R.id.item1);
        heroNameText.setText(object.getString("Name"));

        TextView heroYearText = (TextView) v.findViewById(R.id.item2);
        heroYearText.setText(Integer.valueOf(object.getInt("Year")).toString());
        return v;
    }
}