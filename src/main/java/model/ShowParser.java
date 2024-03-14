package model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

public class ShowParser {
    public Show parseJsonResponse(String jsonResponse) {
        Gson gson = new Gson();
        JsonElement jsonElement = JsonParser.parseString(jsonResponse);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Show show = new Show();
        show.setId(jsonObject.get("id").getAsInt());
        show.setName(jsonObject.get("name").getAsString());
        show.setType(jsonObject.get("type").getAsString());
        show.setLanguage(jsonObject.get("language").getAsString());

        // Parse genres
        List<String> genres = new ArrayList<>();
        for (JsonElement genreElement : jsonObject.getAsJsonArray("genres")) {
            genres.add(genreElement.getAsString());
        }
        show.setGenres(genres);

        show.setStatus(jsonObject.get("status").getAsString());
        show.setRuntime(jsonObject.get("runtime").getAsInt());
        show.setAverageRuntime(jsonObject.get("averageRuntime").getAsInt());
        show.setPremiered(jsonObject.get("premiered").getAsString());
        show.setEnded(jsonObject.get("ended").getAsString());
        show.setOfficialSite(jsonObject.get("officialSite").getAsString());


        // Parse rating
        JsonObject ratingObject = jsonObject.getAsJsonObject("rating");
        Rating rating = new Rating();
        rating.setAverage(ratingObject.get("average").getAsDouble());
        show.setRating(rating);


        // Parse image
        JsonObject imageObject = jsonObject.getAsJsonObject("image");
        show.setImageUrl(imageObject.get("medium").getAsString());

        // Parse summary
        show.setSummary(jsonObject.get("summary").getAsString());


        return show;
    }
}
