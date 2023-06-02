package de.thi.ufo.Helper;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

///////////////////////////////////////////////////////
//Hilfsklasse um ein rotierendes Ufo-Icon umzusetzen //
///////////////////////////////////////////////////////

@SuppressWarnings("serial")
public class RotateIcon extends JLabel {
	
	private double rotation;
	
	// Bei jeder Aktualisierung wird dieses Icon der aktuelllen Drehung entsprechend gezeichnet 
    @Override
    public void paintComponent(Graphics g) {
       Graphics2D gx = (Graphics2D) g;
       gx.rotate((rotation/360)*2*Math.PI, getWidth()/2, getHeight()/2);
       super.paintComponent(g);
    }

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation + 90; // Offset von 90°
	}
 }
