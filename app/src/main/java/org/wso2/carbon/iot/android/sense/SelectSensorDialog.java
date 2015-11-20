package org.wso2.carbon.iot.android.sense;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import org.wso2.carbon.iot.android.sense.constants.AvailableSensors;
import org.wso2.carbon.iot.android.sense.constants.SenseConstants;
//import org.wso2.carbon.iot.android.sense.scheduler.DataUploaderReceiver;
import org.wso2.carbon.iot.android.sense.service.SenseScheduleReceiver;

import java.util.HashSet;
import java.util.Set;


/**
 * Functionality
 *
 * Show the list of available sensors in a list
 * Get the user selections
 * Put them in to shared preferences
 *
 * */


public class SelectSensorDialog extends DialogFragment {


    protected boolean[] selections = new boolean[AvailableSensors.SUPPORTED_SENSOR_COUNT];
    private Set<String> senseorList = new HashSet<>();
    Activity activity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Sensors");
        activity = getActivity();

        SharedPreferences preferences = getActivity().getSharedPreferences(SenseConstants.AVAILABLE_SENSORS, Context.MODE_MULTI_PROCESS);

        Set<String> set= preferences.getStringSet(SenseConstants.GET_AVAILABLE_SENSORS, null);
        final CharSequence[] sequence = getSequence(set);

        final boolean[] pos = new boolean[selections.length];
        final boolean[] neg = new boolean[selections.length];


        builder.setMultiChoiceItems(sequence, selections, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    senseorList.add(sequence[which].toString());

                    pos[which] = true;
                } else {
                    senseorList.remove(sequence[which].toString());
                    neg[which] = true;
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Click", "Ok");
//                update();
                //call sensor reading class

//                SenseScheduleReceiver senseScheduleReceiver = new SenseScheduleReceiver();
//                senseScheduleReceiver.clearAbortBroadcast();
//                senseScheduleReceiver.onReceive(activity, null);


//                DataUploaderReceiver dataUploaderReceiver = new DataUploaderReceiver();
//                dataUploaderReceiver.clearAbortBroadcast();
//                dataUploaderReceiver.onReceive(activity, null);

                                sensorListListener.onDialogPositiveClick(SelectSensorDialog.this);

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                sensorListListener.onDialogPositiveClick(SelectSensorDialog.this);
                Log.d("Click", "Cancel");
                for (int i = 0; i < AvailableSensors.SUPPORTED_SENSOR_COUNT; i++) {

                    if (pos[i])
                        selections[i] = false;
                    if (neg[i])
                        selections[i] = true;
                }
            }
        });

        return builder.create();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            sensorListListener = (SensorListListener) getActivity();
        } catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must implement the SensorListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * Interface to be implemented by the parent
     * */
    public CharSequence[] getSequence(Set<String> sensorset){
        CharSequence[] seq = new CharSequence[sensorset.size()];
        String[] seq2 = sensorset.toArray(new String[sensorset.size()]);


        for(int i = 0; i<seq.length; i++){
            seq[i] = seq2[i];
        }
        return seq;
    }


    //Set the selected values to Shared Preferences to be handled in Sensor reading.
//    public boolean update(){
//        try{
//            Log.d("Update", "Set the values to SP");
//            Log.d("List", senseorList.toString());
//
//            SharedPreferences sp = getActivity().getSharedPreferences(SenseConstants.SELECTED_SENSORS, 0);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putStringSet(SenseConstants.SELECTED_SENSORS_BY_USER, senseorList);
//            editor.apply();
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }


    SensorListListener sensorListListener;

    public interface SensorListListener {
        public void onDialogPositiveClick(SelectSensorDialog dialog);

        public void onDialogNegativeClick(SelectSensorDialog dialog);
    }

    public Set<String> getSet(){
        return this.senseorList;
    }

}