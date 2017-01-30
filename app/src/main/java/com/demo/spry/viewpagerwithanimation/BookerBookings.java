package com.demo.spry.viewpagerwithanimation;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class BookerBookings extends Activity {


    Button pickup,drop,go;
    TextView picktxt,droptxt,listtxt;
    String pickuptxt,drotxt,list1,driver,car,model,rupay,time,datetime;
    ImageButton mini,sedan;
    private Databasehandler dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booker_bookings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_18dp);
        toolbar.setTitle("Bookings");
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeFragment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        dbHelper = new Databasehandler(this);
        picktxt=(TextView)findViewById(R.id.pickuptext);
        droptxt=(TextView)findViewById(R.id.droptext);
        listtxt=(TextView)findViewById(R.id.list);
        Intent list=getIntent();
        list1= list.getStringExtra("ListData");
        System.out.println("LLLLLLIIIISSSTTTTT"+list1);
        String[] parts = list1.split(",");
        String[]parts1=list1.split("\n");
        driver=parts[0];
        car=parts[1];
        model = parts[2];
        rupay=parts[3];
        datetime= parts1[1];
        System.out.println("driver"+driver);
        System.out.println("cer"+car);
        System.out.println("rupay"+rupay);
        System.out.println("datetime"+datetime);
        listtxt.setText(list1);
        listtxt.setGravity(Gravity.CENTER);
        Intent intent=getIntent();
        Intent intent1=getIntent();
        pickuptxt=intent.getStringExtra("pickup");
        picktxt.setText(pickuptxt);
        drotxt=intent1.getStringExtra("drop");
        droptxt.setText(drotxt);
        pickup = (Button) findViewById(R.id.pickup);
        pickup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("list1",list1);
                startActivity(intent);

            }
        });
        drop=(Button)findViewById(R.id.drop);
        drop.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("pickupvalue", pickuptxt);
                intent.putExtra("list1",list1);
                startActivity(intent);


            }
        });
        go=(Button)findViewById(R.id.go);
        go.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                savedetialtodb();
                Intent intent = new Intent(getApplicationContext(), BookercurrentRyde.class);
                intent.putExtra("currentryde",list1);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }

    private void savedetialtodb() {
        if (dbHelper.insertBooking(driver.toString(),
                car.toString(), model.toString(), rupay.toString(),datetime.toString(),pickuptxt.toString(),drotxt.toString())){
        }
        else {
            Toast.makeText(getApplicationContext(), "Could not Insert Car", Toast.LENGTH_SHORT).show();
        }
    }
}