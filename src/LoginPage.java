import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

public class LoginPage extends JPanel {
	
	// Getting the database object !
	
	DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	// Creating the components...
	
	JOptionPane loginAlert = new JOptionPane();
	
	JTabbedPane loginTabs = new JTabbedPane();
	
	JLabel loginLabel = new JLabel("Please Enter the Credentials !");
	
	JLabel managerLoginLabel = new JLabel("Please Enter the Credentials !");
	
	JLabel userNameLabel = new JLabel("Username");
	
	JLabel managerUserNameLabel = new JLabel("Manager Username");
	
	JLabel passwordLabel = new JLabel("User Password");
	
	JLabel managerPasswordLabel = new JLabel("Manager Password");
	
	JButton backButton = new JButton("Back to Main Menu");
	
	JButton loginButton = new JButton("User Login !");
	
	JButton managerLoginButton = new JButton("Manager Login !");
	
	JTextField userNameInput = new JTextField(16);
	
	JTextField managerUserNameInput = new JTextField(16);
	
	JPasswordField passwordInput = new JPasswordField(16);
	
	JPasswordField managerPasswordInput = new JPasswordField(16);
	
	GridBagConstraints gc = new GridBagConstraints();
	
	// Getting the navigator object
	NavigationStack navigator = NavigationStack.getInstance();

	public LoginPage(double width, double height) {
		
		//  Setting properties of the UI components
		Font labelFont = this.loginLabel.getFont();
        
		this.loginLabel.setFont(new Font(labelFont.getName(),Font.PLAIN,20));
		
		labelFont = this.managerLoginLabel.getFont();
        
		this.managerLoginLabel.setFont(new Font(labelFont.getName(),Font.PLAIN,20));
		
		backButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.managerLoginButton.setBackground(new Color(70,70,70));
		this.loginButton.setBackground(new Color(70,70,70));
		
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
		
		passwordInput.addKeyListener(new KeyAdapter() {
			
			@SuppressWarnings("deprecation")
			@Override
	        public void keyTyped(KeyEvent e) {
				
				if(passwordInput.getText().length() >= 16) {
					
					e.consume();
					
				}
	        	}
			
		});
		
		this.userNameInput.setHorizontalAlignment(JTextField.CENTER);
		this.managerUserNameInput.setHorizontalAlignment(JTextField.CENTER);
		
		this.passwordInput.setHorizontalAlignment(JPasswordField.CENTER);
		this.managerPasswordInput.setHorizontalAlignment(JPasswordField.CENTER);
		
		this.managerLoginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println(String.valueOf(managerPasswordInput.getPassword()));
				System.out.println(managerUserNameInput.getText());
				
				if( managerUserNameInput.getText().equals("admin") && String.valueOf(managerPasswordInput.getPassword()).equals("password") ) {
					
					navigator.redirectTo("Admin Page");
					
				} else {
					
					loginAlert.showMessageDialog(null, "Wrong credentials for manager account !");
					
				}
				
			}
			
		});
		
		this.loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if( dbHandler.logIn(userNameInput.getText().toString(), passwordInput.getText().toString()) ) {
					
					loginAlert.showMessageDialog(null, "Login is successful !");
					
				} else {
					
					loginAlert.showMessageDialog(null, "Wrong credentials !");
					
				}
				
			}
			
		});
		
		this.addComponentsToLoginPage(width, height);
		
	}
	
	public void addComponentsToLoginPage(double width, double height) {
		
		// Initialize Panels
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		mainPanel.setBackground(new Color(127,150,127));
		
		JPanel userLoginPanel = new JPanel();
		
		JPanel managerLoginPanel = new JPanel();
		
		userLoginPanel.setBackground(new Color(40,40,60));
		
		managerLoginPanel.setBackground(new Color(40,40,60));
		
		this.setLayout(new BorderLayout());
		
		userLoginPanel.setLayout(new GridBagLayout());
		
		managerLoginPanel.setLayout(new GridBagLayout());
		
		mainPanel.add(this.backButton,BorderLayout.WEST);
		
		// User Login Panel
		// starting from x=0 and y=0
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		userLoginPanel.add(this.loginLabel,gc);
		
		gc.gridx = 0;
		// y=1 means that this component will be shown at just below at the y=0 component
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		userLoginPanel.add(this.userNameLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		
		userLoginPanel.add(this.userNameInput,gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		userLoginPanel.add(this.passwordLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		userLoginPanel.add(this.passwordInput,gc);
		
		gc.gridx = 0;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		userLoginPanel.add(this.loginButton,gc);
		
		// Manager Login Panel
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		managerLoginPanel.add(this.managerLoginLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		managerLoginPanel.add(this.managerUserNameLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		managerLoginPanel.add(this.managerUserNameInput,gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		managerLoginPanel.add(this.managerPasswordLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		managerLoginPanel.add(this.managerPasswordInput,gc);
		
		gc.gridx = 0;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		managerLoginPanel.add(this.managerLoginButton,gc);
		
		// Adding Panels to the tabs !
		this.loginTabs.addTab("User Login", userLoginPanel);
		
		this.loginTabs.addTab("Manager Login", managerLoginPanel);
		
		// Adding Tab Panel to the Main Panel
		mainPanel.add(loginTabs,BorderLayout.CENTER);
		
		// Create the main Panel
		this.add(mainPanel);
		
	}

}