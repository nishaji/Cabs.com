package com.demo.spry.viewpagerwithanimation;


import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.provider.MediaStore;

import android.widget.ImageView;

import android.widget.TextView;



import com.parse.ParseException;

import java.io.BufferedInputStream;

import java.io.FileInputStream;
import java.io.InputStream;

public class AddDriverActivity extends Activity  {
    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == RESULT_LOAD_GALLERY_IMAGE && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mCurrentPhotoPath = cursor.getString(columnIndex);
                cursor.close();

            }
            File image = new File(mCurrentPhotoPath);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
             bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
            imgPhoto.setImageBitmap(bitmap);
        }
    }
    private EditText et1, et2, et3, et4;


    Button btn;
    String firstname,lastname,phone,licence,email;
    private static int RESULT_LOAD_GALLERY_IMAGE = 1;
    private String mCurrentPhotoPath;
    private ImageView imgPhoto;
    private Button btnUploadImage;
    private TextView mTextView;
    private ContactsContract.CommonDataKinds.Note note;
    private Databasehandler dbHelper;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        SharedPreferences emailid = getSharedPreferences(LoginActivity.PREFS, 0);
        email = emailid.getString("email", "email");
        System.out.println(email + "emailondriveractivity");
        dbHelper = new Databasehandler(this);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
       et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        btn = (Button) findViewById(R.id.btnsubmit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_18dp);
        toolbar.setTitle("Add Driver");
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        imgPhoto = (ImageView)findViewById(R.id.imgPhoto);
      imgPhoto.setOnClickListener(chooseImageListener);
        btnUploadImage = (Button)findViewById(R.id.btnUpload);
        btnUploadImage.setOnClickListener(uploadListener);


    }

    View.OnClickListener chooseImageListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogChooseFrom();

        }
    };

    View.OnClickListener uploadListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (bitmap == null) {
                Toast.makeText(getApplicationContext(),
                        "Please select image", Toast.LENGTH_SHORT).show();
            } else if(et1.getText().toString().equals("")||et2.getText().toString().equals("")||et3.getText().toString().equals("")
                    ||et4.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),
                        "Fields can't be empty ", Toast.LENGTH_SHORT).show();
            }else {
                isInternetOn();
                if (dbHelper.insertDriver(email.toString(),et1.getText().toString(),
                        et2.getText().toString(), et3.getText().toString(), et4.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Driver Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Welcome1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Could not Insert Car", Toast.LENGTH_SHORT).show();
                }


            }
        }
            };

    private void dialogChooseFrom(){

        final CharSequence[] items={"From Gallery"};

        AlertDialog.Builder chooseDialog =new AlertDialog.Builder(this);
        chooseDialog.setTitle("Pick your choice").setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(items[which].equals("From Gallery")){

                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, RESULT_LOAD_GALLERY_IMAGE);
                }
            }
        });

        chooseDialog.show();
    }

    private byte[] readInFile(String path) throws IOException {

        byte[] data = null;
        File file = new File(path);
        InputStream input_stream = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        data = new byte[16384]; // 16K
        int bytes_read;

        while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytes_read);
        }

        input_stream.close();
        return buffer.toByteArray();
    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

           // Toast.makeText(this, " Connected to internet ", Toast.LENGTH_LONG).show();

            firstname = et1.getText().toString();
            lastname = et2.getText().toString();
            phone = et3.getText().toString();
            licence = et4.getText().toString();
            firstname = firstname.trim();
            lastname = lastname.trim();
            phone = phone.trim();
            licence = licence.trim();

            if (!firstname.isEmpty()) if (note == null) {
                byte[] image = null;
                try {
                    image = readInFile(mCurrentPhotoPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ParseObject post = new ParseObject("DriverList1");
                post.put("driverid", ParseUser.getCurrentUser());
                post.put("FirstName", firstname);
                post.put("LastName", lastname);
                post.put("Phone", phone);
                post.put("Licence", licence);
                ParseFile file = new ParseFile("picturePath", image);
                System.out.println(file+"fileeeeeeeeeeeeeeeeeeeeeeeeeimagefile");
                file.saveInBackground();
                post.put("image", "picturePath");
                post.put("ImageFile", file);
                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getBaseContext(), "You sucessfully added your driver!", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(AddDriverActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            Log.d(getClass().getSimpleName(), "Could not insert driver: " + e);
                        }

                    }
                });


            }
            return true;
        }
            else if (
                    connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                            connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

                Toast.makeText(this, " Not Internet connection , please connect to the internet ", Toast.LENGTH_LONG).show();
                return false;
            }
            return false;
        }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),Welcome1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
