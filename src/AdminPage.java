import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class AdminPage extends JPanel {
	
	// Initializing the UI Components !
	
	JLabel adminWelcomeLabel = new JLabel("Welcome Back, Administrator !");
	
	JButton backButton = new JButton("Back to Main Menu");
	
	JTabbedPane movieTabs = new JTabbedPane();
	
	JPanel movieListPanel = new JPanel();
	
	JPanel addMoviePanel = new JPanel();
	
	GridBagConstraints gc = new GridBagConstraints();
	
	// Getting the navigator object
	NavigationStack navigator = NavigationStack.getInstance();
	
	MovieList movieList = new MovieList();
	
	AddMovie addMovie = new AddMovie();

	public AdminPage(double width, double height) {
		// TODO Auto-generated constructor stub
		
		this.addComponentsToAdminPage(width, height);
		
		// Adding a action to the back button which redirects the user to the main page.
		this.backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				navigator.redirectTo("Welcome Page");
				
			}
			
		});
		
	}
	
	public void addComponentsToAdminPage(double width, double height) {
		
		// Setting Border Layout of the top panel
		this.setLayout(new BorderLayout());
		
		// Creating a main panel
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		// Setting background color
		mainPanel.setBackground(new Color(127,150,127));
		
		// Adding the back button to the west of the main panel
		mainPanel.add(this.backButton,BorderLayout.WEST);
		
	    // Adding two tabs which divides the main panel into two sections
		this.movieTabs.add("Add Movie", this.addMovie);
		this.movieTabs.add("Movie List", this.movieList);
		mainPanel.add(this.movieTabs,BorderLayout.CENTER);		
		
		this.add(mainPanel);
		
	}

}
