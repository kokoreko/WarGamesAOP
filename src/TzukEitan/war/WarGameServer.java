package TzukEitan.war;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import TzukEitan.missiles.RemoteMissile;


public class WarGameServer extends Thread{

	private WarControl warControl;


	public WarGameServer(WarControl warControl ) throws IOException{
		@SuppressWarnings("resource")
		final ServerSocket server = new ServerSocket(7000);
		
		this.warControl = warControl;
		try {
			while(true){
				final Socket socket = server.accept(); // blocking
				
				new Thread(new Runnable() {
					private boolean IsRunning=false;
					@Override
					public void run() {
						try {
							ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
							ObjectInputStream inputStream  = new ObjectInputStream(socket.getInputStream());
							System.out.println(new Date() + " --> Server waits for client...");

							System.out.println(new Date() + " --> Client connected from "
									+ socket.getInetAddress() + ":" + socket.getPort());

							outputStream.writeObject("Welcome to server!");
							System.out.println("*** Sent welcome message to client");
							socket.setSoTimeout(30000);
							IsRunning=true;
							running(outputStream,inputStream);
						} catch (IOException e) {
							System.err.println(e);
						}
					}
					public void running(ObjectOutputStream outputStream, ObjectInputStream inputStream){

						String action=null;
						do {
							try {
								action = (String)inputStream.readObject();
							} catch (ClassNotFoundException | IOException e) {

							}
							if(action!=null){
								System.out.println("*** got action "+action);
								switch (action) {
								case "getCitys":

									try {
										outputStream.writeObject(getDestenations().clone());
										System.out.println("*** sended citys");
									} catch (Exception e) {
										// TODO Auto-generated catch block

									}
									break;
								case "getLauncher":

									try {
										outputStream.writeObject(getLaunchers().clone());
										System.out.println("*** sended launchers");
									} catch (Exception e) {


									}
									break;
								case "addMissile":
									try {
										RemoteMissile m = (RemoteMissile)inputStream.readObject();
										warControl.addEnemyMissileUI(m.getWhoLaunchedMeId(), m.getDestination(), m.getFlyTime(), m.getFlyTime());
										System.out.println("*** got missile to " + m.getDestination());
										//ServerStatus stat = new ServerStatus(ServerStatus.STATUS.Accepted);
										outputStream.writeObject("Accepted");
									} catch (ClassNotFoundException | IOException e) {
										try {
											//	ServerStatus stat = new ServerStatus(ServerStatus.STATUS.Deniend);
											outputStream.writeObject("Deniend");
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									break;
								case "addLauncher":
									try {
										System.out.println("*** got add launcher action");
										if(addLauncher()){

											outputStream.writeObject("Accepted");
										} else{
											// stat = new ServerStatus(ServerStatus.STATUS.Deniend);
											outputStream.writeObject("Deniend");
										}
									} catch (IOException e) {
										try {
											//	ServerStatus stat = new ServerStatus(ServerStatus.STATUS.Deniend);
											outputStream.writeObject("Deniend");
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
									break;
								case "out":
									IsRunning=false;
								default:

									break;
								}
							}
							action=null;
						} while (IsRunning);

						//					try {
						//						socket.close();
						//						server.close();
						//						System.out
						//						.println("Sever is closing after client is disconnected");
						//					} catch (IOException e) { }

						//					try {
						//						wait();
						//					} catch (InterruptedException e) {
						//						// TODO Auto-generated catch block
						//						e.printStackTrace();
						//					}
					}
				}).start();


			}
		} catch (Exception e) {
			System.out.println(e);
		} 

	}

	private boolean addLauncher(){
		if (warControl.addEnemyLauncherUI() != null)
			return true;
		return false;
	}

	private ArrayList<String> getLaunchers(){
		return new ArrayList<String>(warControl.showAllLaunchersUI());

	}

	private ArrayList<String> getDestenations(){
		ArrayList<String> destenations = new ArrayList<String>();
		for (String string : warControl.getAllWarDestinationsUI()) {
			destenations.add(string);
		}
		return destenations;
	}


}
