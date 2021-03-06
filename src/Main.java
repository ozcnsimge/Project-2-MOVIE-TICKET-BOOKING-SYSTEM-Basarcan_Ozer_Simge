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
import java.io.IOException;
import java.awt.Color;
import java.awt.Component;

public class Main {

	// This is the main program of the application.
	// Creating the main frame
	static JFrame frame = new JFrame("SE 318 Movie Ticket Management System");

	// Getting dimensions of the screen size which the application runs on, which
	// later helps us to
	// serve responsive UI elements.
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	static double width = screenSize.getWidth();
	static double height = screenSize.getHeight();

	// Creating an instance of the Navigation Stack class
	static NavigationStack navigator;

	// Creating an instance of the Database Handler class
	static DatabaseHandler dbHandler = DatabaseHandler.getInstance();

	public Main() {
		// TODO Auto-generated constructor stub

	}

	public static boolean addComponentsToMainFrame(Container panel) {
		
		try {
			
			navigator = NavigationStack.getInstance();
			
			// Initialize welcomePage
			WelcomePage welcomePg = new WelcomePage(width, height);

			// Initialize loginPage
			LoginPage loginPage = new LoginPage(width, height);

			// Initialize signUpPage
			SignUpPage signUpPage = new SignUpPage(width, height);
			
			// Initialize Movies Page for user to choose a movie and then buy the ticket
			MoviesPage moviesPage = new MoviesPage(width, height);

			// Initialize AdminPage
			AdminPage adminPage = new AdminPage(width, height);
			
			// Initiailize Payment Page
			PaymentPage paymentPage = new PaymentPage(width, height);

			// Adding pages to the navigator object !
			navigator.addPageToNavigator(welcomePg, "Welcome Page");

			navigator.addPageToNavigator(loginPage, "Login Page");

			navigator.addPageToNavigator(signUpPage, "SignUp Page");
			
			navigator.addPageToNavigator(moviesPage, "Movies Page");
			
			navigator.addPageToNavigator(paymentPage, "Payment Page");

			navigator.addPageToNavigator(adminPage, "Admin Page");

			// Add navigator object to the Main Frame of the application...
			panel.add(navigator);
			
			return true;
			
		} catch(Exception e) {
			
			return false;
			
		}

	}

	public static void main(String[] args) throws IOException {

		// Height and width of the screen size on the device.
		System.out.println(height);
		System.out.println(width);

		// TODO Auto-generated method stub
		System.out.println("Hello World");

		// App exits only on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Full screen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Resizable
		frame.setResizable(true);

		// App will be visible
		frame.setVisible(true);

		if (dbHandler.connectToDatabase()) {

			System.out.println("Database connection was established succcessfully !");

			// Adding components to the Main Frame of the application...
			// The method below helps us to add the pages and UI elements to the main of the
			// application,
			// when the database connection successfully established.
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					addComponentsToMainFrame(frame.getContentPane());
					frame.pack();
				}
			});

		} else {

			System.out.println("Database connection was not established thus the app will exit !");

			System.exit(0);

		}

	}

}
