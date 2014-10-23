package TzukEitan.listeners;

import java.util.Vector;

public interface WarEventUIListener {
	

	/** Show current war statistics **/
	public void showStatisticsUI();

	/** Ask for current missiles in air **/
	public Vector<String> chooseMissileToInterceptUI();

	/** Will try to intercept given missile id **/
	public void interceptGivenMissileUI(String id);

	/** Used for xml **/
	void interceptGivenMissileUI(String ironDomeId, String missileId);

	/** Ask for current launcher that are not hidden **/
	public Vector<String> chooseLauncherToInterceptUI();

	/** Will try to intercept given launcher id **/
	public void interceptGivenLauncherUI(String id);

	/** Used for xml **/
	void interceptGivenLauncherUI(String destructorId, String launcherId);

	/** User will select from which launcher he would like to launch missile **/
	public Vector<String> showAllLaunchersUI();

	/** Add missile to given launcher **/
	public void addEnemyMissileUI(String launcherId, String destination,
			int damage, int flyTime);

	/** Add defense Iron Dome **/
	public String addIronDomeUI();
	
	/** Stop the war, truce and show war statistics **/
	public void finishWarUI();
	
	/** Add enemy launcher **/
	public String addEnemyLauncherUI();
	
	/** Add enemy launcher from xml **/
	public String addEnemyLauncherUI(String launcherId, boolean isHidden);

	/** Add plane or ship **/
	public String addDefenseLauncherDestructorUI(String type);
	

	



	/** Returns all war city targets **/
	public String[] getAllWarDestinationsUI();

}
