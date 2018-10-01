import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Roues {
	
	private int puissance;
	private int sens; // restrict to 1 and 0
	private boolean active;
	UnregulatedMotor roueG = new UnregulatedMotor(MotorPort.A);
	UnregulatedMotor roueD = new UnregulatedMotor(MotorPort.C);
	
	public Roues() {
//		setPuissance(0);
//		setSens(1);
//		setActive(false);
	}
	
	public int getPuissance() {
		return puissance;
	}
	
	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}
	
	public int getSens() {
		return sens;
	}
	
	public void setSens(int sens) {
		this.sens = sens;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	//indiquer la durée pour chaque méthode de mouvement
	//prendre en compte les données des capteurs pour agir en fonction
	
	public void avance(int puissance, int duree) {
		//tant qu'on dit d'avancer
		roueG.setPower(puissance);
		roueD.setPower(puissance);
		
		roueG.forward();
		roueD.forward();
		
//		arret
//		stop();
	}
	
	public void recule(int puissance, int duree) {
		//tant qu'on dit de reculer
		roueG.setPower(puissance);
		roueD.setPower(puissance);
		
		roueG.backward();
		roueD.backward();
	}		
	
	//peut-etre juste faire genre une méthode qui fait tourner
	public void tourne(int p1, int p2, boolean tourneGauche) {
		int pFaible;
		int pHaute;
		
		if (p2 < p1) {
			pFaible = p2;
			pHaute = p1;
		} else {
			pFaible = p1;
			pHaute = p2;
		}
		
		if (tourneGauche) {
			roueG.setPower(pHaute);
			roueD.setPower(pFaible);
		} else {
			//tourneDroite du coup hein
			roueG.setPower(pFaible);
			roueD.setPower(pHaute);
		}
		
		roueG.forward();
		roueD.forward();
		//à changer
		Delay.msDelay(5000);
		stop();
		
	}
	
	//on verra plus tard pour la direction
	public void demiTour() {
		roueG.setPower(80);
		roueD.setPower(80);
		
		roueG.forward();
		roueD.backward();
		//à changer
		Delay.msDelay(510);
		stop();
	}
	
//	public void pivot / demiTour
	
	public void stop() {
		roueG.stop();
		roueD.stop();
		roueG.close();
		roueD.close();
	}
	
}
