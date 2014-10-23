package TzukEitan.war;

import java.util.Vector;

import TzukEitan.listeners.WarEventListener;
import TzukEitan.listeners.WarEventUIListener;
import TzukEitan.view.ConsoleView;
import TzukEitan.view.SwingView;
import TzukEitan.view.WarXMLReader;


public class WarControl implements WarEventListener, WarEventUIListener{
	private War warModel;
	
	private ConsoleView view;
	private SwingView viewGUI;
	
	public WarControl(War warModel, ConsoleView view, SwingView viewGUI){
		this.warModel = warModel;
		this.view = view;
		this.viewGUI = viewGUI;
		
		
		warModel.registerListenerts(this);
		view.registerListeners(this);
		viewGUI.registerListeners(this);
		
	}
	
	//Method that related to the view & viewGUI
	@Override
	public void defenseLaunchMissile(String myMunitionsId, String missileId, String enemyMissileId) {
		view.showDefenseLaunchMissile(myMunitionsId,missileId,enemyMissileId);
		viewGUI.showDefenseLaunchMissile(myMunitionsId,missileId,enemyMissileId);
	}

	@Override
	public void defenseLaunchMissile(String myMunitionsId, String type,	String missileId, String enemyLauncherId) {
		view.showDefenseLaunchMissile(myMunitionsId, type, missileId, enemyLauncherId);
	}

	@Override
	public void enemyLaunchMissile(String myMunitionsId, String missileId, String destination, int damage) {
		view.showEnemyLaunchMissile(myMunitionsId, missileId, destination, damage);	
		viewGUI.showEnemyLaunchMissile(myMunitionsId, missileId, destination, damage);
	}
	
	@Override
	public void enemyLauncherWasAdd(String id, boolean visible) {
		viewGUI.showEnemyAddLauncher(id,visible);
	}
	
	@Override
	public void enemyLauncherIsVisible(String id,boolean visible) {
		view.showLauncherIsVisible(id,visible);
		viewGUI.showLauncherIsVisible(id, visible);
		
		
	}
	
	@Override
	public void defenseCreatedIronDome(String id) {
		viewGUI.showIronDome(id);
	}
	
	@Override
	public void defenseCreatedLauncherDestractor(String id, String type) {
		viewGUI.showLauncherDestractor(id,type);
	}
	
	@Override
	public void defenseMissInterceptionMissile(String whoLaunchedMeId, String missileId, String enemyMissileId, int damage) {
		view.showMissInterceptionMissile(whoLaunchedMeId, missileId, enemyMissileId);
	}

	@Override
	public void defenseHitInterceptionMissile(String whoLaunchedMeId, String missileId, String enemyMissileId) {
		view.showHitInterceptionMissile(whoLaunchedMeId, missileId, enemyMissileId);
		viewGUI.showHitInterceptionMissile(enemyMissileId);
	
	}

	@Override
	public void enemyHitDestination(String whoLaunchedMeId, String missileId, String destination, int damage, String launchTime) {
		view.showEnemyHitDestination(whoLaunchedMeId, missileId, destination, damage);
		viewGUI.showEnemyHitDestination(missileId);
	}

	@Override
	public void defenseMissInterceptionLauncher(String whoLaunchedMeId,	String type, String missileId, String enemyLauncherId) {
		view.showMissInterceptionLauncher(whoLaunchedMeId,type, enemyLauncherId, missileId);

	}
	
	@Override
	public void defenseMissInterceptionHiddenLauncher(String whoLaunchedMeId, String type, String enemyLauncherId) {
		view.showMissInterceptionHiddenLauncher(whoLaunchedMeId,type, enemyLauncherId);
	}
	
	@Override
	public void defenseHitInterceptionLauncher(String whoLaunchedMeId, String type, String missileId, String enemyLauncherId) {
		view.showHitInterceptionLauncher(whoLaunchedMeId, type, enemyLauncherId, missileId);
		viewGUI.showHitInterceptionLauncher(whoLaunchedMeId, type, enemyLauncherId, missileId);
	
	}
	
	
	
	//Methods related to the model
	@Override
	public void finishWarUI() {
		WarXMLReader.stopAllThreads();
		//warModel.finishWar();
		
		//notify the war
		synchronized (warModel) {
			warModel.notify();
		}
	}

	@Override
	public void showStatisticsUI() {
		WarStatistics statistics = warModel.getStatistics();
		view.showStatistics(statistics.statisticsToArray());	
		viewGUI.showStatistics(statistics.statisticsToArray());
	}

	@Override
	public Vector<String> chooseMissileToInterceptUI() {
		Vector<String> ids = warModel.getAllDuringFlyMissilesIds();
		return ids;
	}

	@Override
	public void interceptGivenMissileUI(String ironDomeId, String missileId) {
		warModel.interceptGivenMissile(ironDomeId, missileId);
	}

	@Override
	public void interceptGivenMissileUI(String missileId) {
		warModel.interceptGivenMissile(missileId);
	}
	
	@Override
	public void interceptGivenLauncherUI(String launcherId) {
		warModel.interceptGivenLauncher(launcherId);
	}

	@Override
	public void interceptGivenLauncherUI(String destructorId, String launcherId) {
		warModel.interceptGivenLauncher(destructorId,launcherId);
		
		
	}
	
	@Override
	public Vector<String> chooseLauncherToInterceptUI() {
		Vector<String> ids = warModel.getAllVisibleLaunchersIds();
		return ids;
	}

	@Override
	public Vector<String> showAllLaunchersUI() {
		Vector<String> ids = warModel.getAllLaunchersIds();
		return ids;
	}

	@Override
	public void addEnemyMissileUI(String launcherId, String destination, int damage, int flyTime) {
		warModel.launchEnemyMissile(launcherId, destination, damage, flyTime);
		
		
	}

	@Override
	public String addEnemyLauncherUI(String launcherId, boolean isHidden) {
		String id = warModel.addEnemyLauncher(launcherId, isHidden);
		
		return id;
	}
	
	@Override
	public String addEnemyLauncherUI() {
		String id = warModel.addEnemyLauncher();
		
		return id;
	}

	@Override
	public String addIronDomeUI() {
		String id = warModel.addIronDome();
		return id;
	}
	
	@Override
	public String addIronDome(String id) {
		String iId = warModel.addIronDome(id);
		return iId;
	}

	@Override
	public String addDefenseLauncherDestructorUI(String type) {
		String id = warModel.addDefenseLauncherDestructor(type);
		
		return id;
	}

	@Override
	public String[] getAllWarDestinationsUI() {
		String[] warTargets = warModel.getAllTargetCities();
		return warTargets;
	}

	@Override
	public void warHasBeenFinished() {	
		/**try {
			view.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} **/
	
		view.showWarHasBeenFinished();
		viewGUI.showEndWar();
	}

	@Override
	public void warHasBeenStarted() {
		view.showWarHasBeenStarted();
	}

	@Override
	public void noSuchObject(String type) {
		view.showNoSuchObject(type);
	}

	@Override
	public void missileNotExist(String defenseLauncherId, String enemyId) {
		view.showMissileNotExist(defenseLauncherId, enemyId);
	}
	
	@Override
	public void enemyLauncherNotExist(String defenseLauncherId,
			String launcherId) {
		view.showLauncherNotExist(defenseLauncherId, launcherId);
	}

	@Override
	public void enemyMissDestination(String whoLaunchedMeId, String id,
			String destination, String launchTime) {
		view.showEnemyMissDestination(whoLaunchedMeId, id, destination, launchTime);
		viewGUI.showEnemyMissDestination(whoLaunchedMeId, id, destination, launchTime);
	}










}
