package auth;

// order tanggalnya now
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PageAuth {

	private JFrame frame;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton button;
	
	private JTextField usernameRegisterField;
	private JTextField fullnameRegisterField;
	private JPasswordField passwordRegisterField;
	private JButton btnCancel;

	private JPanel loginPanel;
	private JPanel registerPanel;
	private JPanel cards;

	final static String LOGINPANEL = "Login Panel";
	final static String REGISTERPANEL = "Register Panel";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageAuth window = new PageAuth();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initLoginPanel() {
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		usernameField.setBounds(341, 272, 471, 37);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(341, 330, 471, 37);
		loginPanel.add(passwordField);
		
		btnLogin = new JButton("");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int loginResult = UserManagement.doLogin(usernameField.getText(), passwordField.getPassword());
				if (loginResult == UserManagement.LOGIN_SUCCESS) {
					JOptionPane.showMessageDialog(null, "Authentication successful!");
					// move to dashboard
				} else if (loginResult == UserManagement.AUTHENTICATION_FAILED) {
					JOptionPane.showMessageDialog(null, "Username or/and password doesn't match.");
				} else if (loginResult == UserManagement.EMPTY_FIELD) {
					JOptionPane.showMessageDialog(null, "All fields must be filled.");
				}
			}
		});
		btnLogin.setIcon(new ImageIcon(PageAuth.class.getResource("/img_btn/login_btn.png")));
		btnLogin.setBounds(687, 446, 125, 57);
		loginPanel.add(btnLogin);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//change to confirm page
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, REGISTERPANEL);
			}
		});
		button.setIcon(new ImageIcon(PageAuth.class.getResource("/img_btn/register_btn.png")));
		button.setBounds(555, 446, 125, 57);
		loginPanel.add(button);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(PageAuth.class.getResource("/img_pages/LoginPage.png")));
		border.setBounds(0, 0, 994, 621);
		loginPanel.add(border);
	}

	private void initRegisterPanel() {
		registerPanel = new JPanel();
		registerPanel.setLayout(null);
		
		usernameRegisterField = new JTextField();
		usernameRegisterField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		usernameRegisterField.setBounds(341, 208, 471, 37);
		registerPanel.add(usernameRegisterField);
		usernameRegisterField.setColumns(10);
		
		passwordRegisterField = new JPasswordField();
		passwordRegisterField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordRegisterField.setBounds(341, 259, 471, 37);
		registerPanel.add(passwordRegisterField);
		
		fullnameRegisterField = new JTextField();
		fullnameRegisterField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fullnameRegisterField.setColumns(10);
		fullnameRegisterField.setBounds(341, 315, 471, 37);
		registerPanel.add(fullnameRegisterField);
		
		JComboBox<String> positionBox = new JComboBox<String>();
		positionBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		positionBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Store Manager", "Cashier"}));
		positionBox.setBounds(341, 368, 471, 37);
		registerPanel.add(positionBox);
		
		JButton btnSubmit = new JButton("");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure the input is correct?", 
						"Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (dialogResult == JOptionPane.YES_OPTION) {
					int registerResult = UserManagement.registerUser(usernameRegisterField.getText(), 
							passwordRegisterField.getPassword(), fullnameRegisterField.getText(), 
							positionBox.getSelectedItem().toString());
					
					if (registerResult == UserManagement.EMPTY_FIELD) {
						JOptionPane.showMessageDialog(null, "All fields must be filled.");
					} else if (registerResult == UserManagement.USERNAME_ALREADY_EXISTS) {
						JOptionPane.showMessageDialog(null, "Username already exists.");
					} else if (registerResult == UserManagement.PASSWORD_TOO_SHORT) {
						JOptionPane.showMessageDialog(null, "Password must be at least 6 characters.");
					} else if (registerResult == UserManagement.REGISTER_SUCCESS) {
						JOptionPane.showMessageDialog(null, "Registration success!");
						// go to dashboard
					}
				}
			}
		});
		btnSubmit.setIcon(new ImageIcon(PageAuth.class.getResource("/img_btn/submit_btn.png")));
		btnSubmit.setBounds(687, 446, 125, 57);
		registerPanel.add(btnSubmit);
		
		btnCancel = new JButton("");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//change to confirm page
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, LOGINPANEL);
			}
		});
		btnCancel.setIcon(new ImageIcon(PageAuth.class.getResource("/img_btn/cancel_btn.png")));
		btnCancel.setBounds(552, 446, 125, 57);
		registerPanel.add(btnCancel);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(PageAuth.class.getResource("/img_pages/RegisterPage.png")));
		border.setBounds(0, 0, 994, 621);
		registerPanel.add(border);
	}

	/**
	 * Create the application.
	 */
	public PageAuth() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);

		cards = new JPanel(new CardLayout());

		initLoginPanel();
		initRegisterPanel();

		cards.add(loginPanel, LOGINPANEL);
		cards.add(registerPanel, REGISTERPANEL);

		frame.add(cards, BorderLayout.CENTER);

		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
