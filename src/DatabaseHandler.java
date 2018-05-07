import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;

// Singleton Pattern is being used in this method due to avoid further extra connection processes.

public class DatabaseHandler {

	// Initializing the neccessary variables
	private static MongoClient mongoClient;

	private static DatabaseHandler instance = null;

	private static DBCollection usersCollection;

	private static DBCollection moviesCollection;

	private static DB myDatabase;
	
	private static User user;

	public DatabaseHandler() {

		// TODO Auto-generated constructor stub

		System.out.println("Database Handler Constructor !");

	}

	public static DatabaseHandler getInstance() {

		// Singleton pattern
		// if there is any instance created before return that instance
		// otherwise create a new instance and return that one

		if (instance == null) {

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

		System.out.println("Username :" + username + " is trying to login with the password" + password);

		try {

			// Preparing a query
			BasicDBObject query = new BasicDBObject();
			// Assigning parameters to the query
			query.put("username", username);
			query.put("password", password);

			// Search the query in the database
			DBCursor cursor = usersCollection
					.find(new BasicDBObject("username", username).append("password", password));

			// The cursor has size more than 1, well that means that we have a user matches
			// with the username along with the password
			if (cursor.size() >= 1) {

				DBObject user = cursor.next();
				
				this.user = new User(username);

				System.out.println(user);

				return true;

			} else {

				// it looks like the given credentials are wrong so it denies the login request.
				return false;

			}

		} catch (Exception e) {

			System.out.println(
					"Error occured in authenticating user information from the database due to the following error message : "
							+ e);

			throw new RuntimeException(e);
		}

	}

	// This function registers a user with the given credentials.
	public boolean signUp(String username, String password) {

		System.out.println("Username :" + username + " is trying to sign up !");

		try {
			// Preparing the query
			BasicDBObject newUser = new BasicDBObject();

			BasicDBList purchasedTickets = new BasicDBList();
		
			
			// Assigning the parameters to the query
			newUser.put("username", username);
			newUser.put("password", password);
			newUser.put("purchasedTickets", purchasedTickets);
			
			// Inserting the user to the database
			usersCollection.insert(newUser);

			// Authenticate the sign up process !
			return true;

		} catch (Exception e) {

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

			throw new RuntimeException(e);

		}

	}
	
	public boolean buyTicket(String movieName) {
		
		try {
			
			System.out.println("The user wants to buy a ticket : "+this.user.getUsername());
			
			// Search the query in the database
			DBObject foundUser = usersCollection
				.find(new BasicDBObject("username", this.user.getUsername())).next();
			
			// data
			BasicDBObject purchasedTicket = new BasicDBObject();
			purchasedTicket.append("purchasedTicket", movieName);
			
			DBObject listItem = new BasicDBObject("purchasedTickets",purchasedTicket);
			
			DBObject updateQuery = new BasicDBObject("$push", listItem);

			usersCollection.update(foundUser, updateQuery);
			
			return true;
			
		} catch(Exception e) {
			
			System.out.println(
					"Error occured in buying a ticket of the movie to the database due to the following error message : " + e);

			throw new RuntimeException(e);
			
		}
		
	}

	// This function lets the manager to add new movies to the application.
	public boolean addMovie(String movieName, int ticketPrice) {

		try {

			System.out.println("Movie Name :" + movieName);
			System.out.println("Ticket Price : " + ticketPrice);
			// Preparing the query
			BasicDBObject newMovie = new BasicDBObject();

			// Assigning the parameters to the query
			newMovie.put("name", movieName);
			newMovie.put("ticketPrice", ticketPrice);
			
			BasicDBList sessions = new BasicDBList();
			sessions.add(new BasicDBObject("hour","09.00-11.00"));
			sessions.add(new BasicDBObject("hour","13.00-15.00"));
			sessions.add(new BasicDBObject("hour","17.00-19.00"));
			
			newMovie.put("sessions", sessions);
			
			moviesCollection.insert(newMovie);

			return true;

		} catch (Exception e) {

			System.out.println(
					"Error occured in adding the movie to the database due to the following error message : " + e);

			throw new RuntimeException(e);

		}

	}

	// This function returns the list of the movies saved in the database.
	public List listMovies() {

		try {

			// Creating a list of DB Object
			List<DBObject> movieList = moviesCollection.find().toArray();

			return movieList;

		} catch (Exception e) {

			System.out.println(
					"Error occured in fetching movies from the database due to the following error message : " + e);

			throw new RuntimeException(e);

		}

	}

	// Removes an existed user from the database.
	public boolean removeUser(String username) {

		System.out.println("Database handler is trying to remove the user from the database !");

		try {

			// Preparing the query
			BasicDBObject user = new BasicDBObject();

			// Assigning the parameters to the query
			user.put("username", username);

			// This function firstly runs the query to find the object in the database and
			// then removes the object from the database.
			usersCollection.findAndRemove(user);

			return true;

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}
	
	// Removes an existed movie from the database.
		public boolean removeMovie(String movieName) {

			System.out.println("Database handler is trying to remove the movie from the database !");

			try {

				// Preparing the query
				BasicDBObject movie = new BasicDBObject();

				// Assigning the parameters to the query
				movie.put("name", movieName);

				// This function firstly runs the query to find the object in the database and
				// then removes the object from the database.
				moviesCollection.findAndRemove(movie);

				return true;

			} catch (Exception e) {

				throw new RuntimeException(e);

			}

		}

	// Checks the users collection has an user with the given parameter.
	public String isUsernameExists(String username) {

		System.out.println("Database handler is trying to check the username in the database !");

		try {

			// Preparing the query
			BasicDBObject user = new BasicDBObject();

			// Assigning the parameters to the query
			user.put("username", username);

			DBCursor existedUser = usersCollection.find(user);

			if (existedUser == null) {
				
				System.out.println("Username exists !");

				return "Existed";

			} else {
				
				System.out.println("Username does not exist !");

				return "Not Existed";
			}

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

	// Checks the movies collection has a movie with the given parameter.
	public String isMovieExists(String movieName) {

		try {

			// Preparing the query
			BasicDBObject movie = new BasicDBObject();

			// Assigning the parameters to the query
			movie.put("username", movieName);

			DBCursor existedMovie = moviesCollection.find(movie);

			if (existedMovie == null) {

				return "Existed";

			} else {

				return "Not Existed";
			}

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

	// Establishes connection to the database.
	@SuppressWarnings("deprecation")
	public boolean connectToDatabase() {

		System.out.println("Database handler is trying to connect to the mongoDb !");

		try {

			// Connect MongoClient to the Mongo Server
			mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

			// Select the database
			myDatabase = mongoClient.getDB("local");

			// Get Users Collection
			usersCollection = myDatabase.getCollection("users");

			// Get Movies Collection
			moviesCollection = myDatabase.getCollection("movies");

			return true;

		} catch (Exception e) {

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

			throw new RuntimeException(e);

		}

	}

	public boolean killTheConnection() {

		System.out.println("Database handler closes the connection to the database !");

		try {
			// Kill the connection !
			mongoClient.close();

			return true;

		} catch (Exception e) {

			throw new RuntimeException(e);

		}
	}

}
