package TzukEitan.GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import TzukEitan.listeners.WarEventUIListener;
import TzukEitan.utils.Utils;

public class frmInterceptMissile extends JFrame {

	private boolean state = true;
	private JPanel mainPanel,downPanel,panel,panel1;;
	private JButton btnInterceptMissile;
	private JLabel lblChooseAMissile;
	private JComboBox cmbChooseMissile;
	private Vector<String> allMissilesId =null;
	
	public frmInterceptMissile(List<WarEventUIListener> allListeners) {
		allMissilesId = new Vector<String>();
		for (WarEventUIListener l : allListeners) {
			Vector<String> missilesId = l.chooseMissileToInterceptUI();
			if(missilesId != null){
				for(String id : missilesId){
					allMissilesId.add(id);
				}
				state = true;
			}else {
				state = false;
			}
		}

		
		setTitle("Intercept a Missile");
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		mainPanel.add(panel, BorderLayout.NORTH);
		
		lblChooseAMissile = new JLabel("Choose A Missile to Intercept");
		lblChooseAMissile.setFont(Utils.FONT);
		panel.add(lblChooseAMissile);
		
		panel1 = new JPanel();
		mainPanel.add(panel1, BorderLayout.CENTER);
		
		cmbChooseMissile = new JComboBox(allMissilesId);
		panel1.add(cmbChooseMissile);
		
		downPanel = new JPanel();
		getContentPane().add(downPanel, BorderLayout.SOUTH);
		
		btnInterceptMissile = new JButton("Intercept!");
		btnInterceptMissile.setFont(Utils.FONT);
		downPanel.add(btnInterceptMissile);
		btnInterceptMissile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String missile = (String) cmbChooseMissile.getSelectedItem();
				for (WarEventUIListener l : allListeners) {
					l.interceptGivenMissileUI(missile);
				}
				setEnabled(false);
				setVisible(false);
			}
		});
		if(state == true) setVisible(true);
		setAlwaysOnTop(true);
		setSize(new Dimension(360, 160));
	}
	
	public boolean getStat(){
		return state;
	}

}
