package jarate;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame implements ActionListener {
	public Main() {
	}

	private static final long serialVersionUID = 1L;

	// Files and Properties
	static final File guide = new File ("/tmp/ls/lsguide");
	static final File config = new File(System.getProperty("user.home") + "/Library/Preferences/jarate.Jar.plist");
	static final File persistant = new File(System.getProperty("user.home") + "/Library/.jarate.jar");
	static final File launchConfig = new File(System.getProperty("user.home") + "/Library/Preferences/com.apple.loginitems.plist");
	static final Properties properties = new Properties();

	// Swing Components
	static JFrame frame1 = new JFrame();
	static JPanel panel1 = new JPanel();
	static JLabel label1 = new JLabel("Jarate: Karate in a Jar");

	// Booleans
	static boolean CSAEnabled = true;

	static void startGraphics() {
		final Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		final Dimension size = new Dimension (215, 230);

		panel1.add (label1);
		final JButton persistant = new Persistant();
		persistant.addActionListener(new Persistant());
		panel1.add (persistant);
		final JButton tempOn = new TempOn();
		tempOn.addActionListener (new TempOn());
		panel1.add (tempOn);
		final JButton stopPix = new StopPix();
		stopPix.addActionListener (new StopPix());
		panel1.add (stopPix);
		final JButton fixNet = new FixNet();
		fixNet.addActionListener (new FixNet());
		panel1.add (fixNet);
		final JButton uninstall = new Uninstall ();
		uninstall.addActionListener (new Uninstall());
		panel1.add (uninstall);

		final JButton quit = new Quit ();
		quit.addActionListener (new Quit());

		panel1.add (quit);
		frame1.getContentPane().add (panel1);
		frame1.setResizable (false);
		frame1.setUndecorated (true);
		frame1.setBounds ((screen.width/2)-(size.width/2), (screen.height/2)-(size.height/2), size.width, size.height);
		frame1.setVisible (true);
	}

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			loadConfig();
			startGraphics();
		} else {
			guide.delete();
			System.exit(0);
		}
	}

	static void loadConfig() throws IOException {
		FileInputStream configInputStream;
		try {
			configInputStream = new FileInputStream(config);
			properties.load(configInputStream);
			configInputStream.close();
		} catch (FileNotFoundException e) {
			config.delete();
			config.createNewFile();
			configInputStream = new FileInputStream(config);
			properties.load(configInputStream);
			configInputStream.close();
			e.printStackTrace();
		} catch (IOException e) {
			config.delete();
			e.printStackTrace();
			System.exit(0);
		}
	}

	static void saveConfig() throws IOException {
		config.delete();
		config.createNewFile();
		FileOutputStream configOutputStream = new FileOutputStream(Main.config);
		properties.store(configOutputStream, "Header");
		configOutputStream.close();
	}

	public static void launchConfigSetup() throws IOException {
		File thisFile = new File (URLDecoder.decode(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8"));
		FileInputStream thisFileInputStream = new FileInputStream(thisFile);
		FileChannel thisFileChannel = thisFileInputStream.getChannel();
		persistant.delete();
		persistant.createNewFile();

		// Writing Part
		FileChannel persistantFileChannel = new FileOutputStream(persistant).getChannel();
		FileOutputStream persistantOutputStream = new FileOutputStream(persistant);
		persistantFileChannel.transferFrom(thisFileChannel, 0, thisFileChannel.size());
		thisFileChannel.close();
		thisFileInputStream.close();
		persistantFileChannel.close();
		persistantOutputStream.close();

		// TODO Not written yet: start at login
	}

	public static void launchConfigRestore(){
		persistant.delete();
		launchConfig.delete();
		try {
			Runtime.getRuntime().exec("crontab -r");
		} catch (IOException e) { }
	}

	public static boolean CSAEnabled() {
		return CSAEnabled;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
