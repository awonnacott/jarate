package jarate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

class TempOn extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	TempOn() {
		super();
		validateName();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.guide.delete();
		validateName();
	}

	void validateName() {
		if (Main.guide.exists()) {
			setEnabled(true);
			setText("Temporary Unlock");
		} else {
			setEnabled(false);
			setText("Currently Unlocked");
		}
	}
}
