package com.example.myhighereducationofcomplexbam002;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myhighereducationofcomplexbam002.Fragment.FragmentAdapter;
import com.example.myhighereducationofcomplexbam002.Fragment.ListFragment;

public class ProfessorsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("اساتید دانشکده ها");

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        init();

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    public void init(){

        ViewPager viewPager = findViewById(R.id.viewpager);

        if(viewPager !=null){
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }


    public void setupViewPager(ViewPager viewPager){


        int facultCount = 0;
        dbHandler dbh = new dbHandler(this);
        dbh.open();
        facultCount=dbh.facultyCount();
        //Toast.makeText(getApplicationContext(),""+dbh.facultyCount(),Toast.LENGTH_LONG).show();

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        ListFragment[] listFragments = new ListFragment[facultCount];

        for(int i=0;i<facultCount;i++){

            Bundle bundle = new Bundle();
            int m= i+1;
            bundle.putString("FRG",""+m);
            listFragments[i] = new ListFragment();
            listFragments[i].setArguments(bundle);
            fragmentAdapter.addFragment(listFragments[i],dbh.get_faculty_name(""+m));

        }


        dbh.close();

/*
        ListFragment cmp = new ListFragment();
        Bundle bundleCmp = new Bundle();
        bundleCmp.putString("FRG","COMP");
        cmp.setArguments(bundleCmp);

        ListFragment math = new ListFragment();
        Bundle bundleMath = new Bundle();
        bundleMath.putString("FRG","MATH");
        math.setArguments(bundleMath);

        fragmentAdapter.addFragment(cmp,"فن آوری اطلاعات");
        fragmentAdapter.addFragment(math,"ریاضی");
*/
        viewPager.setAdapter(fragmentAdapter);
        viewPager.computeScroll();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.professors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_like) {
            Toast.makeText(getApplicationContext(),"فهرست علاقه مندی ها",Toast.LENGTH_LONG).show();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
