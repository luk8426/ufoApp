package de.thi.ufo.App;

import java.awt.Color;
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
	public UfoSim sim;
	public JFrame frame;
	public StartView start_view;
	public TargetView target_view;
	public ControlView control_view;
	public UfoModel ufo_model;
	
	public UfoApp() {
		sim = UfoSim.getInstance();
		frame = new JFrame();
		start_view = new StartView(this);
		target_view = new TargetView(this);
		control_view = new ControlView(this);
		ufo_model = new UfoModel(this);	
	}
	
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
						//app.sim.requestDeltaV(10);	
						app.ufo_model.speedhandler.setTargetSpeed(10);
						app.sim.setD((int) app.ufo_model.positions.horizontalOrientationToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY())));
						app.ufo_model.fly_state = FlyState.TAKEOFF;
						break;
					case TAKEOFF:
						if (app.sim.getZ()>=10) {
							//app.sim.requestDeltaV(40); // Now at total speed of 50 km/h	
							app.ufo_model.speedhandler.setTargetSpeed(50);
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
							//app.sim.requestDeltaV(-49); // Now at total speed of 1 km/h	
							app.ufo_model.speedhandler.setTargetSpeed(1);
							app.ufo_model.fly_state = FlyState.LANDING;
						}
						break;
					case LANDING:
						if (app.sim.getZ()<=0) {
							app.sim.setI(0);
							app.ufo_model.speedhandler.setTargetSpeed(0);
							//app.sim.requestDeltaV(-40); // Now at total speed of 0 km/h	
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
					if(app.ufo_model.stop_requested) {
						//app.ufo_model.velocity_before_stopped = app.sim.getV();
							app.ufo_model.speedhandler.setTargetSpeed(0);
						if ((app.ufo_model.fly_state != FlyState.WAITING)&& app.sim.getV()<1)	app.ufo_model.ufo_state = UfoState.STOPPED;
					}
						
					break;
				case STOPPED:
					if (!app.ufo_model.stop_requested) {
						app.sim.setSpeedup(1);
						app.ufo_model.ufo_state = UfoState.STARTED;
					}
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
