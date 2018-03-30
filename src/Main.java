import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.Color;

public class Main {
	
	static JPanel navigationStack;

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void addComponentsToMainFrame(Container panel) {
	
		 navigationStack = new JPanel(new CardLayout());
		 
		 JPanel welcomePage = new JPanel();
		 
		 welcomePage.setBackground(new Color(125,125,190));
		 
		 JLabel welcomeLabel = new JLabel();
		 
		 welcomeLabel.setText("Welcome to the Movie Ticket Booking");
		 
		 welcomePage.add(welcomeLabel);
		 
		 welcomePage.setVisible(true);
		 
		 navigationStack.add(welcomePage," Welcome Page");
		 
		 panel.add(navigationStack);
		
	}
	
	public static int divInt(int n1, int n2) {
		
		if(n2 == 0) {
			
			throw new IllegalArgumentException("Cannot divide by 0 !");
			
		}
		
		return n1 / n2;
		
	}
	
	public static int testFactorial(int number) {
		
		int fact = 1;
		
		for(int i=1;i<number;i++) {
			fact = fact + fact*i;
		}
		
		System.out.println(fact);
		
		return fact;
		
	}
	
	public static String testConcateText(String text1, String text2) {
		
		String concatanatedString = "";
		
		concatanatedString = text1 + text2;
		
		concatanatedString = concatanatedString + "testingisgood";
		
		return concatanatedString;
		
	}
	
	public static int testSumOdds(int number) {
		
		int sum = 0;
		
		for(int i=1;i<=number;i++) {
			
			if ( i %2 == 1 ) {
				
				sum = sum + i;
				
			}
			
		}
		
		return sum;
		
	}

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		
		divInt(5,10);
		
		testFactorial(5);
		
		JFrame frame = new JFrame("SE 318 Movie Ticket Management System");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
        // frame.setLocation(dim.width/2-frame.getSize().width/2-350, dim.height/2-frame.getSize().height/2-300);
        
        //  frame.setMinimumSize(new Dimension(750, 200));
        
        // frame.setBackground(new Color(123, 235, 148));
        
        // frame.pack();
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        frame.setResizable(true);
        
        frame.setVisible(true);
        
        addComponentsToMainFrame(frame.getContentPane());
        
	}

}
