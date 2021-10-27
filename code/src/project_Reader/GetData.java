package project_Reader;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * The GetData program implements an application that fetches desired data from
 * WorldDataBank, it takes four interger inputs which are int startyear, int endyear,
 * int countryindex, int analysisindex respectively, and return a double array with a
 * size of endyear - startyear, and it contains the desired data in a chronological order.
 * @author ZiqinShang
 * @version 1.0.2
 * @since 2021-04-02
 */
public class GetData {

	/**
	 * This is the main method which makes use of GetData method.
	 * @param startyear The start year
	 * @param endyear	The end year
	 * @param countryindex	The index to country array
	 * @param analysisindex	The index to analysis_type array
	 * @return result The data in double array
	 */

	public static double[] datagetter(int startyear, int endyear, int countryindex, int analysisindex) {
		//the start year and end year for the data desired
		int start_year = startyear;
		int end_year = endyear;
		//the result array to be returned
		double[] result;
		//a string array that contained all available analysis type
		String[] analysis_type;
		//a string array that contained all available countries
		String[] country;
		//initalization of analysis_type
		analysis_type = new String[20];
		//initalization of country
		country = new String[20];
		//the size of the returned array will be the total years of the desired data
		result = new double[(endyear - startyear)+1];
		//definition of countries
		country[1] = "CHN"; //China
		country[2] = "CAN"; //Canada
		country[3] = "GBR"; //United Kingdom
		country[4] = "USA"; //United State
		country[5] = "JPN"; //Japan
		//definition of analysis type
		analysis_type[0] = "SP.POP.TOTL";		//Total Population
		analysis_type[1] = "EN.ATM.CO2E.PC";	//CO2 emissions (metric tons per capita)
		analysis_type[2] ="EN.ATM.PM25.MC.M3";  //PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)
		analysis_type[3] = "AG.LND.FRST.ZS";	//Forest area (% of land area)
		analysis_type[4] = "EG.USE.PCAP.KG.OE"; //Energy use (kg of oil equivalent per capita)
		analysis_type[5] = "NY.GDP.PCAP.CD";	//GDP per capita (current US$)
		analysis_type[6] = "SH.MED.BEDS.ZS";	//Hospital beds (per 1,000 people)
		analysis_type[7] = "SE.XPD.TOTL.GD.ZS";	//Government expenditure on education, total (% of GDP)
		analysis_type[8] = "SH.STA.MMRT";       //Maternal mortality ratio (modeled estimate, per 100,000 live births)
		analysis_type[9] = "SH.XPD.CHEX.PC.CD"; //Current health expenditure per capita (current US$)
		analysis_type[10] = "SH.XPD.CHEX.GD.ZS";	//Current health expenditure per capita (current US$)
		analysis_type[11] = "SP.DYN.IMRT.IN";	//Mortality rate, infant (per 1,000 live births)
		//construct the address to be visited
		String urlString = String.format(
				"http://api.worldbank.org/v2/country/%s/indicator/%s?date=%d:%d&format=json", country[countryindex],analysis_type[analysisindex],start_year,end_year);
		//the result for individual year
		double resultforyear = 0;
		//sum of all result
		double cummulativeresult = 0;
		try {
			//establish ocnnection with world data bank
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());	
				while (sc.hasNext()) {
					//scan line by line
					inline += sc.nextLine();
				}
				sc.close();
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				//the size of result will be the total size of the fetched result size
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
				for (int i = 0; i < sizeOfResults; i++) {
					//if there is no data available, make the result for year 0
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
						resultforyear = 0;
					else
						//if there is data available, put it in as a double data
						resultforyear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsDouble();
					//store the result for year into result array in a chronological order
					result[sizeOfResults-i-1] = resultforyear;
					//sum the fetched result
					cummulativeresult = cummulativeresult + resultforyear;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}


	
}


