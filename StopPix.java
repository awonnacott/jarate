package jarate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

public class StopPix extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	StopPix() {
		super();
		validateName();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Runtime.getRuntime().exec("killall -9 CSA");
		} catch (IOException e1) {
			
		}
		Jar.CSAEnabled = false;
		validateName();
	}

	void validateName() {
		if (Jar.CSAEnabled) {
			setEnabled(true);
			setText("Stop Screencapture Software");
		} else {
			setEnabled(false);
			setText("Screencapture Disabled");
		}
	}
}
