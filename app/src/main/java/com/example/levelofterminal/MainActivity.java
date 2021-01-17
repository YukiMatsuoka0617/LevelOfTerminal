package com.example.levelofterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private CanvasView mCanvasView;

    private float sensorX;
    private float sensorY;
    private float sensorZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mCanvasView = new CanvasView(this);
        setContentView(mCanvasView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch(sensorEvent.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                sensorX = sensorEvent.values[0];
                sensorY = sensorEvent.values[1];
                sensorZ = sensorEvent.values[2];
                break;
        }
        mCanvasView.invalidate(sensorX, sensorY, sensorZ);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}