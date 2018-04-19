import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class SignUpPage extends JPanel{
	
		// Getting the database object !
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
		// Creating the components...	
		JLabel signUpLabel = new JLabel("Please enter the credentials !");
		
		JLabel userNameLabel = new JLabel("Username");
		
		JLabel passwordLabel = new JLabel("Password");
		
		JLabel passwordConfirmationLabel = new JLabel("Confirm the password");
		
		JButton backButton = new JButton("Back to Main Menu");
		
		JButton signUpButton = new JButton("Sign Up !");
		
		JTextField userNameInput = new JTextField(16);
		
		JPasswordField passwordInput = new JPasswordField(16);
		
		JPasswordField passwordConfirmationInput = new JPasswordField(16);
		
		JLabel passwordMatchWarning = new JLabel("Please confirm the password !");
		
		JOptionPane signUpAlert = new JOptionPane();
		
		GridBagConstraints gc = new GridBagConstraints();
		
		// Getting the navigator object
		NavigationStack navigator = NavigationStack.getInstance();

	public SignUpPage(double width, double height) {
		// TODO Auto-generated constructor stub
		
		Font labelFont = this.signUpLabel.getFont();
        
		this.signUpLabel.setFont(new Font(labelFont.getName(),Font.PLAIN,20));
		
		// Actions and some configurations...
		this.userNameLabel.setForeground(new Color(180,180,180));
		this.passwordLabel.setForeground(new Color(180,180,180));
		this.passwordConfirmationLabel.setForeground(new Color(180,180,180));
				
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
		backButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				navigator.redirectTo("Welcome Page");
			}
			
		});
		
		
		
		userNameInput.addKeyListener(new KeyAdapter() {
			
			@Override
	        public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(Character.isDigit(c)) {
					
					e.consume();
					
				}
				
				if(userNameInput.getText().length() >= 10) {
					
					e.consume();
					
				}
				
				if (Character.isLetter(c)) {
					
	                e.setKeyChar(Character.toLowerCase(c));
	                
	            }
	        	}
			
		});

		userNameInput.setHorizontalAlignment(JTextField.CENTER);
		
		passwordInput.addKeyListener(new KeyAdapter() {
			
			@SuppressWarnings("deprecation")
			@Override
	        public void keyTyped(KeyEvent e) {
				
				if(passwordInput.getPassword().length >= 16) {
					
					e.consume();
					
				}
	        	}
			
		});
		
		passwordInput.setHorizontalAlignment(JPasswordField.CENTER);
		
		this.passwordConfirmationInput.addKeyListener(new KeyAdapter() {
			
			@SuppressWarnings("deprecation")
			@Override
	        public void keyTyped(KeyEvent e) {
				
				System.out.println("sjdhasd");
				
				if(passwordConfirmationInput.getPassword().length >= 16) {
					
					e.consume();
					
				}
				
				if(passwordConfirmationInput.getPassword().equals(passwordInput.getPassword())) {
					
					System.out.println("Password do not match !");
					
				}
	        	}
			
		});
		
		this.passwordConfirmationInput.setHorizontalAlignment(JPasswordField.CENTER);
		
		this.signUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if( userNameInput.getText().toString().length() == 0 || passwordInput.getPassword().toString().length() == 0 || passwordConfirmationInput.getPassword().toString().length() == 0 ) {
					
					System.out.println("The required fields are empty !");
					
					signUpAlert.showMessageDialog(null, "You did not fill all the required fields !");
					
				} 
				else {
					
					System.out.println("The required fields are non-empty, the system will try to sign the user up !");
					
					if( dbHandler.signUp(userNameInput.getText().toString(), passwordInput.getText().toString() ) ) {
						
						signUpAlert.showMessageDialog(null, "You are registered successfully !");
						
					} else {
						
						signUpAlert.showMessageDialog(null, "Registration was not completed !");
						
					}
					
				}
				
			}
			
		});
			
		this.addComponentsToLoginPage(width, height);
				
	}
	
public void addComponentsToLoginPage(double width, double height) {
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel gridbagPanel = new JPanel();
		
		gridbagPanel.setBackground(new Color(40,40,60));
		
		this.setLayout(new BorderLayout());
		
		gridbagPanel.setLayout(new GridBagLayout());
		
		mainPanel.add(this.backButton,BorderLayout.WEST);
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		gridbagPanel.add(this.signUpLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		gridbagPanel.add(this.userNameLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		gridbagPanel.add(this.userNameInput,gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		gridbagPanel.add(this.passwordLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		gridbagPanel.add(this.passwordInput,gc);
		
		gc.gridx = 0;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		gridbagPanel.add(this.passwordConfirmationLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		gridbagPanel.add(this.passwordConfirmationInput,gc);
		
		gc.gridx = 0;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		gridbagPanel.add(this.passwordMatchWarning,gc);
		
		gc.gridx = 0;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		gridbagPanel.add(this.signUpButton,gc);
		
		mainPanel.add(gridbagPanel,BorderLayout.CENTER);
		
		this.add(mainPanel);
		
	}
}
