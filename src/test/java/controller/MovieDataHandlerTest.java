package controller;

public class MovieDataHandlerTest {

    public static void main(String[] args) {
        // Initialize MovieDataHandler
        MovieDataHandler dataHandler = new MovieDataHandler();

        // Provide a movie title to save to the database
        String movieTitle = "Breaking Bad";

        // Save the movie ID to the database
        dataHandler.saveMovieIdToDatabase(movieTitle);

        // Print a message indicating that the movie ID has been saved to the database
        System.out.println("Movie ID for '" + movieTitle + "' saved to the database.");
    }
}
