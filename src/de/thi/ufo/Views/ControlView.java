package de.thi.ufo.Views;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import de.thi.ufo.App.RoundedPanel;
import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.RotateIcon;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;

public class ControlView{
	//public JFrame frame; // To be removed
	private UfoApp app;
	public Container content_pane;
	private JLayeredPane top_layered_pane;
	public JPanel zielanzeige_panel;
	
	private JLabel altitude_val;
	private JLabel velocity_val;
	private JLabel vertical_val;
	private JProgressBar battery_bar;
	private RotateIcon ufo_icon;

	/**
	 * Initialize the contents of the frame.
	 */
	public ControlView(UfoApp p_app) {
		app = p_app;
		content_pane = new Container();
		content_pane.setLayout(new GridLayout(2, 0));
		top_layered_pane = app.target_view.top_layered_pane;
		
// Here starts the upper Part of the App-Screen

		JPanel upper_panel = new JPanel();
		//frame.getContentPane().add(upper_panel);
		content_pane.add(upper_panel);
		upper_panel.setLayout(null);
		
		JPanel blue_frame_upper = new RoundedPanel(35);
		blue_frame_upper.setBounds(10, 11, 414, 369);
		blue_frame_upper.setLayout(null);
		upper_panel.add(blue_frame_upper);
		
		zielanzeige_panel = new JPanel();
		zielanzeige_panel.setBounds(20, 11, 371, 347);
		blue_frame_upper.add(zielanzeige_panel);
		zielanzeige_panel.setLayout(null);
		
		// Make additions to the map
		
		ufo_icon = new RotateIcon();
		top_layered_pane.add(ufo_icon, 0);
		
// Here starts the lower Part of the App-Screen
	
		JPanel lower_panel = new JPanel();
//		frame.getContentPane().add(lower_panel);
		content_pane.add(lower_panel);

		lower_panel.setLayout(null);
		
		JPanel blue_frame_lower = new RoundedPanel(35);
		blue_frame_lower.setBounds(10, 0, 414, 369);
		lower_panel.add(blue_frame_lower);
		blue_frame_lower.setLayout(null);
		
		JPanel status_panel = new JPanel();
		status_panel.setBounds(21, 11, 371, 347);
		blue_frame_lower.add(status_panel);
		status_panel.setLayout(new BorderLayout());
		
		// Create Heading Section (Icons + Heading)
		JPanel status_panel_top = new JPanel();
		status_panel.add(status_panel_top, BorderLayout.PAGE_START);
		status_panel_top.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel status_label = new JLabel("Status");
		status_label.setFont(new Font("Comic Sans MS", Font.BOLD, 34));
		status_label.setHorizontalAlignment(SwingConstants.CENTER);
		status_panel_top.add(status_label);
		
		JPanel icons_panel = new JPanel();
		status_panel_top.add(icons_panel);
		icons_panel.setLayout(new GridLayout(1, 2, 10, 0));
		
		JPanel warnings_panel = new JPanel();
		warnings_panel.setLayout(new GridLayout(1, 3, 3, 0));
		icons_panel.add(warnings_panel);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/battery_low_gray.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		warnings_panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/arrow-limit-grey.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		warnings_panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/obstacle-gray.png")));
		warnings_panel.add(lblNewLabel_1);
		
		JPanel stateicons_panel = new JPanel();
		stateicons_panel.setLayout(new GridLayout(1, 3, 3, 0));
		icons_panel.add(stateicons_panel);
		
		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/marker-check-gray.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		stateicons_panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-gray.png")));
		stateicons_panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel();
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/airplane-green.png")));
		stateicons_panel.add(lblNewLabel_5);
		
		// Create Data Panel
		JPanel data_panel = new JPanel();
		status_panel.add(data_panel, BorderLayout.CENTER);
		data_panel.setLayout(new GridLayout(6, 2, 0, 0));
		
		// Labels for Data
		JLabel absolute_distance_label_txt = new JLabel("Gesamtdistanz");
		absolute_distance_label_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		absolute_distance_label_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(absolute_distance_label_txt);
		JLabel absolute_distance_label_val = new JLabel("360m");
		absolute_distance_label_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		absolute_distance_label_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(absolute_distance_label_val);
		
		JLabel remaining_distance_label_txt = new JLabel("Distanz zum Ziel");
		remaining_distance_label_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		remaining_distance_label_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(remaining_distance_label_txt);
		JProgressBar progress_bar = new JProgressBar();
		progress_bar.setForeground(new Color(43, 120, 228));
		progress_bar.setValue(20);
		data_panel.add(progress_bar);
		
		JLabel altitude_txt = new JLabel("Flughoehe");
		altitude_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		altitude_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(altitude_txt);
		altitude_val = new JLabel(Double.toString(app.sim.getZ()) + " m");
		altitude_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		altitude_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(altitude_val);
		
		JLabel velocity_txt = new JLabel("Geschwindigkeit");
		velocity_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		velocity_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(velocity_txt);
		velocity_val = new JLabel(Integer.toString(app.sim.getV()) + " km/h");
		velocity_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		velocity_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(velocity_val);
		
		JLabel vertical_txt = new JLabel("Vertikale Bewegung");
		vertical_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		vertical_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(vertical_txt);
		vertical_val = new JLabel("");
		vertical_val.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/down-arrow-blue.png")));
		vertical_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		vertical_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(vertical_val);
		
		JLabel battery_txt = new JLabel("Batterieladung");
		battery_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		battery_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(battery_txt);
		battery_bar = new JProgressBar();
		battery_bar.setValue(100);
		battery_bar.setStringPainted(true);
		battery_bar.setForeground(new Color(0, 128, 0));
		data_panel.add(battery_bar);
	}
	
	public void update() {
		altitude_val.setText(Double.toString(Math.round(app.sim.getZ() * 100.0) / 100.0) + " m");
		velocity_val.setText(Double.toString(Math.round(app.sim.getV() * 100.0) / 100.0) + " km/h");
		vertical_val.setIcon(verticalIcon());
		updateBatteryBar();
		checkWarnings();
		updateUfoIcon();
	}
	
	private void updateUfoIcon() {
		ufo_icon.setRotation(app.sim.getD());
		ufo_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/navigation-blue.png")));
		ufo_icon.setBounds((int)app.sim.getX() + 170, (int)app.sim.getY() + 170, 50, 50);				
}

	private void updateBatteryBar() {
		battery_bar.setValue((int) (100 * (1-(app.sim.getDist()/3000))));
		if (battery_bar.getValue() < 10) battery_bar.setForeground(new Color(136, 8, 8));
		else if (battery_bar.getValue() < 20) battery_bar.setForeground(new Color(255,165,0));
	}

	private void checkWarnings() {
		// TODO Auto-generated method stub
		
	}

	private ImageIcon verticalIcon() {
		if(app.sim.getV()==0) 		return (new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/straight-arrow-blue.png")));
		else if(app.sim.getI()<0) 	return (new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/down-arrow-blue.png")));
		else 						return (new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/up-arrow-blue.png")));
	}
}

