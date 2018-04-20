import static org.junit.Assert.*;

import org.junit.Test;

public class UIComponentTest {
	
	Main mainpage = new Main();

	// Test case for if the UI components are loaded
	@Test
	public void testMainPage() {
		
		assertNotNull(mainpage);
		
	}
	
	// Test case for Navigation Stack is loaded or not to the main page of the application.
	@Test
	public void testNavigatorStack() {
		
		assertNull(mainpage.navigator);
		
	}
	
}
