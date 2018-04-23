import javax.swing.JPanel;
import java.awt.CardLayout;

public class NavigationStack extends JPanel {

	// Singleton Pattern was used thus we need a unique navigator to traverse pages
	// within the application.

	private static NavigationStack instance = null;

	// The navigation stack is simply CardLayout.
	// We put the pages inside the stack as a Card.

	public NavigationStack() {
		// TODO Auto-generated constructor stub

		System.out.println("Navigation Stack constructor !");

		this.setLayout(new CardLayout());

	}

	// This function will return us the singleton object.
	public static NavigationStack getInstance() {

		if (instance == null) {

			System.out.println("Navigation Stack was not created !");

			instance = new NavigationStack();

		}

		System.out.println("Navigation stack object is returning back !");

		return instance;

	}

	// This function adds pages to the navigator.
	public boolean addPageToNavigator(JPanel newPage, String pageLabel) {

		try {

			this.add(newPage, pageLabel);

			return true;

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

	// This function enables navigator to show the requested page.
	public boolean redirectTo(String pageLabel) {

		System.out.println("User wants to redirect to : " + pageLabel);

		try {

			CardLayout cl = (CardLayout) (this.getLayout());

			cl.show(this, pageLabel);

			return true;

		} catch (Exception e) {

			System.out.println(e);

			throw new RuntimeException(e);

		}

	}

}