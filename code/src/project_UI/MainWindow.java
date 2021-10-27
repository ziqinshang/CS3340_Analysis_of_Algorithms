/**
 * The mainWindow class is designed to display the major contents of the program
 * and collect the user selections for further process.
 * 
 * @author Yifei Zhang 251109144
 *
 */

package project_UI;

import project_Handler.requestHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * The main window that act as an agent between user and the components of the program.
 *
 */
public class MainWindow extends JFrame implements UserWindows, ActionListener{

	private int chosenCountry, chosenStartYear, chosenEndYear, chosenAnalysis, chosenViewer; // User Selections
	private Vector<Integer> chosenViewerList = new Vector<Integer>();						 // The viewer list to be sent for handling.
	private String viewerList;																 //	A visualization of viewer list to be added on the main window.
	private static final long serialVersionUID = 1L;
	JLabel lb1, lb2, lb3, lb4, lb5, lb6, invalid;											 // Label 1-6 are some texts on the main window, invalid is a notification text that's only visible when there are conflicts in selections.
	JButton recalculate, addViewer, removeViewer;
	JComboBox<String> country, startYear, endYear, viewerType, analysisType;
	JPanel top, bottom, viewers;

	public void display() {		// display method that initializes the components and add them to the main window.
		// Set top bar
		lb1 = new JLabel("Choose a country:");
		lb2 = new JLabel("From");
		lb3 = new JLabel("To");
		invalid = new JLabel("----Invalid Combination!----");
		
		
		String[] countryNames = {"----------", "China", "Canada", "United Kindom", "United States", "Japan"};
		country = new JComboBox<String>(countryNames);
		Vector<String> years_1 = new Vector<String>();
		Vector<String> years_2 = new Vector<String>();
		years_1.add("----"); years_2.add("----");
		for (int i = 2010; i <= 2018; i++) years_1.add("" + i);
		for (int i = 2018; i >= 2010; i--) years_2.add("" + i);
		startYear = new JComboBox<String>(years_1);
		endYear = new JComboBox<String>(years_2);
		
		top = new JPanel();
		lb1.setFont(new Font("Helvetica", Font.PLAIN, 17));
		lb2.setFont(new Font("Helvetica", Font.PLAIN, 17));
		lb3.setFont(new Font("Helvetica", Font.PLAIN, 17));
		invalid.setFont(new Font("Helvetica", Font.BOLD, 20));
		country.setFont(new Font("Helvetica", Font.BOLD, 17));
		startYear.setFont(new Font("Helvetica", Font.BOLD, 17));
		endYear.setFont(new Font("Helvetica", Font.BOLD, 17));
		top.add(lb1); top.add(country); top.add(lb2); top.add(startYear); top.add(lb3); top.add(endYear); top.add(invalid);
		invalid.setVisible(false);
		
		// Set center panel that contains viewers
		viewers = new JPanel();
		viewers.setLayout(new GridLayout(2,0));
		viewers.setBackground(Color.white);
		
		// Set bottom bar
		lb4 = new JLabel("Available Views:");
		lb5 = new JLabel("Choose analysis method");
		viewerList = "Current viewer List: ";
		lb6 = new JLabel(viewerList);
		recalculate = new JButton("Recalculate");
		addViewer = new JButton("+");
		removeViewer = new JButton("-");
		
		String[] viewerTypes = {"----------", "Pie Chart", "Line Chart", "Bar Chart", "Scatter Chart", "Report", "Time Series"};
		String[] analysisTypes = {"-----------------", "CO2 emissions vs Energy use vs PM2.5 air poluition", "PM2.5 air polution vs Forest area", "CO2 emissions vs GDP per capita", "Forest area (% of land area)", "Government expenditure on education in total (% of GDP)", "Hospital beds vs Current health expenditure(per 1000 people)", "Current health expenditure per capita vs Mortality rate", "expenditure on education vs health expenditure"};
		viewerType = new JComboBox<String>(viewerTypes);
		analysisType = new JComboBox<String>(analysisTypes);
		
		bottom = new JPanel();
		lb4.setFont(new Font("Helvetica", Font.PLAIN, 16));
		lb5.setFont(new Font("Helvetica", Font.PLAIN, 16));
		lb6.setFont(new Font("Helvetica", Font.BOLD, 16));
		viewerType.setFont(new Font("Helvetica", Font.BOLD, 16));
		addViewer.setFont(new Font("Helvetica", Font.BOLD, 16));
		removeViewer.setFont(new Font("Helvetica", Font.BOLD, 16));
		analysisType.setFont(new Font("Helvetica", Font.BOLD, 15));
		recalculate.setFont(new Font("Helvetica", Font.BOLD, 16));
		bottom.add(lb4);bottom.add(viewerType);bottom.add(addViewer);bottom.add(removeViewer);bottom.add(lb5);bottom.add(analysisType);bottom.add(recalculate); bottom.add(lb6);

		// Display contents
		getContentPane().add(top, BorderLayout.NORTH);
		getContentPane().add(viewers, BorderLayout.CENTER);
		getContentPane().add(bottom, BorderLayout.SOUTH);
		
		
		setSize(1500,1000);
		setTitle("Global Statistics Visualization Tool");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		country.addActionListener(this);
		startYear.addActionListener(this);
		endYear.addActionListener(this);
		viewerType.addActionListener(this);
		addViewer.addActionListener(this);
		removeViewer.addActionListener(this);
		analysisType.addActionListener(this);
		recalculate.addActionListener(this);
		this.getRootPane().setDefaultButton(recalculate);
	}
	
