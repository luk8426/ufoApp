package de.thi.ufo.Model;

import de.thi.ufo.Helper.Simple3DPoint;
import de.thi.ufo.Helper.UfoState;

public class UfoPositions {
	private static final int MAX_ALTITUDE = 50;
	private static final int MAX_DISTANCE = 3000;
	private double inital_distance = 0;
	private Simple3DPoint current_position;
	private Simple3DPoint destination;
	private int desired_altitude;
	
	public boolean isDestinationValid() {
		return (this.destination.horizontalDistanceFromOrigin()<=MAX_DISTANCE);
	}

	public Simple3DPoint getCurrentPosition() {
		return this.current_position;
	}
	
	public Simple3DPoint getDestination() {
		return this.destination;
	}

	public void setDestination(Simple3DPoint destination) {
		this.destination = destination;
	};

	public void setCurrentPosition(Simple3DPoint current_position) {
		this.current_position = current_position;
	}

	public int getDesiredAltitude() {
		return desired_altitude;
	}

	public void setDesiredAltitude(int desired_altitude) {
		this.desired_altitude = desired_altitude;
	}

	public double getInitalDistance() {
		if (inital_distance==0) {
			this.inital_distance = destination.horizontalDistanceFromOrigin();
		}
		return inital_distance;
	};
	
}
