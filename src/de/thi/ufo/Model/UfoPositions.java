package de.thi.ufo.Model;

import de.thi.ufo.Helper.Simple3DPoint;
import de.thi.ufo.Helper.UfoState;

public class UfoPositions {
	public static final int MAX_ALTITUDE = 50;
	private static final int MAX_DISTANCE = 3000;
	private double inital_distance = 0;
	private Simple3DPoint destination;
	private int desired_altitude;
	private static boolean reached_desired_alt = false;
	
	public static int positionInMap(double ufo_position) {
		return ((int)ufo_position/5)+ 170;
	}
	
	public boolean isDestinationValid() {
		return (this.destination.horizontalDistanceFromOrigin()<=MAX_DISTANCE);
	}
	
	public double distanceToDestination(Simple3DPoint current_position) {
		double distance = current_position.totalDistanceTo(destination);
		if (!reached_desired_alt) {
			double delta_z = Math.abs(desired_altitude-current_position.getZ());
			distance += delta_z*2;
			if (delta_z <= 0.5) {
				reached_desired_alt = true;
			}
		}
		return distance;
	}
	
	public double horizontalDistanceToDestination(Simple3DPoint current_position) {
		return current_position.horizontalDistanceTo(destination);
	}
	
	public double horizontalOrientationToDestination(Simple3DPoint current_position) {
		double orientation = (Math.atan2(UfoPositions.positionInMap(destination.getY())-UfoPositions.positionInMap(current_position.getY()), UfoPositions.positionInMap(destination.getX())-UfoPositions.positionInMap(current_position.getX()))*360/(2*Math.PI));
		if (orientation<0) orientation += 360;
		return orientation; 
	}
	
	// GETTERS AND SETTERS

	
	public Simple3DPoint getDestination() {
		return this.destination;
	}

	public void setDestination(Simple3DPoint destination) {
		this.destination = destination;
	};

	public int getDesiredAltitude() {
		return desired_altitude;
	}

	public void setDesiredAltitude(int desired_altitude) {
		this.desired_altitude = desired_altitude;
	}

	public double getInitalDistance() {
		if (inital_distance==0) {
			this.inital_distance = destination.horizontalDistanceFromOrigin() + 2*desired_altitude;
		}
		return inital_distance;
	};
	
}
