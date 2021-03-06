package cashier;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import auth.UserManagement;
import dashboard.DashboardCashierPage;
import estimation.EstimationPage;
import order.OrderController;
import order.PageOrder;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PageSalesInput {

	public JFrame frame;
	private JDateChooser inputDate;
	private JButton btnSave;
	private JButton btnDiscard;
	private JTextField item_1;
	private JTextField item_3;
	private JTextField item_5;
	private JTextField item_4;
	private JTextField item_2;
	private JButton dec_2;
	private JButton dec_3;
	private JButton dec_4;
	private JButton dec_5;
	private JButton inc_1;
	private JButton inc_2;
	private JButton inc_3;
	private JButton inc_4;
	private JButton inc_5;
	private JButton dashboardButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageSalesInput window = new PageSalesInput();
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
	public PageSalesInput() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		
		dashboardButton = new JButton();
		dashboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close window or return to dashboard
				try {
					DashboardCashierPage window = new DashboardCashierPage();
					window.frame.setVisible(true);
					frame.setVisible(false); //you can't see me!
					frame.dispose(); //Destroy the JFrame object
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		dashboardButton.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/dashboard_btn.png")));
		dashboardButton.setBounds(825, 40, 125, 57);
		frame.getContentPane().add(dashboardButton);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		inputDate = new JDateChooser();
		inputDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inputDate.setBounds(248, 152, 594, 35);
		panel.add(inputDate);
		
		btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string1 = item_1.getText();
				String string2 = item_2.getText();
				String string3 = item_3.getText();
				String string4 = item_4.getText();
				String string5 = item_5.getText();
				if (OrderController.isInputValid(string1,string2,string3,string4,string5)) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure the input is correct?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						SalesController.addToDatabase(0, inputDate.getDate(), 
								Integer.parseInt(item_1.getText()), Integer.parseInt(item_2.getText()), 
								Integer.parseInt(item_3.getText()), Integer.parseInt(item_4.getText()), 
								Integer.parseInt(item_5.getText()));
						JOptionPane.showMessageDialog(null, "Sales entry has been inserted successfully!", "Notification", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "All fields must be a positive number!");
				}
				try {//Change to DASHBOARD
					DashboardCashierPage window = new DashboardCashierPage();
					window.frame.setVisible(true);
					frame.setVisible(false); //you can't see me!
					frame.dispose(); //Destroy the JFrame object
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/save_btn.png")));
		btnSave.setBounds(728, 540, 125, 57);
		panel.add(btnSave);
		
		btnDiscard = new JButton("");
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {//Change to DASHBOARD
					DashboardCashierPage window = new DashboardCashierPage();
					window.frame.setVisible(true);
					frame.setVisible(false); //you can't see me!
					frame.dispose(); //Destroy the JFrame object
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDiscard.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/discard_btn.png")));
		btnDiscard.setBounds(593, 540, 125, 57);
		panel.add(btnDiscard);
		
		item_1 = new JTextField();
		item_1.setText("0");
		item_1.setHorizontalAlignment(SwingConstants.CENTER);
		item_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_1.setBounds(738, 264, 53, 35);
		panel.add(item_1);
		item_1.setColumns(10);
		
		item_2 = new JTextField();
		item_2.setText("0");
		item_2.setHorizontalAlignment(SwingConstants.CENTER);
		item_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_2.setColumns(10);
		item_2.setBounds(738, 313, 53, 35);
		panel.add(item_2);
		
		item_3 = new JTextField();
		item_3.setText("0");
		item_3.setHorizontalAlignment(SwingConstants.CENTER);
		item_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_3.setColumns(10);
		item_3.setBounds(738, 367, 53, 35);
		panel.add(item_3);
		
		item_5 = new JTextField();
		item_5.setText("0");
		item_5.setHorizontalAlignment(SwingConstants.CENTER);
		item_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_5.setColumns(10);
		item_5.setBounds(738, 472, 53, 35);
		panel.add(item_5);
		
		item_4 = new JTextField();
		item_4.setText("0");
		item_4.setHorizontalAlignment(SwingConstants.CENTER);
		item_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_4.setColumns(10);
		item_4.setBounds(738, 418, 53, 35);
		panel.add(item_4);
		
		JButton dec_1 = new JButton("");
		dec_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_1.getText());
				if (val <= 0) val = 0; else val--;
				item_1.setText(val.toString());
			}
		});
		dec_1.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/dec_btn.png")));
		dec_1.setBounds(693, 264, 36, 35);
		panel.add(dec_1);
		
		dec_2 = new JButton("");
		dec_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_2.getText());
				if (val <= 0) val = 0; else val--;
				item_2.setText(val.toString());
			}
		});
		dec_2.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/dec_btn.png")));
		dec_2.setBounds(693, 313, 36, 35);
		panel.add(dec_2);
		
		dec_3 = new JButton("");
		dec_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_3.getText());
				if (val <= 0) val = 0; else val--;
				item_3.setText(val.toString());
			}
		});
		dec_3.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/dec_btn.png")));
		dec_3.setBounds(693, 367, 36, 35);
		panel.add(dec_3);
		
		dec_4 = new JButton("");
		dec_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_4.getText());
				if (val <= 0) val = 0; else val--;
				item_4.setText(val.toString());
			}
		});
		dec_4.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/dec_btn.png")));
		dec_4.setBounds(693, 418, 36, 35);
		panel.add(dec_4);
		
		dec_5 = new JButton("");
		dec_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_5.getText());
				if (val <= 0) val = 0; else val--;
				item_5.setText(val.toString());
			}
		});
		dec_5.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/dec_btn.png")));
		dec_5.setBounds(693, 472, 36, 35);
		panel.add(dec_5);
		
		inc_1 = new JButton("");
		inc_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_1.getText());
				val++; item_1.setText(val.toString());
			}
		});
		inc_1.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/inc_btn.png")));
		inc_1.setBounds(799, 264, 36, 35);
		panel.add(inc_1);
		
		inc_2 = new JButton("");
		inc_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_2.getText());
				val++; item_2.setText(val.toString());
			}
		});
		inc_2.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/inc_btn.png")));
		inc_2.setBounds(799, 313, 36, 35);
		panel.add(inc_2);
		
		inc_3 = new JButton("");
		inc_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_3.getText());
				val++; item_3.setText(val.toString());
			}
		});
		inc_3.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/inc_btn.png")));
		inc_3.setBounds(799, 367, 36, 35);
		panel.add(inc_3);
		
		inc_4 = new JButton("");
		inc_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_4.getText());
				val++; item_4.setText(val.toString());
			}
		});
		inc_4.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/inc_btn.png")));
		inc_4.setBounds(799, 418, 36, 35);
		panel.add(inc_4);
		
		inc_5 = new JButton("");
		inc_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_5.getText());
				val++; item_5.setText(val.toString());
			}
		});
		inc_5.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_btn/inc_btn.png")));
		inc_5.setBounds(799, 472, 36, 35);
		panel.add(inc_5);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(PageSalesInput.class.getResource("/img_pages/SalesPage.png")));
		border.setBounds(0, 0, 994, 621);
		panel.add(border);
		
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
