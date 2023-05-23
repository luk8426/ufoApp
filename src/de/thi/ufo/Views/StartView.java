package de.thi.ufo.Views;

import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import de.thi.ufo.App.RoundedPanel;
import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.Simple3DPoint;

import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;

public class StartView{
	private UfoApp app;
	public Container content_pane;
	private JTextField norden_text;
	private JTextField osten_text;
	private JTextField hoehe_text;

	/**
	 * Initialize the contents of the frame.
	 */
	public StartView(UfoApp p_app) {
		app = p_app;
		content_pane = app.frame.getContentPane();
		content_pane.setLayout(new GridLayout(2, 0));		
		JPanel willkommen_top_panel = new JPanel();
		content_pane.add(willkommen_top_panel);
		//GridBagLayout gbl_willkommen_top_panel = new GridBagLayout();
		//gbl_willkommen_top_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		//gbl_willkommen_top_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		//gbl_willkommen_top_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		//gbl_willkommen_top_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		willkommen_top_panel.setLayout(null);
		willkommen_top_panel.setBackground(Color.WHITE);
		
		JLabel ufo_icon_start = new JLabel("");
		ufo_icon_start.setHorizontalAlignment(SwingConstants.CENTER);
		ufo_icon_start.setVerticalAlignment(SwingConstants.TOP);
		ufo_icon_start.setIcon(new ImageIcon(StartView.class.getResource("/de/thi/ufo/Resources/ufo_small.png")));
		ufo_icon_start.setBounds((450/2)-(205/2), 0, 205, 205);
		willkommen_top_panel.add(ufo_icon_start);
		
		JLabel willkommen_zu_label = new JLabel("Willkommen zu");
		willkommen_zu_label.setFont(new Font("Comic Sans MS", Font.BOLD, 31));
		willkommen_zu_label.setHorizontalAlignment(SwingConstants.CENTER);
		willkommen_zu_label.setBounds(0, 220, 450, 50);
		willkommen_top_panel.add(willkommen_zu_label);
		
		JLabel app_name_label = new JLabel("Deliver UFO");
		app_name_label.setForeground(new Color(43, 120, 228));
		app_name_label.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
		app_name_label.setHorizontalAlignment(SwingConstants.CENTER);
		app_name_label.setBounds(0, 280, 450, 70);
		willkommen_top_panel.add(app_name_label);
		
		JPanel lower_panel = new JPanel();
		lower_panel.setBackground(new Color(255, 255, 255));
		//frame.getContentPane().add(lower_panel);
		content_pane.add(lower_panel);
		lower_panel.setLayout(null);
		
		JPanel blue_frame = new RoundedPanel(35);
		blue_frame.setBounds(10, 0, 414, 369);
		lower_panel.add(blue_frame);
		blue_frame.setLayout(null);
		
		JPanel zieleingabe_panel = new JPanel();
		zieleingabe_panel.setBackground(new Color(255, 255, 255));
		zieleingabe_panel.setBounds(21, 11, 371, 347);
		blue_frame.add(zieleingabe_panel);
		zieleingabe_panel.setLayout(new GridLayout(7, 0, 0, 0));
		
		JLabel zieleingabe_label = new JLabel("Zieleingabe");
		zieleingabe_label.setFont(new Font("Comic Sans MS", Font.BOLD, 34));
		zieleingabe_label.setHorizontalAlignment(SwingConstants.CENTER);
		zieleingabe_panel.add(zieleingabe_label);
		
		JLabel entfernungen_label = new JLabel("Entfernungen in m");
		entfernungen_label.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		zieleingabe_panel.add(entfernungen_label);
		
		JPanel norden_panel = new JPanel();
		norden_panel.setBackground(new Color(255, 255, 255));
		zieleingabe_panel.add(norden_panel);
		norden_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel norden_label = new JLabel("Nach Norden: ");
		norden_label.setHorizontalAlignment(SwingConstants.RIGHT);
		norden_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		norden_panel.add(norden_label);
		
		norden_text = new JTextField("-200", 10);
		norden_text.setForeground(new Color(192, 192, 192));
		norden_text.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		//norden_text.setText("-200");
		//norden_text.setSize(new Dimension(100, 20));

		norden_panel.add(norden_text);		
		JPanel osten_panel = new JPanel();
		osten_panel.setBackground(new Color(255, 255, 255));
		zieleingabe_panel.add(osten_panel);
		osten_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel osten_label = new JLabel("Nach Osten: ");
		osten_label.setHorizontalAlignment(SwingConstants.RIGHT);
		osten_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		osten_panel.add(osten_label);
		
		osten_text = new JTextField();
		osten_text.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		osten_text.setForeground(new Color(192, 192, 192));
		osten_text.setText("500");
		osten_panel.add(osten_text);
		osten_text.setColumns(10);
		
		JLabel flughoehe_label = new JLabel("Flugh\u00F6he in m");
		flughoehe_label.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		zieleingabe_panel.add(flughoehe_label);
		
		JPanel hoehe_panel = new JPanel();
		hoehe_panel.setBackground(new Color(255, 255, 255));
		zieleingabe_panel.add(hoehe_panel);
		hoehe_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel sollhoehe_label = new JLabel("Soll-H\u00F6he: ");
		sollhoehe_label.setHorizontalAlignment(SwingConstants.RIGHT);
		sollhoehe_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		hoehe_panel.add(sollhoehe_label);
		
		hoehe_text = new JTextField();
		hoehe_text.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		hoehe_text.setForeground(new Color(192, 192, 192));
		hoehe_text.setText("30");
		hoehe_panel.add(hoehe_text);
		hoehe_text.setColumns(10);
		
		JButton check_btn = new JButton("Ziel \u00FCberpr\u00FCfen");
		check_btn.setBackground(new Color(255, 255, 255));
		check_btn.addActionListener(e -> {
			// Set Destination in Model
			try {
				app.ufo_model.positions.setDestination(new Simple3DPoint(Integer.parseInt(osten_text.getText()),-Integer.parseInt(norden_text.getText())));
				app.ufo_model.positions.setDesiredAltitude(Integer.parseInt(hoehe_text.getText()));
			}catch (NumberFormatException exception) {
				return;
			}
			if (app.ufo_model.positions.isDestinationValid()) {
				app.target_view.update();
				app.frame.setContentPane(app.target_view.content_pane);
				app.frame.revalidate();
			}
		});
		zieleingabe_panel.add(check_btn);
		
		

	}
}
