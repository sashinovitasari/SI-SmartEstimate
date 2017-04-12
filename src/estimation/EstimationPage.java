package estimation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import dashboard.DashboardCashierPage;
import dashboard.DashboardSMPage;
import order.PageOrder;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class EstimationPage {

	public JFrame frame;
	private JButton dashboardButton;

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
		
		dashboardButton = new JButton();
		dashboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close window or return to dashboard
				try {
					DashboardSMPage window = new DashboardSMPage();
					window.frame.setVisible(true);
					frame.setVisible(false); //you can't see me!
					frame.dispose(); //Destroy the JFrame object
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			}
		});
		dashboardButton.setIcon(new ImageIcon(PageOrder.class.getResource("/img_btn/dashboard_btn.png")));
		dashboardButton.setBounds(825, 40, 125, 57);
		frame.getContentPane().add(dashboardButton);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		ActionListener actL = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window win = SwingUtilities.getWindowAncestor((AbstractButton)arg0.getSource());
			    JDialog dialog = new JDialog(win, "", ModalityType.APPLICATION_MODAL);
			    
				SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
			        @Override
			        protected Void doInBackground() throws Exception {
			        	for (int i = 1; i <= 5; i++) {
							InfoEstimation.fetchData(i);
							ResultController.result[i] = Math.ceil(ProcEstimation.calculateEstimation());
						}
			        	Date d = new Date();
						Calendar cal = Calendar.getInstance();
			            cal.setTime(d);
			            cal.add(Calendar.DATE, 1);
			            d = cal.getTime();
			            
			            if (!InfoEstimation.alreadyInDatabase(d)) {
				        	InfoEstimation.addToDatabase(0, d, (int) ResultController.result[1], 
				        			(int) ResultController.result[2], (int) ResultController.result[3], 
				        			(int) ResultController.result[4], (int) ResultController.result[5]);
			            }
			        	
						/* Invoke Result Page */
						ResultPage.launch();
						frame.setVisible(false);
						frame.dispose();
						return null;
			        }
			    };
			    
			    mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

			        @Override
			        public void propertyChange(PropertyChangeEvent evt) {
			            if (evt.getPropertyName().equals("state")) {
			               if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
			                  dialog.dispose();
			               }
			            }
			        }
			    });
			    mySwingWorker.execute();
				
				JProgressBar progressBar = new JProgressBar();
			    progressBar.setIndeterminate(true);
			    JPanel panel = new JPanel(new BorderLayout());
			    panel.add(progressBar, BorderLayout.CENTER);
			    panel.add(new JLabel("Please wait......."), BorderLayout.PAGE_START);
			    
			    dialog.getContentPane().add(panel);
			    dialog.pack();
			    dialog.setLocationRelativeTo(win);
			    dialog.setVisible(true);
			}
		};
		
		JButton btnStart = new JButton("");
		btnStart.addActionListener(actL);
		btnStart.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_btn/start_btn.png")));
		btnStart.setBounds(743, 219, 125, 57);
		panel.add(btnStart);
		
		JButton btnStart2 = new JButton("");
		btnStart2.addActionListener(actL);
		btnStart2.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_btn/start_btn.png")));
		btnStart2.setBounds(743, 500, 125, 57);
		panel.add(btnStart2);
		JDateChooser startDate = new JDateChooser();
		startDate.setBounds(785, 402, 125, 34);
		panel.add(startDate);
		
		JDateChooser endDate = new JDateChooser();
		endDate.setBounds(785, 447, 125, 34);
		panel.add(endDate);

		double averageAcc = InfoEstimation.calculateAverageAccuracy();
		String percentage = String.format("%.2f", averageAcc) + " %";
		JLabel avgAcc = new JLabel(percentage);
		avgAcc.setHorizontalAlignment(SwingConstants.CENTER);
		avgAcc.setForeground(new Color(112, 68, 160));
		avgAcc.setFont(new Font("Tahoma", Font.BOLD, 22));
		avgAcc.setBounds(77, 500, 245, 57);
		panel.add(avgAcc);
		
		JLabel border = new JLabel("");
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_pages/EstimationPage.png")));
		border.setBounds(0, 0, 994, 621);
		panel.add(border);
		
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
