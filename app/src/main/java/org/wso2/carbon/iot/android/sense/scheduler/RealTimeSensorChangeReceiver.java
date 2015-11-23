package org.wso2.carbon.iot.android.sense.scheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.wso2.carbon.iot.android.sense.constants.SenseConstants;
import org.wso2.carbon.iot.android.sense.events.input.Sensor.RealTimeSensor;
import org.wso2.carbon.iot.android.sense.events.input.Sensor.SensorData;
import org.wso2.carbon.iot.android.sense.util.SensorViewAdaptor;
import org.wso2.carbon.iot.android.sense.util.TempStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/**
 * Created by menaka on 11/20/15.
 *
 * Get the user selected sensors from shared preferences.
 * Put those to a list and return
 *
 */
public class RealTimeSensorChangeReceiver extends BroadcastReceiver {

    SensorViewAdaptor adaptor;
    public  void updateOnChange(SensorViewAdaptor adaptor){
        this.adaptor = adaptor;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SenseConstants.SELECTED_SENSORS, Context.MODE_MULTI_PROCESS);
        Set<String> selectedSet = sharedPreferences.getStringSet(SenseConstants.SELECTED_SENSORS_BY_USER, null);

//        System.out.println();
//        Set<String> names = TempStore.sensor.keySet();
//        names.retainAll(selectedSet);

        TempStore.realTimeSensors.clear();


        TempStore.realTimeSensors.addAll(TempStore.sensor.values());
//        if(TempStore.realTimeSensors.size()>0){
//            for(RealTimeSensor s : TempStore.realTimeSensors){
//                if(selectedSet.contains(s.getName())){
//                    TempStore.realTimeSensors.add(TempStore.realTimeSensors.indexOf(s), TempStore.sensor.get(s.getName()));
//                }
//            }
//
//        }else{
//           for(String s : names){
//               TempStore.realTimeSensors.add(TempStore.sensor.get(s));
//           }
//        }
//        adaptor.notifyDataSetChanged();

//        Log.i("sensor readings " + " " + TempStore.realTimeSensors.size(), TempStore.sensor.toString());
    }

}
