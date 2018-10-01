import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Pinces {
	
	private int puissance;
	private boolean ferme;
	private UnregulatedMotor pinces = new UnregulatedMotor(MotorPort.B);
	
	public Pinces() {
		
	}
	
	public int getPuissance() {
		return puissance;
	}
	
	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}
	
	public boolean isFerme() {
		return ferme;
	}
	
	public void setFerme(boolean ferme) {
		this.ferme = ferme;
	}
	
	
	//genre faut calculer le nombre de tours du moteur pour ouvrir/fermer
	public void ouvrir(int puissance, int delay, int duree) {
		//ptetre vérifier pour valeur négatives
		if (delay != 0) {
			Delay.msDelay(delay);
		}		
		pinces.setPower(puissance);
		pinces.forward();
		Delay.msDelay(duree);
		pinces.stop();
		pinces.close();
		setFerme(false);
	}
	
	public void fermer(int puissance, int delay, int duree) {
		if (delay != 0) {
			Delay.msDelay(delay);
		}
		pinces.setPower(puissance);		
		pinces.backward();
		Delay.msDelay(duree);
		pinces.stop();
		pinces.close();
		setFerme(true);		
	}
	
//	public void stop() {
//		
//	}
	
	//verifier quand c'est un palet ou juste quand il fait calin dans le vide
}
