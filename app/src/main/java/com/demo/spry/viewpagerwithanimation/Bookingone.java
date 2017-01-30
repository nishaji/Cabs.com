package com.demo.spry.viewpagerwithanimation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by sprydev5 on 18/12/15.
 */
public class Bookingone extends Fragment {
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ArrayAdapter<String> adapter;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bookingone, container, false);
        listview=(ListView)rootView.findViewById(R.id.listview);
        new RemoteDataTask().execute();
        return rootView;
    }
    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Bookingone.this.getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Parse.com Simple ListView Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "Country" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Trips1");
            query.orderByDescending("_created_at");
            try {
                ob = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            adapter = new ArrayAdapter<String>(Bookingone.this.getActivity(),R.layout.listview_item);
            for (ParseObject country : ob) {
                String ab=((String) country.get("From"));
                String ab1=((String)country.get("To"));
                String ab2=((String)country.get("Date"));
                String ab3=((String)country.get("Time"));
                StringBuilder builder=new StringBuilder();
                builder.append("From :").append(ab).append("\n").append("To :").append(ab1).append("\n")
                        .append("Date :").append(ab2).append("\n").append("Time :").append(ab3);
                adapter.add(builder.toString());


            }
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
            // Capture button clicks on ListView items
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // Send single item click data to SingleItemView Class
                    Intent i = new Intent(Bookingone.this.getActivity(),Welcome1.class);
                    // Pass data "name" followed by the position
                    i.putExtra("From", ob.get(position).getString("From").toString());
                    i.putExtra("To", ob.get(position).getString("To").toString());
                    i.putExtra("Date", ob.get(position).getString("Date").toString());
                    i.putExtra("Time", ob.get(position).getString("Time").toString());
                    // Open SingleItemView.java Activity
                    startActivity(i);
                }
            });
        }
    }
}






