package TzukEitan.GUI;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import TzukEitan.utils.Utils;

public class pnLauncherDistroyer extends JPanel{
	private JLabel lblLauncherDestroyer;
	
	public pnLauncherDistroyer(String LauncherDestroyerId,String type) {

		initLabelAndIcon(LauncherDestroyerId,type);

		add(lblLauncherDestroyer);
		setPreferredSize(new Dimension(100, 120));
		setVisible(true);

	}

	private void initLabelAndIcon(String MissileIntercepterId,String type) {

		lblLauncherDestroyer = new JLabel();
		lblLauncherDestroyer.setText(MissileIntercepterId);
		lblLauncherDestroyer.setIcon(Utils.getImageIcon(type.equals("Plane") ? Utils.PLANE_IMAGE : Utils.SHIP_IMAGE));
		lblLauncherDestroyer.setHorizontalAlignment(SwingConstants.CENTER);
		lblLauncherDestroyer.setBorder(BorderFactory.createEtchedBorder());
		lblLauncherDestroyer.setVerticalTextPosition(SwingConstants.TOP);
		lblLauncherDestroyer.setHorizontalTextPosition(JLabel.CENTER);
		lblLauncherDestroyer.setPreferredSize(new Dimension(70, 80));
	}

}
