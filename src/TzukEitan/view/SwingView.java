package TzukEitan.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import TzukEitan.GUI.frmAddLauncherDestractor;
import TzukEitan.GUI.frmDestroyLauncher;
import TzukEitan.GUI.frmInterceptMissile;
import TzukEitan.GUI.frmLaunchMissile;
import TzukEitan.GUI.frmShowStats;
import TzukEitan.GUI.pnLauncher;
import TzukEitan.GUI.pnLauncherDistroyer;
import TzukEitan.GUI.pnMissile;
import TzukEitan.GUI.pnMissileIntercepter;
import TzukEitan.GUI.warMenu;
import TzukEitan.listeners.WarEventUIListener;
import TzukEitan.utils.Utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.awt.Dimension;

import javax.swing.SwingConstants;
/**
 * 
 * @author Omri Glam
 *
 */
public class SwingView extends JFrame{
	private JPanel upPanel, downPanel,buttonsPanel,panCenter,panMissile,panLauncher,panMap,panMissileIntercepter,
					panLauncherDestractor,missileAllPanels,launcherAllPanels,lancherDestroyerAllPanel,missileIntercepterAllPanel,mapAllPanel,
					mainPanel;
			
	private JLabel lblLog, mapLabel,lblLaunchers,lblMIssiles,lblMap,lblMissileIntercepter,lblLauncherDestractor;
	private JButton btnAddMissileIntercepter, btnLaunchMissile,btnAddLauncherIntercepter
					,btnAddLauncher,btnInterceptLauncher,btnInterceptMissile,btnShowStatistics,btnEndWar;
	private List<WarEventUIListener> allListeners;

	private JScrollPane spMissiles,spLauncherDestroyer,spMissileIntercepter,spLaunchers,spMap,jspInfo;
	private Hashtable<String, pnLauncher> allLauncherPanels;
	private Hashtable<String, pnMissile> allMissilePanels;
	private boolean warHasEnded = false;
	private Hashtable<String,Point> cityLocation;

