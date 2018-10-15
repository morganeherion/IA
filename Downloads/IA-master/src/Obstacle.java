
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
			r.avance(40,300);
			d = distance.getDistance();
		}
		
		r.quartTourDroite();
		if(distance.getDistance()<distanceInitiale) {
			this.isAttrape(toucher, r, p);
		}
		else  {
			r.demiTourGauche();
			if(distance.getDistance()<distanceInitiale) {
				this.isAttrape(toucher, r, p);	
			}
			else {
				r.quartTourDroite();
				this.isAttrape(toucher, r, p);
			}
		}
		distance.disable();
	}
		
	public void isAttrape(CapteurToucher toucher, Roues r, Pinces p) {
		while (!toucher.isTouche()) {
			r.avance(40, 2000);
		}
		p.capturerPalet();
		r.stop(); 
		
	}
	
	
	

}
