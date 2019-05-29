package zuul;

import java.util.ArrayList;
import zuul.items.Gegenstand;
import zuul.items.HandelsWaren;

public class Haendler {

	private String name;
	private String beschreibung;
	private ArrayList<HandelsWaren> verkaufsGegenstaende = new ArrayList<>();

	public Haendler(String name, String beschreibung) {
		this.name=name;
		this.beschreibung=beschreibung;

	}

	public String toString() {
		return name + ", " + this.beschreibung;
	}

	public String verkaufsGegenstaendeAusgeben() {

		String erg = "";
		for (int i = 0; i < verkaufsGegenstaende.size(); i++) {
			erg += verkaufsGegenstaende.get(i).getName()+",\tfür"+verkaufsGegenstaende.get(i).getPreis()+"Goldtaler\n";
		}
		return erg;
	}

	public ArrayList getVerkaufsGegenstaende() {
		return this.verkaufsGegenstaende;
	}

	public HandelsWaren sucheVerkaufsGegenstand(String name) {
		for(HandelsWaren g: this.verkaufsGegenstaende) {

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
