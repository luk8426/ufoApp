package de.thi.ufo.Helper;

/////////////////////////////////////////////////////////////////////////////////////////////////////
//Hilfsklasse die Koordinaten eines Punktes zusammennfasst und benötigte Funktionen direkt anbietet//
/////////////////////////////////////////////////////////////////////////////////////////////////////

public class Simple3DPoint {
	private double x, y, z;

	public Simple3DPoint(double p_x, double p_y, double p_z) {
	    x = p_x;
	    y = p_y;
	    z = p_z;
	 }
	
	public Simple3DPoint(double p_x, double p_y) {
	    x = p_x;
	    y = p_y;
	    z = 0;
	 }

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setX(double p_x) {
		x = p_x;
	}

	public void setY(double p_y) {
		x = p_y;
	}
	
	public void setZ(double p_z) {
		x = p_z;
	}
	
	public double horizontalDistanceTo(Simple3DPoint p) {
		return Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));
	}
	
	public double totalDistanceTo(Simple3DPoint p) {
		return horizontalDistanceTo(p) + Math.abs(p.z-this.z);
	}
	
	public double horizontalDistanceFromOrigin() {
		Simple3DPoint p = new Simple3DPoint(0, 0, 0);
		return horizontalDistanceTo(p);
	}
	
	public double totalDistanceFromOrigin() {
		Simple3DPoint p = new Simple3DPoint(0, 0, 0);
		return totalDistanceTo(p);
	}
}
