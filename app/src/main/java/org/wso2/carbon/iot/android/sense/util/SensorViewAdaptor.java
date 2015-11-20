package org.wso2.carbon.iot.android.sense.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.wso2.carbon.iot.android.sense.events.input.Sensor.SensorData;

import java.util.List;

import agent.sense.android.iot.carbon.wso2.org.wso2_senseagent.R;

/**
 * Created by menaka on 11/20/15.
 */
public class SensorViewAdaptor extends BaseAdapter{


    private Context context;
    private List<SensorData> data;

    public SensorViewAdaptor(Context context, List<SensorData> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        updatelist();
        ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;

        if(convertView == null){
            view = inflater.inflate(R.layout.display_sensor_values, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.values = (TextView) view.findViewById(R.id.values);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        SensorData data = this.data.get(position);

        holder.name.setText(data.getSensorName());
        holder.values.setText(data.getSensorValues());

        return view;

    }



    private class ViewHolder{
        public TextView name;
        public TextView values;
    }
}
