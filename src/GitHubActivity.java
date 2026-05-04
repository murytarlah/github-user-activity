public class GitHubActivity {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Usage: GitHubActivity <username>");
			return;
		}

		String username = args[0];
		System.out.println("Fetching activity for: " + username);

		GItHubClient gHubClient = new GItHubClient();
		String response;
		try {
			response = gHubClient.fetchuserActivity(username);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return;
		}

		if (response == null || response.isBlank() || response.equals("[]")) {
			System.out.println("No recent activity found for: " + username);
			return;
		}

		EventFormatter formatter = new EventFormatter();
		formatter.format(response);

	}
}