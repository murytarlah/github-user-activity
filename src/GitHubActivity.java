public class GitHubActivity {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Usage: GitHubActivity <username>");
			return;
		}

		String username = args[0];
		System.out.println("Fetching activity for: " + username);
	}
}