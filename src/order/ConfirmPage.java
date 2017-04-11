package order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cashier.SalesPage;
import estimation.EstimationPage;

public class ConfirmPage {

	private JFrame frame;
	private JLabel amount1;
	private JLabel amount2;
	private JLabel amount3;
	private JLabel amount4;
	private JLabel amount5;
	private JButton btnSave;
	private JButton btnDiscard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmPage window = new ConfirmPage();
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
	public ConfirmPage() {
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
		
		amount1 = new JLabel();
		amount1.setText("0");
		amount1.setHorizontalAlignment(SwingConstants.CENTER);
		amount1.setVerticalAlignment(SwingConstants.CENTER);
		amount1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount1.setBounds(738, 232, 53, 53);
		panel.add(amount1);
		
		amount2 = new JLabel();
		amount2.setText("0");
		amount2.setHorizontalAlignment(SwingConstants.CENTER);
		amount2.setVerticalAlignment(SwingConstants.CENTER);
		amount2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount2.setBounds(738, 281, 53, 53);
		panel.add(amount2);
		
		amount3 = new JLabel();
		amount3.setText("0");
		amount3.setHorizontalAlignment(SwingConstants.CENTER);
		amount3.setVerticalAlignment(SwingConstants.CENTER);
		amount3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount3.setBounds(738, 335, 53, 53);
		panel.add(amount3);
		
		amount4 = new JLabel();
		amount4.setText("0");
		amount4.setHorizontalAlignment(SwingConstants.CENTER);
		amount4.setVerticalAlignment(SwingConstants.CENTER);
		amount4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount4.setBounds(738, 386, 53, 53);
		panel.add(amount4);
		
		amount5 = new JLabel();
		amount5.setText("0");
		amount5.setHorizontalAlignment(SwingConstants.CENTER);
		amount5.setVerticalAlignment(SwingConstants.CENTER);
		amount5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount5.setBounds(738, 440, 53, 53);
		panel.add(amount5);
		
		btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure the input is correct?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					OrderController.addToDatabase(0, new Date(), 
							Integer.parseInt(amount1.getText()),
							Integer.parseInt(amount2.getText()), 
							Integer.parseInt(amount3.getText()),
							Integer.parseInt(amount4.getText()), 
							Integer.parseInt(amount5.getText()));
					
				}
			}
		});
		btnSave.setIcon(new ImageIcon(SalesPage.class.getResource("/img_btn/proceed_btn.png")));
		btnSave.setBounds(728, 540, 125, 57);
		panel.add(btnSave);
		
		btnDiscard = new JButton("");
		btnDiscard.setIcon(new ImageIcon(SalesPage.class.getResource("/img_btn/cancel_btn.png")));
		btnDiscard.setBounds(593, 540, 125, 57);
		panel.add(btnDiscard);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_pages/ConfirmPage.png")));
		border.setBounds(0, 0, 994, 621);
		panel.add(border);
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
