package de.thi.ufo.Views;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import de.thi.ufo.App.RoundedPanel;
import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.RotateIcon;
import de.thi.ufo.Helper.Simple3DPoint;
import de.thi.ufo.Helper.UfoState;
import de.thi.ufo.Model.UfoPositions;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;

public class ControlView {
	//public JFrame frame; // To be removed
	private UfoApp app;
	public Container content_pane;
	private JLayeredPane top_layered_pane;
	public JPanel zielanzeige_panel;
	
	// Icons ins Status panel
	private JLabel flying_icon;
	private JLabel stop_icon;
	private JLabel battery_low_icon;
	private JLabel obstacle_icon;
	private JLabel alt_limit_icon;
	private JLabel dest_reached_icon;
	// Data values
	private JLabel altitude_val;
	private JLabel velocity_val;
	private JLabel vertical_val;
	public JLabel absolute_distance_label_val;
	// Bars
	private JProgressBar battery_bar;
	private JProgressBar progress_bar;
	
	// Top Icons & Buttons
	private RotateIcon ufo_icon;
	private JButton stop_btn;
	private JButton return_btn;

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
		upper_panel.setBackground(Color.WHITE);
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
		stop_btn = new JButton();
		stop_btn.setBorderPainted(false);
		stop_btn.setContentAreaFilled(false);
		stop_btn.addActionListener(e -> {
			app.ufo_model.stop_requested = true;
		});
		return_btn = new JButton();
		return_btn.setBorderPainted(false);
		return_btn.setContentAreaFilled(false);
		stop_btn.addActionListener(e -> {
			// Do nothing yet
		});
		
		top_layered_pane.add(ufo_icon, 0);
		top_layered_pane.add(stop_btn, 0);
		top_layered_pane.add(return_btn, 0);

				
// Here starts the lower Part of the App-Screen
	
		JPanel lower_panel = new JPanel();
		lower_panel.setBackground(Color.WHITE);
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
		status_panel_top.setBackground(Color.WHITE);

		
		JLabel status_label = new JLabel("Status");
		status_label.setFont(new Font("Comic Sans MS", Font.BOLD, 34));
		status_label.setHorizontalAlignment(SwingConstants.CENTER);
		status_panel_top.add(status_label);
		
		JPanel icons_panel = new JPanel();
		status_panel_top.add(icons_panel);
		icons_panel.setLayout(new GridLayout(1, 2, 10, 0));
		icons_panel.setBackground(Color.WHITE);

		
		JPanel warnings_panel = new JPanel();
		warnings_panel.setLayout(new GridLayout(1, 3, 3, 0));
		warnings_panel.setBackground(Color.WHITE);
		icons_panel.add(warnings_panel);
		
		battery_low_icon = new JLabel();
		battery_low_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/battery_low_gray.png")));
		battery_low_icon.setHorizontalAlignment(SwingConstants.CENTER);
		
		warnings_panel.add(battery_low_icon);
		
		alt_limit_icon = new JLabel();
		alt_limit_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/arrow-limit-gray.png")));
		alt_limit_icon.setHorizontalAlignment(SwingConstants.RIGHT);
		warnings_panel.add(alt_limit_icon);
		
		obstacle_icon = new JLabel();
		obstacle_icon.setHorizontalAlignment(SwingConstants.CENTER);
		obstacle_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/obstacle-gray.png")));
		warnings_panel.add(obstacle_icon);
		
		JPanel stateicons_panel = new JPanel();
		stateicons_panel.setLayout(new GridLayout(1, 3, 3, 0));
		stateicons_panel.setBackground(Color.WHITE);
		icons_panel.add(stateicons_panel);
		
		dest_reached_icon = new JLabel();
		dest_reached_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/marker-check-gray.png")));
		dest_reached_icon.setHorizontalAlignment(SwingConstants.CENTER);
		stateicons_panel.add(dest_reached_icon);
		
		stop_icon = new JLabel();
		stop_icon.setHorizontalAlignment(SwingConstants.CENTER);
		stop_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-gray.png")));
		stateicons_panel.add(stop_icon);
		
