package com.happyiterating.lightsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mSensorStatus;
    TextView mSensorValue;
    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorStatus = (TextView) findViewById(R.id.sensor_status);
        mSensorValue = (TextView) findViewById(R.id.sensor_value);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (lightSensor != null) {
            mSensorManager.registerListener(mSensorEventLister, lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    private final SensorEventListener mSensorEventLister = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
                mSensorStatus.setText("light sensor change");
                mSensorValue.setText("value = " + sensorEvent.values[0]);
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };


    public native String stringFromJNI();
    static {
        System.loadLibrary("native-lib");
    }
}
