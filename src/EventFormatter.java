import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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