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
		
		int dwidth = view.getWidth();
		int dheight = view.getHeight();
		float dx = this.x + this.velocityX;
		float dy = this.y + this.velocityY;

		if (dx > dwidth || dwidth < 0) {
			dx = this.x;
		}
		
		if (dy > dheight || dheight < 0) {
			dy = this.y;
		}
				
		this.x = dx;
		this.y = dy;

		/*
		// use the float array
		float[] location = new float[2];
		location[0] = this.x;
		location[1] = this.y;
		coordinatesf.add(location);
		
		coordinates.add(new Coordinate(x,y));
		*/
	}
	
	// accelerate
	public void accelerate(float accelX, float accelY) {
		this.velocityX = -accelX;
		this.velocityY = accelY;
	}
	
	public ArrayList<float[]> getCoords() {
		return this.coordinatesf;
	}
	
}
