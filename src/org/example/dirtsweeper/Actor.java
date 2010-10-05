package org.example.dirtsweeper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Actor {

	protected Canvas canvas;
	protected float x;
	protected float y;
	protected float radius;
	protected Paint skin;
	protected int color;
	
	public Actor(float x, float y, float radius, int color) { 
		this.x = x; 
		this.y = y;
		this.radius = radius;
		this.color = color;
		this.skin = new Paint(Paint.ANTI_ALIAS_FLAG);
		this.skin.setColor(this.color);
	}
	
	/**
	 *  drawMe function
	 *  
	 *  draws a circle, this is meant to be over-written
	 */
	public void drawMe(Canvas c) {
		c.drawCircle(this.x, this.y, this.radius, this.skin);
	}
	
	public void moveMe(View view, float accelX, float accelY) {
		// you need to over-ride this one yourself suckas
	}

}