	private JTextArea jtaInfo;

	
	/**
	 * Constructor of to the war Main Frame
	 */
	public SwingView(){

		setSize(new Dimension(1210, 768));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		setLayoutAndStyle();
		createButtons();
		addButtonsListener();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
					endWar();
				
			}
		});
		setFocusableWindowState(isFocusableWindow());
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		allLauncherPanels = new Hashtable<String, pnLauncher>();
		allMissilePanels = new Hashtable<String, pnMissile>();
		allListeners = new LinkedList<WarEventUIListener>();
		
	}
	
	private Hashtable<String, Point> createLocation(){
		cityLocation = new Hashtable<String, Point>();
		cityLocation.put("Sderot", new Point(136, 575));
		cityLocation.put("Ofakim",new Point(132,665));
		cityLocation.put("Beer-Sheva", new Point(200,668));
		cityLocation.put("Netivot", new Point(139,616));
		cityLocation.put("Tel-Aviv", new Point(189,423));
		cityLocation.put("Re'ut", new Point(226,504));
		
		return cityLocation;
		
	}
	private void setLayoutAndStyle() {
		warMenu warMenuBar = new warMenu(this);
		upPanel = new JPanel();
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2,1));
		mainPanel.add(upPanel);
		
		jtaInfo = new JTextArea();
		jtaInfo.setText("");
		jtaInfo.setSize(getMaximumSize());
		jspInfo = new JScrollPane(jtaInfo);
		lblLog = new JLabel("War Log");
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		downPanel = new JPanel();
		downPanel.setLayout(new BorderLayout(0, 0));
		downPanel.add(lblLog,BorderLayout.NORTH);
		downPanel.add(jspInfo,BorderLayout.CENTER);
		mainPanel.add(downPanel);
		
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		upPanel.setLayout(new BorderLayout(0, 0));
		upPanel.add(warMenuBar,BorderLayout.NORTH);
		
		panCenter= new JPanel();
		upPanel.add(panCenter, BorderLayout.CENTER);
		panCenter.setLayout(new GridLayout(0, 5, 0, 0));
		
		panLauncher = new JPanel();
		panCenter.add(panLauncher);
		panLauncher.setLayout(new BorderLayout(0, 0));
		
		lblLaunchers = new JLabel("Enemy Launchers");
		lblLaunchers.setHorizontalAlignment(SwingConstants.CENTER);
		panLauncher.add(lblLaunchers, BorderLayout.NORTH);
		
		launcherAllPanels = new JPanel();
		launcherAllPanels.setLayout(new GridLayout(0, 2, 0, 0));
		spLaunchers = new JScrollPane(launcherAllPanels);
		panLauncher.add(spLaunchers);
		
		panMissile = new JPanel();
		panCenter.add(panMissile);
		panMissile.setLayout(new BorderLayout(0, 0));

		missileAllPanels = new JPanel();
		missileAllPanels.setLayout(new GridLayout(0, 2, 0, 0));
		spMissiles = new JScrollPane(missileAllPanels);
		panMissile.add(spMissiles);
		
		
		lblMIssiles = new JLabel("In Air Missiles");
		lblMIssiles.setHorizontalAlignment(SwingConstants.CENTER);
		panMissile.add(lblMIssiles, BorderLayout.NORTH);
		
		mapAllPanel = new JPanel();
		panMap = new JPanel();
		panMap.setLayout(new BorderLayout(0, 0));
		lblMap = new JLabel("Israel Map");
		lblMap.setHorizontalAlignment(SwingConstants.CENTER);
		panMap.add(lblMap, BorderLayout.NORTH);
		mapLabel = new JLabel(new ImageIcon(getClass().getResource("/TzukEitan/images/Israel_relief_location_mapSmall.jpg")));
		spMap = new JScrollPane(mapLabel);
		mapAllPanel = new JPanel();
		mapAllPanel.setLayout(new GridLayout(0, 1, 0, 0));
		mapAllPanel.add(spMap);
		
		panMap.add(mapAllPanel, BorderLayout.CENTER);
		panCenter.add(panMap);
		
		panMissileIntercepter = new JPanel();
		panCenter.add(panMissileIntercepter);
		panMissileIntercepter.setLayout(new BorderLayout(0, 0));
		
		lblMissileIntercepter = new JLabel("Missile Intercepters");
		lblMissileIntercepter.setHorizontalAlignment(SwingConstants.CENTER);
		panMissileIntercepter.add(lblMissileIntercepter, BorderLayout.NORTH);
		
		missileIntercepterAllPanel = new JPanel();
		missileIntercepterAllPanel.setLayout(new GridLayout(0, 2, 0, 0));
		spMissileIntercepter = new JScrollPane(missileIntercepterAllPanel);
		panMissileIntercepter.add(spMissileIntercepter);
		
		panLauncherDestractor = new JPanel();
		panCenter.add(panLauncherDestractor);
		panLauncherDestractor.setLayout(new BorderLayout(0, 0));
		
		lblLauncherDestractor = new JLabel("Launcher Destractors");
		lblLauncherDestractor.setHorizontalAlignment(SwingConstants.CENTER);
		panLauncherDestractor.add(lblLauncherDestractor, BorderLayout.NORTH);
		
		lancherDestroyerAllPanel = new JPanel();
		lancherDestroyerAllPanel.setLayout(new GridLayout(0, 2, 0, 0));
		spLauncherDestroyer = new JScrollPane(lancherDestroyerAllPanel);
		panLauncherDestractor.add(spLauncherDestroyer);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.LIGHT_GRAY);
		
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setLayout(new GridLayout(2, 4, 2, 2));
		createLocation();
	}
	
	//Create Buttons Action Listeners
	private void addButtonsListener() {
		btnEndWar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				endWar();
			}
		});
		btnAddMissileIntercepter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAddIronDome();
			}
		});
		btnAddLauncher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAddLauncher();
			}
		});
		
		btnAddLauncherIntercepter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireAddLauncherIntercepter();
			}
		});
		btnInterceptLauncher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireDestroyLauncher();
			}
		});
		btnShowStatistics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireShowStats();
			}
		});
		btnLaunchMissile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireLaunchMissile();
			}
		});
		
		btnInterceptMissile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireInterceptMissile();
			}
		});
	}
		
	//Button Choice handlers
	protected void fireDestroyLauncher() {
		frmDestroyLauncher DestroyLauncher = new frmDestroyLauncher(allListeners);
		if(DestroyLauncher.getStat() == false){
			updateLauncher();
			JOptionPane.showMessageDialog(this, "All Launchers Are Hidden or Destroyed");
		}
	}

	private void updateLauncher() {
		for(pnLauncher l : allLauncherPanels.values()){
			if(l.isHiddin()) removePanel(l, missileAllPanels);
		}
		
	}

	protected void fireAddLauncher() {
		for (WarEventUIListener l : allListeners)
			l.addEnemyLauncherUI();
	}
	
	protected void fireLaunchMissile() {
		frmLaunchMissile launchMissile = new frmLaunchMissile(allListeners);
		if(launchMissile.getStat() == false){
			JOptionPane.showMessageDialog(this, "All Launchers was Distroyed");
		}
		
	}

	protected void fireInterceptMissile() {
		frmInterceptMissile interceptMissile = new frmInterceptMissile(allListeners);
		if(interceptMissile.getStat() == false){
			updateMissiles();
			JOptionPane.showMessageDialog(this, "No Missiles to intercept");	
		}
	}
	
	private void updateMissiles() {
		for( pnMissile m : allMissilePanels.values())
			removePanel(m , missileAllPanels);
		
	}

	protected void fireAddIronDome() {
		for (WarEventUIListener l : allListeners)
			l.addIronDomeUI();
	}

	protected void fireAddLauncherIntercepter() {
		new frmAddLauncherDestractor(allListeners);
	}

	protected void fireShowStats() {
		for (WarEventUIListener l : allListeners)
			l.showStatisticsUI();
	}

	public void endWar() {
		int result = JOptionPane.showConfirmDialog(SwingView.this,
				"Are you sure you want to exit?", "Goodbye?",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
		for (WarEventUIListener l : allListeners)
			l.finishWarUI();	
		warHasEnded = true;
		}
	}
	
	
	public void registerListeners(WarEventUIListener listener) {
		allListeners.add(listener);
	}
	//Create Buttons
	private void createButtons(){
		btnAddMissileIntercepter = new JButton("Add Munition to Intercept missile");
		buttonsPanel.add(btnAddMissileIntercepter);
		
		btnLaunchMissile = new JButton("Launch Missile");
		buttonsPanel.add(btnLaunchMissile);
		
		btnAddLauncherIntercepter = new JButton("Add Munition to Intercept launchers");
		buttonsPanel.add(btnAddLauncherIntercepter);
		
		btnAddLauncher = new JButton("Add launcher");
		buttonsPanel.add(btnAddLauncher);
		
		btnInterceptLauncher = new JButton("Intercept a launcher");
		buttonsPanel.add(btnInterceptLauncher);
		
		btnInterceptMissile = new JButton("Intercept a missile");
		buttonsPanel.add(btnInterceptMissile);
		
		btnShowStatistics = new JButton("Show statistics");
		buttonsPanel.add(btnShowStatistics);
		
		btnEndWar = new JButton("End the war");
		btnEndWar.setToolTipText("End the war and show statistics");
		buttonsPanel.add(btnEndWar);
	}
	
	// Show Changes to GUI - Functions
	private void removePanel(JPanel removePanel, JPanel panelList){
		try{
		panelList.remove(removePanel);
		}catch(Exception e){}
		repaint();
		validate();
	}

	public void showDefenseLaunchMissile(String myMunitionsId,
			String missileId, String enemyMissileId) {
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] Iron dome: "
				+ myMunitionsId + " just launched missile: " + missileId
				+ " towards missile: " + enemyMissileId);
		
	}
	
	public void showDefenseLaunchMissile(String myMunitionsId, String type,
			String missileId, String enemyLauncherId) {
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] " + type + ": "
				+ myMunitionsId + " just launched missile: " + missileId
				+ " towards launcher: " + enemyLauncherId);
		
	}
	
	public void showEnemyLaunchMissile(String myMunitionsId, String missileId,
			String destination, int damage) {
		pnMissile missilePanel = new pnMissile(myMunitionsId,missileId,destination,damage);
		allMissilePanels.put(missileId, missilePanel);
		missileAllPanels.add(missilePanel);
		
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] Launcher: "
				+ myMunitionsId + " just launched missile: " + missileId
				+ " towards: " + destination
				+ " its about to cause damade of: " + damage);
		
		repaint();
		validate();
	}
	
	public void showMissInterceptionMissile(String whoLaunchedMeId,
			String missileId, String enemyMissileId) {
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] Iron Dome: "
				+ whoLaunchedMeId + " fired missile: " + missileId
				+ " but missed the missile: " + enemyMissileId);
		
	}
	
	public void showHitInterceptionMissile(String whoLaunchedMeId, String missileId, String enemyMissileId2) {
		JPanel missilePanel = allMissilePanels.get(enemyMissileId2);
		removePanel(missilePanel, missileAllPanels);
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] Iron Dome: "
				+ whoLaunchedMeId + " fired missile: " + missileId
				+ " and intercept succesfully the missile: " + whoLaunchedMeId);
	}
	
	public void showEnemyHitDestination(String enemyMissileId, String missileId, String destination, int damage) {
		
		pnMissile missilePanel = allMissilePanels.get(missileId);
		removePanel(missilePanel, missileAllPanels);
		
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] Enemy Missile: "
				+ missileId + " HIT " + destination + ". the damage is: " + damage
				+ ". Launch by: " + enemyMissileId);
		
		// Create a Red Dot On The Map!!!!!!!!!!!!!!!!!!
		//makeDotOnMap(missilePanel.getDestination());
	}
	
	public void showEnemyMissDestination(String whoLaunchedMeId, String id,
			String destination, String launchTime) {
		
		JPanel missilePanel = allMissilePanels.get(id);
		removePanel(missilePanel, missileAllPanels);
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] Enemy Missile: "
				+ id + " MISSED " + destination + " launch at: " + launchTime
				+ ". Launch by: " + whoLaunchedMeId);
	}
	
	public void showMissInterceptionLauncher(String whoLaunchedMeId,
			String type, String enemyLauncherId, String missileId) {
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] " + type + ": "
				+ whoLaunchedMeId + " fired missile: " + missileId
				+ " but missed the Launcher: " + enemyLauncherId);
		
	}
	
	public void showMissInterceptionHiddenLauncher(String whoLaunchedMeId,
			String type, String enemyLauncherId) {
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] " + type + ": "
				+ whoLaunchedMeId + " missed the Launcher: " + enemyLauncherId
				+ " because he is hidden");
	}

	public void showHitInterceptionLauncher(String whoLaunchedMeId,
			String type, String enemyLauncherId, String missileId) {
		
		JPanel launcherPanel = allLauncherPanels.get(enemyLauncherId);
		removePanel(launcherPanel, launcherAllPanels);
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] " + type + ": "
						+ whoLaunchedMeId + " fired missile: " + missileId
						+ " and intercept succesfully the Launcher: "
						+ enemyLauncherId);
		
	}
	
	public void showStatistics(long[] statisticsToArray) {
		frmShowStats statsFrm = new frmShowStats(warHasEnded);
		statsFrm.addStats(statisticsToArray);
		
	}

	public void showEnemyAddLauncher(String LauncherId, boolean isHidden) {
		pnLauncher launcherPanel = new pnLauncher(LauncherId,isHidden);
		allLauncherPanels.put(LauncherId, launcherPanel);
		launcherAllPanels.add(launcherPanel);
		
		repaint();
		validate();
		
	}

	public void showLauncherIsVisible(String id, boolean visible) {
		pnLauncher tempLauncher = allLauncherPanels.get(id);
		tempLauncher.setHiden(visible);
		String str = visible ? "visible" : "hidden";
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] Launcher: " + id
				+ " just turned " + str);
		
	}

	public void showIronDome(String id) {
		pnMissileIntercepter ironDome = new pnMissileIntercepter(id);
		missileIntercepterAllPanel.add(ironDome);
		
		repaint();
		validate();
	}

	public void showLauncherDestractor(String id, String type) {
		pnLauncherDistroyer launcherDes = new pnLauncherDistroyer(id, type);
		lancherDestroyerAllPanel.add(launcherDes);
		
		repaint();
		validate();
	}


	private void makeDotOnMap(String destination) {
		
		JLabel lblRedDot = new JLabel(new ImageIcon(getClass().getResource("/TzukEitan/images/red.png")));
		mapAllPanel.add(lblRedDot);
		try{
		lblRedDot.setLocation(cityLocation.get(destination));
		}catch(Exception e){}
		
		repaint();
		validate();
	}

	
	public void showMissileNotExist(String defenseLauncherId, String enemyId) {
		JPanel missilePanel = allMissilePanels.get(enemyId);
		removePanel(missilePanel, missileAllPanels);
		
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] ERROR: "
				+ defenseLauncherId + " tried to intercept, " + "but missed: "
				+ enemyId + " doesn't exist!");
	}

	public void showLauncherNotExist(String defenseLauncherId, String launcherId) {
		pnLauncher launcherPanel = allLauncherPanels.get(launcherId);
		removePanel(launcherPanel, launcherAllPanels);
		
		jtaInfo.append("\n[" + Utils.getCurrentTime() + "] ERROR: "
		+ defenseLauncherId + " tried to intercept, " + "but missed: "
		+ launcherId + " doesn't exist!");
		
	}
	
	public void showEndWar() {
		this.setVisible(false);
		jtaInfo.append("\n[" + Utils.getCurrentTime()
			+ "] =========>> Finally THIS WAR IS OVER!!! <<=========");
		
	}

	public void showWarHasBeenStarted() {
		jtaInfo.append("[" + Utils.getCurrentTime()
				+ "] =========>> War has been strated!!! <<=========");
		
	}

	public void showNoSuchObject(String type) {
		jtaInfo.append("\n[" + Utils.getCurrentTime()
		+ "] ERROR: Cannot find " + type + " you selected in war");
		
	}









	
}
