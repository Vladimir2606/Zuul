package zuul.rüstung;

import java.util.ArrayList;

import zuul.Gegenstand;
import zuul.Spieler;

public class Rüstung extends Gegenstand {

	private int ruestungspunkte;
	private Spieler spieler;
	private ArrayList<Gegenstand> gegenstaende;

	public Rüstung(String name, String beschreibung, int gewicht, int ruestungspunkte) {
		super(name, beschreibung, gewicht);
		this.ruestungspunkte = ruestungspunkte;
		this.gegenstaende=new ArrayList<>();
	}

	public int getRuestung() {
		return this.ruestungspunkte;
	}
	
	public void entrüsten(Gegenstand neuerGegenstand) {
		this.gegenstaende.add(neuerGegenstand);
	}
}
