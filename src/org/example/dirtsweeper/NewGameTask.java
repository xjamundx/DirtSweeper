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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.AsyncTask;
import android.util.Log;

public class NewGameTask extends AsyncTask<Sweeper, Integer, Long > {

	private static final String TAG = "NewGameTask";
	private static final String NEW_GAME_URL = "http://www.touchenabledweb.com/games/addGame.json";

		@Override
	protected Long doInBackground(Sweeper...sweep) {
		Log.d(TAG, "BACKGROUND: new game");
		int gameID = this.newGame(sweep[0]);
		sweep[0].setGameID(gameID);
		return null;
	} 

	private int newGame(Sweeper sweeper) {
		Log.d(TAG, TAG +"NEW GAME!");
        int gameID = 0;
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(NEW_GAME_URL);
	    try {
	   
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair("data[Game][width]", String.valueOf(sweeper.floor.getWidth())));
	        nameValuePairs.add(new BasicNameValuePair("data[Game][height]", String.valueOf(sweeper.floor.getHeight())));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        Log.d(TAG, TAG + "WIDTH: " + String.valueOf(sweeper.floor.getWidth()));
	        Log.d(TAG, TAG + "HEIGHT: " + String.valueOf(sweeper.floor.getHeight()));
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	        String json = reader.readLine();
	        Log.d(TAG, TAG + "JSON: " + json);
	        JSONTokener tokener = new JSONTokener(json);
	        try {
				JSONObject finalResult = new JSONObject(tokener);
				gameID = finalResult.getInt("game_id");
		        Log.d(TAG, TAG + "result: " + gameID);
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
	    
	    return gameID;
	}
}
