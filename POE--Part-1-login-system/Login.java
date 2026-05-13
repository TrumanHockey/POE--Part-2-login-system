public class Login {

// Instance variables to store user details
private String username;
private String password;
private String cellPhone;

// Method to check if username is valid
public boolean checkUserName(String username) {
// Username must contain "_" and be no more than 5 characters
return username.contains("_") && username.length() <= 5;
}

// Method to check password complexity using regex
public boolean checkPasswordComplexity(String password) {
// Regex explanation:
// (?=.*[A-Z]) → at least one uppercase letter
// (?=.*[0-9]) → at least one number
// (?=.*[@#$%^&+=!]) → at least one special character
// .{8,} → minimum length of 8 characters
return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$");
}

// Method to validate South African cell phone number
public boolean checkCellPhoneNumber(String number) {
// Must start with +27 and have 9 digits after that
return number.matches("^\\+27[0-9]{9}$");
}

// Method to register the user
public String registerUser(String username, String password, String number) {

// Check username validity
if (!checkUserName(username)) {
return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
}

// Check password validity
if (!checkPasswordComplexity(password)) {
return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
}

// Check phone number validity
if (!checkCellPhoneNumber(number)) {
return "Cell phone number incorrectly formatted or does not contain international code.";
}

// If all validations pass, store the values
this.username = username;
this.password = password;
this.cellPhone = number;

// Return success message
return "User successfully registered.";
}

// Method to verify login details
public boolean loginUser(String username, String password) {
// Return false if no user has been registered yet
if (this.username == null || this.password == null) {
return false;
}

// Check if entered details match stored details
return this.username.equals(username) && this.password.equals(password);
}

// Method to return login status message
public String returnLoginStatus(boolean loginStatus, String username) {

    // If login is successful
    if (loginStatus) {
        return "Welcome " + username + ", it is great to see you again.";
    }

    // If login fails
    else {
        return "Username or password incorrect, please try again.";
    }
}
}