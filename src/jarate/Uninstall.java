package jarate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

class Uninstall extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Uninstall() {
		super();
		validateName();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.config.delete();
		Main.persistant.delete();
		Main.launchConfigRestore();
		validateName();
		System.exit(0);
	}

	void validateName() {
		if (Main.config.exists()) {
			setEnabled(true);
			setText("Uninstall Jarate");
		} else {
			setEnabled(false);
			setText("No files to uninstall");
		}
	}
}