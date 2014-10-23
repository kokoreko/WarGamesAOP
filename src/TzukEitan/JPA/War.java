package TzukEitan.JPA;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the wars database table.
 * 
 */
@Entity
@Table(name="wars")
@NamedQuery(name="War.findAll", query="SELECT w FROM War w")
public class War implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to EnemyLauncher
	@OneToMany(mappedBy="war")
	private List<EnemyLauncher> enemyLaunchers;

	//bi-directional many-to-one association to EnemyMissile
	@OneToMany(mappedBy="war")
	private List<EnemyMissile> enemyMissiles;

	//bi-directional many-to-one association to LauncherDestructor
	@OneToMany(mappedBy="war")
	private List<LauncherDestructor> launcherDestructors;

	public War() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<EnemyLauncher> getEnemyLaunchers() {
		return this.enemyLaunchers;
	}

	public void setEnemyLaunchers(List<EnemyLauncher> enemyLaunchers) {
		this.enemyLaunchers = enemyLaunchers;
	}

	public EnemyLauncher addEnemyLauncher(EnemyLauncher enemyLauncher) {
		getEnemyLaunchers().add(enemyLauncher);
		enemyLauncher.setWar(this);

		return enemyLauncher;
	}

	public EnemyLauncher removeEnemyLauncher(EnemyLauncher enemyLauncher) {
		getEnemyLaunchers().remove(enemyLauncher);
		enemyLauncher.setWar(null);

		return enemyLauncher;
	}

	public List<EnemyMissile> getEnemyMissiles() {
		return this.enemyMissiles;
	}

	public void setEnemyMissiles(List<EnemyMissile> enemyMissiles) {
		this.enemyMissiles = enemyMissiles;
	}

	public EnemyMissile addEnemyMissile(EnemyMissile enemyMissile) {
		getEnemyMissiles().add(enemyMissile);
		enemyMissile.setWar(this);

		return enemyMissile;
	}

	public EnemyMissile removeEnemyMissile(EnemyMissile enemyMissile) {
		getEnemyMissiles().remove(enemyMissile);
		enemyMissile.setWar(null);

		return enemyMissile;
	}

	public List<LauncherDestructor> getLauncherDestructors() {
		return this.launcherDestructors;
	}

	public void setLauncherDestructors(List<LauncherDestructor> launcherDestructors) {
		this.launcherDestructors = launcherDestructors;
	}

	public LauncherDestructor addLauncherDestructor(LauncherDestructor launcherDestructor) {
		getLauncherDestructors().add(launcherDestructor);
		launcherDestructor.setWar(this);

		return launcherDestructor;
	}

	public LauncherDestructor removeLauncherDestructor(LauncherDestructor launcherDestructor) {
		getLauncherDestructors().remove(launcherDestructor);
		launcherDestructor.setWar(null);

		return launcherDestructor;
	}

}