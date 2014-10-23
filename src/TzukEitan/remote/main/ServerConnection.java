package TzukEitan.remote.main;

import java.awt.List;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;




import TzukEitan.missiles.RemoteMissile;
import javafx.scene.control.Label;

public class ServerConnection extends Thread {
	boolean IsConnected=false;
	Socket socket = null;
	ObjectInputStream fromNetInputStream;
	ObjectOutputStream toNetOutputStream;
	boolean run=false;
	
	public ServerConnection(Label lbLine1) {
		while(!IsConnected)
		{
			try {
				socket = new Socket("localhost", 7000);
				IsConnected=true;
				socket.setSoTimeout(30000);
				lbLine1.setText("Connected to server");

				// NOTE: have to set the output stream before the input stream!
				toNetOutputStream = new ObjectOutputStream(socket.getOutputStream());
				fromNetInputStream = new ObjectInputStream(socket.getInputStream());

				String text = (String)fromNetInputStream.readObject();
				System.out.println("Recieved from server: " + text);

				start();
			} catch (Exception e) {	System.out.println("*** " + e.getMessage());}

		}
	}

	@Override
	public void run() {
		run=true;

		super.run();
		
			do  {
//				Status statusData = null;
//				try{
//				statusData = (Status)fromNetInputStream.readObject();
//				} catch (Exception e){};
//				if (statusData != null){
//				System.out.println(new Date() + " --> Recieved from server: "
//						+ statusData.toString());
//				}
//				statusData = null;
			} while (run);
		
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
		if(run){
			run=false;
		}
	}
}


