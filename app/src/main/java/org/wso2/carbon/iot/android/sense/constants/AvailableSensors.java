package org.wso2.carbon.iot.android.sense.constants;

/**
 * Created by menaka on 11/18/15.
 */

import android.hardware.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * class to store the supported sensor types.
 */
public class AvailableSensors {

    private static List<String> sensorList = new ArrayList<>();
    public static int SUPPORTED_SENSOR_COUNT = 10;

    //List of supported sensors by the system
    public static List<String> getList() {
        sensorList.add("Accelerometer");
        sensorList.add("Magnetometer");
        sensorList.add("Gravity");
        sensorList.add("Rotation Vector");
        sensorList.add("Pressure");
        sensorList.add("Light");
        sensorList.add("Gyroscope");
        sensorList.add("Proximity");
        return sensorList;
    }



    //Get the int type of the sensor
    public static int getType(String sensor) {
        int type = 1;
        switch (sensor) {
            case "accelerometer":
                type = Sensor.TYPE_ACCELEROMETER;
                break;
            case "magnetometer":
                type = Sensor.TYPE_MAGNETIC_FIELD;
                break;
            case "gravity":
                type = Sensor.TYPE_GRAVITY;
                break;
            case "rotationVector":
                type = Sensor.TYPE_ROTATION_VECTOR;
                break;
            case "pressure":
                type = Sensor.TYPE_PRESSURE;
                break;
            case "gyroscope":
                type = Sensor.TYPE_GYROSCOPE;
                break;
            case "light":
                type = Sensor.TYPE_LIGHT;
                break;
            case "proximity":
                type = Sensor.TYPE_PROXIMITY;

        }
        return type;
    }

    //Get the string type of te sensor
    public static String getType(int type){
        String s = "";
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER:
                s = "accelerometer";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                s = "magnetometer";
                break;
            case Sensor.TYPE_GRAVITY:
                s = "gravity";
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                s = "rotationVector";
                break;
            case Sensor.TYPE_PRESSURE:
                s = "pressure";
                break;
            case Sensor.TYPE_GYROSCOPE:
                s = "gyroscope";
                break;
            case Sensor.TYPE_LIGHT:
                s = "light";
                break;
            case Sensor.TYPE_PROXIMITY:
                s = "proximity";

        }
        return s;
    }

}
