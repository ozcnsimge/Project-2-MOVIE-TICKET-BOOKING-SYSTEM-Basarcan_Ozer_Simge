import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mongodb.DBObject;

public class MovieList extends JPanel{
	
	GridBagConstraints gc = new GridBagConstraints();
	
	JLabel movieListLabel = new JLabel();
	
	JLabel myFirstMovie = new JLabel("The Lord of the Rings : ");
	
	JScrollPane scrollPane = new JScrollPane();
	
	JPanel movieListPanel = new JPanel(new BorderLayout());
	
	JButton refreshMoviesButton = new JButton();
	
	// Getting the database object !
	
	DatabaseHandler dbHandler = DatabaseHandler.getInstance();
	
	List <DBObject> movieList = new ArrayList<DBObject>();

	public MovieList() {
		// TODO Auto-generated constructor stub
		this.movieListLabel.setText("List of the movies !");
		
		this.scrollPane.setAutoscrolls(true);
		
		this.movieListPanel.setPreferredSize(new Dimension(300,400));
		
		this.movieList = this.dbHandler.listMovies();
		
		System.out.println("List of Movies !");
		
		System.out.println(this.movieList);
		
		this.refreshMoviesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				movieList = dbHandler.listMovies();
				
				addComponentsToPanel(movieList);
				
			} 
			
		});
		
		this.addComponentsToPanel(this.movieList);
		
	}

	public void addComponentsToPanel(List <DBObject> movieL) {
		
		// this.setBackground(new Color(127,150,127));
		this.setLayout(new GridBagLayout());
		
		int i = 0;
		
		for(i=0;i<movieL.size();i++) {
			
			gc.gridx = 0;
			gc.gridy = i;
			gc.anchor = GridBagConstraints.CENTER;
			gc.insets = new Insets(5,0,0,5);
			
			String nameOfTheMovie = (String) movieL.get(i).get("name");
			
			JLabel movieN = new JLabel(nameOfTheMovie);
			
			System.out.println("For loop !!"+nameOfTheMovie);
			
			this.add(movieN,gc);
			
		}
		
		gc.gridx = 0;
		gc.gridy = i+1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5,0,0,5);
		
		this.add(this.refreshMoviesButton, gc);
		
		this.scrollPane.add(this.movieListPanel);
		
	}
}
