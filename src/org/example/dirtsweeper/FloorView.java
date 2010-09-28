package org.example.dirtsweeper;

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

public class FloorView extends View {
	private static final String TAG = "DirtSweeper";
	private final Game game;
	private float width;
	private float height;
	private float sweeperX;
	private float sweeperY;
	private float sweeperWidth;
	private float sweeperVelocity;
	private Sweeper sweeper;
	private ArrayList<Actor> actors;
	private int selX;
	private int selY;
	private final Rect selRect = new Rect();
	private Random rand;
	
	public FloorView(Context context) {
		super(context);
		this.game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.rand = new Random();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w / 5f;
		height = h / 5f;
		this.sweeperX = width*2.5f;
		this.sweeperY = height*2.5f;
		this.sweeperWidth = width/3f;
		this.sweeperVelocity = 1;
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
		this.sweeper = new Sweeper(width*2.5f, height*2.5f, circleWidth, Color.BLACK, 1);
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
			actor.moveMe();
			actor.drawMe(canvas);
		}

		// move the sweeper
		this.sweeper.x += this.sweeper.velocity;
		if (this.sweeper.x < 0) {
			this.sweeper.velocity = 1;
		} else if  (this.sweeper.x > getWidth()) {
			this.sweeper.velocity = -1;
		}

		// clear the canvas and restart
		invalidate();
	}

}

