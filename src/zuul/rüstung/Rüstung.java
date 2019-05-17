package zuul.r�stung;

import java.util.ArrayList;

import zuul.Gegenstand;
import zuul.Spieler;

public class R�stung extends Gegenstand {

	private int ruestungspunkte;
	private Spieler spieler;
	private ArrayList<Gegenstand> gegenstaende;

	public R�stung(String name, String beschreibung, int gewicht, int ruestungspunkte) {
		super(name, beschreibung, gewicht);
		this.ruestungspunkte = ruestungspunkte;
		this.gegenstaende=new ArrayList<>();
	}

	public int getRuestung() {
		return this.ruestungspunkte;
	}
	
	public void entr�sten(Gegenstand neuerGegenstand) {
		this.gegenstaende.add(neuerGegenstand);
	}
}
