package TzukEitan.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import TzukEitan.utils.Utils;

import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;



public class pnMissile extends JPanel{
	

			
	private JLabel lblMissile;
	private JScrollPane jspMissileInfo;
	private JTextArea jtaMissileInfo;
	private String destination;
	
	public pnMissile(String myMunitionsId, String missileId,
			String destination, int damage) {
		this.destination = destination;
		initLabelAndIcon(missileId);
		setLayout(new MigLayout("", "[124px]", "[75px][75px]"));
		add(lblMissile, "cell 0 0,grow");
		
		jtaMissileInfo = new JTextArea("Launched by: " + myMunitionsId + "\nTo: " + destination 
				+ "\nDamage: " + damage);
		jtaMissileInfo.setFont(Utils.PANEL_FONT);
		jspMissileInfo = new JScrollPane(jtaMissileInfo);
		add(jspMissileInfo, "cell 0 1,grow");
		
		setPreferredSize(new Dimension(100, 146));
		setVisible(true);
		
	}

	private void initLabelAndIcon(String missileId) {

		lblMissile = new JLabel();
		lblMissile.setText(missileId);
		lblMissile.setIcon(Utils.getImageIcon(Utils.MISSILE_IMAGE));
		lblMissile.setHorizontalAlignment(SwingConstants.CENTER);
		lblMissile.setBorder(BorderFactory.createEtchedBorder());
		lblMissile.setVerticalTextPosition(SwingConstants.TOP);
		lblMissile.setHorizontalTextPosition(JLabel.CENTER);
		lblMissile.setPreferredSize(new Dimension(70, 80));
	}
	
	public String getDestination(){
		return destination;
	}
}
