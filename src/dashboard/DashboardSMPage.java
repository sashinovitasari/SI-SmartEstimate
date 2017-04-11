package dashboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.json.JSONObject;

import estimation.EstimationPage;
import weather.InfoWeather;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DashboardSMPage {

	private JFrame frame;

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
		border.setHorizontalAlignment(SwingConstants.CENTER);
		border.setIcon(new ImageIcon(EstimationPage.class.getResource("/img_pages/DashboardPage.png")));
		border.setBounds(-10, 0, 994, 621);
		panel.add(border);
		frame.setBounds(0, 0, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
