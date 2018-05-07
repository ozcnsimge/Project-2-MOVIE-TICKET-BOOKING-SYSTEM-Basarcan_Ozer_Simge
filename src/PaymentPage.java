import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PaymentPage extends JPanel {
	
	// Getting the database object !
	DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	// Initializing the UI Components
	GridBagConstraints gc = new GridBagConstraints();
	
	JLabel cardNumberLabel = new JLabel();
	
	JTextField creditCardNumber = new JTextField(16);
	
	JButton payButton = new JButton();
	
	public PaymentPage(double width, double height) {
		
		this.cardNumberLabel.setText("Enter a valid credit card number !");
		this.cardNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.creditCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.payButton.setText("Pay");
		this.payButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.creditCardNumber.addKeyListener(new KeyAdapter() {
			
			@Override
	        public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(creditCardNumber.getText().length() >= 16) {
					
					e.consume();
					
				}
				
				if(Character.isLetter(c)) {
					e.consume();
				}
				
				if(Character.isWhitespace(c)) {
					e.consume();
				}
				
				if(Character.isAlphabetic(c)) {
					e.consume();
				}
				
			}
			
		});
		
		this.payButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(dbHandler.buyTicket("selam"));
				
			}
			
		});
		
		this.addComponentsToPanel();
	}
	
	public void addComponentsToPanel() {
		
		// Setting properties of the UI components and setting Layout configuration
		// To Place a table in the center and layout of the page using coordinates such
		// as x=0, y=1, we use Grid Bag Layout
		this.setLayout(new GridBagLayout());
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 0, 0, 5);
		this.add(this.cardNumberLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 0, 0, 5);
		this.add(this.creditCardNumber,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 0, 0, 5);
		this.add(this.payButton,gc);
		
	}

}
