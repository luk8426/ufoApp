package de.thi.ufo.Views;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import de.thi.ufo.App.RoundedPanel;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

public class ControlView extends UfoView{
	public JFrame frame; // To be removed
	private JTextField norden_text;
	private JTextField osten_text;
	private JTextField höhe_text;
	private JTextField textField;

	/**
	 * Initialize the contents of the frame.
	 */
	public ControlView() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 0));
		
// Here starts the upper Part of the App-Screen

		JPanel upper_panel = new JPanel();
		frame.getContentPane().add(upper_panel);
		upper_panel.setLayout(null);
		
		JPanel blue_frame_upper = new RoundedPanel(35);
		blue_frame_upper.setBounds(10, 11, 414, 369);
		blue_frame_upper.setLayout(null);
		upper_panel.add(blue_frame_upper);
		
		JPanel zielanzeige_panel = new JPanel();
		zielanzeige_panel.setBounds(20, 11, 371, 347);
		blue_frame_upper.add(zielanzeige_panel);
		zielanzeige_panel.setLayout(new GridLayout(7, 0, 0, 0));
		
// Here starts the lower Part of the App-Screen
	
		JPanel lower_panel = new JPanel();
		frame.getContentPane().add(lower_panel);
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
		icons_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JPanel warnings_panel = new JPanel();
		warnings_panel.setLayout(new GridLayout(1, 3, 3, 0));
		icons_panel.add(warnings_panel);
		
		JPanel stateicons_panel = new JPanel();
		stateicons_panel.setLayout(new GridLayout(1, 3, 3, 0));
		icons_panel.add(stateicons_panel);
		
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
		progress_bar.setValue(20);
		progress_bar.setForeground(new Color(43, 120, 228));
		data_panel.add(progress_bar);
		
		JLabel altitude_txt = new JLabel("Flughöhe");
		altitude_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		altitude_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(altitude_txt);
		JLabel altitude_val = new JLabel("0m");
		altitude_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		altitude_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(altitude_val);
		
		JLabel velocity_txt = new JLabel("Geschwindigkeit");
		velocity_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		velocity_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(velocity_txt);
		JLabel velocity_val = new JLabel("0km/h");
		velocity_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		velocity_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(velocity_val);
		
		JLabel vertical_txt = new JLabel("Vertikale Bewegung");
		vertical_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		vertical_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(vertical_txt);
		JLabel vertical_val = new JLabel("0km/h");
		vertical_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		vertical_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(vertical_val);
		
		JLabel battery_txt = new JLabel("Batterieladung");
		battery_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		battery_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(battery_txt);
		JProgressBar battery_bar = new JProgressBar();
		battery_bar.setValue(100);
		battery_bar.setForeground(new Color(0, 128, 0));
		data_panel.add(battery_bar);
		
	}
}

