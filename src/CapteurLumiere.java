import lejos.robotics.Color;
import java.io.*;
import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;

public class CapteurLumiere {
	
	public float[] blue;
	public float[] red;
	public float[] yellow;
	public float[] green;
	public float[] black;
	public float[] white;
	public float[] grey;
	
	Port port;
	EV3ColorSensor colorSensor;
	SampleProvider average;	
	ArrayList<Float> colorValues = new ArrayList<Float>();
	
	public CapteurLumiere() {		
		average = new MeanFilter(colorSensor.getRGBMode(), 1);	
		port = LocalEV3.get().getPort("S3");
		colorSensor = new EV3ColorSensor(port);
		
		blue = new float[average.sampleSize()];
		red = new float[average.sampleSize()];
		yellow = new float[average.sampleSize()];
		green = new float[average.sampleSize()];
		black = new float[average.sampleSize()];
		white = new float[average.sampleSize()];
		grey = new float[average.sampleSize()];
	}
	
	public void readLines() {
		// peut-�tre � changer selon le r�pertoire du projet
		// voir comment utiliser la position relative par rapport au projet
        String fileName = "C:\\Users\\leiwu\\eclipse-workspace\\RobotIA\\src\\valeursCouleurs.txt";

        // This will reference one line at a time
        String line = null;               

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
            	colorValues.add(Float.parseFloat(line));            	
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
	
	//� voir pour le param�tre de retour
	//�a devrait se changer tranquille
	public String determinerCouleur() {
		String color = "";
		try {
			boolean continuer = true;
			
			colorSensor.setFloodlight(Color.WHITE);
			blue[0] = colorValues.get(0);
			red[0] = colorValues.get(1);
			yellow[0] = colorValues.get(2);
			green[0] = colorValues.get(3);
			black[0] = colorValues.get(4);
			white[0] = colorValues.get(5);
			grey[0] = colorValues.get(6);
			
			while (continuer) {
				float[] sample = new float[average.sampleSize()];
				average.fetchSample(sample, 0);
				double minscal = Double.MAX_VALUE;
				//String color = "";
				
				double scalaire = scalaire(sample, blue);				
				
				if (scalaire < minscal) {
					minscal = scalaire;
					color = "blue";
				}
				
				scalaire = scalaire(sample, red);
				if (scalaire < minscal) {
					minscal = scalaire;
					color = "red";
				}
				
				scalaire = scalaire(sample, green);
				if (scalaire < minscal) {
					minscal = scalaire;
					color = "green";
				}
				
				scalaire = scalaire(sample, yellow);
				if (scalaire < minscal) {
					minscal = scalaire;
					color = "yellow";
				}
				
				scalaire = scalaire(sample, white);
				if (scalaire < minscal) {
					minscal = scalaire;
					color = "white";
				}
				
				scalaire = scalaire(sample, grey);
				if (scalaire < minscal) {
					minscal = scalaire;
					color = "grey";
				}
				
				scalaire = scalaire(sample, black);
				if (scalaire < minscal) {
					minscal = scalaire;
					color = "black";
				}
				
				//System.out.println(color);				
				/*Button.waitForAnyPress();
				if(Button.ESCAPE.isDown()) {
					colorSensor.setFloodlight(false);
					continuer = false;
				}*/		
			}
		} catch (Throwable t) {
			t.printStackTrace();
			Delay.msDelay(10000);
			System.exit(0);
		}
		return color;
	}
	
	public static double scalaire(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}
	

}


