package TzukEitan.remote.main;

import java.awt.List;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;




import TzukEitan.missiles.RemoteMissile;


public class ServerConnection extends Thread {
	boolean IsConnected=false;
	Socket socket = null;
	ObjectInputStream fromNetInputStream;
	ObjectOutputStream toNetOutputStream;
	RemoteWarClientController remoteWarClientController;
	
	public ServerConnection(RemoteWarClientController remoteWarClientController) {
		this.remoteWarClientController = remoteWarClientController;
	
	}

	@Override
	public void run() {
		super.run();
		while(!IsConnected)
		{
			try {
				socket = new Socket("localhost", 7000);
				IsConnected=true;
				socket.setSoTimeout(30000);
			
				synchronized (remoteWarClientController) {
					remoteWarClientController.notify();
				}

				// NOTE: have to set the output stream before the input stream!
				toNetOutputStream = new ObjectOutputStream(socket.getOutputStream());
				fromNetInputStream = new ObjectInputStream(socket.getInputStream());

				String text = (String)fromNetInputStream.readObject();
				System.out.println("Recieved from server: " + text);

			
			} catch (Exception e) {	System.out.println("*** " + e.getMessage());}

		}
		
		try {
			synchronized (this) {
				wait();
			}
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			socket.close();
			System.out.println("Client said goodbye..");
		} catch (IOException e) {}
		catch (NullPointerException e){}
	}

	public ArrayList<String> requestCitys() throws IOException, ClassNotFoundException{

		toNetOutputStream.writeObject("getCitys");
		@SuppressWarnings("unchecked")
		ArrayList<String> readObject = (ArrayList<String>)fromNetInputStream.readObject();
		return readObject;
	}
	
	public ArrayList<String> requestLauncher() throws IOException, ClassNotFoundException{

		toNetOutputStream.writeObject("getLauncher");
		@SuppressWarnings("unchecked")
		ArrayList<String> readObject = (ArrayList<String>)fromNetInputStream.readObject();
		return readObject;
	}

	public String addMissile(RemoteMissile m) throws IOException, ClassNotFoundException{
		toNetOutputStream.writeObject("addMissile");
		toNetOutputStream.writeObject(m);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String readObject = (String)fromNetInputStream.readObject();
		return readObject;

	}
	public String  addLauncher() throws IOException, ClassNotFoundException {
		
		toNetOutputStream.writeObject("addLauncher");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String readObject = (String)fromNetInputStream.readObject();
		return readObject;
	}

	/**
	 * Close active server connection
	 */
	public void close() {
		synchronized (this) {
			notifyAll();
		}
	}
}


