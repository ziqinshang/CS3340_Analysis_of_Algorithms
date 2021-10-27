package project_Viewer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



/**
 * According to the data, output after drawing on the baseboard.
 * 
 * 
 * @author Zhen Yang 250947763
 * @author Yifei Zhang 251109144
 * @author Yumeng Chen 251055437
 *
 */

public class Viewers extends JFrame {

	private static final long serialVersionUID = 1L;
	

	
	/**
	 *  Write message report on the baseboard and output by using the input data.
	 * @param west	The baseboard.
	 * @param chosenStartYear	The starting year.
	 * @param chosenEndYear	The ending year.
	 * @param chosenAnalysis	Analysis method.
	 * @param ave1	The data needed for writing	.
	 * @param ave2	The data needed for writing.
	 * @param ave3	The data needed for writing.
	 */	
	
	public static void createReport(JPanel west, int chosenStartYear, int chosenEndYear, int chosenAnalysis,double[] ave1,double[] ave2,double[] ave3) {
		
		//Limit the size and the style of the image.
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		

		String reportMessage;
		int methods = chosenAnalysis;
		int startyear =chosenStartYear;
		int endyear = chosenEndYear;
		String name="";
		
		double[] ave_1=ave1;
		double[] ave_2=ave2;
		double[] ave_3=ave3;
		
		if(methods == 1){name = "CO2 emissions vs Energy use vs PM2.5 air poluition";}
		if(methods == 2){name = "PM2.5 air polution vs Forest area";}
		if(methods == 3){name = "CO2 emissions vs GDP per capita";}
		if(methods == 4){name = "Forest area";}
		if(methods == 5){name = "Government expenditure on education";}
		if(methods == 6){name = "Hospital beds vs Current health expenditure";}
		if(methods == 7){name = "Current health expenditure per capita vs Mortality rate";}
		if(methods == 8){name = "Government expenditure on education vs Current health expenditure";}
		
		
		reportMessage = name + "\n" + "==============================\n";
		
		//CO2 emissions (metric tons per capita) vs Energy use (kg of oil equivalent per capita) vs PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)
		if(methods == 1){
			for(int i=0; i<=endyear-startyear; i++) {
				reportMessage = reportMessage + "Year " + (startyear+i) +": \n" + "\tCO2 emissions/Metric tons per capita => " + Math.round(ave_1[i]*100)/100 +"\n" + "\tEnergy use/Kg of oil equivalent per capita => " + Math.round(ave_2[i]*100)/100 +"\n"+  "\tPM2.5/Micrograms per cubic meter => " + Math.round(ave_3[i]*100)/100 +"\n"+ "\n";
			}
		}
		
		//PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) vs Forest area (% of land area)
		if(methods == 2) {
			for(int i=0; i<=endyear-startyear; i++) {
				reportMessage = reportMessage + "Year " + (startyear+i) +": \n" + "\tPM2.5/Micrograms per cubic meter => " + Math.round(ave_1[i]*100)/100 +"\n" + "\tForest area/% of land area => " + Math.round(ave_2[i]*100)/100 +"\n"+ "\n";
			}	
		}
		
		//Ratio of CO2 emissions (metric tons per capita) and GDP per capita (current US$)
		if(methods == 3){
			for(int i=0; i<=endyear-startyear; i++) {
				reportMessage = reportMessage + "Year " + (startyear+i) +": \n" + "\tCO2 emissions/Metric tons per capita => " + Math.round(ave_1[i]*100)/100 +"\n" + "\tGDP per capita/Current US$ => " + Math.round(ave_2[i]*100)/100 +"\n"+ "\n";
			}	
		}
		
		//Average Forest area (% of land area) for the selected years
		if(methods == 4){
			for(int i=0; i<=endyear-startyear; i++) {
				reportMessage = reportMessage + "Year " + (startyear+i) +": \n" + "\tForest area/% of land area => " + Math.round(ave_1[i]*100)/100 +"\n" + "\n";
			}	
		}
		
		//Average of Government expenditure on education, total (% of GDP) for the selected years
		if(methods == 5){
			for(int i=0; i<=endyear-startyear; i++) {
				reportMessage = reportMessage + "Year " + (startyear+i) +": \n" + "\tGovernment expenditure on education/% of GDP => " + Math.round(ave_1[i]*100)/100 +"\n" + "\n";
			}	
		}
		
		//Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per 1,000 people) 
		if(methods == 6) {
			for(int i=0; i<=endyear-startyear; i++) {
				reportMessage = reportMessage + "Year " + (startyear+i) +": \n" + "\tHospital beds per 1000 people => " + Math.round(ave_1[i]*100)/100 +"\n" + "\tCurrent health expenditure per 1000 people => " + Math.round(ave_2[i]*100)/100 +"\n"+ "\n";
			}	
		}
		
		//Current health expenditure per capita (current US$) vs Mortality rate, infant (per 1,000 live births) 
		if(methods == 7) {
			for(int i=0; i<=endyear-startyear; i++) {
				reportMessage = reportMessage + "Year " + (startyear+i) +": \n" + "\tCurrent health expenditure per capita/Current US$=> " + Math.round(ave_1[i]*100)/100 +"\n" + "\tMortality rate per 1000 live births => " + Math.round(ave_2[i]*1000) +"\n"+ "\n";
			}	
		}
		
		//Ratio of Government expenditure on education, total (% of GDP) vs Current health expenditure (% of GDP)
		if(methods == 8) {
			for(int i=0; i<=endyear-startyear; i++) {
				reportMessage = reportMessage + "Year " + (startyear+i) +": \n" + "\tGovernment expenditure on education/% of GDP => " + Math.round(ave_1[i]*100)/100 +"\n" + "\tCurrent health expenditure/% of GDP => " + Math.round(ave_2[i]*100)/100 +"\n"+ "\n";
			}	
		}
		
		//Write on the baseboard
		report.setText(reportMessage );
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
		
		
	}

