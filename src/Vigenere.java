
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
	 * @return
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
		text = text.toUpperCase().replace(" ", "");
		key = key.toUpperCase().replace(" ", "");
		String keystream = generateKey(key, text.length());

		StringBuilder plaintext = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			int letterVal = (int) text.charAt(i) - 65;
			int keyShift = (int) keystream.charAt(i) - 65;
			plaintext.append((char) ((letterVal + keyShift) % 26 + 65));
		}

		return plaintext.toString();
	}

	/**
	 * Decrypts the given ciphertext with the specified keyword.
	 * 
	 * @param text the ciphertext to decrypt
	 * @param key  the keyword
	 * @return text decrypted with the keyword
	 */
	public static String decrypt(String text, String key) {
		text = text.toUpperCase().replace(" ", "");
		key = key.toUpperCase().replace(" ", "");
		String keystream = generateKey(key, text.length());

		StringBuilder ciphertext = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			int letterVal = (int) text.charAt(i) - 65;
			int keyShift = (int) keystream.charAt(i) - 65;
			ciphertext.append((char) ((letterVal - keyShift + 26) % 26 + 65));
		}

		return ciphertext.toString();
	}

}
