import static org.junit.Assert.*;

import org.junit.Test;

public class FactorialTest {

	@Test
	public void testEquals() {
		
		assertEquals(720,LabWorks.testFactorial(6));
		
	}
	
	@Test
	public void testNotSame() {
		
		assertNotSame(719,LabWorks.testFactorial(6));
		
	}

}
