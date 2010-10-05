package org.example.dirtsweeper;

import java.util.ArrayList;

import android.view.View;

public class Sweeper extends Actor {
	public float velocityX;
	public float velocityY;
	private ArrayList<Coordinate> coordinates;
	private ArrayList<float[]> coordinatesf;

	class Coordinate {
		private float x;
		private float y;
		private float location[] = new float[2];
		
		public Coordinate(float x, float y) {
			this.y = y;
			this.location[0] = x;
			this.location[1] = y;
		}
		
		public void setX(float x) {
			this.x = x;
			this.location[0] = x;
		}
		
		public void setY(float y) {
			this.y = y;
			this.location[1] = y;
		}
			
		public float[] getCoords() {
			return location;
			
		}
	}	
	
	public Sweeper(float x, float y, float radius, int color) {
		super(x, y, radius, color);
		this.velocityX = 0.0f;
		this.velocityY = 0.0f;
	}

	// the move me method will actually work here.
	// it's pretty straight forward. we figure out the
	// x and y acceleration and where the wall is.
	// we try to move it as long as we're not running
	// into the wall
	@Override
	public void moveMe(View view, float accelX, float accelY) {
		
		this.accelerate(accelX, accelY);
		int width = view.getWidth();
		int height = view.getHeight();
		float x = this.x;
		float y = this.y;
		x += accelX;
		y += accelY;
		
		if (width > x || width < 0) {
			return;
		}
		
		if (height > y || height < 0) {
			return;
		}
		
		this.x = x;
		this.y = y;
		
		// use the float array
		float[] location = new float[2];
		location[0] = this.x;
		location[1] = this.y;
		coordinatesf.add(location);
		
		coordinates.add(new Coordinate(x,y));

	}
	
	// accelerate
	public void accelerate(float accelX, float accelY) {
		if (accelX == accelY) {
			return;
		} else if (accelX > accelY) {
			this.velocityX += accelX;
		} else {
			this.velocityY += accelY;
		}
	}
	
	public ArrayList<float[]> getCoords() {
		return this.coordinatesf;
	}
	
}
