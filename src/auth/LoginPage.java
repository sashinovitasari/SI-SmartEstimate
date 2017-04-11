package auth;

import java.awt.EventQueue;
import java.awt.Font;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;

//import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JPanel {

	private static final long serialVersionUID = 2782053392620796788L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					LoginPage panel = new LoginPage();
					
					frame.setResizable(false);
					frame.getContentPane().setBackground(Color.WHITE);
					frame.getContentPane().add(panel, BorderLayout.CENTER);
					frame.setBounds(0, 0, 1000, 650);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setButtonActionListener(JPanel cards, String id) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//change to confirm page
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, id);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		usernameField.setBounds(341, 272, 471, 37);
		this.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(341, 330, 471, 37);
		this.add(passwordField);
		
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
		btnLogin.setIcon(new ImageIcon(LoginPage.class.getResource("/img_btn/login_btn.png")));
		btnLogin.setBounds(687, 446, 125, 57);
		this.add(btnLogin);
		
		button = new JButton("");
		button.setIcon(new ImageIcon(LoginPage.class.getResource("/img_btn/register_btn.png")));
		button.setBounds(555, 446, 125, 57);
		this.add(button);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(LoginPage.class.getResource("/img_pages/LoginPage.png")));
		border.setBounds(0, 0, 994, 621);
		this.add(border);
	}
}
