package com.demo.spry.viewpagerwithanimation;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sprydev5 on 7/12/15.
 */
public class Trips extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView listview;
    View rootView;
    SimpleCursorAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.trips, container, false);

        FloatingActionButton myFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent slideactivity = new Intent(getActivity(), TripsActivity.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getContext(), R.anim.animation,R.anim.animation2).toBundle();
               getActivity().startActivity(slideactivity, bndlanimation);

            }
        });
        listview=(ListView)rootView.findViewById(R.id.triplistview);
        mAdapter = new SimpleCursorAdapter(getContext(),
                R.layout.trip_list_item,
                null,
                new String[] { Databasehandler.trip_column_route, Databasehandler.trip_column_date, Databasehandler.trip_column_time},
                new int[] { R.id.route , R.id.date, R.id.time }, 0);

        listview.setAdapter(mAdapter);

        /** Creating a loader for populating listview from sqlite database */
        /** This statement, invokes the method onCreatedLoader() */
        Trips.this.getActivity().getSupportLoaderManager().initLoader(1, null, this);
        return rootView;

    }
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Uri uri = Cars.tripsuri;
        Log.v("Trips.onCreateLoader", "uri "+ uri.toString());
        return new CursorLoader(Trips.this.getContext(), uri, null, null, null, null);
    }

    /** A callback method, invoked after the requested content provider returned all the data */
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        Log.v("Trips.onCreateLoader", "cursor "+ ((CursorLoader)arg0).getUri());
        Log.v("Trips.onLoadFinished", "columns from table "+ Arrays.toString(arg1.getColumnNames()));
        mAdapter.swapCursor(arg1);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mAdapter.swapCursor(null);
    }


}