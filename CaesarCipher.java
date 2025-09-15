/**
 * The CaesarCipher interface defines the contract for implementing
 * Caesar cipher encryption. It includes a method for encrypting text
 * using a specified shift value.
 */
public interface CaesarCipher {
    /**
     * Encrypts the given text using the Caesar cipher algorithm.
     * <p>
     * Each character in the text is shifted by the specified number of positions.
     * Only numeric characters are shifted; other characters remain unchanged.
     * 
     * @param text The text to be encrypted.
     * @param shift The number of positions to shift each character.
     * @return The encrypted text.
     */
    String encrypt(String text, int shift);
}

/**
 * The CaesarCipherImpl class provides an implementation of the CaesarCipher interface.
 * It performs encryption by shifting numeric characters in the text.
 */
class CaesarCipherImpl implements CaesarCipher {
    /**
     * Encrypts the given text using the Caesar cipher algorithm.
     * <p>
     * Numeric characters are shifted by the specified number of positions,
     * wrapping around if the shift exceeds the range of digits (0-9).
     * Non-numeric characters are left unchanged.
     * 
     * @param text The text to be encrypted.
     * @param shift The number of positions to shift numeric characters.
     * @return The encrypted text.
     */
    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder(); // StringBuilder to construct the encrypted text

        // Iterate through each character in the input text
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                // Shift numeric characters and wrap around within the range of 0-9
                result.append((char) ('0' + (c - '0' + shift) % 10));
            } else {
                // Leave non-numeric characters unchanged
                result.append(c);
            }
        }

        // Return the encrypted text as a string
        return result.toString();
    }
}