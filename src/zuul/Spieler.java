
package zuul;

import zuul.items.Essen;
import zuul.items.Gegenstand;
import zuul.items.Heilungstraenke;
import zuul.items.Krafttraenke;
import zuul.rüstung.Brust;
import zuul.rüstung.Helm;
import zuul.rüstung.Hose;
import zuul.rüstung.Schuhe;

import java.util.ArrayList;

public class Spieler {

	private Raum aktuellerRaum;
	private int tragkraft;
	private ArrayList<Gegenstand> gegenstaende;
	private int hunger;
	private double lebenspunkte;
	private int auszuhaltendeKaelte;
	private Spiel spiel;
	private Hose hose;
	private Brust brust;
	private Helm helm;
	private Schuhe schuhe;
	private int ruestung;
	private double schaden;
	/** @param tragkraft = das maximalgewicht in kg das getragen werden kann
	 *	@param hunger = die hungerpunkte des spielers, wie satt er ist
	 *	@param lebenspunkte = wie viel leben der spieler hat
	 *	@param auszuhaltendeKaelte = die niedrichste temperatur, welche er aushält ohne schaden zuzunehmen
	 */
	public Spieler(Spiel spiel) {
		this.gegenstaende=new ArrayList<>();
		this.tragkraft = 30;
		this.hunger = 10;
		this.lebenspunkte = 20;
		this.auszuhaltendeKaelte = 12;

	}



	public void frieren() {
		if (aktuellerRaum.getTemperatur() <= auszuhaltendeKaelte) {
			this.lebenspunkte -= 0.5;
			System.out.println("Du frierst!");
		}

	}

	public void hungern() {
		this.hunger -= 1;
		if (this.hunger <= 0) {
			this.hunger = 0;
			this.lebenspunkte -= 1;
		}
		if (this.lebenspunkte <= 0) {
			System.out.println("Du bist gestorben!");
			spiel.quit();
		}
	}

	public int ermittleGewicht() {
		int gesamtgewicht=0;

		// this.gegenstaende wird durchlaufen
		// Jeder Gegenstand in der Liste wird einmal
		// in der Variablen g abgespeichert
		for(Gegenstand g: this.gegenstaende) {
			// a = a + b oder a+=b
			gesamtgewicht += g.getGewicht();
		}
		return gesamtgewicht;
	}


	/**

	 *
	 * Dieser Gegenstand sollte dann im aktuellen Raum
	 * gesucht werden (Methode dafür erstellen!).
	 * Sofern dieser Gegenstand mit diesem Namen
	 * existiert und sofern die Tragkraft es zulässt,
	 * wird dieser Gegenstand aufgenommen.
	 *
	 * Das bedeutet natürlich, dass der Raum diesen
	 * Gegenstand dann nicht mehr haben kann
	 * (Methode dafür erstellen!).
	 *
	 * Die Methode gegenstandAufnehmen() liefert dann
	 * true oder false zurück, je nachdem ob es
	 * geklappt hat oder nicht.
	 */
	public boolean gegenstandAufnehmen(String name) {
		Gegenstand gesucht=this.aktuellerRaum.sucheGegenstand(name);
		if(gesucht==null) {
			return false;
		} else {
			if(ermittleGewicht()+gesucht.getGewicht()<=this.tragkraft) {
				this.gegenstaende.add(gesucht);
				this.aktuellerRaum.entferneGegenstand(gesucht);
				return true;
			} else {
				return false;
			}
		}
	}


	public boolean gegenstandAblegen(String name) {
		for(Gegenstand g: this.gegenstaende) {
			if(g.getName().equalsIgnoreCase(name)) {
				this.gegenstaende.remove(g);
				this.aktuellerRaum.gegenstandAblegen(g);
				return true;
				// Methode wird abgebrochen (damit auch die Schleife)
			}
		}
		// Falls das hier erreicht wird, wurde der Gegenstand
		// nicht gefunden
		return false;
	}

	public String zeigeStatus() {
		String erg = "Ich kann insgesamt ";
		erg += this.tragkraft + "kg tragen\n";
		for (Gegenstand g : this.gegenstaende) {
			erg += " - " + g.getName() + " " + g.getGewicht() + "kg\n";
		}
		erg += this.tragkraft - ermittleGewicht() + "kg kann ich noch tragen!\n";
		erg += "Ich habe noch ";
		erg += this.hunger + " Hungerpunkte\n";
		erg += "Ich habe noch ";
		erg += this.lebenspunkte + " Lebenspunkte\n";
		erg += "Ich habe ";
		erg += this.schaden +"Schaden\n";
		erg += "Ich habe ";
		erg += this.ruestung + " Rüstungspunkte\n";
		return erg;
	}


	public void geheZu(Raum raum) {
		this.aktuellerRaum=raum;
	}

	public Raum getAktuellerRaum() {
		return aktuellerRaum;
	}

	/** 
	 */
	public void essen(String name) {
		for(Gegenstand g: this.gegenstaende) {
			if(g.getName().equalsIgnoreCase(name)) {
				// Ist g ein Objekt vom Typ Essen
				if(g instanceof Essen) {
					Essen e=(Essen)g;
					this.tragkraft+=e.getBonus();
					this.hunger += e.getEssen();
					this.gegenstaende.remove(g);
					return;
				}
			}
		}
	}

	public void benutzen(String name) {
		for(Gegenstand g: this.gegenstaende) {
			if(g.getName().equalsIgnoreCase(name)) {
				// Ist t ein Objekt vom Typ traenke
				if(g instanceof Heilungstraenke) {
					Heilungstraenke t=(Heilungstraenke)g;
					this.lebenspunkte+=t.getHeilungsBonus();
					this.gegenstaende.remove(g);
					return;
				} else if (g instanceof Krafttraenke) {
					Krafttraenke t=(Krafttraenke)g;
					this.tragkraft+=t.getKraftBonus();
					this.gegenstaende.remove(g);
					return;
				}
			}
		}
	}

	public void sleep() {

		System.out.println("Ich schlaf dann mal");
	}

	public void ruesten() {
		if (this.ruestung > 15) {
			System.out.println("Du kannst keine Rüstung mehr ausrüsten!");
		}
	}


	public void ruestung(String name) {
		Gegenstand g = getGegenstandByName(name);
		if (g instanceof Helm) {
			Helm he = (Helm) g;
			this.ruestung += he.getRuestung();
		}
		if (g instanceof Brust) {
			Brust b = (Brust) g;
			this.ruestung += b.getRuestung();

		}
		if (g instanceof Hose) {
			Hose ho = (Hose) g;
			this.ruestung += ho.getRuestung();
		}
		if (g instanceof Schuhe) {
			Schuhe s = (Schuhe) g;
			this.ruestung += s.getRuestung();
		}
	}

	public void schaden(String name) {
		Gegenstand g= getGegenstandByName(name);
		if (g instanceof Waffen) {
			Waffen wa = (Waffen) g;
			this.schaden += wa.getSchaden();
		}
	}



	private Gegenstand getGegenstandByName(String name) {
		for (Gegenstand g : this.gegenstaende) {
			if (g.getName().equalsIgnoreCase(name)) {
				return g;
			}
		}
		return null;
	}
}