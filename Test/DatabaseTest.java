import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest {

	static DatabaseHandler dbHandler = DatabaseHandler.getInstance();

	static String movieName;
	static int movieTicketPrice;
	static String username;
	static String password;

	// Execute only once, in the starting of this test code file!
	@BeforeClass
	public static void beforeClass() {
		System.out.println("in before class");
		// Establishing the connection !
		dbHandler.connectToDatabase();
		movieName = "The Lord of The Rings";
		movieTicketPrice = 20;
		username = "ozer";
		password = "123";
	}

	// Login Test Case
	@Test
	public void login() {
		assertTrue(dbHandler.logIn("ozeriko".toString(), "123456".toString()));
	}

	// Sign Up Test Case
	@Test
	public void signUp() {
		assertTrue(dbHandler.signUp("ozeriko", "123456"));
	}
	
	// Add Movie Test Case
	@Test
	public void addMovie() {
		assertTrue(dbHandler.addMovie("john wick 2", 15));
	}
	
	// Testing of fetching the list of the movies in the database.
	@Test
	public void movieList() {
		assertNotNull(dbHandler.listMovies());
	}
	
	// Testing of checking the given username existed on the users collection in the database.
	@Test
	public void checkUser() {
		assertNotEquals("Existed",dbHandler.isUsernameExists("ozeriko"));
	}

	// Execute only once, in the end of this test code file!
	@AfterClass
	public static void afterClass() {
		System.out.println("in after class");
		// Dropping the connection !
		dbHandler.killTheConnection();
	}

}
