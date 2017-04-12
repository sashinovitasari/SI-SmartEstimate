package dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import auth.PageAuth;
import cashier.PageSalesInput;
import estimation.EstimationPage;
import javax.swing.JButton;

public class DashboardCashierPage {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardCashierPage window = new DashboardCashierPage();
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
	public DashboardCashierPage() {
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
		
		JLabel btnInputSales = new JLabel("");
		btnInputSales.setHorizontalAlignment(SwingConstants.CENTER);
		btnInputSales.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_pages/Dashboard2Page.png")));
		btnInputSales.setBounds(0, 0, 994, 621);
		panel.add(btnInputSales);
		
		JButton btnLogOut = new JButton("");	
		btnLogOut.setOpaque(false);
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setBorderPainted(false);
		btnLogOut.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_btn/logout_btn.png")));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PageAuth window = new PageAuth();
					window.frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogOut.setBounds(854, 56, 95, 31);
		panel.add(btnLogOut);
		
		JButton btnSales = new JButton("");
		btnSales.setOpaque(false);
		btnSales.setContentAreaFilled(false);
		btnSales.setBorderPainted(false);
		btnSales.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_btn/sales_btn.png")));
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PageSalesInput window = new PageSalesInput();
					window.frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSales.setBounds(411, 474, 169, 51);
		panel.add(btnSales);
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
