package de.thi.ufo.Helper;

public class WarningManager {
	private String warning_text;
	private int current_prio = 100;
	
	public WarningManager() {
		warning_text = "";
	}
	
	public void setNewText(String text, int prio) {
		if (prio < current_prio) {
			warning_text = text;
		}
	}
	public void removeLastText() {
		warning_text = "";
		current_prio = 100;
				
	}
	public String getWarningText() {
		return warning_text;
	}
}
