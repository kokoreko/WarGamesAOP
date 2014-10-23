package TzukEitan.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.GridLayout;

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
	private JPanel upPanel,buttonsPanel,panCenter,panMissile,panLauncher,panMap,panMissileIntercepter,
					panLauncherDestractor,missileAllPanels,launcherAllPanels,lancherDestroyerAllPanel,missileIntercepterAllPanel,mapAllPanel;
			
	private JLabel mapLabel,lblLaunchers,lblMIssiles,lblMap,lblMissileIntercepter,lblLauncherDestractor;
	private JButton btnAddMissileIntercepter, btnLaunchMissile,btnAddLauncherIntercepter
					,btnAddLauncher,btnInterceptLauncher,btnInterceptMissile,btnShowStatistics,btnEndWar;
	private List<WarEventUIListener> allListeners;

	private JScrollPane spMissiles,spLauncherDestroyer,spMissileIntercepter,spLaunchers,spMap;
	private Hashtable<String, pnLauncher> allLauncherPanels;
	private Hashtable<String, pnMissile> allMissilePanels;
	private boolean warHasEnded = false;

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
	 
	private void setLayoutAndStyle() {
		warMenu warMenuBar = new warMenu(this);
		upPanel = new JPanel();
		getContentPane().add(upPanel, BorderLayout.CENTER);
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
			JOptionPane.showMessageDialog(this, "All Launchers Are Hidden or Destroyed");
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
			JOptionPane.showMessageDialog(this, "No Missiles to intercept");		
		}
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
	
	// Show Changes to GUI Functions
	private void removePanel(JPanel removePanel, JPanel panelList){
		try{
		panelList.remove(removePanel);
		}catch(Exception e){}
		repaint();
		validate();
	}

	public void showDefenseLaunchMissile(String myMunitionsId,
			String missileId, String enemyMissileId) {
		
		
	}

	public void showStatistics(long[] statisticsToArray) {
		frmShowStats statsFrm = new frmShowStats(warHasEnded);
		statsFrm.addStats(statisticsToArray);
		
	}

	public void showEnemyLaunchMissile(String myMunitionsId, String missileId,
			String destination, int damage) {
		pnMissile missilePanel = new pnMissile(myMunitionsId,missileId,destination,damage);
		allMissilePanels.put(missileId, missilePanel);
		missileAllPanels.add(missilePanel);
		
		repaint();
		validate();
		
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

	public void showHitInterceptionMissile(String enemyMissileId) {
		JPanel missilePanel = allMissilePanels.get(enemyMissileId);
		removePanel(missilePanel, missileAllPanels);
		
	}

	public void showEnemyHitDestination(String enemyMissileId) {
		JPanel missilePanel = allMissilePanels.get(enemyMissileId);
		removePanel(missilePanel, missileAllPanels);
		// Create a Red Dot On The Map!!!!!!!!!!!!!!!!!!
	}


	public void showHitInterceptionLauncher(String whoLaunchedMeId,
			String type, String enemyLauncherId, String missileId) {
		
		JPanel launcherPanel = allLauncherPanels.get(enemyLauncherId);
		removePanel(launcherPanel, launcherAllPanels);
		
	}

	public void showEnemyMissDestination(String whoLaunchedMeId, String id,
			String destination, String launchTime) {
		
		JPanel missilePanel = allMissilePanels.get(id);
		removePanel(missilePanel, missileAllPanels);
		
	}


	public void showMissileNotExist(String defenseLauncherId, String enemyId) {
		JPanel missilePanel = allMissilePanels.get(enemyId);
		removePanel(missilePanel, missileAllPanels);
	}

	public void showLauncherNotExist(String defenseLauncherId, String launcherId) {
		pnLauncher launcherPanel = allLauncherPanels.get(launcherId);
		removePanel(launcherPanel, launcherAllPanels);
		
	}

	public void showEndWar() {
		this.setVisible(false);
		
	}
	
}
