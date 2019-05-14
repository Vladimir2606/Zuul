package zuul.r�stung;

import zuul.Gegenstand;

public class R�stung extends Gegenstand {

	private int ruestungspunkte;

	public R�stung(String name, String beschreibung, int gewicht, int ruestungspunkte) {
		super(name, beschreibung, gewicht);
		this.ruestungspunkte = ruestungspunkte;
	}

	public int getRuestung() {
		return this.ruestungspunkte;
	}
}
