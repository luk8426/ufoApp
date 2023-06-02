package de.thi.ufo.Model;

import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.UfoState;

////////////////////////////////////////////////////////////////
//Thread um die Geschwindigkeit des UFOs gezielt setzen können//
// Stellt eine Art Regler dar								  //
////////////////////////////////////////////////////////////////

public class SpeedHandler extends Thread implements Runnable {
	private int current_target_speed, new_target_speed;
	private UfoApp app;
	public SpeedHandler(UfoApp app) {
		super();
		current_target_speed = 0;
		new_target_speed = 0;
		this.app = app;
	}
	
	@Override
	public void run() {
		while(app.ufo_model.getUfoState() != UfoState.TERMINATED) {
			int delta = new_target_speed - current_target_speed;
			if (delta!=0){
				current_target_speed = new_target_speed;
				app.sim.requestDeltaV(delta);
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getTargetSpeed() {
		return current_target_speed;
	}

	public void setTargetSpeed(int target_speed) {
		new_target_speed = target_speed;
	}
	
}
