package TzukEitan.GUI;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jdk.nashorn.internal.runtime.JSType;
import TzukEitan.DB.WarDbJDBC;

public class frmDbInfo extends JFrame {
	private JScrollPane jspInfo;
	private JTextArea jtaInfo; 
	
	public frmDbInfo(LocalDateTime fromDate, LocalDateTime toDate) {
		String DBInfo = WarDbJDBC.getHistoryLog(fromDate, toDate);
		jtaInfo = new JTextArea();
		jtaInfo.setText(DBInfo.equals("") ? "No War Logs In This Dates" : DBInfo);
		jspInfo = new JScrollPane(jtaInfo);
		getContentPane().add(jspInfo);
		
		setTitle("War Info By Date");
		setSize(700,420);
		setVisible(true);
	}

}
