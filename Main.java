package main;

import dao.VoteDAO;
import java.util.Scanner;
import model.User;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VoteDAO voteDAO = new VoteDAO();

        while (true) {
            System.out.println("\nWelcome to the Online Voting System");
            System.out.println("1. Register");
            System.out.println("2. Login as Voter");
            System.out.println("3. Login as Admin");
            System.out.println("4. Exit");

            int choice = getValidIntInput(scanner, "Enter your choice: ", 1, 4);

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    String regPassword = readPassword("Enter password: ");

                    boolean registered = voteDAO.registerUser(new User(regUsername, regPassword));
                    if (registered) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Username already taken. Please try another.");
                    }
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    String loginPassword = readPassword("Enter password: ");

                    if (voteDAO.loginUser(loginUsername, loginPassword)) {
                        System.out.println("Login successful! You can now vote.");
                        User loggedInUser = new User(loginUsername, loginPassword);
                        runVotingMenu(scanner, voteDAO, loggedInUser);
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case 3:
                    System.out.print("Enter admin username: ");
                    String adminUsername = scanner.nextLine();
                    String adminPassword = readPassword("Enter admin password: ");

                    if (voteDAO.loginAdmin(adminUsername, adminPassword)) {
                        System.out.println("Admin login successful!");
                        runAdminMenu(scanner, voteDAO);
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
            }
        }
    }

    public static void runVotingMenu(Scanner scanner, VoteDAO voteDAO, User user) {
        while (true) {
            System.out.println("\nVoting Menu");
            System.out.println("1. View Candidates");
            System.out.println("2. Vote for a Candidate");
            System.out.println("3. View Results");
            System.out.println("4. Logout");

            int menuChoice = getValidIntInput(scanner, "Enter your choice: ", 1, 4);

            switch (menuChoice) {
                case 1:
                    voteDAO.showCandidates();
                    break;
                case 2:
                    voteDAO.vote(user, scanner);
                    break;
                case 3:
                    voteDAO.showResults();
                    break;
                case 4:
                    System.out.println("Logged out successfully.");
                    return;
            }
        }
    }

    public static void runAdminMenu(Scanner scanner, VoteDAO voteDAO) {
        while (true) {
            System.out.println("\nAdmin Panel");
            System.out.println("1. View All Users");
            System.out.println("2. View All Votes");
            System.out.println("3. Reset Voting");
            System.out.println("4. Logout");

            int adminChoice = getValidIntInput(scanner, "Enter your choice: ", 1, 4);

            switch (adminChoice) {
                case 1:
                    voteDAO.viewAllUsers();
                    break;
                case 2:
                    voteDAO.showResults();
                    break;
                case 3:
                    voteDAO.resetVotes();
                    break;
                case 4:
                    System.out.println("Logged out of Admin Panel.");
                    return;
            }
        }
    }

    public static int getValidIntInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                input = Integer.parseInt(line);
                if (input < min || input > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static String readPassword(String prompt) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

