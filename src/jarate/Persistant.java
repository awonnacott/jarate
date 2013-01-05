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
		System.out.println(" action " + Jar.properties.getProperty("enabled"));
		if (Jar.properties.getProperty("enabled").equals("yes")) {
			Jar.launchConfigRestore();
			System.out.println(" yes " + Jar.properties.getProperty("enabled"));
			Jar.properties.setProperty("enabled", "no");
			System.out.println(" no " + Jar.properties.getProperty("enabled"));
		} else {
			try {
				Jar.launchConfigSetup();
			} catch (IOException e1) {
			}
			System.out.println(" no " + Jar.properties.getProperty("enabled"));
			Jar.properties.setProperty("enabled", "yes");
			System.out.println(" yes " + Jar.properties.getProperty("enabled"));
		}
		try {
			Runtime.getRuntime().exec("killall CSA");
		} catch (IOException e1) {
		}
		validateName();
	}

	void validateName() {
		System.out.println(" validate " + Jar.properties.getProperty("enabled"));
		if (Jar.properties.getProperty("enabled").equals("yes")) {
			System.out.println(" yes " + Jar.properties.getProperty("enabled"));
			setText("Remove persistant lock");
		} else {
			System.out.println(" no " + Jar.properties.getProperty("enabled"));
			Jar.properties.setProperty("enabled", "no");
			System.out.println(" no " + Jar.properties.getProperty("enabled"));
			setText("Persistant Unlock");
		}
	}
}