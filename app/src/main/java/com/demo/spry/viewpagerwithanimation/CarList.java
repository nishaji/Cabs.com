package com.demo.spry.viewpagerwithanimation;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.widget.SimpleCursorAdapter;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.content.CursorLoader;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sprydev5 on 7/12/15.
 */
public class CarList extends Fragment implements LoaderCallbacks<Cursor> {

        ListView listview;
        View rootView;
        SimpleCursorAdapter mAdapter;
    String userName;
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.carlist, container, false);
        Bundle args = getArguments();
        if (args  != null && args.containsKey("user_name")) {
            userName = args.getString("user_name");
            System.out.println(userName+"uusername");
        }
        FloatingActionButton myFab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CarList.this.getActivity(), AddcarActivity.class);
                intent.putExtra("email",userName);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getContext(), R.anim.animation, R.anim.animation2).toBundle();
                getActivity().startActivity(intent, bndlanimation);
            }
        });
        listview = (ListView) rootView.findViewById(R.id.carlistview);
        mAdapter = new SimpleCursorAdapter(getContext(),
                R.layout.car_list_item,
                null,
                new String[] { Databasehandler.CAR_COLUMN_BRAND, Databasehandler.CAR_COLUMN_MODEL, Databasehandler.CAR_COLUMN_COLOR,Databasehandler.CAR_COLUMN_REGISTRATION},
                new int[] { R.id.brand , R.id.model, R.id.color,R.id.registration }, 0);

        listview.setAdapter(mAdapter);

        /** Creating a loader for populating listview from sqlite database */
        /** This statement, invokes the method onCreatedLoader() */
        CarList.this.getActivity().getSupportLoaderManager().initLoader(0, null, this);
        return rootView;

    }
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Uri uri = Cars.carsuri;
        Log.v("Cars.onCreateLoader", "uri "+ uri.toString());
        return new CursorLoader(CarList.this.getContext(), uri, null, null, null, null);
    }

    /** A callback method, invoked after the requested content provider returned all the data */
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        mAdapter.swapCursor(arg1);
        mAdapter.notifyDataSetChanged();
        listview.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mAdapter.swapCursor(null);
    }


}
