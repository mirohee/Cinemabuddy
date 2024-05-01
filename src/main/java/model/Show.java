package model;

import java.util.List;

/**
 * Class representing information about a TV show.
 */
public class Show {
    private int id;
    private String name;
    private String type;
    private String language;
    private List<String> genres;
    private String status;
    private int runtime;
    private int averageRuntime;
    private String premiered;
    private String ended;
    private String officialSite;
    private String imageUrl;
    private Rating rating;
    private String summary;

    /**
     * Gets the ID of the show.
     * @return The ID of the show.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the show.
     * @param id The ID of the show to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the show.
     * @return The name of the show.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the show.
     * @param name The name of the show to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the show.
     * @return The type of the show.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the show.
     * @param type The type of the show to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the language of the show.
     * @return The language of the show.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language of the show.
     * @param language The language of the show to set.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets the list of genres of the show.
     * @return The list of genres of the show.
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Sets the list of genres of the show.
     * @param genres The list of genres of the show to set.
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Gets the status of the show.
     * @return The status of the show.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the show.
     * @param status The status of the show to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the runtime of the show.
     * @return The runtime of the show.
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * Sets the runtime of the show.
     * @param runtime The runtime of the show to set.
     */
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /**
     * Gets the average runtime of the show.
     * @return The average runtime of the show.
     */
    public int getAverageRuntime() {
        return averageRuntime;
    }

    /**
     * Sets the average runtime of the show.
     * @param averageRuntime The average runtime of the show to set.
     */
    public void setAverageRuntime(int averageRuntime) {
        this.averageRuntime = averageRuntime;
    }

    /**
     * Gets the premiered date of the show.
     * @return The premiered date of the show.
     */
    public String getPremiered() {
        return premiered;
    }

    /**
     * Sets the premiered date of the show.
     * @param premiered The premiered date of the show to set.
     */
    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    /**
     * Gets the ended date of the show.
     * @return The ended date of the show.
     */
    public String getEnded() {
        return ended;
    }

    /**
     * Sets the ended date of the show.
     * @param ended The ended date of the show to set.
     */
    public void setEnded(String ended) {
        this.ended = ended;
    }

    /**
     * Gets the official site URL of the show.
     * @return The official site URL of the show.
     */
    public String getOfficialSite() {
        return officialSite;
    }

    /**
     * Sets the official site URL of the show.
     * @param officialSite The official site URL of the show to set.
     */
    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    /**
     * Gets the image URL of the show.
     * @return The image URL of the show.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL of the show.
     * @param imageUrl The image URL of the show to set.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the rating of the show.
     * @return The rating of the show.
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Sets the rating of the show.
     * @param rating The rating of the show to set.
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    /**
     * Gets the summary of the show.
     * @return The summary of the show.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary of the show.
     * @param summary The summary of the show to set.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
