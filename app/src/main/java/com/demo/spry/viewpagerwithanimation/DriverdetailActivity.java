package com.demo.spry.viewpagerwithanimation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class DriverdetailActivity extends Fragment {

    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<DriverFulldetail> driverlist = null;
      View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       rootview = inflater.inflate(R.layout.activity_driverdetail, container, false);
        listview=(ListView)rootview.findViewById(R.id.driverdetaillistview);
        new RemoteDataTask().execute();
        return rootview;

    }
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(DriverdetailActivity.this.getContext());
            // Set progressdialog title
            mProgressDialog.setTitle("DriverdetailList");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            driverlist = new ArrayList<DriverFulldetail>();
            try {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("DriverList1");
                query.whereEqualTo("driverid", ParseUser.getCurrentUser());

                ob = query.find();
                for (ParseObject driver : ob) {
                    ParseFile image = (ParseFile) driver.get("ImageFile");
                    DriverFulldetail map = new DriverFulldetail();
                    map.setFname((String) driver.get("FirstName"));
                    map.setLname((String) driver.get("LastName"));
                    map.setLicence((String) driver.get("Licence"));
                    map.setPhone((String) driver.get("Phone"));
                    map.setDrimage(image.getUrl());
                    driverlist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView)rootview.findViewById(R.id.driverdetaillistview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(DriverdetailActivity.this.getContext(),driverlist);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

}


