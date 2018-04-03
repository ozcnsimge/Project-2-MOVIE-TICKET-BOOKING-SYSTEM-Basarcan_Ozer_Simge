import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;

public class Main {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	static double width = screenSize.getWidth();
	static double height = screenSize.getHeight();
	
	// Creating an instance of the Navigation Stack class
	static NavigationStack navigator = NavigationStack.getInstance();
	
	public Main() {
		// TODO Auto-generated constructor stub
		
		
		
	}
	
	public static void addComponentsToMainFrame(Container panel) {
		 
		 // Initialize welcomePage
		 WelcomePage welcomePg = new WelcomePage(width,height);
		 
		 // Initialize loginPage
		 LoginPage loginPage = new LoginPage(width,height);
		 
		 // Initialize signUpPage
		 SignUpPage signUpPage = new SignUpPage(width,height);
		 
		 // Adding pages to the  navigator object !
		 navigator.addPageToNavigator(welcomePg, "Welcome Page");
		 
		 navigator.addPageToNavigator(loginPage, "Login Page");
		 
		 navigator.addPageToNavigator(signUpPage, "SignUp Page");
		 
		 // Add navigator object to the Main Frame of the application...
		 panel.add(navigator);
		
	}
	
	public static void main(String[] args) {
		
		// Height and width of the screen size on the device.
		System.out.println(height);
		System.out.println(width);
		
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		
		// Creating the main frame
		JFrame frame = new JFrame("SE 318 Movie Ticket Management System");
		
		// App exits only on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		// Full screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Resizable
        frame.setResizable(true);
        
        // App will be visible
        frame.setVisible(true);
        
        // Adding components to the Main Frame of the application...
        addComponentsToMainFrame(frame.getContentPane());
        
	}

}
