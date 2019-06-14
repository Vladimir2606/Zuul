
package zuul;

import zuul.r�stung.Brust;
import zuul.r�stung.Helm;
import zuul.r�stung.Hose;
import zuul.r�stung.R�stung;
import zuul.r�stung.Schuhe;
import java.util.ArrayList;
import zuul.items.Essen;
import zuul.items.Gegenstand;

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
	private int schaden;

	/** @param tragkraft = das maximalgewicht in kg das getragen werden kann
	 *	@param hunger = die hungerpunkte des spielers, wie satt er ist
	 *	@param lebenspunkte = wie viel leben der spieler hat
	 *	@param auszuhaltendeKaelte = die niedrichste temperatur, welche er aush�lt ohne schaden zuzunehmen
	 * @return 
	 */
	public  Spieler(Spiel spiel) {
		this.gegenstaende=new ArrayList<>();
		this.tragkraft = 30;
		this.hunger = 10;
		this.lebenspunkte = 20;
		this.auszuhaltendeKaelte = 12;
		this.ruestung = 0;
		this.spiel = spiel;
		this.schaden = 1;
	}

	/**
	 * 
	 */
	public String nochAmLeben() {
		if (this.lebenspunkte <= 0) {
			spiel.quit();
			return "Du bist gestorben!";
		}
		return "";
	}
	
	public String frieren() {
		if (aktuellerRaum.getTemperatur() <= auszuhaltendeKaelte) {
			this.lebenspunkte -= 0.5;
		}
		if (this.lebenspunkte <= 0) {
			spiel.quit();
			return "Du bist gestorben!";
		}
		return "Du frierst!";
	}

	/**
	 * Nach jedem GoCommand wird ein Hungerpunkt
	 * abgezogen falls die Hungerpunkte auf 0 sind
	 * wird ein Lebenspunkt abgezogen.
	 * Wenn der Spieler keine Leben hat startet das Spiel neu.
	 */
	public String hungern() {
		this.hunger -= 1;
		if (this.hunger <= 0) {
			this.hunger = 0;
			this.lebenspunkte -= 1;
		}
		if (this.lebenspunkte <= 0) {
			spiel.quit();
			return "Du bist gestorben!";
		}
		return "Du bist noch am Leben!";
	}

	/**
	 * Das Gesamtgewicht was der Spieler noch tragen kann.
	 * @return
	 */
	public int ermittleGewicht() {
		int gesamtgewicht=0;
		for(Gegenstand g: this.gegenstaende) {
			gesamtgewicht += g.getGewicht();
		}
		return gesamtgewicht;
	}

	/**
	 *
	 * Dieser Gegenstand sollte dann im aktuellen Raum
	 * gesucht werden (Methode daf�r erstellen!).
	 * Sofern dieser Gegenstand mit diesem Namen
	 * existiert und sofern die Tragkraft es zul�sst,
	 * wird dieser Gegenstand aufgenommen.
	 *
	 * Das bedeutet nat�rlich, dass der Raum diesen
	 * Gegenstand dann nicht mehr haben kann
	 * (Methode daf�r erstellen!).
	 *
	 * Die Methode gegenstandAufnehmen() liefert dann
	 * true oder false zur�ck, je nachdem ob es
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

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean gegenstandAblegen(String name) {
		for(Gegenstand g: this.gegenstaende) {
			if(g.getName().equalsIgnoreCase(name)) {
				this.gegenstaende.remove(g);
				this.aktuellerRaum.gegenstandAblegen(g);
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public String zeigeStatus() {
		String erg="Ich kann insgesamt ";
		erg += this.tragkraft + "kg tragen\n";
		for(Gegenstand g: this.gegenstaende) {
			erg += " - " + g.getName() + " " + g.getGewicht()+"kg\n";
		}
		erg += this.tragkraft - ermittleGewicht() + "kg kann ich noch tragen!\n";
		erg += "Ich habe noch\n ";
		erg += this.hunger + " Hungerpunkte\n ";
		erg += this.lebenspunkte +" Lebenspunkte\n ";
		erg += this.ruestung + " R�stungspunkte\n";
		erg += "Auszuhaltende K�lte: "+this.auszuhaltendeKaelte;

		return erg;
	}

	/**
	 * 
	 * @param raum
	 */
	public void geheZu(Raum raum) {
		this.aktuellerRaum=raum;
	}

	/**
	 * 
	 * @return
	 */
	public Raum getAktuellerRaum() {
		return aktuellerRaum;
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
	 * @param name
	 */
	public String essen(String name) {
		for(Gegenstand g: this.gegenstaende) {
			if(g.getName().equalsIgnoreCase(name)) {
				if(g instanceof Essen) {
					Essen e=(Essen)g;
					this.tragkraft+=e.getBonus();
					this.hunger += e.getEssen();
					this.gegenstaende.remove(g);
					return "Du hast gegessen";
				}
			}
		}
		return "Diesen Gegenstand gibt es nicht!";
	}

	/**
	 * 
	 */
	public String sleep() {
		return "Ich schlaf dann mal";
	}

	/**
	 * Die Methode bestimmt die maximale Punktzahl der R�stung.
	 */
	public String ruesten() {
		if (this.ruestung > 15) {
			return "Du kannst keine R�stung mehr ausr�sten";
		}
		return "R�stung erfolgreich ausger�stet";
	}

	/**
	 * Man kann R�stungen ausr�sten und 
	 * bekommt R�stungspunkte und die 
	 * R�stung sorgt daf�r das der Spieler mehr 
	 * Kaelte aushalten kann.
	 * @param name
	 */
	public String ruestung(String name) {
		Gegenstand g = getGegenstandByName(name);
		if (this.helm == null) {
			if (g instanceof Helm) {
				this.helm = (Helm) g;
				this.ruestung += this.helm.getRuestung();
				this.auszuhaltendeKaelte -= 1;
				return "R�stungsst�ck wurde ausger�stet!";
			}
		}
		if (this.brust == null) {
			if (g instanceof Brust) {
				this.brust = (Brust) g;
				this.ruestung += this.brust.getRuestung();
				this.auszuhaltendeKaelte -= 4;
				return "R�stungsst�ck wurde ausger�stet!";
			}
		}
		if (this.hose == null) {
			if (g instanceof Hose) {
				this.hose = (Hose) g;
				this.ruestung += this.hose.getRuestung();
				this.auszuhaltendeKaelte -= 3;
				return "R�stungsst�ck wurde ausger�stet!";
			}
		}
		if (this.schuhe == null) {
			if (g instanceof Schuhe) {
				this.schuhe = (Schuhe) g;
				this.ruestung += this.schuhe.getRuestung();
				this.auszuhaltendeKaelte -= 2;
				return "R�stungsst�ck wurde ausger�stet!";
			}
		}
		return "Diese R�stung gibt es nicht!";
	}

	/**
	 * Die Methode sorgt daf�r das man
	 * die R�stung ausziehen kann.
	 * @param name
	 * @return
	 */
	public String entr�sten(String name) {
		for(Gegenstand g: this.gegenstaende) {
			if(g.getName().equalsIgnoreCase(name)) {
				this.gegenstaende.remove(g);
				this.aktuellerRaum.gegenstandAblegen(g);
				this.ruestung -= ((R�stung)g).getRuestung();
				if(g instanceof Helm) {
					this.helm = null;
					this.auszuhaltendeKaelte += 1;
					return "Helm ausgezogen, es wird k�lter...";
				} 
				if(g instanceof Brust) {
					this.brust = null;
					this.auszuhaltendeKaelte += 4;
					return "Brust ausgezogen, es wird k�lter...";
				} 
				if(g instanceof Hose) {
					this.hose = null;
					this.auszuhaltendeKaelte += 3;
					return "Hose ausgezogen, es wird k�lter...";
				} 
				if(g instanceof Schuhe) {
					this.schuhe = null;
					this.auszuhaltendeKaelte += 2;
					return "Schuhe ausgezogen, es wird k�lter...";
				} 
			}
		}
		return "Diese R�stung gibt es nicht";
	}

	public int getLeben() {
		return (int) lebenspunkte;
	}

	public int getSchaden() {
		return schaden;
	}

	public void reduziereLeben(int schaden) {
		lebenspunkte-=schaden;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<Gegenstand> getGegenstand() {
		return this.gegenstaende;
	}
}

