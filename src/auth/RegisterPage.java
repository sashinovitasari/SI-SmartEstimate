package auth;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterPage extends JPanel {

	private static final long serialVersionUID = -527887441165306537L;
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
					JFrame frame = new JFrame();
					RegisterPage panel = new RegisterPage();
					
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
		btnCancel.addActionListener(new ActionListener() {
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
	public RegisterPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		usernameField.setBounds(341, 208, 471, 37);
		this.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(341, 259, 471, 37);
		this.add(passwordField);
		
		fullnameField = new JTextField();
		fullnameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fullnameField.setColumns(10);
		fullnameField.setBounds(341, 315, 471, 37);
		this.add(fullnameField);
		
		JComboBox<String> positionBox = new JComboBox<String>();
		positionBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		positionBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Store Manager", "Cashier"}));
		positionBox.setBounds(341, 368, 471, 37);
		this.add(positionBox);
		
		JButton btnSubmit = new JButton("");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure the input is correct?", 
						"Confirmation", JOptionPane.YES_NO_OPTION);
				
				if (dialogResult == JOptionPane.YES_OPTION) {
					int registerResult = UserManagement.registerUser(usernameField.getText(), 
							passwordField.getPassword(), fullnameField.getText(), 
							positionBox.getSelectedItem().toString());
					
					if (registerResult == UserManagement.EMPTY_FIELD) {
						JOptionPane.showMessageDialog(null, "All fields must be filled.");
					} else if (registerResult == UserManagement.USERNAME_ALREADY_EXISTS) {
						JOptionPane.showMessageDialog(null, "Username already exists.");
					} else if (registerResult == UserManagement.PASSWORD_TOO_SHORT) {
						JOptionPane.showMessageDialog(null, "Password must be at least 6 characters.");
					} else if (registerResult == UserManagement.REGISTER_SUCCESS) {
						JOptionPane.showMessageDialog(null, "Registration success!");
					}
				}
			}
		});
		btnSubmit.setIcon(new ImageIcon(RegisterPage.class.getResource("/img_btn/submit_btn.png")));
		btnSubmit.setBounds(687, 446, 125, 57);
		this.add(btnSubmit);
		
		btnCancel = new JButton("");
		btnCancel.setIcon(new ImageIcon(RegisterPage.class.getResource("/img_btn/cancel_btn.png")));
		btnCancel.setBounds(552, 446, 125, 57);
		this.add(btnCancel);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(RegisterPage.class.getResource("/img_pages/RegisterPage.png")));
		border.setBounds(0, 0, 994, 621);
		this.add(border);
	}
}
