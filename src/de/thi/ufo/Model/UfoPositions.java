package de.thi.ufo.Model;

import de.thi.ufo.Helper.Simple3DPoint;

//////////////////////////////////////////////////////////////////////
//Hilfsklasse die alle Daten im Zusammenhang mit Koordinaten bündelt//
//////////////////////////////////////////////////////////////////////

public class UfoPositions {
	public static final int MAX_ALTITUDE = 50;
	private static final int MAX_DISTANCE = 1000;
	private double inital_distance = 0;
	private Simple3DPoint destination;
	private int desired_altitude;
	private static boolean reached_desired_alt = false;
	private static double map_scale = 0.2;

	public static Simple3DPoint positionInMap(Simple3DPoint ufo_position) {
		// Offset und Skalierung einberechnen
		return new Simple3DPoint((((int)(ufo_position.getX()*map_scale))+ 170), ((-(int)(ufo_position.getY()*map_scale))+ 170));
	}
	
	public boolean isDestinationValid() {
		double dist = this.destination.horizontalDistanceFromOrigin();
		if (dist<=MAX_DISTANCE) {
			// Skalierung für die Karte bestimmen, sodass das Ziel immer im Bild liegt und nicht unter den Bannern
			double newscale = (100/dist);
			UfoPositions.map_scale = newscale; 
			return true;
		}
		else return false;
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
		double orientation = (Math.atan2(destination.getY()-current_position.getY(), destination.getX()-current_position.getX())*360/(2*Math.PI));
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
	
	public double getMapScale() {
		return UfoPositions.map_scale;
	}
	
}
