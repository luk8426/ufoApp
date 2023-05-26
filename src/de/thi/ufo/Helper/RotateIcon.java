package de.thi.ufo.Helper;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class RotateIcon extends JLabel {
	
	private double rotation;
	
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
		this.rotation = rotation + 90;
	}
 }
