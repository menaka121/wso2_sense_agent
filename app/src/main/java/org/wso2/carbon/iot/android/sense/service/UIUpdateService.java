package org.wso2.carbon.iot.android.sense.service;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import org.wso2.carbon.iot.android.sense.events.input.DataReader;
import org.wso2.carbon.iot.android.sense.events.input.Sensor.SensorData;
import org.wso2.carbon.iot.android.sense.util.DataMap;
import org.wso2.carbon.iot.android.sense.util.SensorViewAdaptor;

/**
 * Created by menaka on 11/19/15.
 */
public class UIUpdateService {

    private Handler uHandler;
    private Runnable runnable;
    int count = 0;
    public SensorViewAdaptor adaptor;

    public UIUpdateService(final SensorViewAdaptor adaptor1, final Activity context) {

        this.adaptor = adaptor1;
        this.uHandler = new Handler();


        Thread updui = new Thread() {
            public void run() {
                while (true) {
                    uHandler.post(runnable);
                    try {
                        sleep(10);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adaptor1.notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        updui.start();



    }







}
