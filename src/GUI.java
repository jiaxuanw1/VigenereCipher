import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.bulenkov.darcula.DarculaLaf;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JLabel textLabel;
	private JLabel keyLabel;
	private JTextField textField;
	private JTextField keyField;
	private JCheckBox punctuationBox;
	private JButton encryptButton;
	private JButton decryptButton;

	public GUI(boolean dark) {
		super("Vigenère Cipher");

		if (dark) {
			// sets dark mode
			BasicLookAndFeel darcula = new DarculaLaf();
			try {
				UIManager.setLookAndFeel(darcula);
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		} else {
			// sets system theme
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}

		// Frame and content pane
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

		// Preserve punctuation checkbox
		punctuationBox = new JCheckBox("Preserve punctuation");
		punctuationBox.setSelected(true);
		punctuationBox.setBounds(10, 121, 227, 23);
		contentPane.add(punctuationBox);

		// Encrypt button
		encryptButton = new JButton("Encrypt");
		encryptButton.setBounds(10, 154, 89, 23);
		contentPane.add(encryptButton);
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String encryptedMessage = Vigenere.encrypt(textField.getText(), keyField.getText(),
							punctuationBox.isSelected());
					JOptionPane.showMessageDialog(null, "Encrypted Message:\r\n\r\n" + encryptedMessage);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter a valid key and plain text.");
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
					String decryptedMessage = Vigenere.decrypt(textField.getText(), keyField.getText(),
							punctuationBox.isSelected());
					JOptionPane.showMessageDialog(null, "Decrypted Message:\r\n\r\n" + decryptedMessage);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter a valid key and cipher text.");
				}
			}
		});
	}

}
