package jarate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

class Quit extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Quit() {
		super();
		setText ("Quit Jarate");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Jar.saveConfig();
		} catch (IOException e1) {
		}
		System.exit(0);
	}
}