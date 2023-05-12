package de.thi.ufo.Views;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import de.thi.ufo.App.RoundedPanel;

import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class StartView extends UfoView{
	public JFrame frame; // To be removed
	private JTextField norden_text;
	private JTextField osten_text;
	private JTextField h�he_text;

	/**
	 * Initialize the contents of the frame.
	 */
	public StartView() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 0));
		
		JPanel willkommen_top_panel = new JPanel();
		frame.getContentPane().add(willkommen_top_panel);
		GridBagLayout gbl_willkommen_top_panel = new GridBagLayout();
		gbl_willkommen_top_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_willkommen_top_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_willkommen_top_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_willkommen_top_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		willkommen_top_panel.setLayout(gbl_willkommen_top_panel);
		
		JLabel ufo_icon_start = new JLabel("");
		ufo_icon_start.setHorizontalAlignment(SwingConstants.CENTER);
		ufo_icon_start.setVerticalAlignment(SwingConstants.TOP);
		ufo_icon_start.setIcon(new ImageIcon(StartView.class.getResource("/de/thi/ufo/Resources/ufo_small.png")));
		GridBagConstraints gbc_ufo_icon_start = new GridBagConstraints();
		gbc_ufo_icon_start.insets = new Insets(0, 0, 0, 0);
		gbc_ufo_icon_start.gridwidth = 18;
		gbc_ufo_icon_start.gridx = 0;
		gbc_ufo_icon_start.gridy = 1;
		willkommen_top_panel.add(ufo_icon_start, gbc_ufo_icon_start);
		
		JLabel willkommen_zu_label = new JLabel("Willkommen zu");
		willkommen_zu_label.setFont(new Font("Comic Sans MS", Font.BOLD, 31));
		GridBagConstraints gbc_willkommen_zu_label = new GridBagConstraints();
		gbc_willkommen_zu_label.anchor = GridBagConstraints.SOUTH;
		gbc_willkommen_zu_label.gridheight = 2;
		gbc_willkommen_zu_label.gridwidth = 5;
		gbc_willkommen_zu_label.insets = new Insets(0, 0, 5, 5);
		gbc_willkommen_zu_label.gridx = 6;
		gbc_willkommen_zu_label.gridy = 2;
		willkommen_top_panel.add(willkommen_zu_label, gbc_willkommen_zu_label);
		
		JLabel app_name_label = new JLabel("Deliver UFO");
		app_name_label.setForeground(new Color(43, 120, 228));
		app_name_label.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
		GridBagConstraints gbc_app_name_label = new GridBagConstraints();
		gbc_app_name_label.anchor = GridBagConstraints.NORTH;
		gbc_app_name_label.gridheight = 4;
		gbc_app_name_label.gridwidth = 14;
		gbc_app_name_label.insets = new Insets(0, 0, 0, 5);
		gbc_app_name_label.gridx = 2;
		gbc_app_name_label.gridy = 4;
		willkommen_top_panel.add(app_name_label, gbc_app_name_label);
		
		JPanel lower_panel = new JPanel();
		frame.getContentPane().add(lower_panel);
		lower_panel.setLayout(null);
		
		JPanel blue_frame = new RoundedPanel(35);
		blue_frame.setBounds(10, 0, 414, 369);
		lower_panel.add(blue_frame);
		blue_frame.setLayout(null);
		
		JPanel zieleingabe_panel = new JPanel();
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
		zieleingabe_panel.add(norden_panel);
		norden_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel norden_label = new JLabel("Nach Norden: ");
		norden_label.setHorizontalAlignment(SwingConstants.RIGHT);
		norden_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		norden_panel.add(norden_label);
		
		norden_text = new JTextField();
		norden_text.setForeground(new Color(192, 192, 192));
		norden_text.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		norden_text.setText("-200");
		norden_panel.add(norden_text);
		norden_text.setColumns(10);
		
		JPanel osten_panel = new JPanel();
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
		
		JLabel flugh�he_label = new JLabel("Flugh\u00F6he in m");
		flugh�he_label.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		zieleingabe_panel.add(flugh�he_label);
		
		JPanel h�he_panel = new JPanel();
		zieleingabe_panel.add(h�he_panel);
		h�he_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel sollh�he_label = new JLabel("Soll-H\u00F6he: ");
		sollh�he_label.setHorizontalAlignment(SwingConstants.RIGHT);
		sollh�he_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		h�he_panel.add(sollh�he_label);
		
		h�he_text = new JTextField();
		h�he_text.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		h�he_text.setForeground(new Color(192, 192, 192));
		h�he_text.setText("30");
		h�he_panel.add(h�he_text);
		h�he_text.setColumns(10);
		
		JButton check_btn = new JButton("Ziel \u00FCberpr\u00FCfen");
		zieleingabe_panel.add(check_btn);
		
		

	}
}