		flying_icon = new JLabel();
		flying_icon.setHorizontalAlignment(SwingConstants.CENTER);
		flying_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/airplane-green.png")));
		stateicons_panel.add(flying_icon);
		
		// Create Data Panel
		JPanel data_panel = new JPanel();
		data_panel.setBackground(new Color(255, 255, 255));
		status_panel.add(data_panel, BorderLayout.CENTER);
		status_panel.setBackground(Color.WHITE);
		data_panel.setLayout(new GridLayout(6, 2, 0, 0));
		
		// Labels for Data
		JLabel absolute_distance_label_txt = new JLabel("Gesamtdistanz");
		absolute_distance_label_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		absolute_distance_label_txt.setHorizontalAlignment(SwingConstants.LEFT);
		absolute_distance_label_txt.setBackground(Color.WHITE);
		data_panel.add(absolute_distance_label_txt);
		absolute_distance_label_val = new JLabel("360m");
		absolute_distance_label_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		absolute_distance_label_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(absolute_distance_label_val);
		
		JLabel remaining_distance_label_txt = new JLabel("Distanz zum Ziel");
		remaining_distance_label_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		remaining_distance_label_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(remaining_distance_label_txt);
		progress_bar = new JProgressBar();
		progress_bar.setForeground(new Color(43, 120, 228));
		progress_bar.setStringPainted(true);
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
		updateProgressBar();
		checkWarningsAndButtons();
		updateUfoIcon();
	}

	private void updateUfoIcon() {
		ufo_icon.setRotation(app.sim.getD());
		ufo_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/navigation-blue.png")));
		ufo_icon.setBounds(UfoPositions.positionInMap(app.sim.getX()), UfoPositions.positionInMap(app.sim.getY()), 50, 50);				
	}
	
	private void updateProgressBar() {
		double remaining_distance = app.ufo_model.positions.distanceToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY(), app.sim.getZ()));
		progress_bar.setString(Double.toString(Math.round(remaining_distance * 100.0) / 100.0) + " m");
		progress_bar.setValue((int) (100 * (1-(remaining_distance/app.ufo_model.positions.getInitalDistance()))));
		if (progress_bar.getValue() >= 99) progress_bar.setForeground(new Color(0, 128, 0));
	}

	private void updateBatteryBar() {
		battery_bar.setValue((int) (100 * (1-(app.sim.getDist()/3000))));
		if (battery_bar.getValue() < 10) battery_bar.setForeground(new Color(136, 8, 8));
		else if (battery_bar.getValue() < 20) battery_bar.setForeground(new Color(255,165,0));
	}

	private void checkWarningsAndButtons() {
		switch(app.ufo_model.ufo_state) {
		case STARTED:
			System.out.println("Test");
			flying_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/airplane-green.png")));
			stop_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-gray.png")));
			dest_reached_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/marker-check-gray.png")));
			stop_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-inner-red.png")));
			stop_btn.setBounds(120, 280, 60, 60);
			return_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-inner-red.png")));
			return_btn.setBounds(210, 280, 60, 60);	
			break;
		case STOPPED:
			System.out.println("Black?");
			stop_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-inner-black.png")));
			flying_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/airplane-gray.png")));
			stop_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-red.png")));
			break;
		case ARRIVED:
			stop_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-inner-black.png")));
			flying_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/airplane-gray.png")));
			dest_reached_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/marker-check-green.png")));
			ufo_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/navigation-green.png")));
		default:
			break;
		}
		if (battery_bar.getValue() < 10) battery_low_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/battery_low_red.png")));
		else battery_low_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/battery_low_gray.png")));
		if (app.sim.getZ() >= UfoPositions.MAX_ALTITUDE) alt_limit_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/arrow-limit-red.png")));
		else alt_limit_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/arrow-limit-gray.png")));
		//if RADAR obstacle_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/obstacle_color.png"))); else
		obstacle_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/obstacle-gray.png")));	
	}

	private ImageIcon verticalIcon() {
		if(app.sim.getI()==0 || app.sim.getV()==0) 		return (new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/straight-arrow-blue.png")));
		else if(app.sim.getI()<0) 						return (new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/down-arrow-blue.png")));
		else 											return (new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/up-arrow-blue.png")));
	}
}

