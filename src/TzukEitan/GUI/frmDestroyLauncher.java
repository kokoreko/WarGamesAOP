package TzukEitan.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;

import TzukEitan.listeners.WarEventUIListener;
import TzukEitan.utils.Utils;

public class frmDestroyLauncher extends JFrame {


	private JPanel midPanel,downPanel;
	private JButton btnLaunchMissile;
	private JPanel rightPanel;
	private JComboBox<String> cmbChooseLauncher;
	private JLabel lblChooseCity;
	private Vector<String> allLaunchersId;
	private boolean state = true;
	
	public frmDestroyLauncher(List<WarEventUIListener> allListeners) {
		allLaunchersId = new Vector<String>();
		for (WarEventUIListener l : allListeners) {
			Vector<String> launcersId = l.chooseLauncherToInterceptUI();
			if(launcersId != null){
				for(String id : launcersId){
					allLaunchersId.add(id);
					state = true;
				}
			}else{ state = false; }
		}
			
		setSize(new Dimension(356, 161));
		setTitle("Destory Launcher");
		
		midPanel = new JPanel();
		getContentPane().add(midPanel, BorderLayout.CENTER);
		midPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		
		rightPanel = new JPanel();
		midPanel.add(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		cmbChooseLauncher = new JComboBox<String>(allLaunchersId);
		rightPanel.add(cmbChooseLauncher);
		
		lblChooseCity= new JLabel("Choose Launcher to destroy");
		lblChooseCity.setFont(Utils.FONT);
		rightPanel.add(lblChooseCity, BorderLayout.NORTH);
		
		downPanel = new JPanel();
		getContentPane().add(downPanel, BorderLayout.SOUTH);
		
		btnLaunchMissile = new JButton("Destroy!");
		btnLaunchMissile.setFont(Utils.FONT);
		downPanel.add(btnLaunchMissile);
		btnLaunchMissile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String launcher = (String) cmbChooseLauncher.getSelectedItem();
				for (WarEventUIListener l : allListeners) {
						l.interceptGivenLauncherUI(launcher);
				}
				setEnabled(false);
				setVisible(false);
			}
		});
		
		if(state == true) setVisible(true);
		setAlwaysOnTop(true);
		setResizable(false);
	}
	
	public boolean getStat(){
		return state;
	}
}
