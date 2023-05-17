package de.thi.ufo.Views;

import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import de.thi.ufo.App.RoundedPanel;
import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.UfoState;
import de.thi.ufo.Model.UfoPositions;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TargetView{
	public UfoApp app;
	public Container content_pane;
	public JLayeredPane top_layered_pane;
	
	private JLabel norden_text;
	private JLabel osten_text;
	private JLabel hoehe_text;
	
	private JLabel dest;
	private JLabel start;
	
	/**
	 * Initialize the contents of the frame.
	 */
	public TargetView(UfoApp p_app) {
		app = p_app;
		//frame.setBounds(100, 100, 450, 800);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new GridLayout(2, 0));
		content_pane = new Container();
		content_pane.setLayout(new GridLayout(2, 0));
		
// Here starts the upper Part of the App-Screen

		JPanel upper_panel = new JPanel();
		upper_panel.setBackground(new Color(255, 255, 255));
		//frame.getContentPane().add(upper_panel);
		content_pane.add(upper_panel);
		upper_panel.setLayout(null);
		
		JPanel blue_frame_upper = new RoundedPanel(35);
		blue_frame_upper.setBounds(10, 11, 414, 369);
		blue_frame_upper.setLayout(null);
		upper_panel.add(blue_frame_upper);
		
		JPanel zielanzeige_panel = new JPanel();
		zielanzeige_panel.setBackground(new Color(255, 255, 255));
		zielanzeige_panel.setBounds(20, 11, 371, 347);
		blue_frame_upper.add(zielanzeige_panel);
		zielanzeige_panel.setLayout(new BorderLayout());
		
		
		top_layered_pane = new JLayeredPane();
		top_layered_pane.setLayout(null); // Means AbsoluteLayout
		zielanzeige_panel.add(top_layered_pane);

		JLabel map = new JLabel();
		map.setIcon(new ImageIcon(TargetView.class.getResource("/de/thi/ufo/Resources/map_background.jpg")));
		map.setBounds(0, 0, 400, 400);
		top_layered_pane.add(map, -1);
		start = new JLabel();
		start.setIcon(new ImageIcon(TargetView.class.getResource("/de/thi/ufo/Resources/up_start.png")));
		start.setBounds(200-30, 200-30, 30, 30);
		top_layered_pane.add(start, 0);
		dest = new JLabel();
		dest.setIcon(new ImageIcon(TargetView.class.getResource("/de/thi/ufo/Resources/down_land.png")));
		dest.setBounds(100, 100, 30, 30);
		top_layered_pane.add(dest, 1);

		
// Here starts the lower Part of the App-Screen
	
		JPanel lower_panel = new JPanel();
		lower_panel.setBackground(new Color(255, 255, 255));
		//frame.getContentPane().add(lower_panel);
		content_pane.add(lower_panel);
		lower_panel.setLayout(null);
		
		JPanel blue_frame_lower = new RoundedPanel(35);
		blue_frame_lower.setBounds(10, 0, 414, 369);
		lower_panel.add(blue_frame_lower);
		blue_frame_lower.setLayout(null);
		
		JPanel zieleingabe_panel = new JPanel();
		zieleingabe_panel.setBackground(new Color(255, 255, 255));
		zieleingabe_panel.setBounds(21, 11, 371, 347);
		blue_frame_lower.add(zieleingabe_panel);
		zieleingabe_panel.setLayout(new GridLayout(7, 0, 0, 0));
		
		JLabel zieleingabe_label = new JLabel("Zielüberprüfung");
		zieleingabe_label.setFont(new Font("Comic Sans MS", Font.BOLD, 34));
		zieleingabe_label.setHorizontalAlignment(SwingConstants.CENTER);
		zieleingabe_panel.add(zieleingabe_label);
		
		JLabel entfernungen_label = new JLabel("Entfernungen in m");
		entfernungen_label.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		zieleingabe_panel.add(entfernungen_label);
		
		JPanel norden_panel = new JPanel();
		zieleingabe_panel.add(norden_panel);
		norden_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel norden_label = new JLabel("Nach Norden: ");
		norden_label.setBackground(new Color(255, 255, 255));
		norden_label.setHorizontalAlignment(SwingConstants.RIGHT);
		norden_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		norden_panel.add(norden_label);
		
		norden_text = new JLabel();
		norden_text.setBackground(new Color(255, 255, 255));
		//norden_text.setForeground(new Color(192, 192, 192));
		norden_text.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
		norden_text.setText("-200");
		norden_panel.add(norden_text);
		
		JPanel osten_panel = new JPanel();
		zieleingabe_panel.add(osten_panel);
		osten_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel osten_label = new JLabel("Nach Osten: ");
		osten_label.setBackground(new Color(255, 255, 255));
		osten_label.setHorizontalAlignment(SwingConstants.RIGHT);
		osten_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		osten_panel.add(osten_label);
		
		osten_text = new JLabel();
		osten_text.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
		//osten_text.setForeground(new Color(192, 192, 192));
		osten_text.setText("500");
		osten_panel.add(osten_text);
		
		JLabel flughoehe_label = new JLabel("Flugh\u00F6he in m");
		flughoehe_label.setBackground(new Color(255, 255, 255));
		flughoehe_label.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		zieleingabe_panel.add(flughoehe_label);
		
		JPanel hoehe_panel = new JPanel();
		zieleingabe_panel.add(hoehe_panel);
		hoehe_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel sollhoehe_label = new JLabel("Soll-H\u00F6he: ");
		sollhoehe_label.setHorizontalAlignment(SwingConstants.RIGHT);
		sollhoehe_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		hoehe_panel.add(sollhoehe_label);
		
		hoehe_text = new JLabel();
		hoehe_text.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
		//hoehe_text.setForeground(new Color(192, 192, 192));
		hoehe_text.setText("30");
		hoehe_panel.add(hoehe_text);
		
		JPanel button_panel = new JPanel();
		zieleingabe_panel.add(button_panel);
		button_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JButton back_btn = new JButton("Zurück");
		back_btn.addActionListener(e -> {
			app.frame.setContentPane(app.start_view.content_pane);
			app.frame.revalidate();
		});
		button_panel.add(back_btn);
		JButton start_btn = new JButton("Start");
		start_btn.addActionListener(e -> {
			zielanzeige_panel.remove(top_layered_pane);
			app.control_view.absolute_distance_label_val.setText(Double.toString(Math.round(app.ufo_model.positions.getInitalDistance() * 100.0) / 100.0) + " m");
			app.frame.setContentPane(app.control_view.content_pane);
			app.control_view.zielanzeige_panel.add(top_layered_pane);
			app.sim.setSpeedup(1);
			app.ufo_model.ufo_state = UfoState.STARTED;
			app.frame.revalidate();
		});
		button_panel.add(start_btn);
	}
	
	public void update() {
		hoehe_text.setText(Integer.toString(app.ufo_model.positions.getDesiredAltitude()));
		osten_text.setText(Double.toString(app.ufo_model.positions.getDestination().getX()));
		norden_text.setText(Double.toString(app.ufo_model.positions.getDestination().getY()));
		dest.setBounds(
				UfoPositions.positionInMap(app.ufo_model.positions.getDestination().getX()), 
				UfoPositions.positionInMap(app.ufo_model.positions.getDestination().getY()), 
				dest.getBounds().height, 
				dest.getBounds().width);
	}
}
