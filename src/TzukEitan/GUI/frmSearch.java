package TzukEitan.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.scene.layout.Border;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class frmSearch extends JFrame {
	
	public frmSearch() {
		
		getContentPane().setLayout(new BorderLayout());
		JPanel calPanel = new JPanel();
		
		setTitle("Search By Date");
		pnCal calLeft = new pnCal(2013,5,12);
		calPanel.add(calLeft);
		
		pnCal calRight = new pnCal();
		calPanel.add(calRight);
		
		getContentPane().add(calPanel,BorderLayout.CENTER);
		
		JPanel btnPanel = new JPanel();
		JButton btnSearch = new JButton("Search");
		btnPanel.add(btnSearch);
		getContentPane().add(btnPanel,BorderLayout.SOUTH);
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime fromDate = calLeft.userChoise();
				calRight.setIsDayChoosen(true);
				LocalDateTime toDate = calRight.userChoise();
				if(fromDate != null && toDate != null){
					frmDbInfo dbInfo = new frmDbInfo(fromDate,toDate);
					frmSearch.this.setVisible(false);
				}
				
			}
		});
		pack();
		setVisible(true);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
