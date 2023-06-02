package de.thi.ufo.Helper;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

///////////////////////////////////////////////////////////////////////////
//Hilfsklasse um Text zu entfernen beim initalen Klicken in das Textfeld// 
///////////////////////////////////////////////////////////////////////////

public class TextFieldFocusListener implements FocusListener {
    private boolean first_focus;
    private JTextField textfield;

    public TextFieldFocusListener(JTextField textfield) {
    	super();
    	this.first_focus = true;
    	this.textfield = textfield;
    }
    
    @Override
    public void focusGained(FocusEvent e){if (first_focus) textfield.setText("");}
	@Override
	public void focusLost(FocusEvent e) {
		first_focus = false;
		textfield.setForeground(new Color(43, 120, 228));
	}

}
