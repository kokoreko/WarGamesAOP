package TzukEitan.JPA;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the launcher_destructor database table.
 * 
 */
@Entity
@Table(name="launcher_destructor")
@NamedQuery(name="LauncherDestructor.findAll", query="SELECT l FROM LauncherDestructor l")
public class LauncherDestructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dbId;

	private String id;

	private boolean isBusy;

	private boolean isRunning;

	private String toDestroyId;

	private String type;

	//bi-directional many-to-one association to War
	@ManyToOne
	@JoinColumn(name="warId")
	private War war;

	public LauncherDestructor() {
	}

	public int getDbId() {
		return this.dbId;
	}

	public void setDbId(int dbId) {
		this.dbId = dbId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsBusy() {
		return this.isBusy;
	}

	public void setIsBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public boolean getIsRunning() {
		return this.isRunning;
	}

	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public String getToDestroyId() {
		return this.toDestroyId;
	}

	public void setToDestroyId(String toDestroyId) {
		this.toDestroyId = toDestroyId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public War getWar() {
		return this.war;
	}

	public void setWar(War war) {
		this.war = war;
	}

}