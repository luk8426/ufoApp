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

public class TargetView extends UfoView{
	public JFrame frame; // To be removed
	private JTextField norden_text;
	private JTextField osten_text;
	private JTextField höhe_text;

	/**
	 * Initialize the contents of the frame.
	 */
	public TargetView() {
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
		
		JPanel zieleingabe_panel = new JPanel();
		zieleingabe_panel.setBounds(21, 11, 371, 347);
		blue_frame_lower.add(zieleingabe_panel);
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
		
		JLabel flughöhe_label = new JLabel("Flugh\u00F6he in m");
		flughöhe_label.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		zieleingabe_panel.add(flughöhe_label);
		
		JPanel höhe_panel = new JPanel();
		zieleingabe_panel.add(höhe_panel);
		höhe_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JLabel sollhöhe_label = new JLabel("Soll-H\u00F6he: ");
		sollhöhe_label.setHorizontalAlignment(SwingConstants.RIGHT);
		sollhöhe_label.setFont(new Font("Comic Sans MS", Font.ITALIC, 23));
		höhe_panel.add(sollhöhe_label);
		
		höhe_text = new JTextField();
		höhe_text.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		höhe_text.setForeground(new Color(192, 192, 192));
		höhe_text.setText("30");
		höhe_panel.add(höhe_text);
		höhe_text.setColumns(10);
		
		JPanel button_panel = new JPanel();
		zieleingabe_panel.add(button_panel);
		button_panel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JButton update_btn = new JButton("Update");
		button_panel.add(update_btn);
		JButton start_btn = new JButton("Start");
		button_panel.add(start_btn);
	}
}
