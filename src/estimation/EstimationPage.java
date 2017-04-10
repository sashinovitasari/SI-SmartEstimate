package estimation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class EstimationPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstimationPage window = new EstimationPage();
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
	public EstimationPage() {
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
		
		JButton btnStart = new JButton("");
		btnStart.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_btn/start_btn.png")));
		btnStart.setBounds(743, 219, 125, 57);
		panel.add(btnStart);
		
		JButton btnStart2 = new JButton("");
		btnStart2.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_btn/start_btn.png")));
		btnStart2.setBounds(743, 500, 125, 57);
		panel.add(btnStart2);
		JDateChooser startDate = new JDateChooser();
		startDate.setBounds(785, 402, 125, 34);
		panel.add(startDate);
		
		JDateChooser endDate = new JDateChooser();
		endDate.setBounds(785, 447, 125, 34);
		panel.add(endDate);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_pages/EstimationPage.png")));
		border.setBounds(0, 0, 994, 621);
		panel.add(border);
		
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
