package org.example.dirtsweeper;

public class Sweeper extends Actor {
	public int velocity;
	
	public Sweeper(float x, float y, float radius, int color, int velocity) {
		super(x, y, radius, color);
		this.velocity = velocity;
	}

}