	public void actionPerformed(ActionEvent e) {		// The method that collects user selections and react to the actions.
		if (e.getSource() == country && country.getSelectedIndex() > 0) {			// Sets chosenCountry when a country is selected.
			chosenCountry = country.getSelectedIndex();
			invalid.setVisible(false);
		}
		
		else if(e.getSource() == startYear && startYear.getSelectedIndex() > 0) {	// Sets chosenStartYear when a start year is selected.
			chosenStartYear = startYear.getSelectedIndex() + 2009;
			invalid.setVisible(false);
		}
		
		else if(e.getSource() == endYear && endYear.getSelectedIndex() > 0) {		// Sets chosenEndYear when an end year is selected.
			chosenEndYear = 2019 - endYear.getSelectedIndex();
			invalid.setVisible(false);
		}
		
		else if(e.getSource() == viewerType && viewerType.getSelectedIndex() > 0) {	// Sets chosenViewer when a viewer type is selected.
			chosenViewer = viewerType.getSelectedIndex() - 1;	
			invalid.setVisible(false);
		}
		
		else if(e.getSource() == addViewer) {										// Add the chosenViewer to both the viewer lists to be displayed and processed.
			if(chosenViewer > -1 && !chosenViewerList.contains(chosenViewer)) {
				chosenViewerList.add(chosenViewer);
				invalid.setVisible(false);
				switch(chosenViewer) {
					case 0:
						viewerList += "P  ";
						lb6.setText(viewerList);
						break;
					case 1:
						viewerList += "L  ";
						lb6.setText(viewerList);
						break;
					case 2:
						viewerList += "B  ";
						lb6.setText(viewerList);
						break;
					case 3:
						viewerList += "S  ";
						lb6.setText(viewerList);
						break;
					case 4:
						viewerList += "R  ";
						lb6.setText(viewerList);
						break;
					case 5:
						viewerList += "T  ";
						lb6.setText(viewerList);
						break;
				}
			}
			
		}
		
		else if(e.getSource() == removeViewer) {									// 	Remove the chosenViewer from both the viewer lists to be displayed and processed.
			if(chosenViewer > -1 && chosenViewerList.contains(chosenViewer) && !chosenViewerList.isEmpty()) {
				chosenViewerList.removeElement(chosenViewer);
				invalid.setVisible(false);
				switch(chosenViewer) {
				case 0:
					viewerList = viewerList.replace("P  ", "");
					lb6.setText(viewerList);
					break;
				case 1:
					viewerList = viewerList.replace("L  ", "");
					lb6.setText(viewerList);
					break;
				case 2:
					viewerList = viewerList.replace("B  ", "");
					lb6.setText(viewerList);
					break;
				case 3:
					viewerList = viewerList.replace("S  ", "");
					lb6.setText(viewerList);
					break;
				case 4:
					viewerList = viewerList.replace("R  ", "");
					lb6.setText(viewerList);
					break;
				case 5:
					viewerList = viewerList.replace("T  ", "");
					lb6.setText(viewerList);
					break;
			}
			}
			
		}
		
		else if(e.getSource() == analysisType && analysisType.getSelectedIndex() > 0) { // Sets analysisType and clear viewer list when an analysis is selected.
			if(chosenAnalysis != analysisType.getSelectedIndex()) {
				chosenViewerList.removeAllElements();
				viewerList = "Current viewer List: ";
				lb6.setText(viewerList);
			}
			chosenAnalysis = analysisType.getSelectedIndex();
			invalid.setVisible(false);
		}
		
		else if(e.getSource() == recalculate) {											// Clear the currently displaying viewers if any. Then call the requestHandler if the selections have no conflict, otherwise display a message to notify user of conflicts.
			viewers.removeAll();
			System.out.println("Recalculation: " + '\n' + "chosen country: " + chosenCountry + '\n' + "chosen start year: " + chosenStartYear + '\n' + "chosen end year: " + chosenEndYear + '\n' + "chosen viewers:" + chosenViewerList + '\n' + "chosen analysis: " + chosenAnalysis);
			if(chosenStartYear>chosenEndYear) invalid.setVisible(true);
			else if(chosenViewerList.contains(0) && !(chosenAnalysis == 4 ^ chosenAnalysis == 5)) invalid.setVisible(true);
			else if(chosenCountry < 0 || chosenStartYear <= 0 || chosenEndYear <= 0 || chosenAnalysis <= 0 || chosenViewerList.isEmpty()) invalid.setVisible(true);
			else if(chosenCountry == 1 && (chosenAnalysis == 5 ^ chosenAnalysis == 8)) invalid.setVisible(true);
			else requestHandler.dataProcesser(viewers, chosenCountry, chosenStartYear, chosenEndYear, chosenAnalysis, chosenViewerList);
			viewers.validate();
			viewers.repaint();
		}
		
	}
}
