package de.thi.ufo.Model;

import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.FlyState;
import de.thi.ufo.Helper.UfoState;

public class UfoModel {
	public UfoPositions positions;
	public UfoState ufo_state;
	public FlyState fly_state;
	public SpeedHandler speedhandler;
	public int speed_before_stop = 0;
	public boolean stop_requested;
	
	public UfoModel(UfoApp ufoapp) {
		positions = new UfoPositions();
		ufo_state = UfoState.INIT;
		fly_state = FlyState.WAITING;
		speedhandler = new SpeedHandler(ufoapp);
		speedhandler.start();
		stop_requested = false;		
	}
}
