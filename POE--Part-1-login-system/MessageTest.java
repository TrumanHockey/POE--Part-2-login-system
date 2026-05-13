import static org.junit.Assert.*;
import org.junit.Test;

public class MessageTest {

    // ------------------------------------------
    // TEST 1: MESSAGE LENGTH SUCCESS
    // ------------------------------------------
    // This test checks if a valid message
    // under 250 characters passes correctly.
    @Test
    public void testMessageLengthSuccess() {

        // Create message object
        Message msg = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        // Expected result
        assertEquals(
                "Message ready to send.",
                msg.checkMessageLength()
        );
    }

    // ------------------------------------------
    // TEST 2: MESSAGE LENGTH FAILURE
    // ------------------------------------------
    // This test checks if a message longer
    // than 250 characters fails.
    @Test
    public void testMessageLengthFailure() {

        // Create a message with 260 characters
        String longMessage = "A".repeat(260);

        // Create message object
        Message msg = new Message(
                0,
                "+27718693002",
                longMessage
        );

        // Check if error message appears
        assertTrue(
                msg.checkMessageLength().contains(
                        "Message exceeds 250 characters"
                )
        );
    }

    // ------------------------------------------
    // TEST 3: RECIPIENT NUMBER SUCCESS
    // ------------------------------------------
    // Checks valid international number
    @Test
    public void testRecipientSuccess() {

        Message msg = new Message(
                0,
                "+27718693002",
                "Hi Truman Hockey"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                msg.checkRecipientCell()
        );
    }

    // ------------------------------------------
    // TEST 4: RECIPIENT NUMBER FAILURE
    // ------------------------------------------
    // Checks invalid phone number
    @Test
    public void testRecipientFailure() {

        Message msg = new Message(
                0,
                "0812198595",
                "Hi Truman Hockey"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell()
        );
    }

    // ------------------------------------------
    // TEST 5: MESSAGE HASH
    // ------------------------------------------
    // Checks if hash is generated correctly
    @Test
    public void testMessageHash() {

        Message msg = new Message(
                0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight"
        );

        // Create hash
        String hash = msg.createMessageHash();

        // Check if hash contains correct format
        assertTrue(hash.contains(":0:HITONIGHT"));
    }

    // ------------------------------------------
    // TEST 6: MESSAGE ID
    // ------------------------------------------
    // Checks if Message ID is 10 digits
    @Test
    public void testMessageID() {

        Message msg = new Message(
                0,
                "+27718693002",
                "Hi Truman Hockey"
        );

        // Should return true
        assertTrue(msg.checkMessageID());
    }
}