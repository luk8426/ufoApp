package de.thi.ufo.Views;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.FlyState;
import de.thi.ufo.Helper.RotateIcon;
import de.thi.ufo.Helper.RoundedPanel;
import de.thi.ufo.Helper.Simple3DPoint;
import de.thi.ufo.Helper.UfoState;
import de.thi.ufo.Helper.WarningManager;
import de.thi.ufo.Model.UfoPositions;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;

public class ControlView {
	private UfoApp app;
	public Container content_pane;
	private JLayeredPane top_layered_pane;
	public JPanel zielanzeige_panel;
	
	// Icons in Status panel
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
	private JLabel controlLabel;
	
	// Transparent Labels on Map
	JLabel warningLabel;
	private JLabel controlsPane;
	private WarningManager text_for_warning;


	/**
	 * Initialize the contents of the frame.
	 */
	public ControlView(UfoApp p_app) {
		app = p_app;
		content_pane = new Container();
		// Erneut die Zweigeteiltheit des Bildschirms
		content_pane.setLayout(new GridLayout(2, 0));
		// Übernehem einfach das obere Panel aus dem anderen View (Referenz)
		top_layered_pane = app.target_view.top_layered_pane;
		
		// Zunächst wieder der obere Teil
		text_for_warning = new WarningManager();
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
		
		// Die Banner oben und unten auf der Karte
		warningLabel = new JLabel("");
		warningLabel.setText("");
		warningLabel.setBounds(0, 0, 371, 42);
		warningLabel.setBackground(new Color(176,224,230, 170));
		warningLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		warningLabel.setForeground(Color.RED);
		warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		warningLabel.setOpaque(true);
		top_layered_pane.add(warningLabel, 0);
		controlsPane = new JLabel("");
		controlsPane.setBounds(0, zielanzeige_panel.getBounds().height-60, 371, 60);
		controlsPane.setBackground(new Color(176,224,230, 170));
		controlsPane.setForeground(Color.RED);
		controlsPane.setHorizontalAlignment(SwingConstants.CENTER);
		top_layered_pane.add(controlsPane, 1);

		
		// Der Steuerungsbanner mit Inhalt ausgestattet
		controlLabel = new JLabel("Steuerung");
		controlLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 31));
		controlLabel.setHorizontalAlignment(SwingConstants.CENTER);
		controlLabel.setBounds(0, zielanzeige_panel.getBounds().height-60, 371, 50);
		controlLabel.setVisible(false);
		stop_btn = new JButton();
		stop_btn.setBorderPainted(false);
		stop_btn.setFocusPainted(false);
		stop_btn.setContentAreaFilled(false);
		stop_btn.addActionListener(e -> {
			if (app.ufo_model.getUfoState() == UfoState.STARTED)		app.ufo_model.stop_requested = true;
			else if(app.ufo_model.getUfoState() == UfoState.STOPPED) 	app.ufo_model.continue_requested = true;
		});
		return_btn = new JButton();
		return_btn.setBorderPainted(false);
		return_btn.setFocusPainted(false);
		return_btn.setContentAreaFilled(false);
		return_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/return.png")));
		return_btn.addActionListener(e -> {
			if(!app.ufo_model.disable_return) {
				app.ufo_model.disable_return = true;
				app.ufo_model.positions.setDestination(new Simple3DPoint(0, 0));
				return_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/return_green.png")));
				if (app.ufo_model.getUfoState() == UfoState.ARRIVED)app.ufo_model.return_requested_after_arrival = true;
			}
		});
		
		// Und natürlich darf auch das UFO nicht auf der Karte fehlen
		ufo_icon = new RotateIcon();
		
		top_layered_pane.add(ufo_icon, 1);
		top_layered_pane.add(stop_btn, 0);
		top_layered_pane.add(return_btn, 0);
		top_layered_pane.add(controlLabel, 0);

		
		// Ab hier entsteht wieder der untere Teil des GUIs
	
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
		
		// Erstellen der Überschrift und der Status Icons
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
		icons_panel.setLayout(new GridLayout(1, 6, 10, 0));
		icons_panel.setBackground(Color.WHITE);
		
		battery_low_icon = new JLabel();
		battery_low_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/battery_low_gray.png")));
		battery_low_icon.setHorizontalAlignment(SwingConstants.CENTER);
		icons_panel.add(battery_low_icon);
		
		alt_limit_icon = new JLabel();
		alt_limit_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/arrow-limit-gray.png")));
		alt_limit_icon.setHorizontalAlignment(SwingConstants.RIGHT);
		icons_panel.add(alt_limit_icon);
		
		obstacle_icon = new JLabel();
		obstacle_icon.setHorizontalAlignment(SwingConstants.CENTER);
		obstacle_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/obstacle-gray.png")));
		icons_panel.add(obstacle_icon);
		
		dest_reached_icon = new JLabel();
		dest_reached_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/marker-check-gray.png")));
		dest_reached_icon.setHorizontalAlignment(SwingConstants.CENTER);
		icons_panel.add(dest_reached_icon);
		
		stop_icon = new JLabel();
		stop_icon.setHorizontalAlignment(SwingConstants.CENTER);
		stop_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-gray.png")));
		icons_panel.add(stop_icon);
		
		flying_icon = new JLabel();
		flying_icon.setHorizontalAlignment(SwingConstants.CENTER);
		flying_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/airplane-green.png")));
		icons_panel.add(flying_icon);
		
		// Daten Tabelle
		JPanel data_panel = new JPanel();
		data_panel.setBackground(new Color(255, 255, 255));
		status_panel.add(data_panel, BorderLayout.CENTER);
		status_panel.setBackground(Color.WHITE);
		data_panel.setLayout(new GridLayout(6, 2, 0, 0));
		
		// Labels für die Daten (Linke Spalte der Tabelle)
		JLabel absolute_distance_label_txt = new JLabel("Gesamtdistanz");
		absolute_distance_label_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		absolute_distance_label_txt.setHorizontalAlignment(SwingConstants.LEFT);
		absolute_distance_label_txt.setBackground(Color.WHITE);
		data_panel.add(absolute_distance_label_txt);
		absolute_distance_label_val = new JLabel("360m");
		absolute_distance_label_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		absolute_distance_label_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(absolute_distance_label_val);
		
		JLabel altitude_txt = new JLabel("Flugh\u00F6he");
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
		
		JLabel vertical_txt = new JLabel("Vertikale Bew.");
		vertical_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		vertical_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(vertical_txt);
		vertical_val = new JLabel("");
		vertical_val.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/down-arrow-blue.png"))); // Kein Text sondern ein Icon als Wert rechts in der Tabelle
		vertical_val.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		vertical_val.setHorizontalAlignment(SwingConstants.RIGHT);
		data_panel.add(vertical_val);
		
		JLabel remaining_distance_label_txt = new JLabel("Distanz zum Ziel");
		remaining_distance_label_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		remaining_distance_label_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(remaining_distance_label_txt);
		// Die zwei Fortschrittsbalken
		progress_bar = new JProgressBar();
		progress_bar.setForeground(new Color(43, 120, 228));
		progress_bar.setStringPainted(true);
		progress_bar.setValue(20);
		progress_bar.setBackground(Color.white);
		progress_bar.setBorder(new EmptyBorder(5, 20, 5, 0));
		progress_bar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		data_panel.add(progress_bar);
		
		JLabel battery_txt = new JLabel("Batterieladung");
		battery_txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		battery_txt.setHorizontalAlignment(SwingConstants.LEFT);
		data_panel.add(battery_txt);
		battery_bar = new JProgressBar();
		battery_bar.setValue(100);
		battery_bar.setStringPainted(true);
		battery_bar.setForeground(new Color(0, 128, 0));
		battery_bar.setBackground(Color.white);
		battery_bar.setBorder(new EmptyBorder(5, 20, 5, 0));
		battery_bar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		data_panel.add(battery_bar);
	}
	
	// Periodisch aus der Hauptschleife aufgerufen
	// Aktualisierung alle relevanter Werte
	public void update() {
		controlsPane.setOpaque(true);
		checkWarningsAndButtons();
		altitude_val.setText(Double.toString(Math.floor(app.sim.getZ())) + " m");
		velocity_val.setText(Double.toString(Math.round(app.sim.getV() * 100.0) / 100.0) + " km/h");
		vertical_val.setIcon(verticalIcon());
		updateProgressBar();
		updateUfoIcon();
		updateBatteryBar();
		updateWarningText();
		
	}

	private void updateWarningText() {
		String cur_text = text_for_warning.getWarningText();
		if (!cur_text.equals(warningLabel.getText())) {
			warningLabel.setText(cur_text);		
		};
	}
	
	// Unser UFO bewegen
	private void updateUfoIcon() {
		ufo_icon.setRotation(-app.sim.getD());
		Simple3DPoint new_location = UfoPositions.positionInMap(new Simple3DPoint(app.sim.getX(), app.sim.getY()));
		ufo_icon.setBounds((int)new_location.getX(), (int)new_location.getY(), 50, 50);				
	}
	
	// Die Fortschrittsbalken aktualisieren
	private void updateProgressBar() {
		double remaining_distance = app.ufo_model.positions.distanceToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY(), app.sim.getZ()));
		remaining_distance = Math.round((Math.abs(remaining_distance-1)) * 100.0) / 100.0;
		if (remaining_distance < 1) remaining_distance = 0;
		progress_bar.setString(Double.toString(remaining_distance) + " m");
		progress_bar.setValue((int) (100 * (1-(remaining_distance/app.ufo_model.positions.getInitalDistance()))));
		if (progress_bar.getValue() >= 97) progress_bar.setForeground(new Color(0, 128, 0));
		else progress_bar.setForeground(new Color(43, 120, 228));
	}

	private void updateBatteryBar() {
		battery_bar.setValue((int) (100 * (1-(app.sim.getDist()/3000))));
		if (battery_bar.getValue() < 10) {
			battery_bar.setForeground(new Color(136, 8, 8));
			text_for_warning.setNewText("Vorsicht! Batteriestand niedrig!", 2);
			
		}
		else if (battery_bar.getValue() < 20) battery_bar.setForeground(new Color(255,165,0));
	}

	private void checkWarningsAndButtons() {
		// Nochmals eine kleine Zustandsmaschine um die Symbole richtig zu aktualisieren
		switch(app.ufo_model.getUfoState()) {
		case STARTED:
			text_for_warning.setNewText("Sicheres autonomes Fliegen", 10);
			flying_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/airplane-green.png")));
			stop_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-gray.png")));
			dest_reached_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/marker-check-gray.png")));
			if (app.ufo_model.stop_requested) { 
				stop_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-inner-orange.png")));
				text_for_warning.setNewText("Das Ufo wird angehalten", 1);
			}else 								
				stop_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-inner-red.png")));
			stop_btn.setBounds(zielanzeige_panel.getBounds().width-90, zielanzeige_panel.getBounds().height-55, 80, 50);
			stop_btn.setVerticalTextPosition(JLabel.CENTER);
			stop_btn.setHorizontalTextPosition(JLabel.CENTER);
			return_btn.setBounds(10, zielanzeige_panel.getBounds().height-55, 80, 50);	
			return_btn.setVerticalTextPosition(JLabel.CENTER);
			return_btn.setHorizontalTextPosition(JLabel.CENTER);
			controlLabel.setVisible(true);
			ufo_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/navigation-blue.png")));
			break;
		case STOPPED:
			stop_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/play-button-inner-green.png")));
			flying_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/airplane-gray.png")));
			stop_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-red.png")));
			text_for_warning.setNewText("Das Ufo wurde angehalten", 1);			
			break;
		case ARRIVED:
			stop_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/stop-button-inner-gray.png")));
			flying_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/airplane-gray.png")));
			dest_reached_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/marker-check-green.png")));
			ufo_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/navigation-green.png")));
			text_for_warning.setNewText("Das Ufo ist gelandet", 0);
			if (app.ufo_model.disable_return&&app.ufo_model.positions.distanceToDestination(new Simple3DPoint(app.sim.getX(), app.sim.getY(), app.sim.getZ()))<3) 
				return_btn.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/return-gray.png")));
			
		default:
			break;
		}
		if (battery_bar.getValue() < 10) battery_low_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/battery_low_red.png")));
		else battery_low_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/battery_low_gray.png")));
		if (app.sim.getZ() >= UfoPositions.MAX_ALTITUDE) {
			alt_limit_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/arrow-limit-red.png")));
			text_for_warning.setNewText("Vorsicht! Max. Höhe überschritten!", 4);
		}
		else alt_limit_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/arrow-limit-gray.png")));
		if (app.ufo_model.getFlyState()==FlyState.DETOUR) obstacle_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/obstacle-color.png")));
		else obstacle_icon.setIcon(new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/obstacle-gray.png")));	
		if (app.sim.getRadar()!=-1||app.ufo_model.getFlyState()==FlyState.DETOUR)	{
			text_for_warning.setNewText("Vorsicht! Kollision möglich!", 3);
		}
	}

	private ImageIcon verticalIcon() {
		if(app.sim.getI()==0 || app.sim.getV()==0) 		return (new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/straight-arrow-blue.png")));
		else if(app.sim.getI()<0) 						return (new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/down-arrow-blue.png")));
		else 											return (new ImageIcon(ControlView.class.getResource("/de/thi/ufo/Resources/up-arrow-blue.png")));
	}
}

