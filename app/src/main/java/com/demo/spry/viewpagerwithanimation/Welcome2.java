package com.demo.spry.viewpagerwithanimation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
public class Welcome2  extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome2);

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
        mFragmentTransaction.replace(R.id.containerView, new SearchHomePage()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.offer) {
                    Intent intent = new Intent(Welcome2.this, OfferRyde.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(Welcome2.this, R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(intent, bndlanimation);

                }

                if (menuItem.getItemId() == R.id.search) {
                    Intent intent1 = new Intent(Welcome2.this, BookerSearch.class);
                    Bundle bndlanimation1 = ActivityOptions.makeCustomAnimation(Welcome2.this, R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(intent1, bndlanimation1);
                }

                if (menuItem.getItemId() == R.id.addcar) {
                    Intent intent1 = new Intent(Welcome2.this, BookerAddcar.class);
                    Bundle bndlanimation1 = ActivityOptions.makeCustomAnimation(Welcome2.this, R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(intent1, bndlanimation1);
                }

                if (menuItem.getItemId() == R.id.aboutus) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new Aboutus()).commit();
                }

                if (menuItem.getItemId() == R.id.signout) {
                    BookerLogout alertdFragment = new BookerLogout();
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