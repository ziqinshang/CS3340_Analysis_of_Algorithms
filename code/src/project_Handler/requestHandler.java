/**
 * This class is the function of connecting the preceding and the following.
 * @author Zhen Yang 250947763
 * @author Yifei Zhang 251109144
 * @author Yumeng Chen 251055437
 *
 */


package project_Handler;

import java.util.Vector;
import project_Viewer.Viewers;
import javax.swing.JPanel;
import project_Reader.GetData;

/**
 * Receive parameters from MainWindow class, and do some processing then send it to the viewer.
 * 
 */


public class requestHandler {
	/**
	 * Receive parameters from MainWindow class, and do some processing then send it to the viewer.
	 * 
	 * @param p Like a baseboard which received from MainWindow, will send to Viewers.
	 * @param c Country index.
	 * @param s Start year.
	 * @param e End year.
	 * @param a	Analysis method.
	 * @param v	A list of requested viewers.
	 */
	
	public static void dataProcesser(JPanel p, int c, int s, int e, int a, Vector<Integer> v) {
		
		//Because the GetData needs the index of the data type to crawl, here prepare three index.
		int index1 = 0, index2 = 0, index3 = 0;
		
		
		JPanel display=p;
		int country = c;
		int startYear = s;
		int endYear = e;
		int methods = a;
		Vector<Integer> viewers=v;
	
		
	
		//Some methods need two data to compare, some need three. If the method needs two, the third data will set to 99. 
		if(methods == 1){
			index1 = 1; index2 = 4; index3 = 3;
			}
		if(methods == 2){
			index1 = 2; index2 = 3; index3 = 99;
			}
		if(methods == 3){
			index1 = 1; index2 = 5; index3 = 99;
			}
		if(methods == 4){
			index1 = 3; index2 = 99; index3 = 99;
			}
		if(methods == 5){
			index1 = 7; index2 = 99; index3 = 99;
			}
		if(methods == 6){
			index1 = 6; index2 = 9; index3 = 99;
			}
		if(methods == 7){
			index1 = 9; index2 = 8; index3 = 99;
			}
		if(methods == 8){
			index1 = 7; index2 = 10; index3 = 99;
			}
		
		//Prepare three double array use to store the data.
		double[] ave_1 = null;
		double[] ave_2 = null;
		double[] ave_3 = null;

		
		//Use function datagetter in GetData to get the data.
		ave_1 = GetData.datagetter(startYear,endYear,country,index1);
		if (index2 != 99) {
			ave_2 = GetData.datagetter(startYear,endYear,country,index2);
			}
		if (index3 != 99) {
			ave_3 = GetData.datagetter(startYear,endYear,country,index3);
			}
		
		
		
		//Check the viewer list to decide which graph generater to use.
		for(int i = 0; i < viewers.size(); i++) {

			switch(viewers.elementAt(i)) {
				case 0:
					Viewers.createPie(display, startYear, endYear, methods,ave_1);
					break;
				case 1:
					Viewers.createLine(display, startYear, endYear, methods,ave_1,ave_2,ave_3);
					break;
				case 2:
					Viewers.createBar(display, startYear, endYear, methods,ave_1,ave_2,ave_3);
					break;
				case 3:
					Viewers.createScatter(display, startYear, endYear, methods,ave_1,ave_2,ave_3);
					break;
				case 4:
					Viewers.createReport(display, startYear, endYear, methods,ave_1,ave_2,ave_3);
					break;
				case 5:
					Viewers.createTimeSeries(display, startYear, endYear, methods,ave_1,ave_2,ave_3);
					break;
					
			}
		}
		
		
		
		
	}
	
}
