package mma_simulator;

import java.util.Scanner;

public class MmaSimulator {
    private static Tournament tournament;

    public static void main(String[] args) {
        DataBase.establishConnection();
        tournament = new Tournament(100000, "MMA Tournament"); // Initialize the tournament

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            displayMainMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> addFighter(scanner);
                case "2" -> viewFighters();
                case "3" -> searchFighter(scanner);
                case "4" -> startTournament();
                case "5" -> viewMatchDetails(scanner);
                case "0" -> {
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu of the MMA simulator.
     */
    private static void displayMainMenu() {
        System.out.println("===== MMA Tournament Simulator =====");
        System.out.println("1. Add Fighter");
        System.out.println("2. View Fighters");
        System.out.println("3. Search Fighters");
        System.out.println("4. Start Tournament");
        System.out.println("5. View Match Details");
        System.out.println("0. Exit");
        System.out.println("====================================");
        System.out.print("Enter your choice: ");
    }

    /**
     * Prompts the user to enter the details of a new fighter and adds them to the tournament.
     *
     * @param scanner the Scanner object used for user input
     */
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

    /**
     * Displays the details of all the fighters in the tournament.
     */
    private static void viewFighters() {
        Fighter[] fighters = tournament.getFighters();
        for (Fighter fighter : fighters) {
            System.out.println(fighter.toString());
        }
    }

    /**
     * Searches for a fighter in the tournament based on the name or ID provided by the user.
     *
     * @param scanner the Scanner object used for user input
     */
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

    /**
     * Starts the tournament and displays a message indicating that the tournament has started.
     */
    private static void startTournament() {
        System.out.println("Tournament started!");
        tournament.start();
    }

    /**
     * Displays the details of a specific match in the tournament based on the match ID provided by the user.
     *
     * @param scanner the Scanner object used for user input
     */
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
}
