package xyz.romakononovich.battleship;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.widget.FrameLayout;


/**
 * Created by romank on 24.08.17.
 */

public class CompasActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = CompasActivity.class.getSimpleName();
    private CompasView mCompasView;
    private FrameLayout mContainer;
    private int viewSize;
    private SensorManager mSensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compas);
        mContainer = (FrameLayout)findViewById(R.id.compas_container);
        mCompasView = new CompasView(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);



    int width = size.x ;
        int height = size.y ;
        if(width<height) {
            viewSize = width;
        } else {
            viewSize = height;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(viewSize,viewSize);
        params.gravity = Gravity.CENTER;
        mContainer.addView(mCompasView,params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        Log.d(TAG, "onSensorChanged: "+degree);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
