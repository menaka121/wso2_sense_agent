package org.wso2.carbon.iot.android.sense.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import org.wso2.carbon.iot.android.sense.constants.AvailableSensors;
import org.wso2.carbon.iot.android.sense.constants.SenseConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by menaka on 11/18/15.
 */
public class SupportedSensors {

    private Context context;
    private SharedPreferences sensorPreference;
    private AvailableSensors availableSensors = new AvailableSensors();
    private SensorManager mSensorManager;

    public SupportedSensors(Context context){
        this.context = context;
        this.sensorPreference = context.getSharedPreferences(SenseConstants.AVAILABLE_SENSORS, 0);
        this.mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void setContent(){
        List<String> sensor_List = AvailableSensors.getList();
        Set<String> sensorSet = new HashSet<>();
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for (String sen : sensor_List){
            if(sensors.contains(mSensorManager.getDefaultSensor(AvailableSensors.getType(sen.toLowerCase())))){
                sensorSet.add(sen);
            }
        }

        SharedPreferences.Editor editor = this.sensorPreference.edit();
        editor.putStringSet(SenseConstants.GET_AVAILABLE_SENSORS, sensorSet);
        editor.apply();
    }



}
