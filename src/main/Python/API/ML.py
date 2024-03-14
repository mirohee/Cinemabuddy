from flask import Flask, request, jsonify
import requests
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import TfidfVectorizer

app = Flask(__name__)

# Global variable to store shows and the similarity matrix
shows_db = []
cosine_sim = []

def fetch_shows_from_tvmaze():
    """
    Fetches shows from the TVmaze API and stores them in a global list.
    """
    response = requests.get('http://api.tvmaze.com/shows')
    if response.status_code == 200:
        return response.json()
    else:
        return []

def prepare_data(shows):
    """
    Prepares data for similarity computation by combining genres and rating into a single string for each show.
    """
    for show in shows:
        genres = ' '.join(show['genres'])
        rating = show.get('rating', {}).get('average')
        if rating is not None:  # Ensure the rating is not None before conversion
            genres_plus_rating = genres + ' ' + str(int(rating))
        else:
            genres_plus_rating = genres
        yield genres_plus_rating

def calculate_similarity(shows):
    """
    Calculates similarity matrix for the shows based on genres and ratings using TF-IDF and cosine similarity.
    """
    tfidf_vectorizer = TfidfVectorizer()
    tfidf_matrix = tfidf_vectorizer.fit_transform(prepare_data(shows))
    return cosine_similarity(tfidf_matrix, tfidf_matrix)

def get_recommendations(show_id, shows, cosine_sim, num_recommendations=1):
    """
    Given a show ID, finds and returns the most similar shows based on the cosine similarity matrix,
    adjusting for the desired number of recommendations.
    """
    idx = next((index for (index, d) in enumerate(shows) if d["id"] == show_id), None)
    if idx is None:
        return []

    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:num_recommendations + 1]  # Adjust to change the number of recommendations
    show_indices = [i[0] for i in sim_scores]

    return [shows[i] for i in show_indices]

@app.route('/update_model', methods=['GET'])
def update_model():
    global shows_db, cosine_sim
    shows_db = fetch_shows_from_tvmaze()
    cosine_sim = calculate_similarity(shows_db)
    return jsonify({'message': 'Model updated with new shows data.'}), 200

@app.route('/recommend', methods=['GET'])
def recommend():
    show_id = request.args.get('show_id', type=int)
    num_recommendations = request.args.get('num_recommendations', default=1, type=int)
    if not show_id:
        return jsonify({'error': 'Please provide a show_id for recommendations.'}), 400

    recommendations = get_recommendations(show_id, shows_db, cosine_sim, num_recommendations)
    return jsonify({'recommended_shows': recommendations}), 200

@app.route('/search_and_recommend', methods=['GET'])
def search_and_recommend():
    query = request.args.get('name', '')
    num_recommendations = request.args.get('num_recommendations', default=1, type=int)  # Handle num_recommendations
    if not query:
        return jsonify({'error': 'Please provide a show name for searching.'}), 400

    response = requests.get(f'http://api.tvmaze.com/search/shows?q={query}')
    if response.status_code != 200:
        return jsonify({'error': 'Failed to search for shows'}), response.status_code

    search_results = response.json()
    if not search_results:
        return jsonify({'message': 'No shows found with the given name'}), 404

    show = search_results[0]['show']
    recommendations = get_recommendations(show['id'], shows_db, cosine_sim, num_recommendations)

    return jsonify({'recommended_shows': recommendations}), 200

if __name__ == '__main__':
    app.run(debug=True)
