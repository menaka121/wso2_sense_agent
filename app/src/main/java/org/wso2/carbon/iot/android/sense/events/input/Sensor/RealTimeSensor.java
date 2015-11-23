package org.wso2.carbon.iot.android.sense.events.input.Sensor;

import android.hardware.SensorEvent;
import android.support.annotation.NonNull;

/**
 * Created by menaka on 11/20/15.
 *
 *
 */
public class RealTimeSensor implements Comparable {

    private String name;
    private String valueX;
    private String valueY;
    private String valueZ;

    public RealTimeSensor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueX() {
        return valueX;
    }

    public void setValueX(String valueX) {
        this.valueX = valueX;
    }

    public String getValueY() {
        return valueY;
    }

    public void setValueY(String valueY) {
        this.valueY = valueY;
    }

    public String getValueZ() {
        return valueZ;
    }

    public void setValueZ(String valueZ) {
        this.valueZ = valueZ;
    }

    @Override
    public String toString() {
        return this.valueX + ", " + valueY + ", " + valueZ;
    }

    @Override
    public int compareTo(@NonNull Object another) {

        return this.toString().contains(another.toString())?1:0;
    }
}
