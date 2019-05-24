package zuul.rüstung;

import zuul.items.Gegenstand;

public class Rüstung extends Gegenstand {
	
	private int ruestungspunkte;
	
	public Rüstung(String name, String beschreibung, int gewicht, int ruestungspunkte) {
		super(name, beschreibung, gewicht);
		this.ruestungspunkte = ruestungspunkte;
	}
	
	public int getRuestung() {
		return this.ruestungspunkte;
	}
}
