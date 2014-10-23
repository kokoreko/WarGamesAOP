package TzukEitan.JPA;

import java.io.Serializable;

import javax.persistence.*;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the enemy_missile database table.
 * 
 */
@Entity
@Table(name="enemy_missile")
@NamedQuery(name="EnemyMissile.findAll", query="SELECT e FROM EnemyMissile e")
public class EnemyMissile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dbId;

	private boolean beenHit;

	private int damage;

	private String destination;

	private int flyTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hitTime;

	private String id;

	private String launchTime;

	transient private Timestamp timeStamp;

	private String whoLaunchedMeId;

	//bi-directional many-to-one association to War
	@ManyToOne
	@JoinColumn(name="warId")
	private War war;

	public EnemyMissile() {
	}

	public int getDbId() {
		return this.dbId;
	}

	public void setDbId(int dbId) {
		this.dbId = dbId;
	}

	public boolean getBeenHit() {
		return this.beenHit;
	}

	public void setBeenHit(boolean beenHit) {
		this.beenHit = beenHit;
	}

	public int getDamage() {
		return this.damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getFlyTime() {
		return this.flyTime;
	}

	public void setFlyTime(int flyTime) {
		this.flyTime = flyTime;
	}

	public Date getHitTime() {
		return this.hitTime;
	}

	public void setHitTime(Date hitTime) {
		this.hitTime = hitTime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLaunchTime() {
		return this.launchTime;
	}

	public void setLaunchTime(String launchTime) {
		this.launchTime = launchTime;
	}

	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getWhoLaunchedMeId() {
		return this.whoLaunchedMeId;
	}

	public void setWhoLaunchedMeId(String whoLaunchedMeId) {
		this.whoLaunchedMeId = whoLaunchedMeId;
	}

	public War getWar() {
		return this.war;
	}

	public void setWar(War war) {
		this.war = war;
	}

}