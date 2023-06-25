package mma_simulator;

import java.util.Scanner;

public class MMA_Simulator {
    private static Tournament tournament;

    public static void main(String[] args) {
        tournament = new Tournament(100000, "MMA Tournament"); // Initialize the tournament

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMainMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addFighter(scanner);
                    break;
                case "2":
                    viewFighters();
                    break;
                case "3":
                    searchFighter(scanner);
                    break;
                case "4":
                    startTournament();
                    break;
                case "5":
                    viewMatchDetails(scanner);
                    break;
                case "6":
                    displayLeaderboard();
                    break;
                case "7":
                    outputMatchResults();
                    break;
                case "8":
                    displayHelp();
                    break;
                case "9":
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("===== MMA Tournament Simulator =====");
        System.out.println("1. Add Fighter");
        System.out.println("2. View Fighters");
        System.out.println("3. Search Fighters");
        System.out.println("4. Start Tournament");
        System.out.println("5. View Match Details");
        System.out.println("6. Display Leaderboard");
        System.out.println("7. Output Match Results");
        System.out.println("8. Help");
        System.out.println("9. Exit");
        System.out.println("====================================");
        System.out.print("Enter your choice: ");
    }

    private static void addFighter(Scanner scanner) {
        System.out.print("Enter fighter's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter fighter's Strength (between 0.0 and 10.0): ");
        double strength = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter fighter's Speed (between 0.0 and 10.0): ");
        double speed = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter fighter's Skill (between 0.0 and 10.0): ");
        double skill = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter fighter's age (between 18 and 100): ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter fighter's Weight (between 40.0 and 300.0): ");
        double weight = Double.parseDouble(scanner.nextLine());

        tournament.addFighter(name, strength, speed, skill, age, weight);
    }

    private static void viewFighters() {
        Fighter[] fighters = tournament.getFighters();
        for (Fighter fighter : fighters) {
            System.out.println(fighter.toString());
        }
    }

    private static void searchFighter(Scanner scanner) {
        System.out.print("Enter fighter's name or ID: ");
        String searchQuery = scanner.nextLine();

        Fighter fighter = tournament.getFighter(searchQuery);
        if (fighter != null) {
            System.out.println(fighter.toString());
        } else {
            System.out.println("Fighter not found.");
        }
    }

    private static void startTournament() {
        tournament.start();
        System.out.println("Tournament started!");
    }

    private static void viewMatchDetails(Scanner scanner) {
        System.out.print("Enter match ID: ");
        String matchId = scanner.nextLine();

        Match match = tournament.getMatch(matchId);
        if (match != null) {
            System.out.println(match.toString());
        } else {
            System.out.println("Match not found.");
        }
    }

    private static void displayLeaderboard() {
        // Retrieve leaderboard data from tournament and display it in a tabular format
        // Display fighter positions, names, wins, losses, etc.
    }

    private static void outputMatchResults() {
        // Generate a report or file containing the results of all matches in the tournament
        // Include winners, losers, and statistics
        // Save the report to a specified file or display it on the console
    }

    private static void displayHelp() {
        // Display help documentation explaining available commands, their usage, etc.
    }
}
