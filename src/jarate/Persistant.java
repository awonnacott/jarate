package jarate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

class Persistant extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	Persistant() {
		super();
		
		validateName();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(" action " + Main.properties.getProperty("enabled"));
		if (Main.properties.getProperty("enabled").equals("yes")) {
			Main.launchConfigRestore();
			System.out.println(" yes " + Main.properties.getProperty("enabled"));
			Main.properties.setProperty("enabled", "no");
			System.out.println(" no " + Main.properties.getProperty("enabled"));
		} else {
			try {
				Main.launchConfigSetup();
			} catch (IOException e1) {
			}
			System.out.println(" no " + Main.properties.getProperty("enabled"));
			Main.properties.setProperty("enabled", "yes");
			System.out.println(" yes " + Main.properties.getProperty("enabled"));
		}
		try {
			Runtime.getRuntime().exec("killall CSA");
		} catch (IOException e1) {
		}
		validateName();
	}

	void validateName() {
		System.out.println(" validate " + Main.properties.getProperty("enabled"));
		if (Main.properties.getProperty("enabled").equals("yes")) {
			System.out.println(" yes " + Main.properties.getProperty("enabled"));
			setText("Remove persistant lock");
		} else {
			System.out.println(" no " + Main.properties.getProperty("enabled"));
			Main.properties.setProperty("enabled", "no");
			System.out.println(" no " + Main.properties.getProperty("enabled"));
			setText("Persistant Unlock");
		}
	}
}