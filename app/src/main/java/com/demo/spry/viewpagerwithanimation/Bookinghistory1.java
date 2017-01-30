package com.demo.spry.viewpagerwithanimation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Bookinghistory1 extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {



    ListView listview;
    View rootView;
    SimpleCursorAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinghistory1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_18dp);
        toolbar.setTitle("Booking History");
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        listview = (ListView)findViewById(R.id.bookinghistory);
        mAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.booking_list_item,
                null,
                new String[] { Databasehandler.booking_column_drivername, Databasehandler.booking_column_carname,
                        Databasehandler.boking_column_modelname,Databasehandler.booking_column_rupay,Databasehandler.booking_column_datetime
                ,Databasehandler.booking_column_pickup,Databasehandler.booking_column_drop},
                new int[] { R.id.drname , R.id.carname, R.id.modelname,R.id.rupay,R.id.bookingdatetime ,R.id.pickup,R.id.drop}, 0);

        listview.setAdapter(mAdapter);

        /** Creating a loader for populating listview from sqlite database */
        /** This statement, invokes the method onCreatedLoader() */
        getSupportLoaderManager().initLoader(4, null, this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Uri uri = Cars.bookinguri;
        Log.v("Cars.onCreateLoader", "uri " + uri.toString());
        return new CursorLoader(Bookinghistory1.this, uri, null, null, null, null);
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

