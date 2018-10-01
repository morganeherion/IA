import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class CapteurToucher extends EV3TouchSensor{
	
	private boolean touche;
	
	public CapteurToucher() {
		super(SensorPort.S2);
	}
	
	public boolean isTouche() {
        float[] sample = new float[1];
        fetchSample(sample, 0);
        
        setTouche(sample[0] != 0);
        return sample[0] != 0;
	}
	
	private void setTouche(boolean touche) {
		this.touche = touche;
	}
}
