package auth;
// order tanggalnya now
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import cashier.SalesController;
//import cashier.SalesPage;
//import estimation.EstimationPage;

public class LandingPage {

	private JFrame frame;
	private LoginPage loginPage;
	private RegisterPage registerPage;
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
					LandingPage window = new LandingPage();
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
	public LandingPage() {
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
		
		loginPage = new LoginPage();
		loginPage.setButtonActionListener(cards, REGISTERPANEL);
		registerPage = new RegisterPage();
		registerPage.setButtonActionListener(cards, LOGINPANEL);
		
		cards.add(loginPage, LOGINPANEL);
		cards.add(registerPage, REGISTERPANEL);
		
		frame.add(cards, BorderLayout.CENTER);
		
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
