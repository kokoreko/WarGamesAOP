/*
 * Made by:
 * Guy Eshel: eshelguy@gmail.com
 * &
 * Ben Amir: amir.ben@gmail.com
 */

package TzukEitan.view;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


import TzukEitan.war.War;
import TzukEitan.war.WarControl;
import TzukEitan.war.WarGameServer;

public class TzukEitan {

	public static void main(String[] args) {
		WarXMLReader warXML;

		ConsoleView view = new ConsoleView();
		SwingView viewGUI = new SwingView();
		War warModel = new War();
		WarControl warControl = new WarControl(warModel, view,viewGUI);
		
		try {
			warXML = new WarXMLReader("warStart.xml", warControl);
			warXML.start();
			warXML.join();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		warModel.start();
		view.start();
		try {
			@SuppressWarnings("unused")
			WarGameServer serverSide= new WarGameServer(warControl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
