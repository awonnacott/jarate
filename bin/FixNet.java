package jarate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

public class FixNet extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	FixNet() {
		super();
		validateName();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Runtime.getRuntime().exec("networksetup -setairportpower airport off");
			Runtime.getRuntime().exec("networksetup -setairportpower airport on");
		} catch (IOException e1) {
			
		}
	}

	void validateName() {
		setEnabled(true);
		setText("Reconnect WiFi");
	}
}
