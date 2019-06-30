package com.example.myhighereducationofcomplexbam002;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myhighereducationofcomplexbam002.Adapter.FacultyAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String from="1";

    RecyclerView recyclerView;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("دانشکده های آموزش عالی بم");

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


/*
        dbHandler dbh = new dbHandler(this);
        dbh.open();
        Toast.makeText(getApplicationContext(),dbh.get_Name(3),Toast.LENGTH_LONG).show();
        dbh.close();
*/
        recyclerView = findViewById(R.id.rec);
        dbHandler dbh = new dbHandler(this);
        dbh.open();
        FacultyAdapter facultyAdapter = new FacultyAdapter(this,dbh.display_faculty());
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(facultyAdapter);
        dbh.close();






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

    @Override
    protected void onResume() {
        from="1";
        super.onResume();

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sarch) {
            //Toast.makeText(getApplicationContext(),"جستجو",Toast.LENGTH_LONG).show();
            search();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            // Handle the camera action
        }else if (id == R.id.nav_professor) {

            startActivity(new Intent(MainActivity.this,ProfessorsActivity.class));

        }else if (id == R.id.nav_web) {

            String url = "http://www.bam.ac.ir";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

        }
        /*
        else if (id == R.id.) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void search(){
        View alertLayout = LayoutInflater.from(this).inflate(R.layout.alert_search,null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        refreshSearch(alertLayout);
        dialog =alert.create();
        dialog.show();

    }

    public void refreshSearch(View m){

        final RecyclerView recyclerView = m.findViewById(R.id.rec_alert_search);
        final EditText editText = m.findViewById(R.id.edt_alert_search);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHandler dbh = new dbHandler(this);
        dbh.open();
        from="2";
        FacultyAdapter facultyAdapter = new FacultyAdapter(this,dbh.display_professor_list());
        dbh.close();
        recyclerView.setAdapter(facultyAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                dbHandler dbh = new dbHandler(MainActivity.this);
                dbh.open();
                if(dbh.isDisplay(editText.getText().toString())){
                    from="2";
                    FacultyAdapter facultyAdapter = new FacultyAdapter(MainActivity.this,dbh.display_search(editText.getText().toString()));
                    dbh.close();
                    recyclerView.setAdapter(facultyAdapter);
                }
                dbh.close();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


}
