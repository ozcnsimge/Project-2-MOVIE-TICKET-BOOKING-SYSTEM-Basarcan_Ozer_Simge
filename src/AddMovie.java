import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddMovie extends JPanel {
	
	
	// Initializing the UI Components !
	GridBagConstraints gc = new GridBagConstraints();
	
	JLabel addMovieLabel = new JLabel("Enter the name of the movie :",SwingConstants.CENTER);
	
	JTextField movieNameInput = new JTextField(25);

	JButton addMovieButton = new JButton("Add Movie");
	
	JOptionPane addMovieAlert = new JOptionPane();
	
	// Getting the database object !
	
	DatabaseHandler dbHandler = DatabaseHandler.getInstance();

	public AddMovie() {
		// TODO Auto-generated constructor stub
		
		//  Setting properties of the UI components
		this.addMovieLabel.setForeground(new Color(40,40,75));
		
		this.movieNameInput.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.addMovieButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Adding action to the movie name input
		this.movieNameInput.addKeyListener(new KeyAdapter() {
			
			@Override
	        public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				// The movie input does accept only digits and letters !
				if(!Character.isLetterOrDigit(c)){
					
					System.out.println(String.valueOf(e.getKeyChar()));
					
					if(!String.valueOf(e.getKeyChar()).equals(" ")) {
						
						e.consume();
						
					}
					
				}
				// if the length of the input is more than 20 then deny the input.
				if(movieNameInput.getText().length() >= 20) {
					
					e.consume();
					
				}
				
	        	}
			
		});
		// Adding action to the button
		this.addMovieButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// Checking if the adding movie function operated successfully or not
				if(dbHandler.addMovie(movieNameInput.getText().toString())) {
					
					addMovieAlert.showMessageDialog(null, "Movie named '"+movieNameInput.getText().toString()+"' was added successfully !");
					
				} else {
					
					addMovieAlert.showMessageDialog(null, "Adding the movie named '"+movieNameInput.getText().toString()+"'was failed !");
					
				}
				
			}
			
		});
		
		
		this.addComponentsToPanel();
	}
	
public void addComponentsToPanel() {
		
		// Adding Grid Bag Layout
		this.setLayout(new GridBagLayout());
		
		// starting from x=0 and y=0
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		// insets are basically places a margin around the
		gc.insets = new Insets(5,0,0,5);
		// 5 from below and 5 from top
		this.add(this.addMovieLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		// 5 from below and 5 from top
		gc.insets = new Insets(5,0,0,5);
		
		this.add(this.movieNameInput,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.CENTER;
		// 5 from below and 5 from top
		gc.insets = new Insets(5,0,0,5);
		
		this.add(this.addMovieButton,gc);
		
	}

}
