package org.wso2.carbon.iot.android.sense;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.wso2.carbon.iot.android.sense.constants.SenseConstants;
import org.wso2.carbon.iot.android.sense.events.input.Sensor.SensorDataReader;
import org.wso2.carbon.iot.android.sense.register.SenseDeEnroll;
import org.wso2.carbon.iot.android.sense.service.SenseScheduleReceiver;
import org.wso2.carbon.iot.android.sense.service.UIUpdateService;
import org.wso2.carbon.iot.android.sense.util.DataMap;
import org.wso2.carbon.iot.android.sense.util.SensorViewAdaptor;
import org.wso2.carbon.iot.android.sense.util.TempStore;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import agent.sense.android.iot.carbon.wso2.org.wso2_senseagent.R;


/**
 * Activity for selecting sensors available in the device
 *
 * */

public class ActivitySelectSensor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SelectSensorDialog.SensorListListener{

    SharedPreferences sp;
    SelectSensorDialog dialog = new SelectSensorDialog();
    Set<String> senseorList = new HashSet<>();
    FloatingActionButton fab;
    FloatingActionButton add;
    ListView listView;
    TextView textView;
    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_select_sensor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        textView = (TextView) findViewById(R.id.data2);
        listView = (ListView) findViewById(R.id.senseListContainer);
        //Publish data
        fab = (FloatingActionButton) findViewById(R.id.publish);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Publishing data started", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                check = true;
                //Call the stop publish button

            }
        });

        add = (FloatingActionButton) findViewById(R.id.addSensors);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getFragmentManager(), "Sensor List");
            }
        });

        sp = getSharedPreferences(SenseConstants.SELECTED_SENSORS, 0);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.parentContainer);

//        if(senseorList.size()<1){
//            Button bt = new Button(this);
//            bt.setText("Select your Sensors");
//            bt.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
//            layout.addView(bt);
//
//            bt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.show(getFragmentManager(), "Sensor List");
//                }
//            });
//        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.activity_select_sensor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SenseDeEnroll.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.select) {
            // Handle the camera action
//            SharedPreferences preferences = getSharedPreferences("Sensors", 0);
//            Set<String> set= preferences.getStringSet("sensors", null);
//            Toast.makeText(getApplicationContext(), set!=null?set.toString():"nothing", Toast.LENGTH_SHORT).show();
            dialog.show(getFragmentManager(), "Sensor List");



        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDialogPositiveClick(SelectSensorDialog dialog) {
        System.out.println(dialog.getSet().toString());
        senseorList = dialog.getSet();
        update();


        List<String> list = Arrays.asList(senseorList.toArray(new String[senseorList.size()]));

//        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adaptor);

//        SenseScheduleReceiver senseScheduleReceiver = new SenseScheduleReceiver();
//        senseScheduleReceiver.clearAbortBroadcast();
//        senseScheduleReceiver.onReceive(this, null);

        SensorDataReader reader = new SensorDataReader(this);
        new Thread(reader).start();


        final SensorViewAdaptor adaptor1 = new SensorViewAdaptor(this, TempStore.list);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                adaptor1.notifyDataSetChanged();
            }
        });

//        UIUpdateService su = new UIUpdateService(adaptor1, this);
        listView.setAdapter(adaptor1);




//        new Thread().start();



    }

    @Override
    public void onDialogNegativeClick(SelectSensorDialog dialog) {

    }

    public boolean update(){
        try{
            Log.d("Update", "Set the values to SP");
            Log.d("List", senseorList.toString());

//            SharedPreferences sp = getSharedPreferences(SenseConstants.SELECTED_SENSORS, 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putStringSet(SenseConstants.SELECTED_SENSORS_BY_USER, senseorList);
            editor.apply();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
