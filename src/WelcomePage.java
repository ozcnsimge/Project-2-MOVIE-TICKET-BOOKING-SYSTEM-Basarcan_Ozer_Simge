import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class WelcomePage extends JPanel {
	
	// Creating the components...
	
	JPanel titlePanel = new JPanel(new GridBagLayout());
	
	JPanel buttonsPanel = new JPanel(new GridBagLayout());
	
	JLabel welcomeTitle = new JLabel();
	
	JButton loginButton = new JButton("Login !");
	
	JButton signUpButton = new JButton("Sign Up !");
	
	JLabel image = new JLabel(new ImageIcon("movie_ticket_popcorn.jpg"));
	
	GridBagConstraints gc = new GridBagConstraints();
	
	// Getting the navigator object
	NavigationStack navigator = NavigationStack.getInstance();

	public WelcomePage(double width, double height) {
		
		// TODO Auto-generated constructor stub
		
		// Actions and some configurations...
		
		Font labelFont = this.welcomeTitle.getFont();
        
		this.welcomeTitle.setFont(new Font(labelFont.getName(),Font.PLAIN, (int)(width/50) ));
		
		this.welcomeTitle.setText("<html><body><nobr>"+ "Welcome To The Movie Booking" +"</nobr></body></html>");
		
		this.welcomeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.loginButton.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				System.out.println("Login Section button clicked !");
				
				// Routing to the login Page
				navigator.redirectTo("Login Page");
				
			}
			
		});
		
		this.signUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Sign Up Section button clicked !");
			
				navigator.redirectTo("SignUp Page");
			}
			
		});
		
		this.addComponents(width, height);
		
	}
	
	public void addComponents(double width, double height) {
	
		// Designing the frame...
		
		this.setLayout(new GridBagLayout());
		
		this.setBackground(new Color(127,150,127));
		
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setAlignmentY(CENTER_ALIGNMENT);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.insets = new Insets((int) (height/40),(int) (width/40),(int) (width/40),(int) (height/30));
		
		this.add(image);
		
		gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets((int) (height/30),10,30,10);
        
        // welcomeTitle.setPreferredSize(new Dimension((int) (width/10),(int) (height/12)));
      
        this.add(welcomeTitle,gc);
        
        gc.fill = GridBagConstraints.CENTER;
        
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets((int) (height/40),10,30,10);
        
        loginButton.setPreferredSize(new Dimension((int) (width/10),(int) (height/12)));
        
        this.add(loginButton,gc);
        
        gc.fill = GridBagConstraints.CENTER;
        
        gc.gridx = 0;
        gc.gridy = 3;
        gc.insets = new Insets((int) (height/40),10,30,10);
        
        signUpButton.setPreferredSize(new Dimension((int) (width/10),(int) (height/12)));
        
        this.add(signUpButton,gc);
		
	}

}
