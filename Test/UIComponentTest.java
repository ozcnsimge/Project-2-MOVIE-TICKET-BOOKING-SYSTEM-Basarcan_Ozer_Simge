import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import java.awt.Dimension;

// This is necessary for running nested Tests
@RunWith(Enclosed.class)
public class UIComponentTest {

	static Main mainpage = new Main();

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	static double width = screenSize.getWidth();
	static double height = screenSize.getHeight();

	static LoginPage lp = new LoginPage(width, height);
	static SignUpPage sp = new SignUpPage(width, height);

	static NavigationStack navigator;

	public static class MainPageTests {

		// Connecting to the main page to the database before initializing main page as
		// a whole
		@BeforeClass
		public static void initializeComponents() {
			mainpage.dbHandler.connectToDatabase();
		}

		@Test
		public void startUpMainPage() {
			assertNotNull(mainpage.addComponentsToMainFrame(mainpage.frame.getContentPane()));
		}

		// Test case for if the UI components are loaded to the main page of the
		// application.
		@Test
		public void testMainPage() {
			mainpage.addComponentsToMainFrame(mainpage.frame.getContentPane());
			assertNotNull(mainpage);
		}

	}

	public static class LoginPageTests {

		// Testing the login button UI component
		@Test
		public void loginButton() {
			lp.addComponentsToLoginPage(width, height);
			assertNotNull(lp.loginButton);
		}

		// Testing the backButton UI component
		@Test
		public void loginPagebackButton() {
			lp.addComponentsToLoginPage(width, height);
			assertNotNull(lp.backButton);
		}

		// Testing the navigator
		@Test
		public void loginPagenavigator() {
			lp.addComponentsToLoginPage(width, height);
			assertNotNull(lp.navigator);
		}

	}

	public static class SignUpPageTests {
		
		// Testing the sign up button UI component
		@Test
		public void signUpButton() {
			sp.addComponentsToSignUpPage(width, height);
			assertNotNull(sp.signUpButton);
		}

		// Testing the backButton UI component
		@Test
		public void signUpPagebackButton() {
			sp.addComponentsToSignUpPage(width, height);
			assertNotNull(sp.backButton);
		}

		// Testing the navigator
		@Test
		public void signUpPagenavigator() {
			sp.addComponentsToSignUpPage(width, height);
			assertNotNull(sp.navigator);
		}
	}

	public static class NavigatorTests {
		
		// Testing the getting the singleton navigation stack object.
		@Test
		public void getNavigator() {
			assertNotNull(navigator = NavigationStack.getInstance());
		}
		
		// Testing of adding a panel as a new page to the navigator stack.
		@Test
		public void addPageToNavigator() {
			JPanel testPanel = new JPanel();
			assertNotNull(navigator.addPageToNavigator(testPanel, "Test Panel"));
		}
	}

}
