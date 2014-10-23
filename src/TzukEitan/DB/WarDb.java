package TzukEitan.DB;



public interface WarDb {

	public void startWar(boolean jpa);
	public void addEnemyLauncher(String launcherId, boolean isHidden) ;
	public void launchEnemyMissile(String missileId, String launcherId,String destination,int damage,int flyTime);
	public void addDefenseLauncherDestructor(String id, String type);
	public void interceptGivenLauncher(String destructorId, String launcherId) ;
	public void defenseHitInterceptionMissile(String whoLaunchedMeId,String missileId, String enemyMissileId);
	public void defenseHitInterceptionLauncher(String whoLaunchedMeId,String enemyLauncherId);
	
	
	
	public void finishWar();
}
