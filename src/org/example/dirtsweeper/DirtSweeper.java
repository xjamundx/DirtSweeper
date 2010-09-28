package org.example.dirtsweeper;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DirtSweeper extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       		
		// set up click listeners for all of the buttons
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	switch(v.getId()) {
    		case R.id.about_button:
    			Intent i = new Intent(this, About.class);
    			startActivity(i);
    			break;
    		case R.id.new_button:
    			openNewGameDialog();
    			break;
    		case R.id.exit_button:
    			finish();
    			break;
    	}
    }
    
    private static final String TAG = "DirtSweeper";
    
    private void openNewGameDialog() {
    	new AlertDialog.Builder(this)
    		.setTitle(R.string.new_game_title)
    		.setItems(R.array.difficulty,
	    		new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialoginterface, int i) {
	    					startGame(i);
	    			}
	    		})
	    		.show();
    }

    private void startGame(int i) {
    	Log.d(TAG, "Clicked on " + i);
    	Intent intent = new Intent(DirtSweeper.this, Game.class);
    	intent.putExtra(Game.KEY_DIFFICULTY, i);
    	startActivity(intent);
    }
    
    

}