package org.example.dirtsweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.graphics.PointF;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class Sweeper extends Actor {
	private final Boolean ACCELEROMETER = true;
	public float velocityX;
	public float velocityY;
	public View floor;
	public ArrayList<PointF> coordinates;
	private int gameID;
	
	public Sweeper(float x, float y, float radius, int color, View view) {
		super(x, y, radius, color);
		this.velocityX = 3.0f;
		this.velocityY = 2.5f;
		this.coordinates = new ArrayList<PointF>();
		this.gameID = 0;
		this.floor = view;
		
		// startGameTask will update the game ID
		new NewGameTask().execute(this);
	}	

	// the move me method will actually work here.
	// it's pretty straight forward. we figure out the
	// x and y acceleration and where the wall is.
	// we try to move it as long as we're not running
	// into the wall
	@Override
	public void moveMe(View view, float accelX, float accelY) {
		Log.d("Sweeper", "moveMe");

		if (ACCELEROMETER) {
			this.accelerate(accelX, accelY);
		}
		
		int width = view.getWidth();
		int height = view.getHeight();
		float newX = this.x + this.velocityX;
		float newY = this.y + this.velocityY;

		if (newX > width || newX < 0) {
			if (!ACCELEROMETER) {
				this.velocityX = -this.velocityX;
			}
			newX = this.x;
		}
		
		if (newY > height || newY < 0) {
			if (!ACCELEROMETER) {
				this.velocityY = -this.velocityY;
			}
			newY = this.y;
		}
				
		this.x = newX;
		this.y = newY;
		
		Log.d("Sweeper", "size: " + this.coordinates.size());
		synchronized(this.coordinates) {
			this.coordinates.add(new PointF(this.x, this.y));
			if (this.coordinates.size() > 150) {
				 new PostDataTask().execute(new GameFrame(this.gameID, (ArrayList<PointF>) coordinates.clone()));
				 this.coordinates.clear();
			}
		}
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public ArrayList<PointF> getCoords() {
		return this.coordinates;
	}
	
	// accelerate
	public void accelerate(float accelX, float accelY) {
		this.velocityX = -accelX;
		this.velocityY = accelY;
	}
	
}
