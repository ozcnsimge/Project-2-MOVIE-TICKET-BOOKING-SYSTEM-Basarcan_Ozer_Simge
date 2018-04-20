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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mongodb.DBObject;

public class MovieList extends JPanel {

	// Initializing the UI Components
	GridBagConstraints gc = new GridBagConstraints();

	JLabel movieListLabel = new JLabel();

	JLabel myFirstMovie = new JLabel("The Lord of the Rings : ");

	JScrollPane scrollPane = new JScrollPane();

	JPanel movieListPanel = new JPanel(new BorderLayout());

	JButton refreshMoviesButton = new JButton();

	JButton removeMovieButton = new JButton();

	JTable tableOfMovies = new JTable();

	// Columns of the table

	String[] columnNames = { "Name of the Movie", "Price of the Ticket" };

	// Declaring a table with the columns, the data parameter is 0 thus we will fill
	// it later on.
	DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
		// Disabling the editable of the cells in the rows !
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	};

	// Getting the database object !
	DatabaseHandler dbHandler = DatabaseHandler.getInstance();

	// Declare an list of the movies we will fill it by fetching from the database.
	List<DBObject> movieList = new ArrayList<DBObject>();

	// Declaring remove button !

	public MovieList() {
		// TODO Auto-generated constructor stub

		// Setting properties of the UI components and setting Layout configuration
		// To Place a table in the center and layout of the page using coordinates such
		// as x=0, y=1, we use Grid Bag Layout
		this.setLayout(new GridBagLayout());

		// Setting dimensions of the table
		this.tableOfMovies.setPreferredScrollableViewportSize(new Dimension(300, 400));

		// Using default sorter
		this.tableOfMovies.setAutoCreateRowSorter(true);

		this.movieListLabel.setText("List of the movies !");

		this.scrollPane.setAutoscrolls(true);

		this.movieListPanel.setPreferredSize(new Dimension(300, 400));

		this.movieList = this.dbHandler.listMovies();

		System.out.println("List of Movies !");

		System.out.println(this.movieList);

		this.refreshMoviesButton.setText("Refresh Movies");

		// Adding action to the refresher button
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

	public void cleanTheTableModel() {
		int rows = this.model.getRowCount();

		for (int i = rows - 1; i >= 0; i--) {
			this.model.removeRow(i);
		}
	}

	public void addComponentsToPanel(List<DBObject> movieL) {

		// Removing all elements on the panel to replace with the new ones !
		this.removeAll();

		// First, drop all rows in the model to avoid duplicating the list of the movies
		// !
		this.cleanTheTableModel();

		int i;

		for (i = 0; i < movieL.size(); i++) {

			gc.gridx = 0;
			gc.gridy = i;
			gc.anchor = GridBagConstraints.CENTER;
			gc.insets = new Insets(2, 0, 0, 2);

			// To run the below code line, we have to ensure that the each row in the
			// collection has the properties correctly,
			// otherwise, the app will end up with failure.
			
			// Getting ticket price of the movie from the each data fetched from the database
			int ticketPriceOfTheMove = (int) movieL.get(i).get("ticketPrice");

			// Getting name of the movie from the each data fetched from the database
			String nameOfTheMovie = (String) movieL.get(i).get("name");

			// Adding row to the table with the data we got in just above !
			model.addRow(new Object[] { nameOfTheMovie, ticketPriceOfTheMove });

		}

		this.tableOfMovies.setModel(model);

		gc.gridx = 0;
		gc.gridy = i + 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 0, 0, 5);

		this.add(new JScrollPane(this.tableOfMovies), gc);
		
		// Just under the list of movies add the button that refreshes the list of the
		// movie on click action.
		gc.gridx = 0;
		gc.gridy = i + 2;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(5, 0, 0, 5);

		this.add(this.refreshMoviesButton, gc);

		// Re adding all elements to the main panel
		this.revalidate();

	}

}
