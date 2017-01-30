package com.demo.spry.viewpagerwithanimation;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private List<DriverFulldetail> driverdetaillist = null;
    private ArrayList<DriverFulldetail> arraylist;

    public ListViewAdapter(Context context, List<DriverFulldetail> driverdetaillist) {
        this.context = context;
        this.driverdetaillist = driverdetaillist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<DriverFulldetail>();
        this.arraylist.addAll(driverdetaillist);
        imageLoader = new ImageLoader(context);
    }

    public class ViewHolder {
        TextView fname;
        TextView lname;
        TextView licence;
        TextView phone;
        ImageView drimage;
    }

    @Override
    public int getCount() {
        return driverdetaillist.size();
    }

    @Override
    public Object getItem(int position) {
        return driverdetaillist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.driver_detail_item, null);
            holder.fname = (TextView) view.findViewById(R.id.fname);
            holder.lname = (TextView) view.findViewById(R.id.lname);
            holder.licence = (TextView) view.findViewById(R.id.licence);
            holder.phone = (TextView) view.findViewById(R.id.phone);
            holder.drimage = (ImageView) view.findViewById(R.id.drimage);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.fname.setText(driverdetaillist.get(position).getFname());
        holder.lname.setText(driverdetaillist.get(position).getLname());
        holder.licence.setText(driverdetaillist.get(position).getLicence());
        holder.phone.setText(driverdetaillist.get(position).getPhone());
        imageLoader.DisplayImage(driverdetaillist.get(position).getDrimage(),holder.drimage);
        return view;
    }

}
