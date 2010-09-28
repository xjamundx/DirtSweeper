package org.example.dirtsweeper;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class Game extends Activity {
	private static final String TAG = "FloorSweeper";
	
	public static final String KEY_DIFFICULTY = "org.example.dirtsweeper.difficulty";
	public static final int DIFFICULTY_EASY = 0;
	public static final int DIFFICULTY_MEDIUM = 1;
	public static final int DIFFICULTY_HARD = 2;
	
	private int floor[] = new int[9 * 9];
	
	private FloorView floorView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		
		int diff = getIntent().getIntExtra(KEY_DIFFICULTY, DIFFICULTY_EASY);
		// floor = getFloor(diff);
		// calculateUsedTiles();
		
		floorView = new FloorView(this);
		
		setContentView(floorView);
		floorView.requestFocus();

	}
}
