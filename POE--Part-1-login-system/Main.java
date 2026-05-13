import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Scanner for user input
        Scanner input = new Scanner(System.in);

        // Create Login object
        Login login = new Login();

        // ---------------- REGISTRATION ----------------
        System.out.println("=== Registration ===");

        // Ask for username
        System.out.print("Enter username: ");
        String username = input.nextLine();

        // Ask for password
        System.out.print("Enter password: ");
        String password = input.nextLine();

        // Ask for phone number
        System.out.print("Enter phone number (+27...): ");
        String phone = input.nextLine();

        // Register user
        String registrationResult = login.registerUser(username, password, phone);

        // Display result
        System.out.println(registrationResult);

        // Stop program if registration fails
        if(!registrationResult.equals("User successfully registered.")) {
            System.out.println("Registration failed.");
            return;
        }

        // ---------------- LOGIN ----------------
        System.out.println("\n=== Login ===");

        boolean loggedIn = false;

        // Loop until login is correct
        while(!loggedIn) {

            System.out.print("Enter username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = input.nextLine();

            loggedIn = login.loginUser(loginUsername, loginPassword);

            if(!loggedIn) {
                System.out.println("Incorrect username or password. Please try again.");
            }
        }

        // Welcome user
        System.out.println(login.returnLoginStatus(loggedIn, username));

        // QuickChat welcome
        System.out.println("\nWelcome to QuickChat.");

        int menuChoice = 0;

        // Program keeps running until user quits
        while(menuChoice != 3) {

            // Display menu
            System.out.println("\n===== QUICKCHAT MENU =====");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");

            System.out.print("Choose an option: ");
            menuChoice = input.nextInt();
            input.nextLine();

            switch(menuChoice) {

                // SEND MESSAGES
                case 1:

                    // Ask user how many messages they want to send
                    System.out.print("How many messages would you like to send? ");
                    int numberOfMessages = input.nextInt();
                    input.nextLine();

                    // Loop through number of messages
                    for(int i = 1; i <= numberOfMessages; i++) {

                        System.out.println("\nMessage " + i);

                        // Enter recipient
                        System.out.print("Enter recipient number: ");
                        String recipient = input.nextLine();

                        // Enter message
                        System.out.print("Enter your message: ");
                        String messageText = input.nextLine();

                        // Example message using Truman Hockey
                        // Example:
                        // Hi Truman Hockey, your order is ready.

                        // Create message object
                        Message message = new Message(i, recipient, messageText);

                        // Check message ID
                        if(message.checkMessageID()) {
                            System.out.println("Message ID generated successfully.");
                        }
                        else {
                            System.out.println("Invalid Message ID.");
                        }

                        // Check recipient number
                        System.out.println(message.checkRecipientCell());

                        // Check message length
                        System.out.println(message.checkMessageLength());

                        // If message length is valid
                        if(message.checkMessageLength().equals("Message ready to send.")) {

                            // Display hash
                            System.out.println("Message Hash: " + message.createMessageHash());

                            // Send/store/disregard message
                            System.out.println(message.sentMessage());

                            // Display message details
                            System.out.println(message.printMessages());
                        }
                    }

                    // Display total messages sent
                    System.out.println("Total messages sent: " + Message.returnTotalMessages());
                    break;

                // RECENT MESSAGES
                case 2:
                    System.out.println("Coming Soon.");
                    break;

                // QUIT
                case 3:
                    System.out.println("Thank you for using QuickChat Truman Hockey.");
                    break;

                // INVALID OPTION
                default:
                    System.out.println("Invalid option selected.");
            }
        }

        // Close scanner
        input.close();
    }
}
