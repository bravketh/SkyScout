import java.io.*;

/**
 * The User class encrypts login credentials using a Caesar cipher
 * and saves them to a file.
 */
public class User implements CaesarCipher {
    private String encryptUsername; // Encrypted username
    private String encryptPassword; // Encrypted password
    private int shift; // Shift value for encryption

    /**
     * Creates a User with encrypted credentials and saves them to a file.
     * 
     * @param username The username to encrypt.
     * @param password The password to encrypt.
     * @param shift The shift value for encryption.
     */
    public User(String username, String password, int shift) {
        this.shift = shift;
        this.encryptUsername = encrypt(username, shift);
        this.encryptPassword = encrypt(password, shift);
        saveLogin();
    }

    /**
     * Encrypts text using the Caesar cipher.
     * 
     * @param text The text to encrypt.
     * @param shift The shift value for encryption.
     * @return The encrypted text.
     */
    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                result.append((char) ((character + shift - 'A') % 26 + 'A')); // Encrypt uppercase letters
            } else if (Character.isLowerCase(character)) {
                result.append((char) ((character + shift - 'a') % 26 + 'a')); // Encrypt lowercase letters
            } else if (Character.isDigit(character)) {
                result.append((char) ((character + shift - '0') % 10 + '0')); // Encrypt digits
            } else if (character >= 32 && character <= 126) {
                result.append((char) ((character + shift - 32) % 95 + 32)); // Encrypt printable ASCII characters
            } else {
                result.append(character); // Leave other characters unchanged
            }
        }

        return result.toString();
    }

    /**
     * Saves encrypted credentials to a file.
     */
    private void saveLogin() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("LoginInfo.txt"))) {
            pw.println("Username: " + encryptUsername);
            pw.println("Password: " + encryptPassword);
        } catch (IOException e) {
            System.out.println("Error saving login info: " + e.getMessage());
        }
    }
}