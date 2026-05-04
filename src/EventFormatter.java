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
                case "CreateEvent" -> {

                    String ref_type = event.get("payload").getAsJsonObject().get("ref_type").getAsString();
                    
                    String ref = event.get("payload").getAsJsonObject().has("ref") ? event.get("payload").getAsJsonObject().get("ref").getAsString() : null;

                    if (ref == null){
                        yield "Created repository " + repo;
                    }
                    yield "Created " + ref_type + " " + ref + " in " + repo;
                }
                case "ForkEvent" -> {
                    String forkedRepo = event.get("payload").getAsJsonObject().get("forkee").getAsJsonObject().get("name").getAsString();
                    yield "Forked " + forkedRepo + " from "+ repo;
                }
                case "ReleaseEvent" ->{
                    String action = event.get("payload").getAsJsonObject().get("action").getAsString();
                    action = action.substring(0, 1).toUpperCase() + action.substring(1);
                    
                    String tag = event.get("payload").getAsJsonObject().get("release").getAsJsonObject().get("tag_name").getAsString();
                    yield action + " " + tag  + " for " + repo;
                }
                case "WatchEvent" -> "Starred " + repo;
                case "CommitCommentEvent" -> {
                    String commitId = event.get("payload").getAsJsonObject().get("comment").getAsJsonObject().get("commit_id").getAsString().substring(0, 7);
                    yield "Commented on commit " + commitId + " in " + repo;
                }
                case "DeleteEvent" -> {
                    String refType = event.get("payload").getAsJsonObject().get("ref_type").getAsString();
                    String ref = event.get("payload").getAsJsonObject().get("ref").getAsString();
                    yield "Deleted " + refType + " " + ref + " in " + repo;
                }
                case "GollumEvent" -> {
                    JsonArray pages = event.get("payload").getAsJsonObject().get("pages").getAsJsonArray();
                    String action = pages.get(0).getAsJsonObject().get("action").getAsString();
                    String pageName = pages.get(0).getAsJsonObject().get("page_name").getAsString();
                    yield action.substring(0, 1).toUpperCase() + action.substring(1) + " wiki page \"" + pageName + "\" in " + repo;
                }
                case "IssueCommentEvent" -> {
                    String action = event.get("payload").getAsJsonObject().get("action").getAsString();
                    String issueNumber = event.get("payload").getAsJsonObject().get("issue").getAsJsonObject().get("number").getAsString();
                    yield action.substring(0, 1).toUpperCase() + action.substring(1) + " comment on issue #" + issueNumber + " in " + repo;
                }
                case "IssuesEvent" -> {
                    String action = event.get("payload").getAsJsonObject().get("action").getAsString();
                    String issueNumber = event.get("payload").getAsJsonObject().get("issue").getAsJsonObject().get("number").getAsString();
                    yield action.substring(0, 1).toUpperCase() + action.substring(1) + " issue #" + issueNumber + " in " + repo;
                }
                case "MemberEvent" -> {
                    String action = event.get("payload").getAsJsonObject().get("action").getAsString();
                    String member = event.get("payload").getAsJsonObject().get("member").getAsJsonObject().get("login").getAsString();
                    yield action.substring(0, 1).toUpperCase() + action.substring(1) + " " + member + " as collaborator in " + repo;
                }
                case "PublicEvent" -> "Made " + repo + " public";
                case "PullRequestReviewEvent" -> {
                    String action = event.get("payload").getAsJsonObject().get("action").getAsString();
                    String prNumber = event.get("payload").getAsJsonObject().get("pull_request").getAsJsonObject().get("number").getAsString();
                    yield action.substring(0, 1).toUpperCase() + action.substring(1) + " review on pull-request #" + prNumber + " in " + repo;
                }
                case "PullRequestReviewCommentEvent" -> {
                    String action = event.get("payload").getAsJsonObject().get("action").getAsString();
                    String prNumber = event.get("payload").getAsJsonObject().get("pull_request").getAsJsonObject().get("number").getAsString();
                    yield action.substring(0, 1).toUpperCase() + action.substring(1) + " review comment on pull-request #" + prNumber + " in " + repo;
                }
                case "PullRequestReviewThreadEvent" -> {
                    String action = event.get("payload").getAsJsonObject().get("action").getAsString();
                    String prNumber = event.get("payload").getAsJsonObject().get("pull_request").getAsJsonObject().get("number").getAsString();
                    yield action.substring(0, 1).toUpperCase() + action.substring(1) + " review thread on pull-request #" + prNumber + " in " + repo;
                }
                case "SponsorshipEvent" -> {
                    String action = event.get("payload").getAsJsonObject().get("action").getAsString();
                    yield action.substring(0, 1).toUpperCase() + action.substring(1) + " sponsorship in " + repo;
                }
                default -> {
                    yield "Unknown event type: " + type;
                }
            };
            System.out.println(message);
        }
    }
}