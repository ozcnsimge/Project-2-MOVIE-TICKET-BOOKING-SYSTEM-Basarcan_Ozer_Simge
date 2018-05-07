import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MovieTheaterPage extends JPanel {
	
	// Getting the database object !
	DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	// Getting the navigator object
	NavigationStack navigator = NavigationStack.getInstance();
	
	// Initializing the UI Components !
	GridBagConstraints gc = new GridBagConstraints();
	
	JLabel chooseTheSeatLabel = new JLabel();
	
	JButton goToPayment = new JButton();
	
	private static int selectedSeat = 0;
	
	Object[] seatNumbers = { 1, 2, 3, 4, 5, 6, 7, 8 ,9, 10 };
	
	DefaultComboBoxModel model = new DefaultComboBoxModel(seatNumbers);
	
	JComboBox seatList = new JComboBox(model);
	
	public MovieTheaterPage(double width, double height) {
		
		System.out.println("Movie Theater Page Constructor !");
		
		this.chooseTheSeatLabel.setText("Choose an avaiable seat !");
		this.chooseTheSeatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.goToPayment.setText("Go To Payment Page");
		this.goToPayment.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.seatList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox) e.getSource();
				selectedSeat = (int) cb.getSelectedItem();
				System.out.println("Selected Seat : "+selectedSeat);
			}
			
		});
		
		this.goToPayment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				navigator.redirectTo("Payment Page");
			}
			
		});
		
		this.addComponentToPanel();
	}
	
	void addComponentToPanel() {
		
		// Adding Grid Bag Layout
		this.setLayout(new GridBagLayout());
		
		// starting from x=0 and y=0
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		// insets are basically places a margin around the
		gc.insets = new Insets(5, 0, 0, 5);
		// 5 from below and 5 from top
		this.add(this.chooseTheSeatLabel, gc);
		
		System.out.println("Selected movie : "+this.navigator.choosenMovieName);
		
		if(!navigator.choosenMovieName.equals("")) {
			System.out.println("Selected Movie exists !");
			// starting from x=0 and y=0
			gc.gridx = 0;
			gc.gridy = 1;
			gc.anchor = GridBagConstraints.CENTER;
			// insets are basically places a margin around the
			gc.insets = new Insets(5, 0, 0, 5);
			// 5 from below and 5 from top
			this.add(this.seatList, gc);
		}
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.CENTER;
		// insets are basically places a margin around the
		gc.insets = new Insets(5, 0, 0, 5);
		// 5 from below and 5 from top
		this.add(this.goToPayment, gc);
		
		
	}

}
