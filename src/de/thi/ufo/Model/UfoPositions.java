package de.thi.ufo.Model;

import de.thi.ufo.Helper.Simple3DPoint;
import de.thi.ufo.Helper.UfoState;

public class UfoPositions {
	private static final int MAX_ALTITUDE = 50;
	private static final int MAX_DISTANCE = 3000;
	private double inital_distance;
	private Simple3DPoint current_position;
	private Simple3DPoint destination;
	public int desired_altitude;
	
	public boolean isDestinationValid() {
		return (this.destination.horizontalDistanceFromOrigin()<=MAX_DISTANCE);
	};
	
}
