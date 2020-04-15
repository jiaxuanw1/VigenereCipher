import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JLabel textLabel;
	private JLabel keyLabel;
	private JTextField textField;
	private JTextField keyField;
	private JButton encryptButton;
	private JButton decryptButton;

	public GUI() {
		// Frame and content pane
		super("Vigenère Cipher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Text field
		textLabel = new JLabel("Text");
		textLabel.setBounds(10, 11, 46, 14);
		contentPane.add(textLabel);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 36, 445, 20);
		contentPane.add(textField);

		// Key field
		keyLabel = new JLabel("Key:");
		keyLabel.setBounds(10, 94, 33, 14);
		contentPane.add(keyLabel);

		keyField = new JTextField();
		keyField.setColumns(10);
		keyField.setBounds(39, 94, 197, 20);
		contentPane.add(keyField);

		// Encrypt button
		encryptButton = new JButton("Encrypt");
		encryptButton.setBounds(10, 154, 89, 23);
		contentPane.add(encryptButton);
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String plaintext = textField.getText();
					try {
						String key = keyField.getText();
						String encryptedMessage = Vigenere.encrypt(plaintext, key);
						JOptionPane.showMessageDialog(null, "Encrypted Message:\r\n\r\n" + encryptedMessage);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Please enter a valid key.");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter valid plain text.");
				}
			}
		});

		// Decrypt button
		decryptButton = new JButton("Decrypt");
		decryptButton.setBounds(109, 154, 89, 23);
		contentPane.add(decryptButton);
		decryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String ciphertext = textField.getText();
					try {
						String key = keyField.getText();
						String decryptedMessage = Vigenere.decrypt(ciphertext, key);
						JOptionPane.showMessageDialog(null, "Decrypted Message:\r\n\r\n" + decryptedMessage);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Please enter a valid key.");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter valid cipher text.");
				}
			}
		});
	}

}
