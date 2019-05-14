/**
 * Diese Klasse modelliert Rï¿½ume in der Welt von Zuul.
 *
 * Ein "Raum" reprï¿½sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Rï¿½umen ï¿½ber Ausgï¿½nge verbunden.
 * Mï¿½gliche Ausgï¿½nge liegen im Norden, Osten, Sï¿½den und Westen.
 * Fï¿½r jede Richtung hï¿½lt ein Raum eine Referenz auf den 
 * benachbarten Raum.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
package zuul;

import java.util.ArrayList;
import java.util.HashMap;

public class Raum
{
	private String beschreibung;
	private HashMap<String, Raum> ausgaenge;
	private ArrayList<Gegenstand> gegenstaende;
	private ArrayList<Monster> monster;

	/**
	 * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
	 * hat anfangs keine Ausgänge.
	 * @param beschreibung enthält eine Beschreibung in der Form
	 *        "in einer Küche" oder "auf einem Sportplatz".
	 */
	public Raum(String beschreibung)
	{
		this.gegenstaende=new ArrayList<>();
		this.monster= new ArrayList<>();
		this.ausgaenge=new HashMap<>();
		this.beschreibung = beschreibung;
	}

	public void gegenstandAblegen(Gegenstand neuerGegenstand) {
		this.gegenstaende.add(neuerGegenstand);
	}
	
	public void setMonster(Monster neuesMonster) {
		this.monster.add(neuesMonster);
	}

	public void setAusgang(String richtung, Raum nachbar) {
		this.ausgaenge.put(richtung, nachbar);
	}

	public Raum getAusgang(String name) {
		return this.ausgaenge.get(name);
	}

	public String getLangeBeschreibung() {
		String erg=  "Sie sind " + this.beschreibung + "\nAusgänge: " + this.ausgaengeToString();
		if(this.monster.size()>0) {
			erg+="\nIn deiner nähe ist ein Monster:\n";
			for(Monster m: this.monster) {
				erg+=" - " + m.toString();
			}
		}
		if(this.gegenstaende.size()>0) {
			erg+="\nGegenstände in diesem Raum:\n";
			for(Gegenstand g: this.gegenstaende) {
				erg+=" - " + g.toString() + "\n";
			}
		}
		return erg;
	}

	public String ausgaengeToString() {
		String erg="";
		for(String richtung: ausgaenge.keySet()) {
			erg+=richtung + " ";
		}
		return erg;
	}

	/**
	 * @return Die Beschreibung dieses Raums.
	 */
	public String gibBeschreibung()
	{
		return beschreibung;
	}

	public void entferneGegenstand(Gegenstand gesucht) {
		this.gegenstaende.remove(gesucht);
	}

	public Gegenstand sucheGegenstand(String name) {
		for(Gegenstand g: this.gegenstaende) {
			// if(g.getName() == name) --> funktioniert nicht,
			// da hier nur die Referenz auf Gleichheit geprüft wird
			// d.h. ob die im gleichen Speicher stehen
			if(g.getName().equalsIgnoreCase(name)) {
				return g;
				// Dieses return beendet die Methode
			}
		}
		// Falls diese Stelle erreicht wird, wurde kein
		// Gegenstand gefunden
		return null;
	}
}
