package zuul;

import java.util.ArrayList;

import zuul.rüstung.Brust;
import zuul.rüstung.Helm;
import zuul.rüstung.Hose;
import zuul.rüstung.Schuhe;

public class Spieler {

	private Raum aktuellerRaum;
	private int tragkraft;
	private Spiel spiel;
	private ArrayList<Gegenstand> gegenstaende;
	private Hose hose;
	private Brust brust;
	private Helm helm;
	private Schuhe schuhe;
	private int hunger;
	private int lebenspunkte;
	private int ruestung;

	public Spieler(Spiel spiel) {
		this.gegenstaende = new ArrayList<>();
		this.tragkraft = 30;
		this.hunger = 10;
		this.lebenspunkte = 20;
		this.ruestung = 0;
		this.spiel = spiel;
	}

	/**
	 * 
	 */
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

	/**
	 * 
	 * @return
	 */

	public int ermittleGewicht() {
		int gesamtgewicht = 0;

		// this.gegenstaende wird durchlaufen
		// Jeder Gegenstand in der Liste wird einmal
		// in der Variablen g abgespeichert
		for (Gegenstand g : this.gegenstaende) {
			// a = a + b oder a+=b
			gesamtgewicht += g.getGewicht();
		}
		return gesamtgewicht;
	}

	/**
	 *
	 * 
	 * Dieser Gegenstand sollte dann im aktuellen Raum gesucht werden (Methode dafür
	 * erstellen!). Sofern dieser Gegenstand mit diesem Namen existiert und sofern
	 * die Tragkraft es zulässt, wird dieser Gegenstand aufgenommen.
	 *
	 * Das bedeutet natürlich, dass der Raum diesen Gegenstand dann nicht mehr haben
	 * kann (Methode dafür erstellen!).
	 *
	 * Die Methode gegenstandAufnehmen() liefert dann true oder false zurück, je
	 * nachdem ob es geklappt hat oder nicht.
	 */
	public boolean gegenstandAufnehmen(String name) {
		Gegenstand gesucht = this.aktuellerRaum.sucheGegenstand(name);
		if (gesucht == null) {
			return false;
		} else {
			if (ermittleGewicht() + gesucht.getGewicht() <= this.tragkraft) {
				this.gegenstaende.add(gesucht);
				this.aktuellerRaum.entferneGegenstand(gesucht);
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean gegenstandAblegen(String name) {
		for (Gegenstand g : this.gegenstaende) {
			if (g.getName().equalsIgnoreCase(name)) {
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
		erg += this.ruestung + " Rüstungspunkte\n";
		return erg;
	}

	public void geheZu(Raum raum) {
		this.aktuellerRaum = raum;
	}

	public Raum getAktuellerRaum() {
		return aktuellerRaum;
	}

	/**
	 * 
	 * @param name
	 */
	public void essen(String name) {
		Gegenstand g = getGegenstandByName(name);
		if (g instanceof Essen) {
			Essen e = (Essen) g;
			this.tragkraft += e.getBonus();
			this.hunger += e.getEssen();
			this.gegenstaende.remove(g);
			return;
		}
	}

	/**
	 * 
	 */
	public void ruesten() {
		if (this.ruestung > 15) {
			System.out.println("Du kannst keine Rüstung mehr ausrüsten!");
		}
	}

	/**
	 * 
	 * @param name
	 */
	public void ruestung(String name) {
		Gegenstand g = getGegenstandByName(name);
		if (this.helm == null) {
			if (g instanceof Helm) {
				this.helm = (Helm) g;
				this.ruestung += this.helm.getRuestung();
				System.out.println("Rüstungsstück wurde ausgerüstet!");
			} else if (this.helm != null) {
				this.ruestung -= this.helm.getRuestung();
				this.gegenstaende.remove(g);
				this.aktuellerRaum.gegenstandAblegen(this.helm);
			}
		} else {
			System.out.println("Du kannst nur einen Helm ausrüsten");
		}
		if (this.brust == null) {
			if (g instanceof Brust) {
				this.brust = (Brust) g;
				this.ruestung += this.brust.getRuestung();
				System.out.println("Rüstungsstück wurde ausgerüstet!");
			} 
		} else {
			System.out.println("Du kannst nur eine Brust ausrüsten");
		}
		if (this.hose == null) {
			if (g instanceof Hose) {
				this.hose = (Hose) g;
				this.ruestung += this.hose.getRuestung();
				System.out.println("Rüstungsstück wurde ausgerüstet!");
			} 
		} else {
			System.out.println("Du kannst nur eine Hose ausrüsten");
		}
		if (this.schuhe == null) {
			if (g instanceof Schuhe) {
				this.schuhe = (Schuhe) g;
				this.ruestung += this.schuhe.getRuestung();
				System.out.println("Rüstungsstück wurde ausgerüstet!");
			}
		} else {
			System.out.println("Du kannst nur ein paar Schuhe ausrüsten");
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

	/**
	 * 
	 */
	public void sleep() {
		System.out.println("Ich schlaf dann mal");
	}
}
