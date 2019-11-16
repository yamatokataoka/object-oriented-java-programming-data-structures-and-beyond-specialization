package module3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.*;

/** EarthquakeCityMap
 * An application with an interactive map displaying life expectancy data.
 * @author yamatokataoka
 * Date: November 16th, 2019
 * */
public class LifeExpectancy extends PApplet {

	UnfoldingMap map;
	
	Map<String, Float> LifeExpectancyMap;
	
	List<Feature> countriesFeatures;
	List<Marker> countryMarkers;

	public void setup() {
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		background(150);
		
		map.zoomLevel(2);
		
		LifeExpectancyMap = loadLifeExpectancyGromCSV("LifeExpectancyWorldBankModule3.csv");
		
		countriesFeatures = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countriesFeatures);
		
		map.addMarkers(countryMarkers);
		shadeCountries();
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
	
	private void shadeCountries() {
		
		for (Marker marker : countryMarkers) {
			String countryId = marker.getId();
			if (LifeExpectancyMap.containsKey(countryId)) {
				float lifeExp = LifeExpectancyMap.get(countryId);
				// Encode value as brightness (values range: 40-90)
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				marker.setColor(color(150,150,150));
			}
		}
	}
}