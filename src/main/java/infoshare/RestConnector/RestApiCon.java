package infoshare.RestConnector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by codex on 2015/06/30.
 */
public class RestApiCon{

     URLConnection urlConnection ;
    public void openCon(String fetchUrl ){

        try {
            urlConnection =  new URL(fetchUrl).openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JsonArray readAll() {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonArray json = rootElement.getAsJsonArray();
        return json;
    }
}
