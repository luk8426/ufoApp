package de.thi.ufo.Views;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import de.thi.ufo.App.UfoApp;
import de.thi.ufo.Helper.RoundedPanel;
import de.thi.ufo.Helper.Simple3DPoint;
import de.thi.ufo.Helper.TextFieldFocusListener;
import de.thi.ufo.Model.UfoPositions;

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
	private JPanel check_failed_panel;

	/**
	 * Initialize the contents of the frame.
	 */
	public StartView(UfoApp p_app) {
		app = p_app;
		content_pane = app.frame.getContentPane();
		content_pane.setLayout(new GridLayout(2, 0));
		JLayeredPane willkommen_layered_panel = new JLayeredPane();
		willkommen_layered_panel.setLayout(null);
		JPanel willkommen_top_panel = new JPanel();
		willkommen_top_panel.setBounds(0, 0, 450, 400);
		willkommen_layered_panel.add(willkommen_top_panel, -1);
		content_pane.add(willkommen_layered_panel);
		willkommen_top_panel.setLayout(null);
		willkommen_top_panel.setBackground(Color.WHITE);
		
		check_failed_panel = new JPanel();//(25, true);
		check_failed_panel.setVisible(false);
		check_failed_panel.setBackground(new Color(176,224,230, 230));
		check_failed_panel.setLayout(new GridLayout(1, 0));
		check_failed_panel.setBounds(20, 0, 390, 210);	
		
		JPanel check_failed_text = new JPanel();
		check_failed_text.setLayout(new GridLayout(3,1));
		JLabel error = new JLabel();
		error.setIcon(new ImageIcon(StartView.class.getResource("/de/thi/ufo/Resources/error.png")));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		error.setVerticalAlignment(SwingConstants.BOTTOM);
		JLabel invalid = new JLabel("Ungültige Werte");
		invalid.setHorizontalAlignment(SwingConstants.CENTER);
		invalid.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
		invalid.setForeground(Color.RED);
		invalid.setBounds(0, 20, 370, 15);
		JLabel reason_inv = new JLabel("");
		reason_inv.setHorizontalAlignment(SwingConstants.CENTER);
		reason_inv.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		reason_inv.setBounds(0, 80, 370, 15);
		check_failed_text.setBackground(new Color(176,224,230, 230));
		check_failed_text.add(error);
		check_failed_text.add(invalid);
		check_failed_text.add(reason_inv);
		check_failed_panel.add(check_failed_text);

		willkommen_layered_panel.add(check_failed_panel, 0);

		
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
		
		JLabel entfernungen_label = new JLabel("Entfernungen:");
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
		
		JPanel norden_text_panel = new JPanel();
		norden_text_panel.setBackground(new Color(255, 255, 255));
		norden_text_panel.setLayout(null);
		norden_panel.add(norden_text_panel);
		norden_text = new JTextField("-200", 10);
		norden_text.addFocusListener(new TextFieldFocusListener(norden_text));
		norden_text.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {}
			@Override public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				norden_text.setText(norden_text.getText().replaceAll("[^0-9|-]", ""));				
			}});
		norden_text.setLocation(5, 7);
		norden_text.setForeground(new Color(0, 0, 255, 100));
		norden_text.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		norden_text.setSize(new Dimension(80, 35));
		JLabel m_label_norden = new JLabel("m");
		m_label_norden.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		m_label_norden.setBounds(90, 0, 40, 40);
		norden_text_panel.add(m_label_norden);		
		norden_text_panel.add(norden_text);		
		
		JPanel osten_panel = new JPanel();
		osten_panel.setBackground(new Color(255, 255, 255));
		zieleingabe_panel.add(osten_panel);
		osten_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel osten_label = new JLabel("Nach Osten: ");
		osten_label.setHorizontalAlignment(SwingConstants.RIGHT);
		osten_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		osten_panel.add(osten_label);
		JPanel osten_text_panel = new JPanel();
		osten_text_panel .setBackground(new Color(255, 255, 255));
		osten_text_panel .setLayout(null);
		osten_panel.add(osten_text_panel);
		osten_text = new JTextField("500", 10);
		osten_text.addFocusListener(new TextFieldFocusListener(osten_text));
		osten_text.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {}
			@Override public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				osten_text.setText(osten_text.getText().replaceAll("[^0-9|-]", ""));				
			}});
		osten_text.setLocation(5, 7);
		osten_text.setForeground(new Color(0, 0, 255, 100));
		osten_text.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		osten_text.setSize(new Dimension(80, 35));
		JLabel m_label_osten = new JLabel("m");
		m_label_osten.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		m_label_osten.setBounds(90, 0, 40, 40);
		osten_text_panel.add(m_label_osten);		
		osten_text_panel.add(osten_text);	
		
		JLabel flughoehe_label = new JLabel("Flugh\u00F6he: ");
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
		
		JPanel hoehe_text_panel = new JPanel();
		hoehe_text_panel .setBackground(new Color(255, 255, 255));
		hoehe_text_panel .setLayout(null);
		hoehe_panel.add(hoehe_text_panel);
		hoehe_text = new JTextField("30", 10);
		hoehe_text.addFocusListener(new TextFieldFocusListener(hoehe_text));
		hoehe_text.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {}
			@Override public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				hoehe_text.setText(hoehe_text.getText().replaceAll("[^0-9]", ""));				
			}});
		hoehe_text.setLocation(5, 7);
		hoehe_text.setForeground(new Color(0, 0, 255, 100));
		hoehe_text.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		hoehe_text.setSize(new Dimension(80, 35));
		JLabel m_label_hoehe = new JLabel("m "); // + Integer.toString(UfoPositions.MAX_ALTITUDE));
		m_label_hoehe.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		m_label_hoehe.setBounds(90, 0, 40, 40);
		hoehe_text_panel.add(m_label_hoehe);		
		hoehe_text_panel.add(hoehe_text);	
		
		JButton check_btn = new JButton("Ziel \u00FCberpr\u00FCfen");
		check_btn.setBackground(new Color(255, 255, 255));
		check_btn.requestFocus();
		check_btn.addActionListener(e -> {
			// Set Destination in Model
			int osten_koord, norden_koord, hoehe;
			try {
				osten_koord = Integer.parseInt(osten_text.getText());
				norden_koord = Integer.parseInt(norden_text.getText());
				hoehe = Integer.parseInt(hoehe_text.getText());
			}catch (NumberFormatException exception) {
				return;
			}
			app.ufo_model.positions.setDestination(new Simple3DPoint(osten_koord, norden_koord));
			app.ufo_model.positions.setDesiredAltitude(hoehe);
			if (app.ufo_model.positions.isDestinationValid()&&hoehe<=UfoPositions.MAX_ALTITUDE) {
				app.target_view.update();
				app.frame.setContentPane(app.target_view.content_pane);
				app.frame.revalidate();
			}else {
				if(hoehe>UfoPositions.MAX_ALTITUDE) {
					reason_inv.setText("Die Flughöhe ist auf maximal 50 m begrenzt");
				}else {
					reason_inv.setText("Das Ziel darf maximal 1000m entfernt sein");					
				}
				
				check_failed_panel.setVisible(true);
			}
		});
		zieleingabe_panel.add(check_btn);
		
		

	}
}
