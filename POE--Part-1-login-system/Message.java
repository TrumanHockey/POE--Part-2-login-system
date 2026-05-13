import java.util.Random;
import java.util.Scanner;

public class Message {

    // Variables to store message details
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Static variable keeps track of total messages sent
    private static int totalMessages = 0;

    // Constructor
    public Message(int messageNumber, String recipient, String messageText) {

        // Save values entered by user
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        // Generate automatic message ID
        this.messageID = generateMessageID();

        // Create automatic hash
        this.messageHash = createMessageHash();
    }

    // Method to generate random 10 digit message ID
    private String generateMessageID() {

        Random random = new Random();

        // Create a random 10 digit number
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    // Method checks if message ID is 10 characters
    public boolean checkMessageID() {

        return messageID.length() == 10;
    }

    // Method checks recipient number
    public String checkRecipientCell() {

        // Number must start with international code
        // Example: +27
        // Maximum 13 characters for South African numbers

        if(recipient.matches("^\\+27[0-9]{9}$")) {
            return "Cell phone number successfully captured.";
        }
        else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Method checks message length
    public String checkMessageLength() {

        // Message must not exceed 250 characters
        if(messageText.length() <= 250) {
            return "Message ready to send.";
        }
        else {

            int extraCharacters = messageText.length() - 250;

            return "Message exceeds 250 characters by " + extraCharacters +
                    "; please reduce the size.";
        }
    }

    // Method creates message hash
    public String createMessageHash() {

        // Get first 2 digits of message ID
        String firstTwoDigits = messageID.substring(0, 2);

        // Split sentence into words
        String[] words = messageText.split(" ");

        // First word
        String firstWord = words[0].toUpperCase();

        // Last word
        String lastWord = words[words.length - 1].toUpperCase();

        // Remove punctuation
        lastWord = lastWord.replaceAll("[^A-Z]", "");

        // Build hash
        return firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Method allows user to send/store/disregard message
    public String sentMessage() {

        Scanner input = new Scanner(System.in);

        System.out.println("\nChoose an option:");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");

        int choice = input.nextInt();

        switch(choice) {

            case 1:
                totalMessages++;
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option selected.";
        }
    }

    // Method prints full message details
    public String printMessages() {

        return "------------------------------" +
                "\nMessage ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + messageText +
                "\n------------------------------";
    }

    // Method returns total messages sent
    public static int returnTotalMessages() {

        return totalMessages;
    }

    // Method to store message in JSON format
    public void storeMessage() {

        // Example JSON format
        String jsonMessage = "{" +
                "\"MessageID\":\"" + messageID + "\"," +
                "\"Recipient\":\"" + recipient + "\"," +
                "\"Message\":\"" + messageText + "\"" +
                "}";

        // Display stored message
        System.out.println("Stored JSON Message:");
        System.out.println(jsonMessage);
    }
}
