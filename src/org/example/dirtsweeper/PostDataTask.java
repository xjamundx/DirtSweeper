package org.example.dirtsweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.example.dirtsweeper.GameFrame;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.graphics.PointF;
import android.os.AsyncTask;
import android.util.Log;

public class PostDataTask extends AsyncTask<GameFrame, Integer, Long > {

	private static final String TAG = "PostDataTask";
	private static final String ADD_TIMES_URL = "http://www.touchenabledweb.com/games/addTimes.json";

	@Override
	protected Long doInBackground(GameFrame...frame) {
		Log.d(TAG, TAG + "do it in the background");
		boolean succcess = this.postData(frame[0]);
		return null;
	} 

	private boolean postData(GameFrame myFrame) {
		Log.d(TAG, TAG + "post, data");
		int gameID = 0;
		Boolean success = false;
		
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(ADD_TIMES_URL);
	    try {
	   
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        String xs = "";
	        String ys = "";
	        for (PointF p : myFrame.coords) {		        	
		        xs += String.valueOf(p.x) + "|";
		        ys += String.valueOf(p.y) + "|";
	    	}
	        xs = xs.substring(0, xs.length() - 1);
	        ys = ys.substring(0, ys.length() - 1);
	        nameValuePairs.add(new BasicNameValuePair("data[GameTime][game_id]", String.valueOf(myFrame.gameID)));
	        nameValuePairs.add(new BasicNameValuePair("data[GameTime][xs]", xs));
	        nameValuePairs.add(new BasicNameValuePair("data[GameTime][ys]", ys));
	        Log.d(TAG, TAG + " sending game id : " + myFrame.gameID);
	        Log.d(TAG, TAG + " sending xs : " + xs);
	        Log.d(TAG, TAG + " sending ys : " + ys);

	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	        String json = reader.readLine();
	        Log.d(TAG, TAG + "JSON: " + json);
	        JSONTokener tokener = new JSONTokener(json);
	        try {
				JSONObject finalResult = new JSONObject(tokener);
				gameID = finalResult.getInt("game_id");
				success = finalResult.getBoolean("success");
		        Log.d(TAG, TAG + "success: " + success + " game_id: " + gameID);
			} catch (JSONException e) {
		        Log.d(TAG, TAG + "ERROR JSON EXCEPTION: " + e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    } catch (ClientProtocolException e) {
	        Log.d(TAG, TAG + "This is your error ClientProtocol " + e.getLocalizedMessage());
	    } catch (IOException e) {
	    	e.printStackTrace();
	        Log.d(TAG, TAG + "This is your IOException error " +  e.getLocalizedMessage());
	    }
	    
	    return true;
	}
}