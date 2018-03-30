import static org.junit.Assert.*;

import org.junit.Test;

public class testConcateText {

	@Test
	public void equals() {
		
		assertEquals("basar ozer testingisgood",Main.testConcateText("basar ", "ozer "));
		
	}
	
	@Test
public void notSame() {
		
		assertNotSame("basar ozer  testingisgood",Main.testConcateText("basar ", "ozer "));
		
	}

}
