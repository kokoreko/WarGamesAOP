package TzukEitan.GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JSplitPane;

import java.awt.FlowLayout;

import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;

import TzukEitan.listeners.WarEventUIListener;
import TzukEitan.utils.Utils;

public class frmLaunchMissile extends JFrame {

	
	private JPanel midPanel,upPanel,downPanel;
	private JButton btnLaunchMissile;
	private JPanel rightPanel, leftPanel;
	private JComboBox cmbChooseLauncher,cmbChooseCity;
	private JLabel lblChooseLauncher,lblChooseCity;
	private Vector<String> allLauncherIds,allDestinations;
	private boolean state = true;
	
	public frmLaunchMissile(List<WarEventUIListener> allListeners) {
		allLauncherIds = new Vector<String>();
		allDestinations = new Vector<String>();
		
		for (WarEventUIListener l : allListeners) {
			String[] destinations = l.getAllWarDestinationsUI();
			for(String dest : destinations){
				allDestinations.add(dest);
			}
			Vector<String> launchersIds = l.showAllLaunchersUI();
			if(launchersIds != null){
				for(String id : launchersIds){ 
					allLauncherIds.add(id);
					state = true;
					}
				}else{ state = false; }
			
		}
		setSize(new Dimension(420, 256));
		setTitle("Launce a missile");
		
		upPanel = new JPanel();
		getContentPane().add(upPanel, BorderLayout.NORTH);
		
		midPanel = new JPanel();
		getContentPane().add(midPanel, BorderLayout.CENTER);
		midPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
		
		leftPanel = new JPanel();
		midPanel.add(leftPanel);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		cmbChooseLauncher = new JComboBox(allLauncherIds);
		leftPanel.add(cmbChooseLauncher);
		
		lblChooseLauncher = new JLabel("Choose a Launcher");
		lblChooseLauncher.setFont(Utils.FONT);
		leftPanel.add(lblChooseLauncher, BorderLayout.NORTH);
		
		rightPanel = new JPanel();
		midPanel.add(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		cmbChooseCity = new JComboBox(allDestinations);
		rightPanel.add(cmbChooseCity);
		
		lblChooseCity= new JLabel("Choose a city to strike");
		lblChooseCity.setFont(Utils.FONT);
		rightPanel.add(lblChooseCity, BorderLayout.NORTH);
		
		downPanel = new JPanel();
		getContentPane().add(downPanel, BorderLayout.SOUTH);
		
		btnLaunchMissile = new JButton("Launch!");
		btnLaunchMissile.setFont(Utils.FONT);
		downPanel.add(btnLaunchMissile);
		btnLaunchMissile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String launcher = (String)cmbChooseLauncher.getSelectedItem();
				String city = (String)cmbChooseCity.getSelectedItem();
				
				int damage = (int) ((Math.random() * Utils.SECOND) + Utils.SECOND * 2);
				int flyTime = (int) ((Math.random() * Utils.FLY_TIME) + Utils.FLY_TIME);
				
				for(WarEventUIListener l : allListeners ){
					l.addEnemyMissileUI(launcher, city, damage, flyTime);
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
