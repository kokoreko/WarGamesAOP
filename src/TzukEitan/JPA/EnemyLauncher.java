package TzukEitan.JPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the enemy_launcher database table.
 * 
 */
@Entity
@Table(name="enemy_launcher")
@NamedQuery(name="EnemyLauncher.findAll", query="SELECT e FROM EnemyLauncher e")
public class EnemyLauncher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dbId;

	private boolean beenHit;

	private boolean firstHiddenState;

	private String hitBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hitTime;

	private String id;

	private boolean isHidden;
 
	transient private Timestamp timeStamp;

	//bi-directional many-to-one association to War
	@ManyToOne
	@JoinColumn(name="warId")
	private War war;

	public EnemyLauncher() {
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

	public boolean getFirstHiddenState() {
		return this.firstHiddenState;
	}

	public void setFirstHiddenState(boolean firstHiddenState) {
		this.firstHiddenState = firstHiddenState;
	}

	public String getHitBy() {
		return this.hitBy;
	}

	public void setHitBy(String hitBy) {
		this.hitBy = hitBy;
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

	public boolean getIsHidden() {
		return this.isHidden;
	}

	public void setIsHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public War getWar() {
		return this.war;
	}

	public void setWar(War war) {
		this.war = war;
	}

}