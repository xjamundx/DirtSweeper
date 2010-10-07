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
	public float velocityX;
	public float velocityY;
	public ArrayList<PointF> coordinates;
	public int gameID;
	
	public Sweeper(float x, float y, float radius, int color) {
		super(x, y, radius, color);
		this.velocityX = 0.0f;
		this.velocityY = 0.0f;
		this.coordinates = new ArrayList<PointF>();
		this.gameID = 99;
	}

	// the move me method will actually work here.
	// it's pretty straight forward. we figure out the
	// x and y acceleration and where the wall is.
	// we try to move it as long as we're not running
	// into the wall
	@Override
	public void moveMe(View view, float accelX, float accelY) {
		Log.d("Sweeper", "moveMe");

		this.accelerate(accelX, accelY);
		
		int width = view.getWidth();
		int height = view.getHeight();
		float dx = this.x + this.velocityX;
		float dy = this.y + this.velocityY;

		if (dx > width || dx < 0) {
			dx = this.x;
		}
		
		if (dy > height || dy < 0) {
			dy = this.y;
		}
				
		this.x = dx;
		this.y = dy;
		
		Log.d("Sweeper", "size: " + this.coordinates.size());
		synchronized(this.coordinates) {
			this.coordinates.add(new PointF(this.x, this.y));
			if (this.coordinates.size() > 75) {
				 new WebServicesTask().execute(new GameFrame(gameID, coordinates));
				 this.coordinates.clear();
			}
		}
	}
	
	private class GameFrame {
		public int gameID;
		public ArrayList<PointF> coords;
		
		public GameFrame(int gameID, ArrayList<PointF> coords) {
			this.coords = coords;
			this.gameID = gameID;
		}
		
	}
	
	public ArrayList<PointF> getCoords() {
		return this.coordinates;
	}
	
	// accelerate
	public void accelerate(float accelX, float accelY) {
		this.velocityX = -accelX;
		this.velocityY = accelY;
	}

	private class WebServicesTask extends AsyncTask<GameFrame, Integer, Long > {
		
		private static final String TAG = "WebServicesTask";
		
		@Override
		protected Long doInBackground(GameFrame...frame) {
			Log.d(TAG, "do it in the background");
			boolean succcess = this.postData(frame[0]);
			return null;
		} 

		private boolean postData(GameFrame myFrame) {
			Log.d(TAG, "post, data");
			
		    // Create a new HttpClient and Post Header
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost("http://www.touchenabledweb.com/games/add.json");
		    try {
		   
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		        nameValuePairs.add(new BasicNameValuePair("data[GameTimer][0][game_id]", String.valueOf(myFrame.gameID)));
		        /*
		        int i = 0;
		        for (PointF p : myFrame.coords) {
			        nameValuePairs.add(new BasicNameValuePair("data[GameTimer]["+i+"][game_id]", String.valueOf(myFrame.myGameID)));
			        nameValuePairs.add(new BasicNameValuePair("data[GameTimer]["+i+"][x]", String.valueOf(myFrame.p.x)));
			        nameValuePairs.add(new BasicNameValuePair("data[GameTimer]["+i+"][y]", String.valueOf(myFrame.p.y)));
			        i++;
		    	}
		    	*/
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        
		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);
		        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF_8"));
		        StringBuilder builder = new StringBuilder();
		        for (String line = null; (line = reader.readLine()) != null;) {
		            builder.append(line).append("\n");
		        }
		        String json = builder.toString();
		        JSONTokener tokener = new JSONTokener(json);
		        try {
					JSONArray finalResult = new JSONArray(tokener);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		        Log.d(TAG, "This is your response (JSON)" + json);
		        Log.d(TAG, "This is your response " + response.getEntity().getContent().toString());
		    } catch (ClientProtocolException e) {
		        Log.d(TAG, "This is your error ClientProtocol " + e.getLocalizedMessage());
		    } catch (IOException e) {
		    	e.printStackTrace();
		        Log.d(TAG, "This is your IOException error " +  e.getLocalizedMessage());
		    }
		    
		    return true;
		}
	}
}
