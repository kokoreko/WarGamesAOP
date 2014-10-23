package TzukEitan.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import TzukEitan.utils.Utils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frmShowStats extends JFrame {
	private JPanel mainPanel;
	private DefaultTableModel tableModel;
	private JTable tblStats;
	private JScrollPane jsmStats;
	public frmShowStats(boolean warHasEnded) {
		setVisible(true);
		
		setTitle("Statistics");
		
		mainPanel = new JPanel();
		mainPanel.setSize(800, 160);
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[] { "Num of launch missiles"
				,"Num of intercept missiles"
				,"Num of hit target missiles"
				,"Num of launchers destroyed"
				,"Total damage"});
		
		tblStats = new JTable(tableModel);
		tblStats.setFont(Utils.FONT);
		
		jsmStats = new JScrollPane(tblStats);
		jsmStats.setEnabled(false);
		jsmStats.setPreferredSize(mainPanel.getSize());
		mainPanel.add(jsmStats);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(warHasEnded) System.exit(0);
			}
		});
		
		setResizable(false);
		setSize(800,160);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		
	}
	public void addStats(long[] statisticsToArray) {
		tableModel.addRow(new Object[] {statisticsToArray[0], statisticsToArray[1],statisticsToArray[2],statisticsToArray[3],statisticsToArray[4]});
		
	}

}
