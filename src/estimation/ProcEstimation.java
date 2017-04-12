package estimation;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import weather.InfoWeather;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.supervised.attribute.Discretize;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class ProcEstimation {
	public static Instances data, dataUnlabeled;
	public static Classifier neuralModel;
	
	public static Classifier generateEstimationModel() {
		MultilayerPerceptron cls = new MultilayerPerceptron();
		cls.setLearningRate(0.1);
		cls.setMomentum(0.2);
		cls.setTrainingTime(2000);
		cls.setHiddenLayers("3");
		
		try {
			cls.buildClassifier(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cls;
	}
	
	public static double calculateEstimation() {
		double result = 0.0;
		try {
			Date d = new Date();
			Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DATE, 1);
            d = cal.getTime();
            
			String day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(d);
			int date = cal.get(Calendar.DATE);
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);
			String weather = InfoWeather.showWeatherInfo(InfoWeather.TOMORROW).getString("text");
			int sales = 0;
			
			Instance inst = ProcEstimation.createInstance(day, date, month, year, weather, sales);
			dataUnlabeled.add(inst);

			result = neuralModel.classifyInstance(dataUnlabeled.firstInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Instance createInstance(String day, int date, int month, 
			int year, String weather, int sales) {
		double[] instanceValue = new double[data.numAttributes()];
		
		instanceValue[0] = data.attribute(0).indexOfValue(day);
		instanceValue[1] = (double) date;
		instanceValue[2] = (double) month;
		instanceValue[3] = (double) year;
		instanceValue[4] = data.attribute(4).indexOfValue(weather);
		instanceValue[5] = (double) sales;
		
		Instance inst = new DenseInstance(1.0, instanceValue);
		return inst;
	}
	
	public static void initializeInstances() {
		ArrayList<String> day_label = new ArrayList<>();
		day_label.add("Sunday"); day_label.add("Monday");
		day_label.add("Tuesday"); day_label.add("Wednesday");
		day_label.add("Thursday"); day_label.add("Friday");
		day_label.add("Saturday");
		
		ArrayList<String> weather_label = new ArrayList<>();
		weather_label.add("Sunny");
		weather_label.add("Rain");
		weather_label.add("Cloudy");
		weather_label.add("Thunderstorm");
		
		Attribute day = new Attribute("day", day_label);
		Attribute date = new Attribute("date");
		Attribute month = new Attribute("month");
		Attribute year = new Attribute("year");
		Attribute weather = new Attribute("weather", weather_label);
		Attribute sales = new Attribute("sales");
		
		ArrayList<Attribute> attributes = new ArrayList<>();
		attributes.add(day); attributes.add(date);
		attributes.add(month); attributes.add(year);
		attributes.add(weather); attributes.add(sales);
		
		data = new Instances("sales", attributes, 0);
		data.setClassIndex(data.numAttributes() - 1);
		
		dataUnlabeled = new Instances("TestInstances", attributes, 0);
		dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);
		
	}
	

	public static void main (String args[]) {
		initializeInstances();
		data.add(createInstance("Sunday", 26, 3, 2017, "Sunny", 75));
		data.add(createInstance("Monday", 27, 3, 2017, "Rain", 98));
		data.add(createInstance("Tuesday", 28, 3, 2017, "Cloudy", 36));
		data.add(createInstance("Wednesday", 29, 3, 2017, "Thunderstorm", 10));
		data.add(createInstance("Friday", 30, 3, 2017, "Sunny", 100));
		data.add(createInstance("Saturday", 31, 3, 2017, "Rain", 75));
		
		neuralModel = generateEstimationModel();
		
		try {     
			Instance inst = createInstance("Sunday", 11, 4, 2017, "Cloudy", 0);
			dataUnlabeled.add(inst);
			
			double classif = neuralModel.classifyInstance(dataUnlabeled.firstInstance());
			System.out.println(classif);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
