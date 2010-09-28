package org.example.dirtsweeper;

import android.graphics.Canvas;
import android.graphics.Color;

public class Mouse extends Actor {

	public Mouse(float x, float y, float radius, int color) {
		super(x, y, radius, color);
	}
	
	public void drawMe(Canvas c) {

		// draw body
		c.drawCircle(this.x, this.y, this.radius, this.skin);
		
		// draw tail
		this.skin.setStrokeWidth(5);
		this.skin.setColor(Color.BLACK);
		c.drawLine(this.x, this.y, this.x + this.radius + 3, this.y + this.radius + 5, this.skin);

		// reset things
		this.skin.setStrokeWidth(0);
		this.skin.setColor(this.color);
		
		// draw nose and eyes
		this.skin.setColor(Color.BLACK);
		c.drawCircle(this.x, this.y - this.radius, ( this.radius / 5 ), this.skin);
		c.drawCircle(this.x - (this.radius / 5.5f), this.y - this.radius + ( this.radius / 3 ), ( this.radius / 10 ), this.skin);
		c.drawCircle(this.x + (this.radius / 5.5f), this.y - this.radius + ( this.radius / 3 ), ( this.radius / 10 ), this.skin);
		
		// reset things
		this.skin.setStrokeWidth(0);
		this.skin.setColor(this.color);

	}	
	
	public void moveMe() {
		
	}

}
