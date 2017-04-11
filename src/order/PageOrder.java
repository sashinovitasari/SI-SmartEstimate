package order;
// order tanggalnya now
import java.awt.BorderLayout;
import java.awt.CardLayout;
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

public class PageOrder {

	private JFrame frame;
	private JTextField item_1;
	private JTextField item_3;
	private JTextField item_5;
	private JTextField item_4;
	private JTextField item_2;
	private JButton btnSave;
	private JButton btnDiscard;
	private JButton dec_1;
	private JButton dec_2;
	private JButton dec_3;
	private JButton dec_4;
	private JButton dec_5;
	private JButton inc_1;
	private JButton inc_2;
	private JButton inc_3;
	private JButton inc_4;
	private JButton inc_5;
	private JLabel amount1;
	private JLabel amount2;
	private JLabel amount3;
	private JLabel amount4;
	private JLabel amount5;
	private JButton dashboardButton;
	
	private JPanel orderPanel;
	private JPanel confirmPanel;
	private JPanel cards;
	
	final static String ORDERPANEL = "Order Panel";
    final static String CONFIRMPANEL = "Confirm Panel";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageOrder window = new PageOrder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initOrderPanel() {
		orderPanel = new JPanel();
		orderPanel.setLayout(null);
		
		item_1 = new JTextField();
		item_1.setText("0");
		item_1.setHorizontalAlignment(SwingConstants.CENTER);
		item_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_1.setBounds(738, 232, 53, 35);
		orderPanel.add(item_1);
		item_1.setColumns(10);
		
		item_2 = new JTextField();
		item_2.setText("0");
		item_2.setHorizontalAlignment(SwingConstants.CENTER);
		item_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_2.setColumns(10);
		item_2.setBounds(738, 281, 53, 35);
		orderPanel.add(item_2);
		
		item_3 = new JTextField();
		item_3.setText("0");
		item_3.setHorizontalAlignment(SwingConstants.CENTER);
		item_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_3.setColumns(10);
		item_3.setBounds(738, 335, 53, 35);
		orderPanel.add(item_3);
		
		item_5 = new JTextField();
		item_5.setText("0");
		item_5.setHorizontalAlignment(SwingConstants.CENTER);
		item_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_5.setColumns(10);
		item_5.setBounds(738, 440, 53, 35);
		orderPanel.add(item_5);
		
		item_4 = new JTextField();
		item_4.setText("0");
		item_4.setHorizontalAlignment(SwingConstants.CENTER);
		item_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		item_4.setColumns(10);
		item_4.setBounds(738, 386, 53, 35);
		orderPanel.add(item_4);
		
		btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string1 = item_1.getText();
				String string2 = item_2.getText();
				String string3 = item_3.getText();
				String string4 = item_4.getText();
				String string5 = item_5.getText();
				if (OrderController.isInputValid(string1,string2,string3,string4,string5)) {
					//change to confirm page
					CardLayout cl = (CardLayout)(cards.getLayout());
			        cl.show(cards, CONFIRMPANEL);
			        amount1.setText(item_1.getText());
			        amount2.setText(item_2.getText());
			        amount3.setText(item_3.getText());
			        amount4.setText(item_4.getText());
			        amount5.setText(item_5.getText());
				} else {
					JOptionPane.showMessageDialog(null, "All fields must be a positive number!");
				}
			}
		});
		btnSave.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/save_btn.png")));
		btnSave.setBounds(728, 540, 125, 57);
		orderPanel.add(btnSave);
		
		btnDiscard = new JButton("");
		btnDiscard.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/discard_btn.png")));
		btnDiscard.setBounds(593, 540, 125, 57);
		orderPanel.add(btnDiscard);
		
		dec_1 = new JButton("");
		dec_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_1.getText());
				if (val <= 0) val = 0; else val--;
				item_1.setText(val.toString());
			}
		});
		dec_1.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/dec_btn.png")));
		dec_1.setBounds(693, 232, 36, 35);
		orderPanel.add(dec_1);
		
		dec_2 = new JButton("");
		dec_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_2.getText());
				if (val <= 0) val = 0; else val--;
				item_2.setText(val.toString());
			}
		});
		dec_2.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/dec_btn.png")));
		dec_2.setBounds(693, 281, 36, 35);
		orderPanel.add(dec_2);
		
		dec_3 = new JButton("");
		dec_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_3.getText());
				if (val <= 0) val = 0; else val--;
				item_3.setText(val.toString());
			}
		});
		dec_3.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/dec_btn.png")));
		dec_3.setBounds(693, 335, 36, 35);
		orderPanel.add(dec_3);
		
		dec_4 = new JButton("");
		dec_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_4.getText());
				if (val <= 0) val = 0; else val--;
				item_4.setText(val.toString());
			}
		});
		dec_4.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/dec_btn.png")));
		dec_4.setBounds(693, 386, 36, 35);
		orderPanel.add(dec_4);
		
		dec_5 = new JButton("");
		dec_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer val = Integer.parseInt(item_5.getText());
				if (val <= 0) val = 0; else val--;
				item_5.setText(val.toString());
			}
		});
		dec_5.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/dec_btn.png")));
		dec_5.setBounds(693, 440, 36, 35);
		orderPanel.add(dec_5);
		
		inc_1 = new JButton("");
		inc_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_1.getText());
				val++; item_1.setText(val.toString());
			}
		});
		inc_1.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/inc_btn.png")));
		inc_1.setBounds(799, 232, 36, 35);
		orderPanel.add(inc_1);
		
		inc_2 = new JButton("");
		inc_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_2.getText());
				val++; item_2.setText(val.toString());
			}
		});
		inc_2.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/inc_btn.png")));
		inc_2.setBounds(799, 281, 36, 35);
		orderPanel.add(inc_2);
		
		inc_3 = new JButton("");
		inc_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_3.getText());
				val++; item_3.setText(val.toString());
			}
		});
		inc_3.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/inc_btn.png")));
		inc_3.setBounds(799, 335, 36, 35);
		orderPanel.add(inc_3);
		
		inc_4 = new JButton("");
		inc_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_4.getText());
				val++; item_4.setText(val.toString());
			}
		});
		inc_4.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/inc_btn.png")));
		inc_4.setBounds(799, 386, 36, 35);
		orderPanel.add(inc_4);
		
		inc_5 = new JButton("");
		inc_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer val = Integer.parseInt(item_5.getText());
				val++; item_5.setText(val.toString());
			}
		});
		inc_5.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/inc_btn.png")));
		inc_5.setBounds(799, 440, 36, 35);
		orderPanel.add(inc_5);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(PageOrder.class.getResource("/img_pages/OrderPage.png")));
		border.setBounds(0, 0, 994, 621);
		orderPanel.add(border);
	}
	
	private void initConfirmPanel() {
		confirmPanel = new JPanel();
		confirmPanel.setLayout(null);
		
		amount1 = new JLabel();
		amount1.setText(item_1.getText());
		amount1.setHorizontalAlignment(SwingConstants.CENTER);
		amount1.setVerticalAlignment(SwingConstants.CENTER);
		amount1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount1.setBounds(738, 232, 53, 53);
		confirmPanel.add(amount1);
		
		amount2 = new JLabel();
		amount2.setText(item_2.getText());
		amount2.setHorizontalAlignment(SwingConstants.CENTER);
		amount2.setVerticalAlignment(SwingConstants.CENTER);
		amount2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount2.setBounds(738, 281, 53, 53);
		confirmPanel.add(amount2);
		
		amount3 = new JLabel();
		amount3.setText(item_3.getText());
		amount3.setHorizontalAlignment(SwingConstants.CENTER);
		amount3.setVerticalAlignment(SwingConstants.CENTER);
		amount3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount3.setBounds(738, 335, 53, 53);
		confirmPanel.add(amount3);
		
		amount4 = new JLabel();
		amount4.setText(item_4.getText());
		amount4.setHorizontalAlignment(SwingConstants.CENTER);
		amount4.setVerticalAlignment(SwingConstants.CENTER);
		amount4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount4.setBounds(738, 386, 53, 53);
		confirmPanel.add(amount4);
		
		amount5 = new JLabel();
		amount5.setText(item_5.getText());
		amount5.setHorizontalAlignment(SwingConstants.CENTER);
		amount5.setVerticalAlignment(SwingConstants.CENTER);
		amount5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		amount5.setBounds(738, 440, 53, 53);
		confirmPanel.add(amount5);
		
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

					JOptionPane.showMessageDialog(null, "Your order has been sent to the Head Office");
					frame.setVisible(false); //you can't see me!
					frame.dispose(); //Destroy the JFrame object
				}
			}
		});
		btnSave.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/proceed_btn.png")));
		btnSave.setBounds(728, 540, 125, 57);
		confirmPanel.add(btnSave);
		
		btnDiscard = new JButton("");
		btnDiscard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//change to confirm page
				CardLayout cl = (CardLayout)(cards.getLayout());
		        cl.show(cards, ORDERPANEL);
			}
		});
		btnDiscard.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/cancel_btn.png")));
		btnDiscard.setBounds(593, 540, 125, 57);
		confirmPanel.add(btnDiscard);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(PageOrder.class.getResource("/img_pages/ConfirmPage.png")));
		border.setBounds(0, 0, 994, 621);
		confirmPanel.add(border);
	}

	/**
	 * Create the application.
	 */
	public PageOrder() {
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
		
		dashboardButton = new JButton();
		dashboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close window or return to dashboard
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			}
		});
		dashboardButton.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/dashboard_btn.png")));
		dashboardButton.setBounds(825, 40, 125, 57);
		frame.getContentPane().add(dashboardButton);
		
		initOrderPanel();
		initConfirmPanel();
		
		cards.add(orderPanel, ORDERPANEL);
		cards.add(confirmPanel, CONFIRMPANEL);
		
		frame.add(cards, BorderLayout.CENTER);
		
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
