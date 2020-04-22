package cipher;

/**
 * This class provides the necessary methods to encrypt and decrypt messages
 * using the Vigenère Cipher.
 * 
 * @author Jiaxuan Wang
 */
public class Vigenere {

	/**
	 * Generates the key with a specified length using a key word/phrase.
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
	 * Encrypts the given plain text with the specified keyword with the choice of
	 * preserving the punctuation/capitalization.
	 * 
	 * @param text          the plain text to encrypt
	 * @param key           the keyword
	 * @param preservePunct whether to preserve punctuation
	 * @return text encrypted with the keyword
	 */
	public static String encrypt(String text, String key, boolean preservePunct) {
		key = key.toUpperCase().replaceAll("\\p{Punct}|\\s", "");
		String keystream = generateKey(key, text.replaceAll("\\p{Punct}|\\s", "").length());

		if (!preservePunct) {
			text = text.toUpperCase().replaceAll("\\p{Punct}|\\s", "");
		}

		StringBuilder ciphertext = new StringBuilder();
		int keyIndex = 0;
		for (char ch : text.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				int letterVal = (int) ch - 65;
				int keyShift = (int) keystream.charAt(keyIndex) - 65;
				ciphertext.append((char) ((letterVal + keyShift) % 26 + 65));
				keyIndex++;
			} else if (Character.isLowerCase(ch)) {
				int letterVal = (int) ch - 97;
				int keyShift = (int) keystream.charAt(keyIndex) - 65;
				ciphertext.append((char) ((letterVal + keyShift) % 26 + 97));
				keyIndex++;
			} else {
				ciphertext.append(ch);
			}
		}

		return ciphertext.toString();
	}

	/**
	 * Decrypts the given cipher text with the specified keyword with the choice of
	 * preserving the punctuation/capitalization.
	 * 
	 * @param text          the cipher text to decrypt
	 * @param key           the keyword
	 * @param preservePunct whether to preserve punctuation
	 * @return text decrypted with the keyword
	 */
	public static String decrypt(String text, String key, boolean preservePunct) {
		key = key.toUpperCase().replaceAll("\\p{Punct}|\\s", "");
		String keystream = generateKey(key, text.replaceAll("\\p{Punct}|\\s", "").length());

		if (!preservePunct) {
			text = text.toUpperCase().replaceAll("\\p{Punct}|\\s", "");
		}

		StringBuilder plaintext = new StringBuilder();
		int keyIndex = 0;
		for (char ch : text.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				int letterVal = (int) ch - 65;
				int keyShift = (int) keystream.charAt(keyIndex) - 65;
				plaintext.append((char) ((letterVal - keyShift + 26) % 26 + 65));
				keyIndex++;
			} else if (Character.isLowerCase(ch)) {
				int letterVal = (int) ch - 97;
				int keyShift = (int) keystream.charAt(keyIndex) - 65;
				plaintext.append((char) ((letterVal - keyShift + 26) % 26 + 97));
				keyIndex++;
			} else {
				plaintext.append(ch);
			}
		}

		return plaintext.toString();
	}

}
