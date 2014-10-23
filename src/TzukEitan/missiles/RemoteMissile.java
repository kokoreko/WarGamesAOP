package TzukEitan.missiles;

import java.io.Serializable;


/** Enemy missile, is been created by the Enemy launcher **/
public class RemoteMissile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -885540752735582183L;


	private String id;
	private String whoLaunchedMeId;
	private String destination;
	private int flyTime;
	private int damage;
	private String launchTime;
	/**
	 * EnemyMissile constructor for remote client
	 * @param id
	 * @param destination
	 * @param flyTime
	 * @param damage
	 */
	public RemoteMissile(String id, String destination, int flyTime, int damage) {
		this.id = id;
		this.destination = destination;
		this.flyTime = flyTime;
		this.damage = damage;
	}
	public RemoteMissile() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public String getWhoLaunchedMeId() {
		return whoLaunchedMeId;
	}
	public String getDestination() {
		return destination;
	}
	public int getFlyTime() {
		return flyTime;
	}
	public int getDamage() {
		return damage;
	}
	public String getLaunchTime() {
		return launchTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setWhoLaunchedMeId(String whoLaunchedMeId) {
		this.whoLaunchedMeId = whoLaunchedMeId;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setFlyTime(int flyTime) {
		this.flyTime = flyTime;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setLaunchTime(String launchTime) {
		this.launchTime = launchTime;
	}
	
	

	


	

}
