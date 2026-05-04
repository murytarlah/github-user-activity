import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;

public class EventFormatter {

    public void format(String jsonResponse) {
        JsonArray events = JsonParser.parseString(jsonResponse).getAsJsonArray();

        for (JsonElement element : events) {
            JsonObject event = element.getAsJsonObject();

            String type = event.get("type").getAsString();

            String repo = event.get("repo")
                    .getAsJsonObject()
                    .get("name").getAsString();

            String message = switch (type) {
                case "PushEvent" -> {
                    String branch = Arrays.asList(event.get("payload").getAsJsonObject().get("ref").getAsString().split("/")).getLast() ;
                    yield "Pushed commit to " + branch + " in " + repo ;
                }
                case "PullRequestEvent" -> {
                    String action = event.get("payload").getAsJsonObject().get("action").getAsString();
                    action = action.substring(0,1).toUpperCase() + action.substring(1);
                    
                    String number = event.get("payload").getAsJsonObject().get("number").getAsString();
                    yield action + " pull-request #" + number + " in " + repo;
                }
                case "WatchEvent" -> {
                    yield "Starred " + repo;
                }
                default -> {
                    yield "Unknown event type: " + type;
                }
            };
            System.out.println(message);
        }
    }
}