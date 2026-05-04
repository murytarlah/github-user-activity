# GitHub User Activity

A command-line tool that fetches and displays recent activity of any GitHub user using the GitHub API.

## What I Learned

Building this project helped me practice:

- **API Consumption**: Fetching data from external APIs and parsing JSON responses
- **Java Fundamentals**: Exception handling, and object-oriented design
- **Error Handling**: Gracefully managing invalid inputs and network failures

## How It Works

The application accepts a GitHub username as a command-line argument, queries the GitHub API for recent events, and displays them in a formatted list.

**Example Output:**
```
- Pushed commit to ui-rebuild in hendurhance/movieace
- Closed issue #1 in hendurhance/fullstack-product-catalog
- Merged pull-request #2 in hendurhance/fullstack-product-catalog
- Opened pull-request #2 in hendurhance/fullstack-product-catalog
- Created comment on issue #1 in hendurhance/fullstack-product-catalog
```

## Running the Project

**Prerequisites:**
- Java Runtime Environment (JRE) 11 or above

**Steps:**

1. Clone or download the project files
2. Navigate to the project directory in your terminal
3. Compile: 
```shell
javac -cp src:lib/gson-2.10.1.jar -d bin src/*.java
```
4. Run: 
```shell
java -cp bin:lib/gson-2.10.1.jar GitHubActivity <username> # do not forget to add gson to cp while executing
```

**Example:**
```bash
java -cp bin:lib/gson-2.10.1.jar GitHubActivity kamranahmedse
```

## API Reference

Uses the GitHub API endpoint: `https://api.github.com/users/<username>/events`

[Learn more about GitHub API](https://docs.github.com/en/rest)

## Credits

This project is part of [roadmap.sh](https://roadmap.sh) - helping developers practice backend skills.


Here is the link to the project: [GitHub User Activity](https://roadmap.sh/projects/github-user-activity)

## Let's Connect!🌟

- [LinkedIn](https://www.linkedin.com/in/murytarlah)
- [X](https://x.com/murytarlah)
