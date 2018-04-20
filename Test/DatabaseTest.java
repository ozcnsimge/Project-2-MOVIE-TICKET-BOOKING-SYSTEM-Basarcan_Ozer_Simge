import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest {

	static DatabaseHandler dbHandler = DatabaseHandler.getInstance();


	// Execute only onces, in the starting of this test code file!
	@BeforeClass
	public static void beforeClass() {
		System.out.println("in before class");
		// Establishing the connection !
		dbHandler.connectToDatabase();
	}

	// Login Test Case
	@Test
	public void login() {
		assertTrue(this.dbHandler.logIn("serhat".toString(), "123".toString()));
	}

	// Sign Up Test Case
	@Test
	public void signUp() {
		assertTrue(this.dbHandler.signUp("ozeriko", "123456"));
	}
	
	// Add Movie Test Case
	@Test
	public void addMovie() {
		assertTrue(this.dbHandler.addMovie("john wick 2"));
	}

	// Execute only onces, in the end of this test code file!
	@AfterClass
	public static void afterClass() {
		System.out.println("in after class");
		// Dropping the connection !
		dbHandler.killTheConnection();
	}

}
