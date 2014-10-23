package TzukEitan.GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import TzukEitan.utils.Utils;
import TzukEitan.view.SwingView;



public class warMenu extends JMenuBar {
	
	private SwingView mainFrame;
	private JFrame searchFrame;
	
	public warMenu(SwingView mainFrame){
		this.mainFrame = mainFrame;

		
		JMenu fileMenu = new JMenu("file");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					mainFrame.endWar();
			}
		});
		fileMenu.add(exitMenuItem);
		this.add(fileMenu);
		
		JMenu HistoryMenu = new JMenu("History");
		JMenuItem SearchItem = new JMenuItem("Search By Date");
		SearchItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
				searchFrame = new frmSearch();
				
			}
		});
		HistoryMenu.add(SearchItem);
		this.add(HistoryMenu);
		

	}
}
