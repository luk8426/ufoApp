package de.thi.ufo.App;

import java.awt.EventQueue;

import javax.swing.JFrame;

import de.thi.ufo.Model.UfoModel;
import de.thi.ufo.Views.ControlView;
import de.thi.ufo.Views.StartView;
import de.thi.ufo.Views.TargetView;

public class UfoApp {
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
					//app.frame.setContentPane(app.start_view.;
					app.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
