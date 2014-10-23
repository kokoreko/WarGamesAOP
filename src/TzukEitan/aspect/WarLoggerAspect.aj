package TzukEitan.aspect;

import TzukEitan.launchers.*;



public aspect WarLoggerAspect {


	private WarLogger warLogger ;
	
	pointcut War():
		execution(TzukEitan.war.War.new());

	after(): War(){
		System.out.println("ASPECT creating new logger");
		warLogger = new WarLogger();
	}

	// Contractors
	pointcut addLoggerHandlerIronDome(String id):
		execution(IronDome.new(* , *)) && args(id, *);

	after(String id): addLoggerHandlerIronDome(id){
		WarLogger.addLoggerHandler("IronDome", id);
	}

	pointcut addLoggerHandlerLauncherDestractor(String type,String id):
		execution(LauncherDestructor.new(* , * , *)) && args(type,id, *);

	after(String type,String id): addLoggerHandlerLauncherDestractor(type,id){
		WarLogger.addLoggerHandler(type, id);
	}
	pointcut addLoggerHandlerEnemyLauncher(String id):
		execution(LauncherDestructor.new(* , * , *)) && args(id, * , *);

	after(String id): addLoggerHandlerEnemyLauncher(id){
		WarLogger.addLoggerHandler("Launcher", id);
	}		

	
	// Handler closers	
	pointcut closeLoggerHandlerLauncherDestractor():
		execution(public void LauncherDestructor.run());
	after(): closeLoggerHandlerLauncherDestractor(){
		String id = ((LauncherDestructor)thisJoinPoint.getTarget()).getDestructorId();
		WarLogger.closeMyHandler(id);
	}

	pointcut closeLoggerHandlerIronDome():
		execution(public void IronDome.run());
	after(): closeLoggerHandlerIronDome(){
		String id = ((IronDome)thisJoinPoint.getTarget()).getIronDomeId();
		WarLogger.closeMyHandler(id);
	}

	pointcut closeLoggerHandlerEnemyLauncher():
		execution(public void EnemyLauncher.run());
	after(): closeLoggerHandlerEnemyLauncher(){
		String id = ((EnemyLauncher)thisJoinPoint.getTarget()).getLauncherId();
		WarLogger.closeMyHandler(id);
	}


	pointcut enemyLaunchMissile(String myMunitionsId, String missileId,
			String destination, int damage):
				execution(public void TzukEitan.war.WarControl.enemyLaunchMissile(String,String,String,int))
				&& args(myMunitionsId,missileId,destination,damage);

	after(String myMunitionsId, String missileId,
			String destination, int damage): enemyLaunchMissile(myMunitionsId,missileId,destination,damage){
		warLogger.enemyLaunchMissile(myMunitionsId, missileId, destination, damage);
	}


	/** Add defense Iron Dome from xml **/
	pointcut addIronDome(String id):
		execution(public String TzukEitan.war.WarControl.addIronDome(String)) && args(id);
	after(String id): addIronDome(id){
		warLogger.addIronDome(id);
	}

	/** Enemy Launcher was add **/
	pointcut enemyLauncherWasAdd(String id, boolean visible):
		execution(public void TzukEitan.war.WarControl.enemyLauncherWasAdd(String,boolean)) && args(id,visible);
	after(String id, boolean visible): enemyLauncherWasAdd(id,visible){
		warLogger.enemyLauncherWasAdd(id, visible);
	}

	/** Enemy is now visible **/
	pointcut enemyLauncherIsVisible(String id, boolean visible):
		execution(public void TzukEitan.war.WarControl.enemyLauncherIsVisible( String,  boolean)) && args(id,  visible);
	after(String id, boolean visible): enemyLauncherWasAdd(id,  visible){
		warLogger.enemyLauncherIsVisible(id,  visible);
	}

	/** Enemy event for hit destination **/
	pointcut enemyHitDestination(String whoLaunchedMeId, String id,
			String destination, int damage, String launchTime):
				execution(public void TzukEitan.war.WarControl.enemyHitDestination(String,String,String,int,String)) 
				&& args( whoLaunchedMeId,  id,destination,  damage,  launchTime);
	after(String whoLaunchedMeId, String id,String destination, int damage, String launchTime): enemyHitDestination( whoLaunchedMeId,  id, destination,  damage,  launchTime){
		warLogger.enemyHitDestination( whoLaunchedMeId,  id, destination,  damage,  launchTime);
	}

		/** Enemy event for miss destination **/
		pointcut enemyMissDestination(String whoLaunchedMeId, String id,
				String destination, String launchTime):
					execution(public void TzukEitan.war.WarControl.enemyMissDestination(String , String ,String , String )) && args(whoLaunchedMeId,id,destination,launchTime);
		after(String whoLaunchedMeId, String id,
				String destination, String launchTime): enemyMissDestination(whoLaunchedMeId,id,destination,launchTime){
			warLogger.enemyMissDestination(whoLaunchedMeId, id, destination, launchTime);
		}
		
		/** Defense Iron Dome was created **/
		pointcut defenseCreatedIronDome(String id):
			execution(public void TzukEitan.war.WarControl.defenseCreatedIronDome(String)) && args(id);
		after(String id): defenseCreatedIronDome(id){
			warLogger.defenseCreatedIronDome(id);
		}
		
		/** Defense Launcher Destroyer was created **/
		pointcut defenseCreatedLauncherDestractor(String id,String type):
			execution(public void TzukEitan.war.WarControl.defenseCreatedLauncherDestractor(String ,String )) && args(id,type);
		after(String id,String type): defenseCreatedLauncherDestractor(id,type){
			warLogger.defenseCreatedLauncherDestractor(id, type);
		}
		
		/** Defense Iron Dome launch interception missile **/
		pointcut defenseLaunchMissile1(String myMunitionsId, String missileId,String enemyMissileId):
					execution(public void TzukEitan.war.WarControl.defenseLaunchMissile(String , String ,String )) 
					&& args( myMunitionsId,  missileId,enemyMissileId);
		
		after(String myMunitionsId, String missileId,String enemyMissileId): defenseLaunchMissile1( myMunitionsId,  missileId,	enemyMissileId){
			warLogger.defenseLaunchMissile(myMunitionsId,  missileId, enemyMissileId);
		}
	
		/** Defense Airplane or ship launch interception launcher **/
		pointcut defenseLaunchMissile(String myMunitionsId, String type,String missileId, String enemyLauncherId):
					execution(public void TzukEitan.war.WarControl.defenseLaunchMissile(String , String ,
							String , String )) && args( myMunitionsId,  type, missileId,  enemyLauncherId);
		after(String myMunitionsId, String type,
				String missileId, String enemyLauncherId): defenseLaunchMissile( myMunitionsId,  type,
						 missileId,  enemyLauncherId){
			warLogger.defenseLaunchMissile(myMunitionsId, type, missileId, enemyLauncherId);
		}
	
		/** Defense event for hit interception (to missile) **/
		pointcut defenseHitInterceptionMissile(String whoLaunchedMeId,
				String id, String enemyMissileId):
					execution(public void TzukEitan.war.WarControl.defenseHitInterceptionMissile(String ,String , String )) 
					&& args( whoLaunchedMeId,id,  enemyMissileId);
		after(String whoLaunchedMeId,String id, String enemyMissileId):
			defenseHitInterceptionMissile( whoLaunchedMeId,id,  enemyMissileId){
			warLogger.defenseHitInterceptionMissile( whoLaunchedMeId,id,  enemyMissileId);
		}

		/** Defense event for miss interception (to missile) **/
		pointcut defenseMissInterceptionMissile(String whoLaunchedMeId,	String id, String enemyMissileId, int damage):
					execution(public void TzukEitan.war.WarControl.defenseMissInterceptionMissile(String ,String , String , int ))
					&& args(whoLaunchedMeId,id,enemyMissileId,damage);
		after(String whoLaunchedMeId,String id, String enemyMissileId, int damage): defenseMissInterceptionMissile(whoLaunchedMeId,id,enemyMissileId,damage){
			warLogger.defenseMissInterceptionMissile(whoLaunchedMeId,id,enemyMissileId,damage);
		}
	
		/** Defense event for hit interception (to Launcher) **/
		pointcut defenseHitInterceptionLauncher(String whoLaunchedMeId,
				String Type, String id, String enemyLauncherId):
					execution(public void TzukEitan.war.WarControl.defenseHitInterceptionLauncher(String ,
							String , String , String )) && args( whoLaunchedMeId,
									 Type,  id,  enemyLauncherId);
		after(String whoLaunchedMeId,
				String Type, String id, String enemyLauncherId): defenseHitInterceptionLauncher( whoLaunchedMeId,
						 Type,  id,  enemyLauncherId){
			warLogger.defenseHitInterceptionLauncher( whoLaunchedMeId,
					 Type,  id,  enemyLauncherId);
		}
	
		/** Defense event for miss interception (to Launcher) **/
		pointcut defenseMissInterceptionLauncher(String whoLaunchedMeId,
				String Type, String id, String enemyLauncherId):
					execution(public void TzukEitan.war.WarControl.defenseMissInterceptionLauncher(String ,
							String , String , String )) && args( whoLaunchedMeId,
									 Type,  id,  enemyLauncherId);
		after(String whoLaunchedMeId,
				String Type, String id, String enemyLauncherId): defenseMissInterceptionLauncher( whoLaunchedMeId,
						 Type,  id,  enemyLauncherId){
			warLogger.defenseMissInterceptionLauncher( whoLaunchedMeId,
					 Type,  id,  enemyLauncherId);
		}
	
		/** Defense event. try to intercept launcher but he was hidden **/
		pointcut defenseMissInterceptionHiddenLauncher(String whoLaunchedMeId,
				String type, String enemyLauncherId):
					execution(public void TzukEitan.war.WarControl.defenseMissInterceptionHiddenLauncher(String ,
							String , String )) && args( whoLaunchedMeId,	 type,  enemyLauncherId);
		after(String whoLaunchedMeId,String type, String enemyLauncherId):  defenseMissInterceptionHiddenLauncher( whoLaunchedMeId,	 type,  enemyLauncherId){
			warLogger.defenseMissInterceptionHiddenLauncher( whoLaunchedMeId,	 type,  enemyLauncherId);
		}
	
		/** Will occur when the target that selected isn't exist **/
		pointcut missileNotExist(String defenseLauncherId, String enemyId):
			execution(public void TzukEitan.war.WarControl.missileNotExist(String , String )) && args( defenseLauncherId,  enemyId);
		after(String defenseLauncherId, String enemyId): missileNotExist(defenseLauncherId,  enemyId){
			warLogger.missileNotExist(defenseLauncherId,  enemyId);
		}
	
	
		
		/** Announce when the war endss **/
		pointcut warHasBeenFinished():
			execution(public void TzukEitan.war.WarControl.warHasBeenFinished());
			after(): warHasBeenFinished(){
				warLogger.warHasBeenFinished();
			}
		
		/** Announce when the war starts **/
		pointcut warHasBeenStarted():
			execution(public void TzukEitan.war.WarControl.warHasBeenStarted());
		after(): warHasBeenStarted(){
			warLogger.warHasBeenStarted();
		}
	
		/** Will occur when ship/plane/iron dome is not found in war **/
		pointcut noSuchObject(String type):
			execution(public void TzukEitan.war.WarControl.noSuchObject(String )) && args(type);
			after(String type):  noSuchObject(type){
				warLogger.noSuchObject(type);
			}
	
		/** Will occur when the target that selected isn't exist **/
		pointcut enemyLauncherNotExist(String defenseLauncherId, String launcherId):
			execution(public void TzukEitan.war.WarControl.enemyLauncherNotExist(String , String )) && args( defenseLauncherId,  launcherId);
			after(String defenseLauncherId, String launcherId): enemyLauncherNotExist( defenseLauncherId,  launcherId){
				warLogger.enemyLauncherNotExist(defenseLauncherId, launcherId);
			}
	
	
	
		


	






}
