package dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import auth.PageAuth;
import db.DBController;
import estimation.EstimationPage;
import weather.InfoWeather;
import news.InfoNews;
import order.PageOrder;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.Icon;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.GridBagLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
public class DashboardSMPage {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardSMPage window = new DashboardSMPage();
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
	public DashboardSMPage() {
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
		
		//Change to estimation windows
		JButton btnEstimate = new JButton("");
		btnEstimate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEstimate.setOpaque(false);
		btnEstimate.setContentAreaFilled(false);
		btnEstimate.setBorderPainted(false);
		btnEstimate.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_btn/estimate_btn.png")));
		btnEstimate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EstimationPage window = new EstimationPage();
					window.frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEstimate.setBounds(419, 280, 126, 55);
		panel.add(btnEstimate);
		//-----------------------
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/tomorrow_forecast.png")));
		
		label.setBounds(194, 266, 117, 15);
		panel.add(label);
		
		/***** WEATHER STARTS HERE *****/
		/* TODAY */
		JSONObject today = InfoWeather.showWeatherInfo(InfoWeather.TODAY);
		//System.out.println(today);
		JLabel today_weather = new JLabel("");
		today_weather.setHorizontalAlignment(SwingConstants.CENTER);
		today_weather.setBounds(29, 244, 74, 74);
		
		JLabel today_desc = new JLabel("Sunny");
		today_desc.setForeground(new Color(112, 68, 160));
		today_desc.setFont(new Font("Tahoma", Font.BOLD, 16));
		today_desc.setBounds(113, 244, 56, 22);
		
		if (today.getString("text").contains("Cloudy")) {
			today_weather.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/cloudy_l.png")));
			today_desc.setText("Cloudy");
		} else if (today.getString("text").contains("Rain") || today.getString("text").contains("Shower")) {
			today_weather.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/rain_l.png")));
			today_desc.setText("Rain");
		} else if (today.getString("text").contains("Thunder")) {
			today_weather.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/thunderstorm_l.png")));
			today_desc.setFont(new Font("Tahoma", Font.BOLD, 12));
			today_desc.setText("Thunderstorm");
		} else {
			today_weather.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/sunny_l.png")));
			today_desc.setText("Sunny");
		}
		
		panel.add(today_weather);
		
		//Close program
		JButton btnPowerButton = new JButton("");
		btnPowerButton.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_btn/power_btn.png")));
		btnPowerButton.setOpaque(false);
		btnPowerButton.setContentAreaFilled(false);
		btnPowerButton.setBorderPainted(false);
		btnPowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnPowerButton.setBounds(468, 456, 48, 40);
		panel.add(btnPowerButton);
		
		panel.add(today_desc);
		
		JLabel today_temp = new JLabel(today.getString("temp"));
		today_temp.setForeground(new Color(112, 68, 160));
		today_temp.setFont(new Font("Tahoma", Font.BOLD, 16));
		today_temp.setBounds(113, 296, 36, 22);
		panel.add(today_temp);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM");
		JLabel today_date = new JLabel(format.format(new Date()));
		today_date.setHorizontalAlignment(SwingConstants.CENTER);
		today_date.setForeground(new Color(112, 68, 160));
		today_date.setFont(new Font("Tahoma", Font.PLAIN, 13));
		today_date.setBounds(39, 313, 56, 22);
		panel.add(today_date);
		
		JLabel lblOF = new JLabel("\u00B0 F");
		lblOF.setForeground(new Color(112, 68, 160));
		lblOF.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOF.setBounds(139, 296, 36, 22);
		panel.add(lblOF);
		
		/* FORECAST */
		JSONObject tomorrow = InfoWeather.showWeatherInfo(InfoWeather.TOMORROW);
		JSONObject next_tomorrow = InfoWeather.showWeatherInfo(InfoWeather.NEXT_OF_TOMORROW);
		JSONObject next2_tomorrow = InfoWeather.showWeatherInfo(InfoWeather.NEXT_NEXT_OF_TOMORROW);
		
		JLabel weather_1 = new JLabel("");
		if (tomorrow.getString("text").contains("Cloudy")) {
			weather_1.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/cloudy.png")));
		} else if (tomorrow.getString("text").contains("Rain") || tomorrow.getString("text").contains("Shower")) {
			weather_1.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/rain.png")));
		} else if (tomorrow.getString("text").contains("Thunder")) {
			weather_1.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/thunderstorm.png")));
		} else {
			weather_1.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/sunny.png")));
		}
		weather_1.setBounds(194, 280, 39, 40);
		panel.add(weather_1);
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		
		JLabel date_1 = new JLabel(format.format(dt));
		date_1.setHorizontalAlignment(SwingConstants.CENTER);
		date_1.setForeground(new Color(112, 68, 160));
		date_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		date_1.setBounds(195, 315, 35, 22);
		panel.add(date_1);
		
		JLabel weather_2 = new JLabel("");
		if (next_tomorrow.getString("text").contains("Cloudy")) {
			weather_2.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/cloudy.png")));
		} else if (next_tomorrow.getString("text").contains("Rain") || next_tomorrow.getString("text").contains("Shower")) {
			weather_2.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/rain.png")));
		} else if (next_tomorrow.getString("text").contains("Thunder")) {
			weather_2.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/thunderstorm.png")));
		} else {
			weather_2.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/sunny.png")));
		}
		weather_2.setBounds(237, 280, 39, 40);
		panel.add(weather_2);
		
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		
		JLabel date_2 = new JLabel(format.format(dt));
		date_2.setHorizontalAlignment(SwingConstants.CENTER);
		date_2.setForeground(new Color(112, 68, 160));
		date_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		date_2.setBounds(238, 315, 35, 22);
		panel.add(date_2);
		
		JLabel weather_3 = new JLabel("");
		if (next2_tomorrow.getString("text").contains("Cloudy")) {
			weather_3.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/cloudy.png")));
		} else if (next2_tomorrow.getString("text").contains("Rain") || next2_tomorrow.getString("text").contains("Shower")) {
			weather_3.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/rain.png")));
		} else if (next2_tomorrow.getString("text").contains("Thunder")) {
			weather_3.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/thunderstorm.png")));
		} else {
			weather_3.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_weather/sunny.png")));
		}
		weather_3.setBounds(275, 280, 39, 40);
		panel.add(weather_3);
		
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		
		JLabel date_3 = new JLabel(format.format(dt));
		date_3.setHorizontalAlignment(SwingConstants.CENTER);
		date_3.setForeground(new Color(112, 68, 160));
		date_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		date_3.setBounds(278, 315, 35, 22);
		panel.add(date_3);
		
		JLabel border = new JLabel("");
		border.setBackground(SystemColor.menu);
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_pages/DashboardPage.png")));
		border.setBounds(-10, 0, 994, 621);
		panel.add(border);
		
		//---------------NEWS---------------------------------------
		
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setBounds(673, 196, 241, 200);
				panel.add(scrollPane);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBackground(Color.WHITE);
				scrollPane.setViewportView(panel_2);
				GridBagLayout gbl_panel_2 = new GridBagLayout();
				gbl_panel_2.columnWidths = new int[]{0};
				gbl_panel_2.rowHeights = new int[]{0};
				gbl_panel_2.columnWeights = new double[]{0};
				gbl_panel_2.rowWeights = new double[]{0};
				panel_2.setLayout(gbl_panel_2);
				
						
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.anchor = GridBagConstraints.NORTHWEST;
				panel_1.setBounds(683, 198, 231, 198);
				panel.add(panel_1,gbc);
				
				JLabel lblNewLabel = new JLabel("You've done well yesterday.\r\n\r\n");
				lblNewLabel.setForeground(new Color(139, 0, 139));
				lblNewLabel.setBounds(156, 427, 144, 22);
				panel.add(lblNewLabel);
						
				ResultSet resSum = null;
				
				int valMon = 0;

				Connection con = DBController.connectDatabase();
				Statement stmt = null;
				try {
					stmt = con.createStatement();
					String querySum ="SELECT sum(jumlah_penjualan) AS jml FROM penjualan_produk,data_penjualan WHERE penjualan_produk.id_penjualan = data_penjualan.id_penjualan AND DATEDIFF(NOW(),data_penjualan.tanggal_penjualan)=1";
					resSum = stmt.executeQuery(querySum);
					try {
							while (resSum.next()) valMon = resSum.getInt("jml");
					} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				JLabel lblRp = new JLabel("Rp. "+(valMon*40000));
				lblRp.setForeground(new Color(34, 139, 34));
				lblRp.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
				lblRp.setBounds(167, 471, 144, 34);
				panel.add(lblRp);
				
				JLabel lblKeepItUp = new JLabel("Keep it up!");
				lblKeepItUp.setForeground(new Color(139, 0, 139));
				lblKeepItUp.setBounds(159, 445, 88, 14);
				panel.add(lblKeepItUp);
				
				//Change to order's windows
				JButton btnPlaceAnOrder = new JButton("");
				btnPlaceAnOrder.setOpaque(false);
				btnPlaceAnOrder.setContentAreaFilled(false);
				btnPlaceAnOrder.setBorderPainted(false);
				btnPlaceAnOrder.setBackground(UIManager.getColor("Panel.background"));
				btnPlaceAnOrder.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnPlaceAnOrder.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_btn/order_btn.png")));
				btnPlaceAnOrder.setBounds(632, 431, 176, 81);
				btnPlaceAnOrder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							PageOrder window = new PageOrder();
							window.frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panel.add(btnPlaceAnOrder);
				
				//Change to login windows (log out)
				JButton btnLogout = new JButton("");
				btnLogout.setFont(new Font("Tahoma", Font.BOLD, 15));
				btnLogout.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_btn/logout_btn.png")));
				btnLogout.setBounds(827, 47, 117, 47);
				btnLogout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							PageAuth window = new PageAuth();
							window.frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panel.add(btnLogout);
							
				ResultSet resNews = InfoNews.showNewsInfo();
				ArrayList<JButton> arrButton = new ArrayList<JButton>();
				ArrayList<JTextArea> arrDesc=new ArrayList<JTextArea>();
				ArrayList<JTextArea> arrTitle = new ArrayList<JTextArea>();;
				
				int i=0;
				try {
					while (resNews.next()){
						JButton btn = new JButton("Read");
						btn.setIcon(new ImageIcon(DashboardSMPage.class.getResource("/img_btn/opnWeb_btn.png")));
						btn.setPreferredSize(new Dimension(20,20));
						btn.setName(resNews.getString("url_berita"));
						btn.addActionListener( new ActionListener()
						{
						    public void actionPerformed(ActionEvent e)
						    {
						    	String url="http://google.com";
						    	try {
						    		JButton o = (JButton)e.getSource();
						    		url = o.getName();
						    		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
						    	}
						    	catch (java.io.IOException e1) {
						    	     System.out.println(e1.getMessage());
						    	}
						    }
						});
									
						JTextArea lblDesc = new JTextArea(resNews.getString("deskripsi"));
						lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 10));
						lblDesc.setLineWrap(true);
						lblDesc.setWrapStyleWord(true);
						
						JTextArea lblTitle = new JTextArea(resNews.getString("kategori"));
						lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
						lblTitle.setLineWrap(true);
						lblTitle.setWrapStyleWord(true);
					        
						arrButton.add(btn);
						arrDesc.add(lblDesc);
						arrTitle.add(lblTitle);		
						
						gbc.fill = GridBagConstraints.EAST;
						gbc.gridx = 0;
					    gbc.gridy = i;
					    gbc.ipady = 10;
					    gbc.insets = new Insets(10,10,1,5);//int top, int left, int bottom, int right)
					    panel_2.add(btn,gbc); 
					    
					    gbc.insets = new Insets(11,5,1,10);
					    gbc.fill = GridBagConstraints.HORIZONTAL;
					    gbc.gridx = 1;
					    gbc.gridy = i; 
					    panel_2.add(lblTitle,gbc);  

					    gbc.insets = new Insets(5,10,1,10);
					    gbc.gridx = 0;
					    gbc.gridy = 1+i; 
					    gbc.gridwidth = 3;
					    gbc.weightx = 1;
					    gbc.weighty = 0;
					    gbc.fill = GridBagConstraints.HORIZONTAL;
					    //gbc.gridwidth = 2;
					    panel_2.add(lblDesc,gbc);
					    		    
					    i+=2;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		
		DBController.closeDatabase();
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
