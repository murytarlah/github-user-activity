
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GItHubClient {

    public String fetchuserActivity(String username) throws Exception {
        String url = "https://api.github.com/users/" + username + "/events";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "github-activity-cli")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            throw new IllegalArgumentException("User not found: " + username);
        }

        return response.body();
    }
}
