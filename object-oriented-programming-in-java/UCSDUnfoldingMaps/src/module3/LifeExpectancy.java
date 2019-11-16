package module3;

import java.util.HashMap;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.*;

public class LifeExpectancy extends PApplet {

	UnfoldingMap map;
	
	Map<String, Float> LifeExpectancyMap;

	public void setup() {
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		LifeExpectancyMap = loadLifeExpectancyGromCSV("LifeExpectancyWorldBankModule3.csv");
	}

	public void draw() {
		map.draw();
	}
	
	private Map<String, Float> loadLifeExpectancyGromCSV(String fileName) {
		Map<String, Float> LifeExpectancyMap = new HashMap<String, Float>();
		
		String[] rows = loadStrings (fileName);
		for (String row : rows) {
			String[] colums = row.split(",");
			if (colums.length == 6 && !colums[5].equals("..")) {
				LifeExpectancyMap.put(colums[4], Float.parseFloat(colums[5]));
			}
		}
		
		return LifeExpectancyMap;
	}
}