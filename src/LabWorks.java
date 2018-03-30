
// Lab Works Section !

public class LabWorks {

	public LabWorks() {
		// TODO Auto-generated constructor stub
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


}
