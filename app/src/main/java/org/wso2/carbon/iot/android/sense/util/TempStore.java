package org.wso2.carbon.iot.android.sense.util;

import org.wso2.carbon.iot.android.sense.events.input.Sensor.RealTimeSensor;
import org.wso2.carbon.iot.android.sense.events.input.Sensor.SensorData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created by menaka on 11/20/15.
 */
public class TempStore {

    public static ConcurrentMap<String, RealTimeSensor> sensor = new ConcurrentHashMap<>();
    public static ArrayList<RealTimeSensor> realTimeSensors = new ArrayList<>();

    public static ConcurrentSkipListSet<RealTimeSensor> sensors = new ConcurrentSkipListSet<>();

}
