package org.wso2.carbon.iot.android.sense.events.input.Sensor;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import org.wso2.carbon.iot.android.sense.constants.AvailableSensors;
import org.wso2.carbon.iot.android.sense.scheduler.RealTimeSensorChangeReceiver;
import org.wso2.carbon.iot.android.sense.util.SensorViewAdaptor;
import org.wso2.carbon.iot.android.sense.util.TempStore;

import java.util.zip.Adler32;

/**
 * Created by menaka on 11/20/15.
 *
 * Put data in to a map
 *
 * */
public class RealTimeSensorReader implements SensorEventListener {

    private Context context;
    private SensorViewAdaptor adaptor;
    public RealTimeSensorReader(Context context, SensorViewAdaptor adaptor) {
        this.context = context;
        this.adaptor = adaptor;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String values = event.values[0] + ", " + event.values[1] + ", " + event.values[2];
        RealTimeSensor realTimeSensor = new RealTimeSensor();
        realTimeSensor.setName(AvailableSensors.getType(event.sensor.getType()).toUpperCase());

        realTimeSensor.setValueX(event.values[0]+"");
        realTimeSensor.setValueY(event.values[1]+"");
        realTimeSensor.setValueZ(event.values[2]+"");

        System.out.println(values);

        TempStore.sensor.put(AvailableSensors.getType(event.sensor.getType()), realTimeSensor);

        Intent intent = new Intent();
        intent.setAction("sensor");
        context.sendBroadcast(intent);

        adaptor.notifyDataSetChanged();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
