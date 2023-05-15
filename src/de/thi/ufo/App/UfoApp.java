package de.thi.ufo.App;

import java.awt.EventQueue;

import javax.swing.JFrame;

import de.thi.ufo.UfoSim;
import de.thi.ufo.Helper.FlyState;
import de.thi.ufo.Helper.Simple3DPoint;
import de.thi.ufo.Helper.UfoState;
import de.thi.ufo.Model.UfoModel;
import de.thi.ufo.Views.ControlView;
import de.thi.ufo.Views.StartView;
import de.thi.ufo.Views.TargetView;

public class UfoApp {
	public UfoSim sim = UfoSim.getInstance();
	public JFrame frame = new JFrame();
	public StartView start_view = new StartView(this);
	public TargetView target_view = new TargetView(this);
	public ControlView control_view = new ControlView(this);
	public UfoModel ufo_model = new UfoModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UfoApp app = new UfoApp();
		app.frame.setBounds(100, 100, 450, 800);
		app.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		app.sim.openViewWindow();
		boolean once = false;
		boolean twice = false;
		boolean xx = false;

		while(app.ufo_model.ufo_state != UfoState.TERMINATED) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			app.frame.repaint();
			switch(app.ufo_model.ufo_state) {
				case STARTED:
					switch(app.ufo_model.fly_state) {
					case WAITING:
						app.sim.requestDeltaV(10);	
						app.sim.setD((int) app.ufo_model.positions.horizontalOrientationToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY())));
						app.ufo_model.fly_state = FlyState.TAKEOFF;
						break;
					case TAKEOFF:
						if (app.sim.getZ()>=10) {
							app.sim.requestDeltaV(40); // Now at total speed of 50 km/h	
							app.ufo_model.fly_state = FlyState.ASCENDING;
						}
						break;
					case ASCENDING:
						if (app.sim.getZ()>=app.ufo_model.positions.getDesiredAltitude()) {
							app.sim.setI(0);	
							app.ufo_model.fly_state = FlyState.FLYING;
						}
						break;
					case FLYING:
						app.sim.setD((int) app.ufo_model.positions.horizontalOrientationToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY())));
						if (app.ufo_model.positions.horizontalDistanceToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY())) < 1) {
							app.sim.setI(-90);
							app.ufo_model.fly_state = FlyState.DESCENDING;
						}
						break;
					case DESCENDING:
						if (app.sim.getZ()<=30) {
							app.sim.requestDeltaV(-49); // Now at total speed of 1 km/h	
							app.ufo_model.fly_state = FlyState.LANDING;
						}
						break;
					case LANDING:
						if (app.sim.getZ()<=0) {
							app.sim.setI(0);
							app.sim.requestDeltaV(-40); // Now at total speed of 0 km/h	
							app.ufo_model.fly_state = FlyState.LANDED;
						}
						break;
					case LANDED:
						app.ufo_model.ufo_state = UfoState.ARRIVED;	
						break;
					default:
						break;
					}
					app.control_view.update();
					break;
				case STOPPED:
					app.control_view.update();
					break;
				case ARRIVED:
					app.control_view.update();
					break;
			default:
				break;
			}
		}
		System.exit(1);
		//*/
	}
}
