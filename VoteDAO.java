package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Candidate;
import model.User;

public class VoteDAO {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Candidate> candidates = new ArrayList<>();
    private HashMap<String, String> userVotes = new HashMap<>();

    public VoteDAO() {
       
        candidates.add(new Candidate("C001", "Aarav Sharma", "Green Party"));
        candidates.add(new Candidate("C002", "Meera Patel", "Unity Party"));
        candidates.add(new Candidate("C003", "Ishaan Verma", "Progressive Front"));

       
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("No previous users found. Starting fresh.");
        }

      
        try (BufferedReader reader = new BufferedReader(new FileReader("votes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userVotes.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous votes found. Starting fresh.");
        }
    }

    public boolean registerUser(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return false;  
            }
        }
        users.add(user);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(user.getUsername() + "," + user.getPassword());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to save user to file.");
        }
        return true;
    }
    
    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void vote(User user, Scanner scanner) {
        if (userVotes.containsKey(user.getUsername())) {
            System.out.println("You have already voted.");
            return;
        }

        showCandidates();

        int choice = -1;
        while (true) {
            System.out.print("Enter the number of the candidate you want to vote for (1-" + candidates.size() + "): ");
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= candidates.size()) {
                    break;
                } else {
                    System.out.println("❌ Invalid choice. Please choose between 1 and " + candidates.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }

        Candidate chosen = candidates.get(choice - 1);
        System.out.println("✅ You voted for: " + chosen.getName() + " (" + chosen.getParty() + ")");
        userVotes.put(user.getUsername(), chosen.getId());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("votes.txt", true))) {
            writer.write(user.getUsername() + "," + chosen.getId());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to save vote to file.");
        }
    }

    public void showResults() {
        System.out.println("\n--- 🗳️ Voting Results ---");

        HashMap<String, Integer> voteCounts = new HashMap<>();
        for (Candidate candidate : candidates) {
            voteCounts.put(candidate.getId(), 0);
        }

        for (String candidateId : userVotes.values()) {
            voteCounts.put(candidateId, voteCounts.get(candidateId) + 1);
        }

        for (Candidate candidate : candidates) {
            System.out.println(candidate.getName() + " (" + candidate.getParty() + "): " +
                    voteCounts.get(candidate.getId()) + " votes");
        }
    }

    public void showCandidates() {
        System.out.println("\n--- 🧑‍💼 Candidates ---");
        for (int i = 0; i < candidates.size(); i++) {
            Candidate c = candidates.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " (" + c.getParty() + ")");
        }
    }

    public int getTotalCandidates() {
        return candidates.size();
    }

    public boolean loginAdmin(String username, String password) {
        return username.equals("admin") && password.equals("letAdminIn");
    }

    public void viewAllUsers() {
        System.out.println("\n--- Registered Users ---");
        if (users.isEmpty()) {
            System.out.println("No users registered yet.");
            return;
        }
        for (User user : users) {
            System.out.println("- " + user.getUsername());
        }
    }

    public void resetVotes() {
        try (PrintWriter writer = new PrintWriter("votes.txt")) {
            writer.print("");
            userVotes.clear();
            System.out.println("All votes have been reset successfully.");
        } catch (IOException e) {
            System.out.println("Failed to reset votes: " + e.getMessage());
        }
    }
}

