package auth;

// order tanggalnya now
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.json.JSONArray;

import dashboard.DashboardCashierPage;
import dashboard.DashboardSMPage;
import db.DBController;
import estimation.EstimationPage;
import news.InfoNews;

public class PageAuth {

	public JFrame frame;
	
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
				PageSplash window = new PageSplash();
				window.frame.setVisible(true);
				
				SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			        @Override
			        protected Void doInBackground() throws Exception {
			        	Thread.sleep(2000);
						return null;
			        }
				};
				
				mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent evt) {
			            if (evt.getPropertyName().equals("state")) {
			               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
			            	   window.frame.setVisible(false);
							   window.frame.dispose();
							   PageAuth auth = new PageAuth();
							   auth.frame.setVisible(true);
			               }
			            }
			        }
			    });
			    mySwingWorker.execute();
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
		//Change to DASHBOARD
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int loginResult = UserManagement.doLogin(usernameField.getText(), passwordField.getPassword());
				if (loginResult == UserManagement.LOGIN_SUCCESS) {
					JOptionPane.showMessageDialog(null, "Authentication successful!");
					
					String prn = UserManagement.getRole(usernameField.getText());	
					
					if (prn.equals("c")) {//CASHIER
						try {
							DashboardCashierPage window = new DashboardCashierPage();
							window.frame.setVisible(true);
							frame.setVisible(false); //you can't see me!
							frame.dispose(); //Destroy the JFrame object
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else{//STORE MANAGER
						try {
							InfoNews.addDatabase();
							DashboardSMPage window = new DashboardSMPage();
							window.frame.setVisible(true);
							frame.setVisible(false); //you can't see me!
							frame.dispose(); //Destroy the JFrame object
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} 
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
						
						String prn = UserManagement.getRole(usernameRegisterField.getText());	
						//Change to DASHBOARD
						if (prn.equals("c")) {
							try {
								DashboardCashierPage window = new DashboardCashierPage();
								window.frame.setVisible(true);
								frame.setVisible(false); //you can't see me!
								frame.dispose(); //Destroy the JFrame object
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else{
							try {
								InfoNews.addDatabase();
								DashboardSMPage window = new DashboardSMPage();
								window.frame.setVisible(true);
								frame.setVisible(false); //you can't see me!
								frame.dispose(); //Destroy the JFrame object
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} 
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
