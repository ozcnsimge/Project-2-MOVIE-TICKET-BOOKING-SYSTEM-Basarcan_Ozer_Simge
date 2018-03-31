import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.awt.ComponentOrientation;



public class Main {
	
	static JPanel navigationStack;

	public Main() {
		// TODO Auto-generated constructor stub
	}
	 
	
	public static void addComponentsToMainFrame(Container panel) {
	
		 navigationStack = new JPanel(new CardLayout());
		 
		 JPanel welcomePage = new JPanel();

		 //Set Layout for the page
		 welcomePage.setLayout(new BoxLayout(welcomePage, BoxLayout.PAGE_AXIS));
		 //Set background color
		 welcomePage.setBackground(new Color(125,125,190));
		 
		 //Set title
		 JLabel welcomeLabel = new JLabel(); 
		 welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		 welcomeLabel.setText("Welcome to the Movie Ticket Booking");
		 welcomePage.add(welcomeLabel);
		 
		 //Sign up button
		 JButton btnSignUp = new JButton("Sign Up!");
		 btnSignUp.setAlignmentX(Component.CENTER_ALIGNMENT);
		 welcomePage.add(btnSignUp);
		 
		 //Login button
		 JButton btnLogin = new JButton("Login");
		 btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		 welcomePage.add(btnLogin); 
		 
		 // welcomePage.setVisible(true);
		 
		 //Example input for combo boxes
		 String list[]={"example1","example2","example3","example4","example5"}; 
		 
		 //List of movies
		 JComboBox comboBox1=new JComboBox(list);
		 JLabel comboBox1Label = new JLabel("Movie :");
		 comboBox1.setBounds(50,100,90,20); 
		 welcomePage.add(comboBox1Label);
		 welcomePage.add(comboBox1);

		 //Available times
		 JComboBox comboBox2=new JComboBox(list);    
		 comboBox2.setBounds(50,100,90,20); 
		 JLabel comboBox2Label = new JLabel("Time :");
		 welcomePage.add(comboBox2Label);
		 welcomePage.add(comboBox2);

		 //Desired number of tickets
		 JComboBox comboBox3=new JComboBox(list); 
		 JLabel comboBox3Label = new JLabel("Number of Seats :");
		 comboBox3.setBounds(50,100,90,20); 
		 welcomePage.add(comboBox3Label);
		 welcomePage.add(comboBox3);

		 //Category of tickets
		 JComboBox comboBox4=new JComboBox(list); 
		 JLabel comboBox4Label = new JLabel("Category :");
		 comboBox4.setBounds(50,100,90,20); 
		 welcomePage.add(comboBox4Label);
		 welcomePage.add(comboBox4);

		 //Submit selections
		 JButton btnBook = new JButton("Book!");
		 welcomePage.add(btnBook);
		 btnBook.setAlignmentX(Component.CENTER_ALIGNMENT);
		 JPanel pane5= new JPanel();
		 
		 btnBook.addActionListener(new ActionListener() {  
			 
			 public void actionPerformed(ActionEvent e) {
				 
				 String selection = "Your selections :\n" 
						 +comboBox1.getSelectedItem() + "\n"
						 +comboBox2.getSelectedItem() + "\n"
						 +comboBox3.getSelectedItem() + "\n"
						 +comboBox4.getSelectedItem() + "\n"
						 + "Would you like to proceed?";
						 
				 int message = JOptionPane.showInternalConfirmDialog(panel, selection);

		     }  
		 }); 
		 
		 navigationStack.add(welcomePage," Welcome Page");
		 
		 panel.add(navigationStack);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		
		JFrame frame = new JFrame("SE 318 Movie Ticket Management System");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
        // frame.setLocation(dim.width/2-frame.getSize().width/2-350, dim.height/2-frame.getSize().height/2-300);
        
        //  frame.setMinimumSize(new Dimension(750, 200));
        
        // frame.setBackground(new Color(123, 235, 148));
        
        // frame.pack();
        
        frame.setSize(300, 400);
        
        addComponentsToMainFrame(frame.getContentPane());
        
        frame.setLocationRelativeTo(null);
        
        frame.setResizable(true);
        
        frame.setVisible(true);
        
        
        
	}

}