	/**
	 * Drawing the scatter plot on the baseboard by using the input data. 
	 * 
	 * @param west	The baseboard.
	 * @param chosenStartYear	The starting year.
	 * @param chosenEndYear	The ending year.
	 * @param chosenAnalysis	Analysis method.
	 * @param ave1	The data needed for drawing	.
	 * @param ave2	The data needed for drawing.
	 * @param ave3	The data needed for drawing.
	 */
	public static void createScatter(JPanel west, int chosenStartYear, int chosenEndYear, int chosenAnalysis,double[] ave1,double[] ave2,double[] ave3) {
		

		int methods = chosenAnalysis;
		int startyear =chosenStartYear;
		int endyear = chosenEndYear;	

		double[] ave_1=ave1;
		double[] ave_2=ave2;
		double[] ave_3=ave3;
		
		
		//CO2 emissions (metric tons per capita) vs Energy use (kg of oil equivalent per capita) vs PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) 
		if(methods == 1){
			TimeSeries series1 = new TimeSeries("CO2 emissions/metric tons percapita");
			TimeSeries series2 = new TimeSeries("Energy use/kg per capita");
			TimeSeries series3 = new TimeSeries("PM2.5 air poluition/mg per cubic meter");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series3.add(new Year(startyear+i), ave_3[i]);
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series3);
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis("kg"));

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			
			//Limit the size and the style of the image
			JFreeChart scatterChart = new JFreeChart("CO2 emissions vs Energy use vs PM2.5 air poluition",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) vs Forest area (% of land area) 
		if(methods == 2){
			TimeSeries series1 = new TimeSeries("PM2.5 air polution/mg per cubic meter");
			TimeSeries series2 = new TimeSeries("Forest area/% of land area");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("mg"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis("% of land area"));

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			
			//Limit the size and the style of the image
			JFreeChart scatterChart = new JFreeChart("PM2.5 air polution vs Forest area",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		

		//Ratio of CO2 emissions (metric tons per capita) and GDP per capita (current US$)
		if(methods == 3){
			TimeSeries series1 = new TimeSeries("CO2 emissions/metric tons per capita");
			TimeSeries series2 = new TimeSeries("GDP per capita/US$");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("metric tons"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis("US$"));

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			
			//Limit the size and the style of the image
			JFreeChart scatterChart = new JFreeChart("CO2 emissions vs GDP per capita",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//Average Forest area (% of land area) for the selected years
		if(methods == 4){
			TimeSeries series1 = new TimeSeries("Forest area/% of land area");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("% of land area"));
			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			
			//Limit the size and the style of the image
			JFreeChart scatterChart = new JFreeChart("Forest area",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//Average of Government expenditure on education, total (% of GDP) for the selected years
		if(methods == 5){
			TimeSeries series1 = new TimeSeries("Government expenditure on education/% of GDP");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("% of GDP"));
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			
			//Limit the size and the style of the image
			JFreeChart scatterChart = new JFreeChart("Government expenditure on education",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per 1,000 people) 
		if(methods == 6){//6
			TimeSeries series1 = new TimeSeries("Hospital beds/per 1000 people");
			TimeSeries series2 = new TimeSeries("Current health expenditure/per 1000 people");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]*1000);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series2);

			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("per 1000 people"));
			

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			
			//Limit the size and the style of the image
			JFreeChart scatterChart = new JFreeChart("Hospital beds vs Current health expenditure",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//Current health expenditure per capita (current US$) vs Mortality rate, infant (per 1,000 live births)
		if(methods == 7){
			TimeSeries series1 = new TimeSeries("Current health expenditure per capita/US$");
			TimeSeries series2 = new TimeSeries("Mortality rate/per 1000 live births");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("US$"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis("per 1000 live births"));

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			
			//Limit the size and the style of the image
			JFreeChart scatterChart = new JFreeChart("Current health expenditure per capita vs Mortality rate",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//Ratio of Government expenditure on education, total (% of GDP) vs Current health expenditure (% of GDP).
		if(methods == 8){
			TimeSeries series1 = new TimeSeries("Government expenditure on education/% of GDP");
			TimeSeries series2 = new TimeSeries("Current health expenditure/% of GDP");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series2);

			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("% of GDP"));
			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart scatterChart = new JFreeChart("Government expenditure on education vs Current health expenditure",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		

	}

	
	/**
	 * Drawing the pie chart on the baseboard by using the input data, science only method 4 and 5 can do the pie chart. 
	 * 
	 * @param west	The baseboard.
	 * @param chosenStartYear	The starting year.
	 * @param chosenEndYear	The ending year.
	 * @param chosenAnalysis	Analysis method.
	 * @param ave1	The data needed for drawing	.
	 */
	
	public static void createPie(JPanel west, int chosenStartYear, int chosenEndYear, int chosenAnalysis,double[] ave1) {
		

		int methods = chosenAnalysis;
		int startyear =chosenStartYear;
		int endyear = chosenEndYear;	
		double[] ave_1=ave1;

		

		//Average Forest area (% of land area) for the selected years 
		if(methods == 4){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for(int i=0; i<=endyear-startyear; i++) {
				dataset.addValue(ave_1[i]/100, "forest area", String.valueOf(startyear+i));
				dataset.addValue(1-ave_1[i]/100, "others", String.valueOf(startyear+i));
			}	
			
			//Limit the size and the style of the image
			JFreeChart pieChart = ChartFactory.createMultiplePieChart("Forest area: years", dataset,
					TableOrder.BY_COLUMN, true, true, false);
			
			ChartPanel chartPanel = new ChartPanel(pieChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//Average of Government expenditure on education, total (% of GDP) for the selected years
		if(methods == 5){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for(int i=0; i<=endyear-startyear; i++) {
				dataset.addValue(ave_1[i]/100, "Education", String.valueOf(startyear+i));
				dataset.addValue(1-ave_1[i]/100, "others", String.valueOf(startyear+i));
			}	
			
			//Limit the size and the style of the image
			JFreeChart pieChart = ChartFactory.createMultiplePieChart("Government expenditure on education: years", dataset,
					TableOrder.BY_COLUMN, true, true, false);
			
			ChartPanel chartPanel = new ChartPanel(pieChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
	}

	
	/**
	 * Drawing the bar chart on the baseboard by using the input data. 
	 * 
	 * @param west	The baseboard.
	 * @param chosenStartYear	The starting year.
	 * @param chosenEndYear	The ending year.
	 * @param chosenAnalysis	Analysis method.
	 * @param ave1	The data needed for drawing	.
	 * @param ave2	The data needed for drawing.
	 * @param ave3	The data needed for drawing.
	 */
	public static void createBar(JPanel west, int chosenStartYear, int chosenEndYear, int chosenAnalysis,double[] ave1,double[] ave2,double[] ave3) {


		int methods = chosenAnalysis;
		int startyear =chosenStartYear;
		int endyear = chosenEndYear;	

		double[] ave_1=ave1;
		double[] ave_2=ave2;
		double[] ave_3=ave3;
		
		
		//CO2 emissions (metric tons per capita) vs Energy use (kg of oil equivalent per capita) vs PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) 
		if(methods == 1){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

			for(int i=0; i<=endyear-startyear; i++) {
				dataset.setValue(ave_1[i], "CO2 emissions/metric tons", String.valueOf(startyear+i));
				dataset2.setValue(ave_2[i], "Energy use/kg", String.valueOf(startyear+i));
				dataset.setValue(ave_3[i], "PM2.5 air poluition/mg", String.valueOf(startyear+i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();
			
			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis("kg"));
			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart barChart = new JFreeChart("CO2 emissions vs Energy use vs PM2.5 air poluition",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) vs Forest area (% of land area) 
		if(methods == 2){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

			for(int i=0; i<=endyear-startyear; i++) {
				dataset.setValue(ave_1[i], "PM2.5 air polution/metric tons", String.valueOf(startyear+i));
				dataset2.setValue(ave_2[i], "Forest area/% of GDP", String.valueOf(startyear+i));
				dataset.setValue(0, " ", String.valueOf(startyear+i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();
			
			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("mg"));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis("% of land area"));
			

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart barChart = new JFreeChart("PM2.5 air polution vs Forest area",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//Ratio of CO2 emissions (metric tons per capita) and GDP per capita (current US$)
		if(methods == 3){//3
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

			for(int i=0; i<=endyear-startyear; i++) {
				dataset.setValue(ave_1[i], "CO2 emissions/metric tons", String.valueOf(startyear+i));
				dataset2.setValue(ave_2[i], "GDP per capita/US$", String.valueOf(startyear+i));
				dataset.setValue(0, " ", String.valueOf(startyear+i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();
			
			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("metric tons"));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis("US$"));
			

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			
			//Limit the size and the style of the image
			JFreeChart barChart = new JFreeChart("CO2 emissions vs GDP per capita",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//Average Forest area (% of land area) for the selected years 
		if(methods == 4){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			for(int i=0; i<=endyear-startyear; i++) {
				dataset.setValue(ave_1[i], "Forest area/% of land area", String.valueOf(startyear+i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			
			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("% of land area"));

			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			
			//Limit the size and the style of the image
			JFreeChart barChart = new JFreeChart("Forest area",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//Average of Government expenditure on education, total (% of GDP) for the selected years
		if(methods == 5){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			for(int i=0; i<=endyear-startyear; i++) {
				dataset.setValue(ave_1[i], "Government expenditure on education/% of GDP", String.valueOf(startyear+i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			
			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("% of GDP"));

			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart barChart = new JFreeChart("Government expenditure on education",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per 1,000 people) 
		if(methods == 6){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

			for(int i=0; i<=endyear-startyear; i++) {
				dataset.setValue(ave_2[i]*1000, "Current health expenditure", String.valueOf(startyear+i));
				dataset2.setValue(ave_1[i], "Hospital beds", String.valueOf(startyear+i));
				dataset.setValue(0, " ", String.valueOf(startyear+i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("Health exp per 1000 people"));
			

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis("Hospital beds per 1000 people"));
			
			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 
			
			//Limit the size and the style of the image
			JFreeChart barChart = new JFreeChart("Hospital beds vs Current health expenditure",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//Current health expenditure per capita (current US$) vs Mortality rate, infant (per 1,000 live births)
		if(methods == 7){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

			for(int i=0; i<=endyear-startyear; i++) {
				dataset.setValue(ave_1[i], "Current health expenditure per capita/US$", String.valueOf(startyear+i));
				dataset2.setValue(ave_2[i], "Mortality rate/per 1000 live births", String.valueOf(startyear+i));
				dataset.setValue(0, " ", String.valueOf(startyear+i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();
			BarRenderer barrenderer2 = new BarRenderer();
			
			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("US$"));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis("per 1000 live births"));
			

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart barChart = new JFreeChart("Current health expenditure per capita vs Mortality rate",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//Ratio of Government expenditure on education, total (% of GDP) vs Current health expenditure (% of GDP)
		if(methods == 8){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			for(int i=0; i<=endyear-startyear; i++) {
				dataset.setValue(ave_1[i], "Government expenditure on education/% of GDP", String.valueOf(startyear+i));
				dataset.setValue(ave_2[i], "Current health expenditure/% of GDP", String.valueOf(startyear+i));
			}
			
			CategoryPlot plot = new CategoryPlot();
			BarRenderer barrenderer1 = new BarRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("% of GDP"));
			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart barChart = new JFreeChart("Government expenditure on education vs Current health expenditure",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(barChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
			
	}

	/**
	 * 
	 * Drawing the line plot on the baseboard by using the input data. 
	 * 
	 * @param west	The baseboard.
	 * @param chosenStartYear	The starting year.
	 * @param chosenEndYear	The ending year.
	 * @param chosenAnalysis	Analysis method.
	 * @param ave1	The data needed for drawing	.
	 * @param ave2	The data needed for drawing.
	 * @param ave3	The data needed for drawing.
	 */
	public static void createLine(JPanel west, int chosenStartYear, int chosenEndYear, int chosenAnalysis,double[] ave1,double[] ave2,double[] ave3) {
		

		int methods = chosenAnalysis;
		int startyear =chosenStartYear;
		int endyear = chosenEndYear;	

		double[] ave_1=ave1;
		double[] ave_2=ave2;
		double[] ave_3=ave3;
		
		
	
		//CO2 emissions (metric tons per capita) vs Energy use (kg of oil equivalent per capita) vs PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)
		if(methods == 1){//1
			XYSeries series1 = new XYSeries("CO2 emissions/metric tons per capita");
			XYSeries series2 = new XYSeries("Energy use/kg per capita");
			XYSeries series3 = new XYSeries("PM2.5 air poluition/mg per cubic meter");
			
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(startyear+i, ave_1[i]);
				series2.add(startyear+i, ave_2[i]);
				series3.add(startyear+i, ave_3[i]);
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			XYSeriesCollection dataset2 = new XYSeriesCollection();
			dataset2.addSeries(series2);
			XYSeriesCollection dataset3 = new XYSeriesCollection();
			dataset3.addSeries(series3);

			JFreeChart chart = ChartFactory.createXYLineChart("CO2 emissions vs Energy use vs PM2.5 air poluition", "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
			XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();
			
			renderer.setSeriesPaint(1, Color.GREEN);
			renderer.setSeriesStroke(1, new BasicStroke(1.0f));
			renderer2.setSeriesPaint(0, Color.GREEN);
			renderer2.setSeriesStroke(0, new BasicStroke(2.0f));
			renderer3.setSeriesPaint(2, Color.BLUE);
			renderer3.setSeriesStroke(2, new BasicStroke(2.0f));

			
			plot.setDataset(0, dataset);
			plot.setRenderer(0, renderer);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, renderer2);
			plot.setRangeAxis(1, new NumberAxis("kg"));
			
			plot.setDataset(3, dataset3);
			plot.setRenderer(3, renderer3);
			
			
			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
			
			
			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(
					new TextTitle("CO2 emissions vs Energy use vs PM2.5 air poluition", new Font("Serif", java.awt.Font.BOLD, 18)));

			//Limit the size and the style of the image

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
			
		}
		
		//PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) vs Forest area (% of land area)
		if(methods == 2){
			XYSeries series1 = new XYSeries("PM2.5 air polution/mg per cubic meter");
			XYSeries series2 = new XYSeries("Forest area/% of land area");
			
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(startyear+i, ave_1[i]);
				series2.add(startyear+i, ave_2[i]);
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			XYSeriesCollection dataset2 = new XYSeriesCollection();
			dataset2.addSeries(series2);

			JFreeChart chart = ChartFactory.createXYLineChart("PM2.5 air polution vs Forest area", "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
			
			renderer.setSeriesPaint(1, Color.BLUE);
			renderer.setSeriesStroke(1, new BasicStroke(2.0f));
			
			renderer2.setSeriesPaint(0, Color.BLUE);
			renderer2.setSeriesStroke(0, new BasicStroke(2.0f));
			

			plot.setDataset(0, dataset);
			plot.setRenderer(0, renderer);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("mg"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, renderer2);
			plot.setRangeAxis(1, new NumberAxis("% of land area"));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(
					new TextTitle("PM2.5 air polution vs Forest area", new Font("Serif", java.awt.Font.BOLD, 18)));

			//Limit the size and the style of the image
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
			
		}
		
		//Ratio of CO2 emissions (metric tons per capita) and GDP per capita (current US$)
		if(methods == 3){
			XYSeries series1 = new XYSeries("CO2 emissions/metric tons per capita");
			XYSeries series2 = new XYSeries("GDP per capita/US$");
			
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(startyear+i, ave_1[i]);
				series2.add(startyear+i, ave_2[i]);
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			XYSeriesCollection dataset2 = new XYSeriesCollection();
			dataset2.addSeries(series2);

			JFreeChart chart = ChartFactory.createXYLineChart("PM2.5 air polution vs Forest area", "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
			
			renderer.setSeriesPaint(1, Color.BLUE);
			renderer.setSeriesStroke(1, new BasicStroke(2.0f));
			
			renderer2.setSeriesPaint(0, Color.BLUE);
			renderer2.setSeriesStroke(0, new BasicStroke(2.0f));
			

			plot.setDataset(0, dataset);
			plot.setRenderer(0, renderer);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("mg"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, renderer2);
			plot.setRangeAxis(1, new NumberAxis("% of land area"));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(
					new TextTitle("PM2.5 air polution vs Forest area", new Font("Serif", java.awt.Font.BOLD, 18)));

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
			
		}
		
		//Average Forest area (% of land area) for the selected years
		if(methods == 4){
			XYSeries series1 = new XYSeries("Forest area/% of land area");
			
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(startyear+i, ave_1[i]);
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);

			JFreeChart chart = ChartFactory.createXYLineChart("Forest area", "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();

			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			//Limit the size and the style of the image
			chart.setTitle(
					new TextTitle("Forest area", new Font("Serif", java.awt.Font.BOLD, 18)));

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
			
		}
		
		//Average of Government expenditure on education, total (% of GDP) for the selected years
		if(methods == 5){
			XYSeries series1 = new XYSeries("Government expenditure on education/% of GDP");
			
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(startyear+i, ave_1[i]);
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);

			JFreeChart chart = ChartFactory.createXYLineChart("Government expenditure on education", "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();

			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			//Limit the size and the style of the image
			chart.setTitle(
					new TextTitle("Government expenditure on education", new Font("Serif", java.awt.Font.BOLD, 18)));

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
			
		}
		//Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per 1,000 people) (2-series graphs).
		if(methods == 6){
			XYSeries series1 = new XYSeries("Hospital beds/per 1000 people");
			XYSeries series2 = new XYSeries("Current health expenditure/per 1000 people");
			
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(startyear+i, ave_1[i]);
				series2.add(startyear+i, ave_2[i]*1000);
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			XYSeriesCollection dataset2 = new XYSeriesCollection();
			dataset2.addSeries(series2);

			JFreeChart chart = ChartFactory.createXYLineChart("Hospital beds vs Current health expenditure", "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
			
			renderer.setSeriesPaint(1, Color.BLUE);
			renderer.setSeriesStroke(1, new BasicStroke(2.0f));
			
			renderer2.setSeriesPaint(0, Color.BLUE);
			renderer2.setSeriesStroke(0, new BasicStroke(2.0f));
			
			plot.setDataset(0, dataset);
			plot.setRenderer(0, renderer);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("Health expenditure"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, renderer2);
			plot.setRangeAxis(1, new NumberAxis("Hospital beds "));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(
					new TextTitle("Hospital beds vs Current health expenditure", new Font("Serif", java.awt.Font.BOLD, 18)));

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
			
		}
		
		//Current health expenditure per capita (current US$) vs Mortality rate, infant (per 1,000 live births)
		if(methods == 7){//7
			XYSeries series1 = new XYSeries("Current health expenditure per capita/US$");
			XYSeries series2 = new XYSeries("Mortality rate/per 1000 live births");
			
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(startyear+i, ave_1[i]);
				series2.add(startyear+i, ave_2[i]);
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			XYSeriesCollection dataset2 = new XYSeriesCollection();
			dataset2.addSeries(series2);

			JFreeChart chart = ChartFactory.createXYLineChart("Current health expenditure per capita vs Mortality rate", "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
			
			renderer.setSeriesPaint(1, Color.BLUE);
			renderer.setSeriesStroke(1, new BasicStroke(2.0f));
			
			renderer2.setSeriesPaint(0, Color.BLUE);
			renderer2.setSeriesStroke(0, new BasicStroke(2.0f));
			
			plot.setDataset(0, dataset);
			plot.setRenderer(0, renderer);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("Health expenditure($)"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, renderer2);
			plot.setRangeAxis(1, new NumberAxis("Mortality rate "));
			
			
			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(
					new TextTitle("Current health expenditure per capita vs Mortality rate", new Font("Serif", java.awt.Font.BOLD, 18)));

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
			
		}
		
		
		//Ratio of Government expenditure on education, total (% of GDP) vs Current health expenditure (% of GDP).
		if(methods == 8){
			XYSeries series1 = new XYSeries("Government expenditure on education/% of GDP");
			XYSeries series2 = new XYSeries("Current health expenditure/% of GDP");
			
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(startyear+i, ave_1[i]);
				series2.add(startyear+i, ave_2[i]);
			}
			
			XYSeriesCollection dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series2);

			JFreeChart chart = ChartFactory.createXYLineChart("Government expenditure on education vs Current health expenditure", "Year", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			XYPlot plot = chart.getXYPlot();

			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesPaint(1, Color.BLUE);
			renderer.setSeriesStroke(1, new BasicStroke(2.0f));

			plot.setRenderer(renderer);
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			//Limit the size and the style of the image
			chart.setTitle(
					new TextTitle("Government expenditure on education vs Current health expenditure", new Font("Serif", java.awt.Font.BOLD, 18)));

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
			
		}

	}

	
	/**
	 * 
	 * Drawing the time series on the baseboard by using the input data. 
	 * 
	 * @param west	The baseboard.
	 * @param chosenStartYear	The starting year.
	 * @param chosenEndYear	The ending year.
	 * @param chosenAnalysis	Analysis method.
	 * @param ave1	The data needed for drawing	.
	 * @param ave2	The data needed for drawing.
	 * @param ave3	The data needed for drawing.
	 */
	public static void createTimeSeries(JPanel west, int chosenStartYear, int chosenEndYear, int chosenAnalysis,double[] ave1,double[] ave2,double[] ave3) {
		

		int methods = chosenAnalysis;
		int startyear =chosenStartYear;
		int endyear = chosenEndYear;	

		double[] ave_1=ave1;
		double[] ave_2=ave2;
		double[] ave_3=ave3;
		
	
	
		//CO2 emissions (metric tons per capita) vs Energy use (kg of oil equivalent per capita) vs PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) 
		if(methods == 1){
			TimeSeries series1 = new TimeSeries("CO2 emissions/metric tons");
			TimeSeries series2 = new TimeSeries("Energy use/kg");
			TimeSeries series3 = new TimeSeries("PM2.5 air poluition/mg");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series3.add(new Year(startyear+i), ave_3[i]);
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series3);
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			XYPlot plot = new XYPlot();
			XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
			XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, splinerenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, splinerenderer2);
			plot.setRangeAxis(1, new NumberAxis("kg"));

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart chart = new JFreeChart("CO2 emissions vs Energy use vs PM2.5 air poluition",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//PM2.5 air pollution, mean annual exposure (micrograms per cubic meter) vs Forest area (% of land area)
		if(methods == 2){
			TimeSeries series1 = new TimeSeries("PM2.5 air polution/mg per cubic meter");
			TimeSeries series2 = new TimeSeries("Forest area/% of land area");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			XYPlot plot = new XYPlot();
			XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
			XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, splinerenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("mg"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, splinerenderer2);
			plot.setRangeAxis(1, new NumberAxis("% of land area"));

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			 // 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1);

			//Limit the size and the style of the image
			JFreeChart chart = new JFreeChart("PM2.5 air polution vs Forest area",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//Ratio of CO2 emissions (metric tons per capita) and GDP per capita (current US$)
		if(methods == 3){
			TimeSeries series1 = new TimeSeries("CO2 emissions/metric tons per capita");
			TimeSeries series2 = new TimeSeries("GDP per capita/US$");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			XYPlot plot = new XYPlot();
			XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
			XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, splinerenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("metric tons"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, splinerenderer2);
			plot.setRangeAxis(1, new NumberAxis("US$"));

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart chart = new JFreeChart("CO2 emissions vs GDP per capita",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//Average Forest area (% of land area) for the selected years
		if(methods == 4){
			TimeSeries series1 = new TimeSeries("Forest area/% of land area");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			
			XYPlot plot = new XYPlot();
			XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
			

			plot.setDataset(0, dataset);
			plot.setRenderer(0, splinerenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("% of land area"));
			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart chart = new JFreeChart("Forest area",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//Average of Government expenditure on education, total (% of GDP) for the selected years 
		if(methods == 5){
			TimeSeries series1 = new TimeSeries("Government expenditure on education/% of GDP");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			
			XYPlot plot = new XYPlot();
			XYSplineRenderer splinerenderer1 = new XYSplineRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, splinerenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("% of GDP"));
			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart chart = new JFreeChart("Government expenditure on education",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per 1,000 people)
		if(methods == 6){
			TimeSeries series1 = new TimeSeries("Hospital beds/per 1000 people");
			TimeSeries series2 = new TimeSeries("Current health expenditure/per 1000 people");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]*1000);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			
			
			XYPlot plot = new XYPlot();
			XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
			XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, splinerenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("Hospital beds"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, splinerenderer2);
			plot.setRangeAxis(1, new NumberAxis("health expenditure "));
			

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart chart = new JFreeChart("Hospital beds vs Current health expenditure",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		
		//Current health expenditure per capita (current US$) vs Mortality rate, infant (per 1,000 live births) 
		if(methods == 7){
			TimeSeries series1 = new TimeSeries("Current health expenditure per capita/US$");
			TimeSeries series2 = new TimeSeries("Mortality rate/per 1000 live births");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			
			XYPlot plot = new XYPlot();
			XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
			XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

			plot.setDataset(0, dataset);
			plot.setRenderer(0, splinerenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("US$"));
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, splinerenderer2);
			plot.setRangeAxis(1, new NumberAxis("per 1000 live births"));

			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1); 

			//Limit the size and the style of the image
			JFreeChart chart = new JFreeChart("Current health expenditure per capita vs Mortality rate",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
		
		//Ratio of Government expenditure on education, total (% of GDP) vs Current health expenditure (% of GDP)
		if(methods == 8){
			TimeSeries series1 = new TimeSeries("Government expenditure on education/% of GDP");
			TimeSeries series2 = new TimeSeries("Current health expenditure/% of GDP");
			for(int i=0; i<=endyear-startyear; i++) {
				series1.add(new Year(startyear+i), ave_1[i]);
			}
			for(int i=0; i<=endyear-startyear; i++) {
				series2.add(new Year(startyear+i), ave_2[i]);
			}

			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series2);

			
			XYPlot plot = new XYPlot();
			XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
			

			plot.setDataset(0, dataset);
			plot.setRenderer(0, splinerenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("% of GDP"));
			
			// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(0, 0);
			// 2nd dataset to 2nd y-axis
			plot.mapDatasetToRangeAxis(1, 1);

			//Limit the size and the style of the image
			JFreeChart chart = new JFreeChart("Government expenditure on education vs Current health expenditure",
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			west.add(chartPanel);
		}
	}
}