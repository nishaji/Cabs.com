package com.demo.spry.viewpagerwithanimation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
/**
 * Created by sprydev5 on 5/12/15.
 */
public class Welcome1  extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    FragmentManager fm = getSupportFragmentManager();
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome1);
        SharedPreferences email = getSharedPreferences(LoginActivity.PREFS, 0);
        id = email.getString("email", "email");
        System.out.println(id+"emailonwelcome");
        Fragment fragment = new Fragment();
        final Bundle bundle = new Bundle();
        bundle.putString("user_name", id);
        fragment.setArguments(bundle);

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


                if (menuItem.getItemId() == R.id.home) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();

                }

                if (menuItem.getItemId() == R.id.carlist) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new CarList()).commit();
                }
                if (menuItem.getItemId() == R.id.driverlist) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new DriversList()).commit();

                }

                if (menuItem.getItemId() == R.id.triplist) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new Trips()).commit();
                }
                if (menuItem.getItemId() == R.id.tripdetail) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Tripdetail()).commit();

                }
                if (menuItem.getItemId() == R.id.driverdetail) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new DriverdetailActivity()).commit();

                }

                if (menuItem.getItemId() == R.id.aboutus) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new Aboutus()).commit();
                }
                if (menuItem.getItemId() == R.id.offer) {
                    Intent intent = new Intent(Welcome1.this, OfferRyde.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(Welcome1.this, R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(intent, bndlanimation);

                }

                if (menuItem.getItemId() == R.id.search) {
                    Intent intent1 = new Intent(Welcome1.this, HomeFragment.class);
                    Bundle bndlanimation1 = ActivityOptions.makeCustomAnimation(Welcome1.this, R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(intent1, bndlanimation1);
                }
                if (menuItem.getItemId() == R.id.bookinghistory) {
                    Intent intent2 = new Intent(Welcome1.this, Bookinghistory1.class);
                    Bundle bndlanimation2 = ActivityOptions.makeCustomAnimation(Welcome1.this, R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(intent2, bndlanimation2);

                }
                if (menuItem.getItemId() == R.id.signout) {
                    AlertDFragment alertdFragment = new AlertDFragment();
                    alertdFragment.show(fm, "Alert Dialog Fragment");
                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }
    @Override
    public void onBackPressed() {
      super.finish();
    }
}