package TzukEitan.missiles;


import java.util.LinkedList;
import java.util.List;

import TzukEitan.listeners.WarEventListener;
import TzukEitan.utils.Utils;
import TzukEitan.war.WarStatistics;

/** Enemy missile, is been created by the Enemy launcher **/
public class EnemyMissile extends Thread{
	
	private List<WarEventListener> allListeners;

	private String id;
	private String whoLaunchedMeId;
	private String destination;
	private int flyTime;
	private int damage;
	private WarStatistics statistics;
	private String launchTime;
	private boolean beenHit = false;

	/**
	 * EnemyMissile constructor for remote client
	 * @param id
	 * @param destination
	 * @param flyTime
	 * @param damage
	 */
	public EnemyMissile(RemoteMissile m) {
		allListeners = new LinkedList<WarEventListener>();
		this.id = m.getId();
		this.destination = m.getDestination();
		this.flyTime = m.getFlyTime();
		this.damage = m.getDamage();
	}
	
	public EnemyMissile(String id, String destination, int flyTime, int damage,
			String whoLaunchedMeId, WarStatistics statistics) {
		allListeners = new LinkedList<WarEventListener>();

		this.id = id;
		this.destination = destination;
		this.flyTime = flyTime;
		this.damage = damage;
		this.whoLaunchedMeId = whoLaunchedMeId;
		this.statistics = statistics;
	}

	public void run() {
		launchTime = Utils.getCurrentTime();

		try {
			// fly time
			sleep(flyTime * Utils.SECOND);

			// Interrupt is thrown when Enemy missile has been hit.
		} catch (InterruptedException ex) {
			// this event was already being thrown by the missile (defense) who
			// hit this
			// missile.
			synchronized (this) {
				beenHit = true;
			}
			
		} finally {
			synchronized (this) {
				if (!beenHit) {
					if (Utils.randomSuccesRate()) {
						fireHitEvent();
					} else {
						fireMissEvent();
					}
				}
			}
		}// finally
	}// run

	// Event
	private void fireHitEvent() {
		for (WarEventListener l : allListeners) {
			l.enemyHitDestination(whoLaunchedMeId, id, destination, damage,
					launchTime);
		}

		// update the war statistics
		statistics.increaseNumOfHitTargetMissiles();
		statistics.increaseTotalDamage(damage);
	}

	// Event
	private void fireMissEvent() {
		for (WarEventListener l : allListeners) {
			l.enemyMissDestination(whoLaunchedMeId, id, destination, launchTime);
		}
	}

	// Event
	public void registerListeners(WarEventListener listener) {
		allListeners.add(listener);
	}

	public String getMissileId() {
		return id;
	}

	public int getDamage() {
		return damage;
	}
	
	public void setStatistics(WarStatistics s){
		this.statistics = s;
	}
	public void setWhoLaunchedMeId(String id){
		this.whoLaunchedMeId=id;
	}
	
	public boolean isBeenHit(){
		return beenHit;
	}

}
