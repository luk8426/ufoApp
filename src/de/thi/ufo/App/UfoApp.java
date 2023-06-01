package de.thi.ufo.App;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
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
		frame = new JFrame("Deliver UFO App");
		start_view = new StartView(this);
		target_view = new TargetView(this);
		control_view = new ControlView(this);
		ufo_model = new UfoModel(this);	
		frame.setIconImage(new ImageIcon(StartView.class.getResource("/de/thi/ufo/Resources/ufo_small.png")).getImage());
		frame.setResizable(false);
		frame.setBounds(50, 50, 450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UfoApp app = new UfoApp();
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
		//app.sim.setSpeedup(3);
		int periodic_check = 1000;
		app.start_view.content_pane.requestFocusInWindow();
		while(app.ufo_model.getUfoState() != UfoState.TERMINATED) {
			System.out.println(app.ufo_model.getUfoState());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			app.frame.repaint();
			switch(app.ufo_model.getUfoState()) {
				case STARTED:
					switch(app.ufo_model.getFlyState()) {
					case WAITING:
						app.ufo_model.speedhandler.setTargetSpeed(10);
						app.sim.setD((int) app.ufo_model.positions.horizontalOrientationToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY())));
						app.ufo_model.setFlyState(FlyState.TAKEOFF);
						break;
					case TAKEOFF:
						if (app.sim.getZ()>=10) {
							app.ufo_model.speedhandler.setTargetSpeed(50);
							app.ufo_model.setFlyState(FlyState.ASCENDING);
						}
						break;
					case ASCENDING:
						if (app.sim.getZ()>=app.ufo_model.positions.getDesiredAltitude()) {
							app.sim.setI(0);	
							app.ufo_model.setFlyState(FlyState.FLYING);
						}
						break;
					case FLYING:
						app.sim.setD((int) app.ufo_model.positions.horizontalOrientationToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY())));
						double horizontal_distance_to_destination = app.ufo_model.positions.horizontalDistanceToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY()));
						if (horizontal_distance_to_destination < 1) {
							app.sim.setI(-90);
							app.ufo_model.setFlyState(FlyState.DESCENDING);
						}
						else if (horizontal_distance_to_destination < 10)
							app.ufo_model.speedhandler.setTargetSpeed(20);
						else if ((app.sim.getI()==0)&&(app.sim.getRadar()>=0)) {
							int cur_v = app.sim.getV();
							if (cur_v > 2) app.ufo_model.speed_before_detour = cur_v;
							app.ufo_model.speedhandler.setTargetSpeed(0);
							app.ufo_model.setFlyState(FlyState.DETOUR);
						}
						break;
					case DETOUR:
						if (app.sim.getV()==0) {
							app.sim.setD((int) app.ufo_model.positions.horizontalOrientationToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY())));							
							if(app.sim.getRadar()==-1) {
								app.ufo_model.speedhandler.setTargetSpeed(app.ufo_model.speed_before_detour);
								app.ufo_model.setFlyState(FlyState.FLYING);
							}
							else {
								int d2test = app.sim.getD();
								double radar = app.sim.getRadar();
								for (int i = 1; i < 360; i++) {
									int curD = d2test + 5* i * (int)Math.pow(-1,(i%2));
									if (curD < 0) curD +=360;
									else if (curD > 359) curD -=360;
									app.sim.setD(curD);
									while(app.sim.getRadar()==radar);
									radar = app.sim.getRadar();
									if(radar==-1) {System.out.println("FoundDir");break;}
								}
								app.ufo_model.speedhandler.setTargetSpeed(5);
								while(app.sim.getV()==0)
									try {
										// Necessary to allow the sim thread to proceed
										Thread.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
							}
						}else {
							periodic_check--;
							if (periodic_check==0) {
								periodic_check = 1000;
								app.ufo_model.speedhandler.setTargetSpeed(0);
							}
						}
						break;
					case DESCENDING:
						app.ufo_model.speedhandler.setTargetSpeed(1);
						app.ufo_model.setFlyState(FlyState.LANDING);
						break;
					case LANDING:
						if (app.sim.getZ()<=1) {
							app.sim.setI(0);
							app.ufo_model.speedhandler.setTargetSpeed(0);
							app.ufo_model.setFlyState(FlyState.LANDED);
						}
						break;
					case LANDED:
						app.ufo_model.setUfoState(UfoState.ARRIVED);	
						break;
					default:
						break;
					}
					app.control_view.update();
					if(app.ufo_model.stop_requested) {
						if (app.ufo_model.speed_before_stop==0) {
							app.ufo_model.speed_before_stop= app.sim.getV();
							app.ufo_model.speedhandler.setTargetSpeed(0);
						}
						if (app.sim.getV() < 1) {
							app.ufo_model.stop_requested = !app.ufo_model.stop_requested;
							app.ufo_model.setUfoState(UfoState.STOPPED);
						}
					}
					
					break;
				case STOPPED:
					if (app.ufo_model.continue_requested) {
						app.ufo_model.continue_requested = !app.ufo_model.continue_requested;
						if (app.ufo_model.getFlyState()!=FlyState.DETOUR)
							app.ufo_model.speedhandler.setTargetSpeed(app.ufo_model.speed_before_stop);
						app.ufo_model.speed_before_stop = 0;
						app.ufo_model.setUfoState(UfoState.STARTED);
					}
					app.control_view.update();
					break;
				case ARRIVED:
					if (app.ufo_model.return_requested_after_arrival) {
						app.ufo_model.return_requested_after_arrival = false;
						app.sim.setI(90);	
						app.ufo_model.setFlyState(FlyState.WAITING);
						app.ufo_model.setUfoState(UfoState.STARTED);
					}
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
