package module3;

import processing.core.*;

public class MyPApplet extends PApplet {
	private String URL = "https://cdn.shopify.com/s/files/1/0871/3066/products/RT-1017-TulipCup170ml-Rata-Cropped_300x300.jpg";
	private PImage img;
	
	public void setup() {
		size(200, 200);
		img = loadImage(URL, "jpg");
		img.resize(0, height);
		image(img, 0, 0);
	}
	
	public void draw() {
		ellipse(width/4, height/5, width/5, height/5);
		System.out.println(hour());
		int[] color = sunColorSec(second());
		// if (10 < hour() || hour() <= 21) {
		// 	fill(255, 209, 0);
		// } else {
		// 	fill(128, 102, 0);
		// }
		fill(color[0], color[1], color[2]);
	}

	private int[] sunColorSec(int second) {
		int [] rgb = new int[3];
		float diffFrom30 = Math.abs(30 - second);
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		System.out.println("R: " + rgb[0] + " G: " + rgb[1] + " B: " + rgb[2]);
		return rgb;
	}
}
