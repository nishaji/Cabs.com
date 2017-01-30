package com.demo.spry.viewpagerwithanimation;

import android.support.v4.app.Fragment;
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
 * Created by sprydev5 on 25/1/16.
 */
public class Tripdetail extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView listview;
    View rootView;
    SimpleCursorAdapter mAdapter;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.trip_detail, container, false);
        listview = (ListView) rootView.findViewById(R.id.tripdetaillistview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 t1 = (TextView) view.findViewById(R.id.route);
                String route = t1.getText().toString();
                t2 = (TextView) view.findViewById(R.id.driver);
                String driver = t2.getText().toString();
                t3 = (TextView) view.findViewById(R.id.car);
                String car = t3.getText().toString();
                t4 = (TextView) view.findViewById(R.id.model);
                String model = t4.getText().toString();
                t5 = (TextView) view.findViewById(R.id.date);
                String date = t5.getText().toString();
                t6 = (TextView) view.findViewById(R.id.time);
                String time = t6.getText().toString();
                t7 = (TextView) view.findViewById(R.id.fare);
                String fare = t7.getText().toString();
                t8 = (TextView) view.findViewById(R.id.seat);
                String seat = t8.getText().toString();
                Intent intent = new Intent(getContext(), EditTripActivity.class);
                intent.putExtra("route",route);
                intent.putExtra("driver",driver);
                intent.putExtra("car",car);
                intent.putExtra("model",model);
                intent.putExtra("date",date);
                intent.putExtra("time",time);
                intent.putExtra("fare",fare);
                intent.putExtra("seat",seat);
                startActivity(intent);

            }
        });
        mAdapter = new SimpleCursorAdapter(getContext(),
                R.layout.trip_detail_item,
                null,
                new String[]{Databasehandler.trip_column_route,Databasehandler.trip_column_drivers
                        ,Databasehandler.trip_column_cars,Databasehandler.trip_column_model
                        ,Databasehandler.trip_column_date,Databasehandler.trip_column_time,
                        Databasehandler.trip_column_fare,Databasehandler.trip_column_seats},
                      new int[]{R.id.route,R.id.driver,R.id.car,R.id.model
                        ,R.id.date,R.id.time,R.id.fare, R.id.seat}, 0);
                         listview.setAdapter(mAdapter);

        /** Creating a loader for populating listview from sqlite database */
        /** This statement, invokes the method onCreatedLoader() */
        Tripdetail.this.getActivity().getSupportLoaderManager().initLoader(3, null, this);
        return rootView;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Uri uri = Cars.tripdetailuri;
        Log.v("Tripdetail.onCreateLoader", "uri " + uri.toString());
        return new CursorLoader(Tripdetail.this.getContext(), uri, null, null, null, null);
    }

    /**
     * A callback method, invoked after the requested content provider returned all the data
     */
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        Log.v("Tripdetail.onCreateLoader", "cursor " + ((CursorLoader) arg0).getUri());
        Log.v("Tripdetail.onLoadFinished", "columns from table " + Arrays.toString(arg1.getColumnNames()));
        mAdapter.swapCursor(arg1);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mAdapter.swapCursor(null);
    }
}



