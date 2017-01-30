package com.demo.spry.viewpagerwithanimation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.widget.TimePicker;


/**
 * Created by sprydev5 on 1/12/15.
 */
public class BookerSearch  extends Activity implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener ,View.OnClickListener {
    String routes, from, dates, times, seatss;

    Spinner route, seats, cartype;
    private ContactsContract.CommonDataKinds.Note note;
    private TextView timeTextView;
    private TextView dateTextView;
    private CheckBox mode12Hours;
    TextView t1, t2;
    TextView e1, e2, e3, e4, e5;
    Button b1, b2;
    View view;
    Integer addhour,minueshour;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ArrayAdapter<String> adapter;
    View rootView;
    ListView listview;
    DisplayAdapter adapter1;
    String duration,datetime;
    Date adddate,minuesdate,add,minues,date;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booker_search);
        route = (Spinner) findViewById(R.id.route);
        seats = (Spinner) findViewById(R.id.seat);
        cartype = (Spinner) findViewById(R.id.cartype);
        t1 = (TextView) findViewById(R.id.text1);
        b1 = (Button) findViewById(R.id.btnsubmit);
        e3 = (TextView) findViewById(R.id.date);
        e4 = (TextView) findViewById(R.id.time);
        timeTextView = (TextView) findViewById(R.id.time_textview);
        dateTextView = (TextView) findViewById(R.id.date_textview);
        ImageButton time = (ImageButton) findViewById(R.id.time_button);
        ImageButton date = (ImageButton) findViewById(R.id.date_button);
        mode12Hours = (CheckBox) findViewById(R.id.mode_24_hours);
        String seat[] = {"1", "2", "3", "4", "5", "6", "7"};
        String cartypess[] = {"sedan", "Mini"};
        String routes[] = {"Jaipur to Delhi", "Delhi to Jaipur"};
        ArrayAdapter<String> seats1 = new ArrayAdapter<String>(BookerSearch.this, R.layout.spinner_layout, seat);
        ArrayAdapter<String> route1 = new ArrayAdapter<String>(BookerSearch.this, R.layout.spinner_layout, routes);
        ArrayAdapter<String> cartypes = new ArrayAdapter<String>(BookerSearch.this, R.layout.spinner_layout, cartypess);
        seats1.setDropDownViewResource(R.layout.spinner_layout);
        seats.setAdapter(seats1);
        route1.setDropDownViewResource(R.layout.spinner_layout);
        route.setAdapter(route1);
        cartypes.setDropDownViewResource(R.layout.spinner_layout);
        cartype.setAdapter(cartypes);
        b1.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_18dp);
        toolbar.setTitle("Search Ride");
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Welcome2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        listview=(ListView)findViewById(R.id.homelistView);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd;
                tpd = TimePickerDialog.newInstance(BookerSearch.this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), mode12Hours.isChecked());
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(BookerSearch.this.getFragmentManager(), "Timepickerdialog");
            }
        });

        // Show a datepicker when the dateButton is clicked
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        BookerSearch.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.show(BookerSearch.this.getFragmentManager(), "Datepickerdialog");
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        TimePickerDialog tpd = (TimePickerDialog) BookerSearch.this.getFragmentManager().findFragmentByTag("Timepickerdialog");
        DatePickerDialog dpd = (DatePickerDialog) BookerSearch.this.getFragmentManager().findFragmentByTag("Datepickerdialog");

        if (tpd != null) tpd.setOnTimeSetListener(this);
        if (dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        // String secondString = second < 10 ? "0"+second : ""+second;
        String time = hourString+":"+minuteString;
        timeTextView.setText(time);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String monthname=(String)android.text.format.DateFormat.format("MMMM", new Date());
        String date=monthname +" " +dayOfMonth+", "+year;
        dateTextView.setText(date);
    }

    @Override
    public void onClick(View v) {
        new RemoteDataTask().execute();
        isInternetOn();
    }

    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(BookerSearch.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Search");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {

            ParseQuery myQuery1 = new ParseQuery("Trips");
            // myQuery1.whereEqualTo("Date", dates);
            myQuery1.whereLessThanOrEqualTo("Date", dates);
            myQuery1.whereEqualTo("trip", ParseUser.getCurrentUser());

            try {
                ob = myQuery1.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            System.out.println("On Post execute");
            listview = (ListView) findViewById(R.id.homelistView);
            listview.setEmptyView( findViewById( R.id.empty_list_view ) );
            adapter = new ArrayAdapter<String>(BookerSearch.this, R.layout.itemview, R.id.tvViewRow);
            for (ParseObject country : ob) {
                String ab = (country.getString("Drivers"));
                String ab1 = (country.getString("Cars"));
                String ab2 = (country.getString("Model"));
                String ab3 = (country.getString("Rupay"));
                Date ab4 = (country.getDate("DateTime"));
                StringBuilder builder = new StringBuilder();
                builder.append(ab).append(",").append(ab1).append(",").append(ab2).append(",").
                        append(ab3).append(",").append("\n").append(ab4);
                System.out.println(builder+"HomeFragment search  list");
                adapter.add(builder.toString());
            }
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
            // Capture button clicks on ListView items

        }


    }

    public void myClickHandler(View v) {
        System.out.println("ListView button click");
        RelativeLayout vwParentRow = (RelativeLayout) v.getParent();
        TextView tv = (TextView)vwParentRow.findViewById(R.id.tvViewRow);
        String text = tv.getText().toString();
        Toast.makeText(BookerSearch.this, text, Toast.LENGTH_SHORT).show();
        TextView child = (TextView)vwParentRow.getChildAt(0);
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        Intent intent = new Intent(getApplicationContext(), BookerBookings.class);
        intent.putExtra("ListData",text);
        startActivity(intent);
    }
    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            dates = dateTextView.getText().toString();
            times = timeTextView.getText().toString();
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy hh:mm");
            datetime=dates.concat(" ").concat(times);
            System.out.println(datetime+"sdfge656fhfghfgh");
            try {
                date=formatter.parse(datetime);
                adddate = formatter.parse(datetime);
                minuesdate=formatter.parse(datetime);
                Calendar calendar = Calendar.getInstance();
                Calendar calendar1 = Calendar.getInstance();
                calendar.setTime(adddate);
                calendar1.setTime(minuesdate);
                calendar.add(Calendar.HOUR, 2);
                add=  calendar.getTime();
                calendar1.add(Calendar.HOUR, -2);
                minues=calendar1.getTime();
                System.out.println(adddate+"adddate");
                System.out.println((formatter.format(adddate))+"aaddate"+add);
                System.out.println(minuesdate+"minuesdate"+minues);
                System.out.println((formatter.format(minuesdate))+"minuesdate"+minues);

            } catch (java.text.ParseException e) {
                adddate=null;
                e.printStackTrace();
            }
            if(date!=null){
                new RemoteDataTask().execute();
            }
            else{
                Toast.makeText(getApplicationContext(), "Please choose date and time", Toast.LENGTH_SHORT).show();
            }

            return true;
        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, " You are not connected to the internet , please connect to the internet ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;

        }
}




