import lejos.utility.Delay;

public class Obstacle {
	
	private boolean attrape;
	private boolean depose;
	private boolean present;

	public Obstacle() {
		attrape = false;
		depose = false;
		present = false;
		}
	
	public void isPresent(float distanceInitiale, CapteurDistance distance, CapteurToucher toucher, Roues r, Pinces p) {
		float d = 0;
		d = distanceInitiale;
		distance.enable();
		while(d<=distanceInitiale) {
			r.avance(40);
			d = distance.getDistance();
		}
		
		r.quartTourDroite();
		Delay.msDelay(500);
		if(distance.getDistance()<distanceInitiale) {
			this.isAttrape(toucher, r, p);
		}
		else  {
			r.demiTourGauche();
			Delay.msDelay(500);
			if(distance.getDistance()<distanceInitiale) {
				this.isAttrape(toucher, r, p);	
			}
			else {
				r.quartTourDroite();
				Delay.msDelay(500);
				this.isAttrape(toucher, r, p);
			}
		}
		distance.disable();
	}
		
	public void isAttrape(CapteurToucher toucher, Roues r, Pinces p) {
		while (!toucher.isTouche()) {
			r.avance(60, 500);
		}
		p.capturerPalet();
		r.stop(); 
		
	}
	
	public void depart(Roues r, Pinces p) {
		
	}
	
	
	

}
