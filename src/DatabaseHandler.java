import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.DBCollection;


public class DatabaseHandler {
	
	private static MongoClient mongoClient;
	
	private static DatabaseHandler instance = null;

	public DatabaseHandler() {
		
		// TODO Auto-generated constructor stub
		
		System.out.println("Database Handler Constructor !");
		
		this.connectToDatabase();
	}
	
	public static DatabaseHandler getInstance() {
		
		if(instance==null) {
			
			System.out.println("Database Handler object was not created !");
			
			instance = new DatabaseHandler();
			
		}
		
		System.out.println("Database handler was already created, the object is returning back !");
		
		return instance;
		
	}
	
	public void logIn(String username, String password) {
		
		System.out.println("Username :"+username+" is trying to login !");
		
	}
	
	public void signUp(String username, String password) {
		
		System.out.println("Username :"+username+" is trying to sign up !");
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean connectToDatabase() {
		
		System.out.println("Database handler is trying to connect to the mongoDb !");
		
		mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		
		@SuppressWarnings("unused")
		DB database = mongoClient.getDB("local");
		
		return true;
	}

}
