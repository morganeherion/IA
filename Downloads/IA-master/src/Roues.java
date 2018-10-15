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
	
	//indiquer la dur�e pour chaque m�thode de mouvement
	//prendre en compte les donn�es des capteurs pour agir en fonction
	
	public void avance(int puissance) {
		//tant qu'on dit d'avancer
		roueG.setPower(puissance);
		roueD.setPower(puissance+1);
		
		roueG.forward();
		roueD.forward();
		}
	
	public void avance(int puissance, int duree) {
		//tant qu'on dit d'avancer
		roueG.setPower(puissance);
		roueD.setPower(puissance+1);
		
		roueG.forward();
		roueD.forward();
		
		Delay.msDelay(duree);
		}
	
	public void recule(int puissance) {
		//tant qu'on dit de reculer
		roueG.setPower(puissance);
		roueD.setPower(puissance);
		
		roueG.backward();
		roueD.backward();
		}		
	
	//peut-etre juste faire genre une m�thode qui fait tourner
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
		//� changer (valeurs "trouv�es" � t�tons)
		Delay.msDelay(5000);
		stop();
		
	}
	
	//on verra plus tard pour la direction
	public void demiTour() {
		roueG.setPower(80);
		roueD.setPower(80);
		
		roueG.forward();
		roueD.backward();
		//� changer (valeurs "trouv�es" � t�tons)
		Delay.msDelay(510);
		stop();
	}
	
	public void demiTourGauche() {
		roueG.setPower(40);
		roueD.setPower(40);
		
		roueG.forward();
		roueD.backward();
		
		Delay.msDelay(500);
		stop();
	}
	
	public void quartTourDroite() {
		roueG.setPower(40);
		roueD.setPower(40);
		
		roueD.forward();
		roueG.backward();
		
		Delay.msDelay(250);
		stop();
	}
	
	public void quartTourGauche() {
		roueG.setPower(30);
		roueD.setPower(30);
		
		roueD.backward();
		roueG.forward();
		
		Delay.msDelay(250);
		stop();
	}
	
	//arret des moteurs et liberation des ressources
	public void stop() {
		roueG.stop();
		roueD.stop();
		//roueG.close();
		//roueD.close();
	}
	
}
