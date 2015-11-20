package org.wso2.carbon.iot.android.sense.util;

import org.wso2.carbon.iot.android.sense.events.input.Sensor.SensorData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by menaka on 11/20/15.
 */
public class TempStore {

    public static  CopyOnWriteArrayList<SensorData> list = new CopyOnWriteArrayList<>();

    public static CopyOnWriteArrayList<SensorData> getList() {
        return list;
    }

    public static synchronized void setList(SensorData data) {
        if(TempStore.list.size()!=0){

            for(int i = 0; i<TempStore.list.size(); i++){
                if(TempStore.list.get(i).getSensorName().equals(data.getSensorName())){
                    TempStore.list.add(i, data);
                }else{
                    TempStore.list.add(data);
                }
            }
        }else{
            TempStore.list.add(data);
        }
    }
}
