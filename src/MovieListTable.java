import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.mongodb.DBObject;

public class MovieListTable extends JPanel
{
	
  private JFrame frame = new JFrame("Table Demo");
  private String[] columnNames = { "Movie Name", "Ticket Price", "", "Delete Movie" };
  private DefaultTableModel tableModel;
  
  JOptionPane movieDeleteAlert = new JOptionPane();
  
  //Getting the database object !
  DatabaseHandler dbHandler = DatabaseHandler.getInstance();
  
  private TableModel model=null;
  
  private JTable table = new JTable(model);

  public MovieListTable(List<DBObject> movieL)
  {
	  
	  List<String[]> values = new ArrayList<String[]>();
	  
	  int i;
	  
	  for (i = 0; i < movieL.size(); i++) {

		// Getting ticket price of the movie from the each data fetched from the database
		int ticketPriceOfTheMovie = (int) movieL.get(i).get("ticketPrice");

		String price = String.valueOf(ticketPriceOfTheMovie);
		// Getting name of the movie from the each data fetched from the database
		String nameOfTheMovie = (String) movieL.get(i).get("name");	
		
		values.add(new String[] { nameOfTheMovie, price });
		
		System.out.println("Name of the movie : "+nameOfTheMovie);
		System.out.println("Ticket price of the movie : "+price);

		}
	
	  this.tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columnNames)
	  {
	    private static final long serialVersionUID = 1L;

	    public boolean isCellEditable(int row, int column)
	    {
	      return column == 3;
	    }
	  };
	
	  
	table.setModel(tableModel);
	
    table.getColumnModel().getColumn(3).setCellRenderer(new ClientsTableButtonRenderer());
    table.getColumnModel().getColumn(3).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setShowHorizontalLines(true);
    table.setShowVerticalLines(false);

    JScrollPane scroll = new JScrollPane(table);
    this.add(scroll);
  }

  class ClientsTableButtonRenderer extends JButton implements TableCellRenderer
  {
    public ClientsTableButtonRenderer()
    {
      setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
      setForeground(Color.black);
      setBackground(UIManager.getColor("Button.background"));
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }
  public class ClientsTableRenderer extends DefaultCellEditor
  {
    private JButton button;
    private final DeleteButtonListener bListener = new DeleteButtonListener();
    private String label;
    private boolean clicked;
    private int row, col;
    private JTable table;

    
    public ClientsTableRenderer(JCheckBox checkBox)
    {
      super(checkBox);
      button = new JButton();
      button.setOpaque(true);
      button.addActionListener(bListener);
    }   
    
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
      // Setting rows and table to the action listener of the table
      bListener.setRow(row);
      bListener.setTable(table);

      button.setForeground(Color.black);
      button.setBackground(UIManager.getColor("Button.background"));
      label = (value == null) ? "" : value.toString();
      button.setText(label);
      clicked = true;
      return button;
    }
    
    // Action Listener to the table to delete the movie when click on the button
    class DeleteButtonListener implements ActionListener {

        private int row;
        private JTable table;

        public void setRow(int row) {
            this.row = row;
        }

        public void setTable(JTable table) {
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (table.getRowCount() > 0) {
                System.out.println("Button clicked : " + ((JButton) event.getSource()).getText());
                DefaultTableModel myModel = ((DefaultTableModel) table.getModel());
                String nameOfTheMovie = (String) myModel.getValueAt(this.row, 0);
                if( dbHandler.removeMovie(nameOfTheMovie) ) {
                    ((DefaultTableModel) table.getModel()).removeRow(this.row);
                    movieDeleteAlert.showMessageDialog(null, "Movie was deleted !");
                } else {
                	movieDeleteAlert.showMessageDialog(null, "Movie was not deleted !");
                }
                ClientsTableRenderer.this.cancelCellEditing();
            }
        }
    }

    public boolean stopCellEditing()
    {
      clicked = false;
      return super.stopCellEditing();
    }

    protected void fireEditingStopped()
    {
      super.fireEditingStopped();
    }
  }

}