package org.example.dirtsweeper;

import java.util.ArrayList;
import android.graphics.PointF;

// GameFrame Object
public class GameFrame {
	public int gameID;
	public ArrayList<PointF> coords;
	
	public GameFrame(int gameID, ArrayList<PointF> coords) {
		this.coords = coords;
		this.gameID = gameID;
	}
	
}