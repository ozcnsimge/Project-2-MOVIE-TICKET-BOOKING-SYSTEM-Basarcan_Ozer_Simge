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
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPopupMenu;

import com.mongodb.DBObject;

public class MoviesPage extends JPanel {
	
	// Initializing the UI Components
	GridBagConstraints gc = new GridBagConstraints();
	
	JPopupMenu menu = new JPopupMenu();
	
	JLabel chooseMovie = new JLabel();
	
	// Getting the database object !
	DatabaseHandler dbHandler = DatabaseHandler.getInstance();

	// Declare an list of the movies we will fill it by fetching from the database.
	List<DBObject> movieList = new ArrayList<DBObject>();
	
	public MoviesPage(double width, double height) {
		
		// Setting properties of the UI components and setting Layout configuration
		// To Place a table in the center and layout of the page using coordinates such
		// as x=0, y=1, we use Grid Bag Layout
		this.setLayout(new GridBagLayout());
		
		this.chooseMovie.setText("Please choose a movie !");
		
		this.movieList = this.dbHandler.listMovies();
		
		this.addComponentsToPanel(this.movieList);
			
	}
	
	public void addComponentsToPanel(List<DBObject> movieL) {
		
		ChooseMovieTable movieTable = new ChooseMovieTable(movieL);
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 0, 0, 5);
		
		this.add(this.chooseMovie,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 0, 0, 5);
		
		this.add(movieTable,gc);
		
	}

}