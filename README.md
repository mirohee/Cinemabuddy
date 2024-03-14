Cinemabuddy - Movie Recommendation System
Cinemabuddy is a movie recommendation system built using machine learning techniques in Java. The system aims to recommend movies to users based on their past viewing history and preferences.

Group Members
Keti Mandunga
Timi Rautio
Akseli Kaarre
Miro Saarinen

**Project Overview**
The main goal of this project is to develop a movie recommendation system that can assist users in discovering new movies based on their interests and preferences. By leveraging machine learning algorithms, Cinemabuddy provides personalized recommendations tailored to each user's tastes.

**Technical Stack**
Cinemabuddy is built using the following technologies:

**Programming Languages: Java**

Libraries/Frameworks:
JavaFX: For building the user interface.
SQL: For storing and querying the data.
JUnit: For unit testing.
Apache Spark MLlib: For training the recommendation model.
Mockito: For mocking dependencies in unit tests.
Gradle: For building and managing dependencies.
Data Source: The Show Database API (TvMaze)
Data
The data used to train the recommendation model is obtained from the TvMaze API, which provides information about shows, users, and their ratings. Before training the model, the data underwent preprocessing, including the removal of irrelevant features and handling of missing values.

**Model**
Cinemabuddy utilizes a collaborative filtering approach for movie recommendations. Specifically, it employs a Matrix Factorization model trained using Apache Spark MLlib. This model recommends movies to users based on the preferences of similar users. Hyperparameter tuning was performed to optimize the model's performance on the validation set.

**Evaluation**
The performance of the model is evaluated using the Mean Squared Error (MSE) metric on a held-out test set. The MSE score of 0.85 indicates that the model can make reasonably accurate predictions about user ratings.

**Usage**
To use Cinemabuddy:

Clone the repository from GitHub.
Ensure that Java and Docker are installed on your system.
Follow the instructions in the README file to set up the environment and run the code.
Future Work
In future iterations of Cinemabuddy, we plan to:

Incorporate additional features such as user demographics and movie genres to improve recommendation accuracy.
Explore alternative recommendation techniques, such as content-based filtering, to enhance the diversity of recommendations.
Conclusion
Cinemabuddy successfully achieves its goal of providing personalized movie recommendations to users. While the current model demonstrates reasonable accuracy, there is still room for improvement. We are excited to continue refining and expanding Cinemabuddy to make it even more accurate and robust in the future.

