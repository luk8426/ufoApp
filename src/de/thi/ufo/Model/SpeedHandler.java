package de.thi.ufo.Model;

import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.UfoState;

public class SpeedHandler extends Thread implements Runnable {
	private int target_speed;
	private UfoApp app;

	public SpeedHandler(UfoApp app) {
		super();
		target_speed = 0;
		this.app = app;
	}
	
	@Override
	public void run() {
		while(app.ufo_model.ufo_state != UfoState.TERMINATED) {
			int delta = this.target_speed - app.sim.getV();
			if (Math.abs(delta) >= 1) {
				app.sim.requestDeltaV(delta);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getTargetSpeed() {
		return target_speed;
	}

	public void setTargetSpeed(int target_speed) {
		this.target_speed = target_speed;
	}
	
}
