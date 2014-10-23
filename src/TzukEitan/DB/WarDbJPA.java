package TzukEitan.DB;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
















import TzukEitan.JPA.EnemyLauncher;
import TzukEitan.JPA.EnemyMissile;
import TzukEitan.JPA.LauncherDestructor;
import TzukEitan.JPA.War;

import java.util.Date;
import java.util.List;

public class WarDbJPA implements WarDb {

	
	private War currentWar;
	private EntityManagerFactory emf;
	
	public WarDbJPA(){

		currentWar = new War();

		emf = Persistence.createEntityManagerFactory("WarGames");
		

	}
	@Override
	public void startWar(boolean jpa){
		if(jpa)
		{
			currentWar.setStartDate(new Date());
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			try {
				em.persist(currentWar);
			
				em.getTransaction().commit();
				
				@SuppressWarnings("unchecked")
				List<War> currentWarList = em.createQuery("SELECT DISTINCT w FROM War w ORDER BY w.id DESC").getResultList();
					
				currentWar = currentWarList.get(0);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
	}
	@Override
	public void launchEnemyMissile(String missileId, String launcherId,
			String destination, int damage, int flyTime) {
		
		EnemyMissile m = new EnemyMissile();
		
		m.setWar(currentWar);
		m.setId(missileId);
		m.setWhoLaunchedMeId(launcherId);
		m.setDestination(destination);
		m.setDamage(damage);
		m.setFlyTime(flyTime);
		
		commitData(m);
	}
	
	@Override
	public void addEnemyLauncher(String launcherId, boolean isHidden) {
		EnemyLauncher l = new EnemyLauncher();
		l.setWar(currentWar);
		l.setId(launcherId);
		commitData(l);
	}


	@Override
	public void addDefenseLauncherDestructor(String id, String type) {
		LauncherDestructor ld = new LauncherDestructor();
		ld.setWar(currentWar);
		ld.setId(id);
		ld.setType(type);
		commitData(ld);
		
	}

	@Override
	public void interceptGivenLauncher(String destructorId, String launcherId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			EnemyLauncher l =  (EnemyLauncher)em.createQuery("SELECT DISTINCT l FROM EnemyLauncher l WHERE l.war:currentWar AND l.id='"+launcherId+"' ")
					.setParameter("currentWar", currentWar).getSingleResult();
			l.setHitBy(destructorId);
			l.setBeenHit(true);
			l.setHitTime(new Date());
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
	}

	@Override
	public void defenseHitInterceptionMissile(String whoLaunchedMeId,
			String missileId, String enemyMissileId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			EnemyMissile m =  (EnemyMissile)em.createQuery("SELECT DISTINCT m FROM EnemyMissile m WHERE m.war=:currentWar AND m.id='"+enemyMissileId+"' ")
					.setParameter("currentWar", currentWar).getSingleResult();
			m.setBeenHit(true);
			
			m.setHitTime(new Date());
			em.getTransaction().commit();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			//e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
	}

	@Override
	public void defenseHitInterceptionLauncher(String whoLaunchedMeId,
			String enemyLauncherId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			EnemyLauncher l =  (EnemyLauncher)em.createQuery("SELECT DISTINCT l FROM EnemyLauncher l WHERE l.war=:currentWar AND l.id='"+enemyLauncherId+"' ")
							.setParameter("currentWar", currentWar).getSingleResult();
			l.setBeenHit(true);
			l.setHitBy(whoLaunchedMeId);
			l.setHitTime(new Date());
			em.getTransaction().commit();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			//e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	@Override
	public void finishWar() {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			War w = em.find(War.class, currentWar.getId());
			w.setEndDate(new Date());
			em.getTransaction().commit();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			//e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}
	
	
	private void commitData(Object m) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {

			em.persist(m);
			em.getTransaction().commit();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			//e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
	}

}
