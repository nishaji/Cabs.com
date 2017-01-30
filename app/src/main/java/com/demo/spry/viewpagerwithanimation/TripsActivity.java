package com.demo.spry.viewpagerwithanimation;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.security.Provider;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import fr.ganfra.materialspinner.MaterialSpinner;
public class TripsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{
    private static final String LOG_TAG = "AddTrips";
    String   seatss, email, rupay, dates, times,routee,models;
    MaterialSpinner cars,  seats,route,driverspinner,carspinner;
    private ContactsContract.CommonDataKinds.Note note;
    private TextView timeTextView;
    private TextView dateTextView;
    private CheckBox mode24Hours;
    Button b1;
    View view;
    EditText model;
    ParseQueryAdapter<ParseObject> adapter;
    private Databasehandler dbHelper;
    int personID;
    String carvalue,drivervalue,modelvalue;

    List<ParseObject> ob,ob1;
    ArrayAdapter<String> driveradapter,caradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        SharedPreferences emailid = getSharedPreferences(LoginActivity.PREFS, 0);
        email = emailid.getString("email", "email");
        System.out.println(email + "emailonaddtripsactivity");
        new DriverData().execute();
        new CarData().execute();
        dbHelper = new Databasehandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_18dp);
        toolbar.setTitle("Add Ryde");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new NumberTextWatcher(editText, "#,###"));
        seats = (MaterialSpinner) findViewById(R.id.seats);
        seats.setHint("Select seat");
        seats.setPaddingSafe(0, 0, 0, 0);
        route=(MaterialSpinner)findViewById(R.id.route);
        route.setHint("Select route");
        route.setPaddingSafe(0, 0, 0, 0);
        model=(EditText)findViewById(R.id.model);
        b1 = (Button) findViewById(R.id.btnsubmit);
        timeTextView = (TextView)findViewById(R.id.time_textview);
        dateTextView = (TextView)findViewById(R.id.date_textview);
        ImageButton timeButton = (ImageButton)findViewById(R.id.pick_time);
        ImageButton date = (ImageButton)findViewById(R.id.pick_date);
        mode24Hours = (CheckBox)findViewById(R.id.mode_24_hours);
        String seat[] = {"1", "2", "3", "4", "5", "6", "7"};
        String routes[] = {"Jaipur to Delhi", "Delhi to Jaipur"};
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, routes);
        route.setAdapter(LTRadapter);
        ArrayAdapter<String> LTRadapter1 = new ArrayAdapter<String>(TripsActivity.this, android.R.layout.select_dialog_item, seat);
        seats.setAdapter(LTRadapter1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getText().toString().equals("") || editText.getText().toString().equals("") || timeTextView.getText().toString().equals("") ||
                        dateTextView.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "fields cant be empty", Toast.LENGTH_SHORT).show();
                } else {

                    if (dbHelper.insertTrip(email.toString(), route.getSelectedItem().toString(),
                            drivervalue.toString(), carvalue.toString(), model.getText().toString(),
                            dateTextView.getText().toString(), timeTextView.getText().toString(),
                            editText.getText().toString(), seats.getSelectedItem().toString())) {
                        Toast.makeText(getApplicationContext(), "trip Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Could not Insert Trip", Toast.LENGTH_SHORT).show();
                    }
                    rupay = editText.getText().toString();
                    models = model.getText().toString();
                    routee = route.getSelectedItem().toString();
                    seatss = seats.getSelectedItem().toString();
                    dates = dateTextView.getText().toString();
                    times = timeTextView.getText().toString();
                    routee = routee.trim();
                    rupay = rupay.trim();
                    seatss = seatss.trim();
                    drivervalue = drivervalue.trim();
                    carvalue = carvalue.trim();
                    models = models.trim();
                    dates = dates.trim();
                    times = times.trim();
                    if (!routee.isEmpty()) if (note == null) {
                        ParseObject post = new ParseObject("Trips");
                        post.put("trip", ParseUser.getCurrentUser());
                        post.put("Rupay", rupay);
                        post.put("Route", routee);
                        post.put("Drivers", drivervalue);
                        post.put("Seats", seatss);
                        post.put("Cars", carvalue);
                        post.put("Model", models);
                        post.put("Time", times);
                        post.put("Date", dates);
                        post.saveInBackground(new SaveCallback() {
                            public void done(com.parse.ParseException e) {
                                if (e == null) {
                                    // Saved successfully.
                                    Toast.makeText(TripsActivity.this, "Saved Sucessfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(TripsActivity.this, Welcome1.class);
                                    startActivity(intent);

                                } else {

                                    Toast.makeText(TripsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                    Log.d(getClass().getSimpleName(), "User update error: " + e);
                                }
                            }
                        });

                    }

                }
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd;
                tpd = TimePickerDialog.newInstance(TripsActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        mode24Hours.isChecked()
                );
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(TripsActivity.this.getFragmentManager(), "Timepickerdialog");
            }
        });

        // Show a datepicker when the dateButton is clicked
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        TripsActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.show(TripsActivity.this.getFragmentManager(), "Datepickerdialog");
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        TimePickerDialog tpd = (TimePickerDialog) this.getFragmentManager().findFragmentByTag("Timepickerdialog");
        DatePickerDialog dpd = (DatePickerDialog) this.getFragmentManager().findFragmentByTag("Datepickerdialog");

        if (tpd != null) tpd.setOnTimeSetListener(this);
        if (dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {

        boolean isPM = (hourOfDay >= 12);
        timeTextView.setText(String.format("%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        dateTextView.setText(date);
    }


    // RemoteDataTask AsyncTask
    private class DriverData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "Country" in Parse.com
            ParseQuery query = new ParseQuery("DriverList1");
            query.whereEqualTo("driverid", ParseUser.getCurrentUser());
            try {
                ob = query.find();
            } catch (com.parse.ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            driverspinner = (MaterialSpinner) findViewById(R.id.drivers);
            driverspinner.setHint("Select driver");
            driverspinner.setPaddingSafe(0, 0, 0, 0);


            // Pass the results into an ArrayAdapter
            driveradapter = new ArrayAdapter<String>(TripsActivity.this,android.R.layout.select_dialog_item);
            for (ParseObject driver : ob) {
                String ab=((String) driver.get("FirstName"));
                String ab1=((String) driver.get("LastName"));
                StringBuilder builder=new StringBuilder();
                builder.append(ab).append(" ").append(ab1);
                driveradapter.add(builder.toString());
            }
            // Binds the Adapter to the ListView
            driverspinner.setAdapter(driveradapter);
            driverspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    drivervalue = driverspinner.getSelectedItem().toString();
                    System.out.println(drivervalue + "drivare nameeeee");
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            driverspinner.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    if (driveradapter.isEmpty()) {
                        driverspinner.setClickable(false);
                        Toast.makeText(TripsActivity.this, "spinner have no items,please add atleast on driver", Toast.LENGTH_LONG).show();
                    }
                    return false;
                }
            });
        }
    }
    // RemoteDataTask AsyncTask
    private class CarData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "Country" in Parse.com
            ParseQuery query = new ParseQuery("Cardetail");
            query.whereEqualTo("carid", ParseUser.getCurrentUser());
            try {
                ob1 = query.find();
            } catch (com.parse.ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            carspinner = (MaterialSpinner) findViewById(R.id.cars);
            carspinner.setHint("Select car");
            carspinner.setPaddingSafe(0, 0, 0, 0);
            caradapter = new ArrayAdapter<String>(TripsActivity.this,android.R.layout.select_dialog_item);
            for (ParseObject car : ob1) {
                String ab=((String) car.get("Make"));
                String ab1=((String) car.get("Registration"));
                StringBuilder builder=new StringBuilder();
                builder.append(ab).append("—").append(ab1);
                caradapter.add(builder.toString());
            }
            carspinner.setAdapter(caradapter);
            carspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    carvalue = driverspinner.getSelectedItem().toString();
                    System.out.println(carvalue + "drivare nameeeee");
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            carspinner.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    if (caradapter.isEmpty()) {
                        carspinner.setClickable(false);
                        Toast.makeText(TripsActivity.this, "spinner have no items, please add atleast one car", Toast.LENGTH_LONG).show();
                    }
                    return false;
                }
            });
        }
    }

}








