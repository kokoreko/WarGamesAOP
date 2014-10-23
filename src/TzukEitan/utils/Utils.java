package TzukEitan.utils;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utils {
	public static final int SECOND = 1000;
	public static final int FLY_TIME = 3;
	public static final int LAUNCH_DURATION = 1000;
	public static final String MISSILE_IMAGE = "/TzukEitan/images/Missile.png";
	public static final String LAUNCHER_IMAGE = "/TzukEitan/images/MissileLauncher.png";
	public static final String PLANE_IMAGE = "/TzukEitan/images/plane.png";
	public static final String SHIP_IMAGE = "/TzukEitan/images/Ship.png";
	public static final String IRON_DOME_IMAGE = "/TzukEitan/images/IronDome.png";
	
	public static final Font PANEL_FONT = new Font("Arial", Font.PLAIN, 9);
	public static final Font FONT = new Font("Arial", Font.PLAIN, 16);
	
	private static final double DESTRACOTR_SUCCES_RATE = 0.95;
	private static final double SUCCES_RATE = 0.7;
	private static final double IS_HIDDEN_RATE = 0.5;
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	// create local date
	public static String getCurrentTime() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
		
		return ldt.format(dtf);
	}
	
	// Image handler
	public static Image getImage(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}

		URL imageURL = Utils.class.getResource(name);
		if (imageURL == null) {
			return null;
		}

		return Toolkit.getDefaultToolkit().createImage(imageURL);
	}
	public static ImageIcon getImageIcon(String name) {
		Image image = getImage(name);
		if (image == null) {
			return null;
		}
		return new ImageIcon(image);
	}
	// delete all old logs in the folder
	public static void deleteFolder() {
		File file = new File("log");
		File[] files = file.listFiles();

		if (files != null){
			for (File f : files){
				f.delete();
			}
		}
	}

	/** randomize the success rate of missile **/
	public static boolean randomSuccesRate() {
		return Math.random() < SUCCES_RATE;
	}

	/** randomize the success rate of destructor **/
	public static boolean randomDestractorSucces() {
		return Math.random() < DESTRACOTR_SUCCES_RATE;
	}

	/** randomize if launcher will be hidden or not **/
	public static boolean randomIsHidden() {
		return Math.random() < IS_HIDDEN_RATE;
	}
	
	/** Capitalize any given string **/
	public static String capitalize(String s) {
        if (s.length() == 0){
        	return s;
        }
        
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
