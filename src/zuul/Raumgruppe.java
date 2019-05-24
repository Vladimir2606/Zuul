package zuul;

import java.util.ArrayList;

public class Raumgruppe {
	
	private ArrayList<EinUndAusgang> einUndAusgaenge;
	
	public Raumgruppe() {
		
		this.einUndAusgaenge = new ArrayList<>();
		
	}
	
	public ArrayList<EinUndAusgang> getEinUndAusgaenge() {
		return this.einUndAusgaenge;
	}
	
	public void addEinAusgang(EinUndAusgang ea) {
		this.einUndAusgaenge.add(ea);
	}
	
	public EinUndAusgang getRandomEinAusgang() {
		
		EinUndAusgang rEinAusgang = getEinUndAusgaenge().get((int) (Math.random()*einUndAusgaenge.size()));
		
		return rEinAusgang;
	}
	
	public ArrayList getRaumgruppe() {
		return einUndAusgaenge;
	}
	

}
