
/**
 * This class provides the necessary methods to encrypt and decrypt messages
 * using the Vigenère Cipher.
 * 
 * @author Jiaxuan Wang
 */
public class Vigenere {

	/**
	 * Generates the key with a specified length using a key word/phrase
	 * 
	 * @param key    the key word/phrase
	 * @param length the desired length for the key
	 * @return the generated key
	 */
	private static String generateKey(String key, int length) {
		StringBuilder keyString = new StringBuilder(key);
		if (keyString.length() > length) {
			return keyString.substring(0, length).toString();
		} else {
			for (int i = 0; keyString.length() < length; i++) {
				keyString.append(key.charAt(i % key.length()));
			}
			return keyString.toString();
		}
	}

	/**
	 * Encrypts the given plaintext with the specified keyword.
	 * 
	 * @param text the plaintext to encrypt
	 * @param key  the keyword
	 * @return text encrypted with the keyword
	 */
	public static String encrypt(String text, String key) {
		text = text.toUpperCase();
		key = key.toUpperCase().replaceAll("\\p{Punct}|\\s", "");
		String keystream = generateKey(key, text.replaceAll("\\p{Punct}|\\s", "").length());

		StringBuilder ciphertext = new StringBuilder();
		int keyIndex = 0;
		for (char character : text.toCharArray()) {
			if (isAlpha(character)) {
				int letterVal = (int) character - 65;
				int keyShift = (int) keystream.charAt(keyIndex) - 65;
				ciphertext.append((char) ((letterVal + keyShift) % 26 + 65));
				keyIndex++;
			} else {
				ciphertext.append(character);
			}
		}

		return ciphertext.toString();
	}

	/**
	 * Decrypts the given ciphertext with the specified keyword.
	 * 
	 * @param text the ciphertext to decrypt
	 * @param key  the keyword
	 * @return text decrypted with the keyword
	 */
	public static String decrypt(String text, String key) {
		text = text.toUpperCase();
		key = key.toUpperCase().replaceAll("\\p{Punct}|\\s", "");
		String keystream = generateKey(key, text.replaceAll("\\p{Punct}|\\s", "").length());

		StringBuilder plaintext = new StringBuilder();
		int keyIndex = 0;
		for (char character : text.toCharArray()) {
			if (isAlpha(character)) {
				int letterVal = (int) character - 65;
				int keyShift = (int) keystream.charAt(keyIndex) - 65;
				plaintext.append((char) ((letterVal - keyShift + 26) % 26 + 65));
				keyIndex++;
			} else {
				plaintext.append(character);
			}
		}

		return plaintext.toString();
	}

	/**
	 * Determines whether the specified character is a letter in the English
	 * alphabet.
	 * 
	 * @param c the character to test
	 * @return {@code true} if the specified character is an English letter;
	 *         {@code false} otherwise
	 */
	private static boolean isAlpha(char c) {
		return isUpperCase(c) || isLowerCase(c);
	}

	/**
	 * Determines whether the specified letter is uppercase.
	 * 
	 * @param letter the letter to test
	 * @return {@code true} if the specified letter is uppercase; {@code false}
	 *         otherwise
	 */
	private static boolean isUpperCase(char letter) {
		int value = (int) letter;
		return 65 <= value && value <= 90;
	}

	/**
	 * Determines whether the specified letter is lowercase.
	 * 
	 * @param letter the letter to test
	 * @return {@code true} if the specified letter is lowercase; {@code false}
	 *         otherwise
	 */
	private static boolean isLowerCase(char letter) {
		int value = (int) letter;
		return 97 <= value && value <= 122;
	}

}
