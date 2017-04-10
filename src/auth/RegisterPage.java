package auth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class RegisterPage {

	private JFrame frame;
	private JTextField usernameField;
	private JTextField fullnameField;
	private JPasswordField passwordField;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage window = new RegisterPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		usernameField.setBounds(341, 208, 471, 37);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(341, 259, 471, 37);
		panel.add(passwordField);
		
		fullnameField = new JTextField();
		fullnameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fullnameField.setColumns(10);
		fullnameField.setBounds(341, 315, 471, 37);
		panel.add(fullnameField);
		
		JComboBox positionBox = new JComboBox();
		positionBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		positionBox.setModel(new DefaultComboBoxModel(new String[] {"Store Manager", "Cashier"}));
		positionBox.setBounds(341, 368, 471, 37);
		panel.add(positionBox);
		
		JButton btnSubmit = new JButton("");
		btnSubmit.setIcon(new ImageIcon(RegisterPage.class.getResource("/img_btn/submit_btn.png")));
		btnSubmit.setBounds(687, 446, 125, 57);
		panel.add(btnSubmit);
		
		btnCancel = new JButton("");
		btnCancel.setIcon(new ImageIcon(RegisterPage.class.getResource("/img_btn/cancel_btn.png")));
		btnCancel.setBounds(552, 446, 125, 57);
		panel.add(btnCancel);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(RegisterPage.class.getResource("/img_pages/RegisterPage.png")));
		border.setBounds(0, 0, 994, 621);
		panel.add(border);
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
