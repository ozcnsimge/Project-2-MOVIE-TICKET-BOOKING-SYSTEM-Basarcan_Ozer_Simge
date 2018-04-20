import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;

// Singleton Pattern is being used in this method due to avoid further extra connection processes.

public class DatabaseHandler {
	
	// Initializing the neccessary variables
	private static MongoClient mongoClient;
	
	private static DatabaseHandler instance = null;
	
	private static DBCollection usersCollection;
	
	private static DBCollection moviesCollection;
	
	private static DB myDatabase;

	public DatabaseHandler() {
		
		// TODO Auto-generated constructor stub
		
		System.out.println("Database Handler Constructor !");
		
	}
	
	public static DatabaseHandler getInstance() {
		
		// Singleton pattern
		// If there is any instance created before return that instance
		// otherwise create a new instance and return that one
		
		if(instance==null) {
			
			System.out.println("Database Handler object was not created !");
			
			instance = new DatabaseHandler();
			
		}
		
		System.out.println("Database handler was already created, the object is returning back !");
		
		return instance;
		
	}
	
	// This function checks if there is any collection given string in the database.
	public boolean collectionExists(String collectionName) {
		
		Set<String> collectionNames = this.myDatabase.getCollectionNames();
		
		for (final String name : collectionNames) {
	        if (name.equalsIgnoreCase(collectionName)) {
	            return true;
	        }
	    }
		
		return false;
		
	}
	
	// This function authenticates the log in request.
	public boolean logIn(String username, String password) {
		
		System.out.println("Username :"+username+" is trying to login with the password"+password);
		
		try {
			
			// Preparing a query
			BasicDBObject query = new BasicDBObject();
			// Assigning parameters to the query
			query.put("username", username);
			query.put("password", password);
			
			// Search the query in the database
			DBCursor cursor = this.usersCollection.find(new BasicDBObject("username",username).append("password", password));
			
			// The cursor has size more than 1, well that means that we have a user matches with the username along with the password
			if(cursor.size() >= 1) {
				
				DBObject user = cursor.next();
				
				System.out.println(user);
				
				return true;
				
			} else {
				
				// It looks like the given credentials are wrong so it denies the login request.
				return false;
				
			}
			
		} catch (Exception e){
			
			System.out.println("Error occured in authenticating user information from the database due to the following error message : "+e);
			
			return false;
			
		}
		
	}
	
	// This function registers a user with the given credentials.
	public boolean signUp(String username, String password) {
		
		System.out.println("Username :"+username+" is trying to sign up !");
		
		try {
			// Preparing the query
			BasicDBObject newUser = new BasicDBObject();
			
			// Assigning the parameters to the query
			newUser.put("username", username);
			
			newUser.put("password", password);
			
			// Inserting the user to the database
			this.usersCollection.insert(newUser);
			
			// Authenticate the sign up process !
			return true;
			
			
		} catch(Exception e) {
			
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
			return false;
			
		}
			
	}
	
	// This function lets the manager to add new movies to the application.
	public boolean addMovie(String movieName) {
		
		try {
			
			// Preparing the query
			BasicDBObject newMovie = new BasicDBObject();
			
			// Assigning the parameters to the query
			newMovie.put("name", movieName);
			
			this.moviesCollection.insert(newMovie);
			
			return true;
			
		} catch(Exception e) {
			
			System.out.println("Error occured in adding the movie to the database due to the following error message : "+e);
			
			return false;
			
		}
		
	}
	// This function returns the list of the movies saved in the database.
	public List listMovies() {
		
		try {
			
			// Creating a list of DB Object
			List<DBObject> movieList = this.moviesCollection.find().toArray();
			
			return movieList;
			
		} catch (Exception e) {
			
			System.out.println("Error occured in fetching movies from the database due to the following error message : "+e);
			
			return null;
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean connectToDatabase() {
		
		System.out.println("Database handler is trying to connect to the mongoDb !");
		
		try {
			
			// Connect MongoClient to the Mongo Server
			mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
			
			// Select the database
			this.myDatabase = mongoClient.getDB("local");
			
			// Get Users Collection
			this.usersCollection = myDatabase.getCollection("users");
			
			// Get Movies Collection
			this.moviesCollection = myDatabase.getCollection("movies");
			
			return true;
			
			
		} catch (Exception e) {
			
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
			return false;
			
		}
		
		
	}
	
	public boolean killTheConnection() {
		
		System.out.println("Database handler closes the connection to the database !");
		
		try {
			// Kill the connection !
			mongoClient.close();
			
			return true;
			
		} catch (Exception e) {
			
			return false;
			
		}
	}

}
