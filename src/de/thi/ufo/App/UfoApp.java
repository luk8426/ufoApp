package de.thi.ufo.App;

import java.awt.EventQueue;

import javax.swing.JFrame;

import de.thi.ufo.UfoSim;
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
		while(app.ufo_model.state != UfoState.TERMINATED) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			app.frame.repaint();
			switch(app.ufo_model.state) {
				case STARTED:
					app.control_view.update();
					if (!once) {
						app.sim.setI(90);
						app.sim.requestDeltaV(50);	
						once = true;
					}
						
					if(app.sim.getZ()>10) {
						if (!twice) {
							app.sim.setI(0);
							app.sim.setD(180);
						}
						twice = true;
					}
						//app.ufo_model.state = UfoState.TERMINATED;
					break;
				case STOPPED:
					continue;
				case ARRIVED:
					break;
			}
		}
		System.exit(1);
		//*/
	}
}
