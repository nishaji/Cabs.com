package com.demo.spry.viewpagerwithanimation;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sprydev5 on 7/12/15.
 */
public class DriversList extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    ListView listview;
    View rootView;
    ArrayAdapter<String> adapter;
    SimpleCursorAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.driverlist, container, false);
        FloatingActionButton myFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDriverActivity.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getContext(), R.anim.animation, R.anim.animation2).toBundle();
                getActivity().startActivity(intent, bndlanimation);
            }
        });
        listview = (ListView) rootView.findViewById(R.id.driverlistview);
        mAdapter = new SimpleCursorAdapter(getContext(),
                R.layout.driver_list_item,
                null,
                new String[] { Databasehandler.driver_fname, Databasehandler.driver_lname, Databasehandler.driver_phone,Databasehandler.driver_licence},
                new int[] { R.id.driverfname , R.id.driverlname, R.id.driverphone,R.id.driverlicence }, 0);

        listview.setAdapter(mAdapter);

        /** Creating a loader for populating listview from sqlite database */
        /** This statement, invokes the method onCreatedLoader() */
        DriversList.this.getActivity().getSupportLoaderManager().initLoader(2, null, this);
        return rootView;

    }
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Uri uri = Cars.driversuri;
        Log.v("Drivers.onCreateLoader", "uri "+ uri.toString());
        return new CursorLoader(DriversList.this.getContext(), uri, null, null, null, null);
    }

    /** A callback method, invoked after the requested content provider returned all the data */
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        mAdapter.swapCursor(arg1);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mAdapter.swapCursor(null);
    }


}

