   import static org.junit.Assert.*;
import org.junit.Test;

public class LoginTest {

// Test if username is correctly formatted
@Test
public void testUsernameCorrect() {
Login login = new Login();

// Expect TRUE because username meets requirements
assertTrue(login.checkUserName("tru_1"));
}

// Test if username is incorrectly formatted
@Test
public void testUsernameIncorrect() {
Login login = new Login();

// Expect FALSE because username is too long and invalid
assertFalse(login.checkUserName("truman!!!!!!!"));
}

// Test valid password
@Test
public void testPasswordValid() {
Login login = new Login();

// Expect TRUE because password meets all rules
assertTrue(login.checkPasswordComplexity("Ch&&sec@ke00!"));
}

// Test invalid password
@Test
public void testPasswordInvalid() {
Login login = new Login();

// Expect FALSE because password lacks complexity
assertFalse(login.checkPasswordComplexity("password"));
}

// Test valid phone number
@Test
public void testCellValid() {
Login login = new Login();

// Expect TRUE because number is correctly formatted
assertTrue(login.checkCellPhoneNumber("+27812198595"));
}

// Test invalid phone number
@Test
public void testCellInvalid() {
Login login = new Login();

// Expect FALSE because number is incorrect
assertFalse(login.checkCellPhoneNumber("0812198595"));
}
}