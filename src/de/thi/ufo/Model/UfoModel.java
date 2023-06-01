package de.thi.ufo.Model;

import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.FlyState;
import de.thi.ufo.Helper.UfoState;

public class UfoModel {
	public UfoPositions positions;
	private UfoState ufo_state;
	private FlyState fly_state;
	public SpeedHandler speedhandler;
	public int speed_before_stop, speed_before_detour = 0;
	public boolean stop_requested, continue_requested, return_requested_after_arrival, disable_return = false;
	
	public UfoModel(UfoApp ufoapp) {
		positions = new UfoPositions();
		setUfoState(UfoState.INIT);
		setFlyState(FlyState.WAITING);
		speedhandler = new SpeedHandler(ufoapp);
		speedhandler.start();
		stop_requested = false;
	}

	public FlyState getFlyState() {
		return fly_state;
	}

	public void setFlyState(FlyState fly_state) {
		this.fly_state = fly_state;
	}

	public UfoState getUfoState() {
		return ufo_state;
	}

	public void setUfoState(UfoState ufo_state) {
		this.ufo_state = ufo_state;
	}
}
