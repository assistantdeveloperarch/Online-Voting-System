# Online Voting System (Java Console App) 

A simple and secure command-line based voting system built using Java. Users can register, log in, cast their vote, and view live voting results. Admins can log in to manage users and voting data.

---

## Features

- User registration
- Login system (password entered visibly due to terminal limitations)
- Vote for a candidate (only once per user)
- View real-time voting results
- Admin login with dedicated panel
- Admin can view all registered users
- Admin can view all votes
- Admin can reset voting data
- Input validation and error handling
- User and vote data persisted in local files (users.txt, votes.txt)

---

## How It Works

1. Users can register with a unique username and password.  
2. Once registered, they can log in securely.  
3. Logged-in users can view candidates and vote.  
4. Each user can vote only once.  
5. Admins can view all users, view votes, and reset voting data.
6. Votes are stored and can be viewed as live results.

---

## Project Structure

OnlineVotingSystem/
├── src/
│   ├── main/
│   │   └── Main.java
│   ├── dao/
│   │   └── VoteDAO.java
│   ├── model/
│   │   ├── User.java
│   │   └── Candidate.java
├── users.txt         # Stores registered users
├── votes.txt         # Stores votes cast
└── README.md         # Project documentation


---

## How to Run and Compile

1. Prerequisites
- Make sure you have Java JDK installed (version 8 or above).
- Use Command Prompt or PowerShell on Windows.
- Make sure you compile all Java files from the src folder so the dependencies are resolved properly.

- Open your terminal or command prompt and navigate to the src directory:

```bash
cd path/to/OnlineVotingSystem
```

2. Compile all Java files:
   ```bash
javac src\main\Main.java src\dao\VoteDAO.java src\model\User.java src\model\Candidate.java
```

3. Run the application:
  ```bash
  java -cp src main.Main
```

4. Use the console menu to:

- Register as a voter
- Login as voter
- Login as admin (default admin credentials: username: admin, password:  letAdminIn)
- Exit



