package com.exercise;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class AndroidProximitySensorActivity extends Activity {
    /** Called when the activity is first created. */
	 TextView ProximitySensor, ProximityMax, ProximityReading;
	 
	 SensorManager mySensorManager;
	 Sensor myProximitySensor;
	 

     public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
	     setContentView(R.layout.main);

	     // TextViews to hold sensor information on the screen
	     ProximitySensor = (TextView)findViewById(R.id.proximitySensor);
	     ProximityMax = (TextView)findViewById(R.id.proximityMax);
	     ProximityReading = (TextView)findViewById(R.id.proximityReading);
	     
	     // Globla service SensorManager and Proximity sensor
	     mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	     myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	     
	     // Test where phone has or has not a proximity sensor
	     if (myProximitySensor == null){
	    	 ProximitySensor.setText("No Proximity Sensor!"); 
	     } else {
	         ProximitySensor.setText(myProximitySensor.getName());
	         ProximityMax.setText("Maximum Range: "
	           + String.valueOf(myProximitySensor.getMaximumRange()));
	         mySensorManager.registerListener(proximitySensorEventListener,
	           myProximitySensor,
	           SensorManager.SENSOR_DELAY_NORMAL);
	     }
     }

     // Listen for broadcasts from proximity sensor (i.e. every time it changes reading status)
     SensorEventListener proximitySensorEventListener = new SensorEventListener() {

		// Called whenever the registered sensor changes its state
		public void onSensorChanged(SensorEvent event) {

			 if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
				 ProximityReading.setText("Proximity Sensor Reading:"
				 + String.valueOf(event.values[0]));
			 }
		}

		// Accuracy is not really necessary, we just looking for status changing
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub

		}
	 };
}