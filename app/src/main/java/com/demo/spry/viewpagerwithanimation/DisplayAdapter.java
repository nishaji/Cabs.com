package com.demo.spry.viewpagerwithanimation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sprydev5 on 12/1/16.
 */
public class DisplayAdapter extends ArrayAdapter<TripAdd> {

    Context context;
    int layoutResourceId;
    ArrayList<TripAdd> students = new ArrayList<TripAdd>();

    public DisplayAdapter(Context context, int layoutResourceId,
                          ArrayList<TripAdd> studs) {
        super(context, layoutResourceId, studs);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.students = studs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        StudentWrapper StudentWrapper = null;

        if (item == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            item = inflater.inflate(layoutResourceId, parent, false);
            StudentWrapper = new StudentWrapper();
            StudentWrapper.name = (TextView) item.findViewById(R.id.textName);
            StudentWrapper.age = (TextView) item.findViewById(R.id.textAge);
            StudentWrapper.address = (TextView) item.findViewById(R.id.textAddr);
            StudentWrapper.edit = (Button) item.findViewById(R.id.btnEdit);
            StudentWrapper.delete = (Button) item.findViewById(R.id.btnDelete);
            item.setTag(StudentWrapper);
        } else {
            StudentWrapper = (StudentWrapper) item.getTag();
        }

        TripAdd student = students.get(position);
        StudentWrapper.name.setText(student.getDriver());
        StudentWrapper.age.setText(student.getCar());
        StudentWrapper.address.setText(student.getRupay());

        StudentWrapper.edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Edit", Toast.LENGTH_LONG).show();
            }
        });

        StudentWrapper.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show();
            }
        });

        return item;

    }

    static class StudentWrapper {
        TextView name;
        TextView age;
        TextView address;
        Button edit;
        Button delete;
    }}