package TzukEitan.remote.main;

import java.io.IOException;
import java.util.ArrayList;



import TzukEitan.missiles.RemoteMissile;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RemoteWarClientController {

	private enum OpMODE {Default,LauncherSelect,CitySelect,FlyTimeSelect,DamageSelect}
	private OpMODE operationMode;
	private ServerConnection connetion = null;
	private ArrayList<String> citys;
	private ArrayList<String> launchers;
	private int listIndex=0;
	private RemoteMissile missileToSend;
	@FXML private Label lbLine1;
	@FXML private Label lbLine2;
	@FXML protected void LoginSubmitButtonAction(ActionEvent event) {

	}
	@FXML protected void AddMissileButtonAction(ActionEvent event) {
		listIndex=0;
		try {
			launchers = connetion.requestLauncher();
			System.out.println("launchers: "+launchers.size());
			citys = connetion.requestCitys();
		} catch (ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}
		if(launchers != null && !launchers.isEmpty()){
			missileToSend=new RemoteMissile();
			operationMode=OpMODE.LauncherSelect;
			lbLine1.setText("Launch Missile - Select launcher:");

			lbLine2.setText(launchers.get(listIndex));
		} else {
			lbLine1.setText("ERROR: no launchers");
		}
	}
	@FXML protected void AddLauncherButtonAction(ActionEvent event) {
		listIndex=0;
		lbLine1.setText("Add Launcher");
		lbLine2.setText("Adding...");
		
		try {
			 
	
			lbLine2.setText(connetion.addLauncher());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@FXML protected void TrunOnOffSystem(ActionEvent event){
		if(connetion == null){
			connetion = new ServerConnection(lbLine1);
			operationMode= OpMODE.Default;
			lbLine2.setText("Select option");
		}
	}
	@SuppressWarnings("incomplete-switch")
	@FXML protected void NextButton(ActionEvent event){
		switch(operationMode){
		case LauncherSelect:
			listIndex++;
			if(listIndex >= launchers.size())
				listIndex=0;
			lbLine2.setText(launchers.get(listIndex));
			break;
		case CitySelect:
			listIndex++;
			if(listIndex >= citys.size())
				listIndex=0;
			lbLine2.setText(citys.get(listIndex));
			break;
		case FlyTimeSelect:
			missileToSend.setFlyTime(missileToSend.getFlyTime() + 1);
			lbLine2.setText(missileToSend.getFlyTime()+"");
			break;
		case DamageSelect:
			missileToSend.setDamage(missileToSend.getDamage() + 100);
			lbLine2.setText(missileToSend.getDamage()+"");
			break;
		}
	}
	@FXML protected void BackButton(ActionEvent event){
		switch(operationMode){
		case LauncherSelect:
			listIndex--;
			if(listIndex <= 0)
				listIndex=launchers.size()-1;
			lbLine2.setText(launchers.get(listIndex));
			listIndex=0;
			break;
		case CitySelect:
			listIndex++;
			if(listIndex >= 0)
				listIndex=citys.size()-1;
			lbLine2.setText(citys.get(listIndex));
			listIndex=0;
			break;
		case FlyTimeSelect:
			missileToSend.setFlyTime(missileToSend.getFlyTime() - 1);
			if(missileToSend.getFlyTime()<0) missileToSend.setFlyTime(0);
			lbLine2.setText(missileToSend.getFlyTime()+"");
			break;
		case DamageSelect:
			missileToSend.setDamage(missileToSend.getDamage() - 100);
			if(missileToSend.getDamage()<0) missileToSend.setDamage(0);
			lbLine2.setText(missileToSend.getDamage()+"");
			break;
		case Default:
			break;
		default:
			break;
		}
	}
	@FXML protected void SelectButton(ActionEvent event){
		switch(operationMode){
		case LauncherSelect:
			missileToSend.setWhoLaunchedMeId(launchers.get(listIndex));
			operationMode=OpMODE.CitySelect;
			lbLine1.setText("Launch Missile - Select city:");
			lbLine2.setText(citys.get(listIndex));
			break;
		case CitySelect:
			missileToSend.setDestination(citys.get(listIndex));
			operationMode=OpMODE.FlyTimeSelect;
			lbLine1.setText("Launch Missile - Set flytime:");
			lbLine2.setText(missileToSend.getFlyTime()+"");
			break;
		case FlyTimeSelect:
			operationMode=OpMODE.DamageSelect;
			lbLine1.setText("Launch Missile - Set damage:");
			lbLine2.setText(missileToSend.getDamage()+"");
			break;
		case DamageSelect:
			try {
					lbLine1.setText("Launch Missile - data send");
					String response = connetion.addMissile(missileToSend);
					lbLine2.setText(response);
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listIndex=0;
			launchers=null;
			citys=null;
			operationMode=OpMODE.Default;
			break;
		case Default:
			break;
		default:
			break;
		}
	}
	@FXML protected void CancelButton(ActionEvent event){

	}
	@FXML protected void ExitButton(ActionEvent event){
		if (connetion != null){
			connetion.close();
		}
		System.exit(0);
	}
}

