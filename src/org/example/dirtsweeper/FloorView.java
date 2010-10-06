package org.example.dirtsweeper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;
import android.view.animation.AnimationUtils;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;

public class FloorView extends View { // implements SensorEventListener {
	private static final String TAG = "DirtSweeper";
	private final Game game;
	private float width;
	private float height;
	private Sweeper sweeper;
	private ArrayList<Actor> actors;
	private int selX;
	private int selY;
    // sensor manager used to control the accelerometer sensor.

	
    private final Rect selRect = new Rect();
	private Random rand;

	private SensorManager mgr;
    private float accelX = 0.0f;
    private float accelY = 0.0f;
    // http://code.google.com/android/reference/android/hardware/SensorManager.html#SENSOR_ACCELEROMETER
    // for an explanation on the values reported by SENSOR_ACCELEROMETER.
	private final SensorEventListener accelerometer = new SensorEventListener() {

		// we don't really care for this example, but
        // reports when the accuracy of sensor has change
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
	        // SENSOR_STATUS_ACCURACY_HIGH = 3
	        // SENSOR_STATUS_ACCURACY_LOW = 1
	        // SENSOR_STATUS_ACCURACY_MEDIUM = 2
	        // SENSOR_STATUS_UNRELIABLE = 0 //calibration required.		
		}

		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
        	accelX = event.values[0];
        	accelY = event.values[1];
    		Log.d(TAG, "onSensorChanged: x " + accelX + ", y " + accelY);
		}
    };
	
	public FloorView(Context context) {
		super(context);
		this.game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		rand = new Random();
        mgr = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        registerListener();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w / 5f;
		height = h / 5f;
		float circleWidth = width/3f;
		getRect(selX, selY, selRect);
		Log.d(TAG, "onSizeChanged: width " + width + ", height " + height);
		super.onSizeChanged(w, h, oldw, oldh);
		this.actors = new ArrayList<Actor>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (rand.nextInt(10) > 5) {
					this.actors.add(new Dirt(width*(i+.5f), height*(j+.5f), circleWidth, Color.rgb(184, 134, 10)));
				}
			}
		}
		this.actors.add(new Mouse(width*4.5f, height*4.5f, circleWidth, Color.GRAY));
		this.actors.add(new Mouse(width*2.5f, height*1.5f, circleWidth, Color.GRAY));
		this.sweeper = new Sweeper(width*2.5f, height*2.5f, circleWidth, Color.BLACK);
		this.actors.add(this.sweeper);
	}
	
	private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * width), (int) (y * height), (int) (x * width + width), (int) (y * height + height));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		// Draw the background
		Paint background = new Paint();
		background.setColor(Color.BLUE);
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);
		
		// Draw the board...
		Paint dark = new Paint();
		dark.setColor(Color.DKGRAY);
		
		Paint hilite = new Paint();
		hilite.setColor(Color.RED);
		
		Paint light = new Paint();
		light.setColor(Color.YELLOW);
		
		for (int i = 0; i < 5; i++ ) {
			canvas.drawLine(0, i * height, getWidth(), i * height, light);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
			canvas.drawLine(i * width, 0, i * width, getHeight(), light);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
		}
		
		// loop through the actors and draw them 
		for (Actor actor : actors) {
			actor.drawMe(canvas);
		}

		// loop through the actors and move them 
		for (Actor actor : actors) {
			actor.moveMe(this, accelX, accelY);
		}

		// clear the canvas and restart
		invalidate();
	}
	
	public void registerListener() {
		mgr.registerListener(accelerometer, mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	}

	public void unregisterListener() {
		mgr.unregisterListener(accelerometer);
		
	}
	
	public void onResume() {
		registerListener();
	}

	public void onPause() {
		unregisterListener();
	}
	
}

