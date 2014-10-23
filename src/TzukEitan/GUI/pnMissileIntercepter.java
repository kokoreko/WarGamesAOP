package TzukEitan.GUI;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import TzukEitan.utils.Utils;

public class pnMissileIntercepter extends JPanel{
	private JLabel lblMissileIntercepter;

	
	public pnMissileIntercepter(String LauncherId) {

		initLabelAndIcon(LauncherId);

		add(lblMissileIntercepter);
		setPreferredSize(new Dimension(100, 120));
		setVisible(true);

	}

	private void initLabelAndIcon(String MissileIntercepterId) {

		lblMissileIntercepter = new JLabel();
		lblMissileIntercepter.setText(MissileIntercepterId);
		lblMissileIntercepter.setIcon(Utils.getImageIcon(Utils.IRON_DOME_IMAGE));
		lblMissileIntercepter.setHorizontalAlignment(SwingConstants.CENTER);
		lblMissileIntercepter.setBorder(BorderFactory.createEtchedBorder());
		lblMissileIntercepter.setVerticalTextPosition(SwingConstants.TOP);
		lblMissileIntercepter.setHorizontalTextPosition(JLabel.CENTER);
		lblMissileIntercepter.setPreferredSize(new Dimension(70, 80));
	}
}
