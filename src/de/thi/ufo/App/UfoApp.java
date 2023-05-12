package de.thi.ufo.App;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import de.thi.ufo.Views.StartView;
import de.thi.ufo.Views.UfoView;

import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class UfoApp {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UfoView current_view = new StartView();
					current_view.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
