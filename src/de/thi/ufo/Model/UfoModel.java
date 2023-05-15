package de.thi.ufo.Model;

import de.thi.ufo.Helper.FlyState;
import de.thi.ufo.Helper.UfoState;

public class UfoModel {
	public UfoPositions positions = new UfoPositions();
	public UfoState ufo_state = UfoState.INIT;
	public FlyState fly_state = FlyState.WAITING;
}
