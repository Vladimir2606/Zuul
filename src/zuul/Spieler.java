
package zuul;

import zuul.rüstung.Brust;
import zuul.rüstung.Helm;
import zuul.rüstung.Hose;
import zuul.rüstung.Rüstung;
import zuul.rüstung.Schuhe;
import java.util.ArrayList;
import zuul.items.Essen;
import zuul.items.Gegenstand;
import zuul.items.HandelsWaren;
import zuul.items.Heilungstraenke;
import zuul.items.Krafttraenke;

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
	private Waffen waffen;
	private int ruestung;
	private int schaden;
	private int goldtaler;

	/** @param tragkraft = das maximalgewicht in kg das getragen werden kann
	 *	@param hunger = die hungerpunkte des spielers, wie satt er ist
	 *	@param lebenspunkte = wie viel leben der spieler hat
	 *	@param auszuhaltendeKaelte = die niedrichste temperatur, welche er aushält ohne schaden zuzunehmen
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
		this.goldtaler = 50;
	}

	/**
	 * 
	 */
	public String frieren() {
		if (aktuellerRaum.getTemperatur() <= auszuhaltendeKaelte) {
			this.lebenspunkte -= 0.5;
		}
		nochAmLeben();
		return "Du frierst!";
	}

	public String nochAmLeben() {
		if (this.lebenspunkte <= 0) {
			spiel.quit();
			return "Du bist gestorben!";
		}
		return "";
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
		nochAmLeben();
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


	public boolean gegenstandKaufen(String name) {
		HandelsWaren gesucht=this.aktuellerRaum.getHaendler().sucheVerkaufsGegenstand(name);
		if(gesucht==null) {
			return false;
		} else {
			if(ermittleGewicht()+gesucht.getGewicht()<=this.tragkraft && this.goldtaler >= 
					this.aktuellerRaum.getHaendler().sucheVerkaufsGegenstand(name).getPreis()) {

				this.goldtaler -= gesucht.getPreis();
				this.gegenstaende.add(gesucht.getGegenstand());			//***********************TODO
				this.aktuellerRaum.entferneVerkaufsGegenstand(gesucht);
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
		erg += this.ruestung + " Rüstungspunkte\n";
		erg += this.goldtaler +" Goldtaler\n";
		erg += "Auszuhaltende Kälte: "+this.auszuhaltendeKaelte;
		return erg;
	}

	/**foo
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

	public void benutzen(String name) {
		for(Gegenstand g: this.gegenstaende) {
			if(g.getName().equalsIgnoreCase(name)) {
				if(g instanceof Heilungstraenke) {
					Heilungstraenke h=(Heilungstraenke)g;
					this.lebenspunkte +=h.getBonus();
					this.gegenstaende.remove(g);
					return;
				} else if (g instanceof Krafttraenke) {
					Krafttraenke k=(Krafttraenke)g;
					this.tragkraft +=k.getBonus();
					this.gegenstaende.remove(g);
					return;
				}
			}
		}
	}

	/**
	 * 
	 */
	public String sleep() {
		return "Ich schlaf dann mal";
	}

	/**
	 * Die Methode bestimmt die maximale Punktzahl der Rüstung.
	 */
	public String ruesten() {
		if (this.ruestung > 15) {
			return "Du kannst keine Rüstung mehr ausrüsten";
		}
		return "Rüstung erfolgreich ausgerüstet";
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public String entwaffnen(String name) {
		for(Gegenstand g: this.gegenstaende) {
			if(g.getName().equalsIgnoreCase(name)) {
				this.gegenstaende.remove(g);
				this.aktuellerRaum.gegenstandAblegen(g);
				this.schaden -= ((Waffen)g).getSchaden();
				if(g instanceof Waffen) {
					this.waffen = null;
					return "Waffe wurde entfernt";
				}
			}
		}
		return "Du hast keine Waffe bei dir!";
	}

	/**
	 * Man kann Rüstungen ausrüsten und 
	 * bekommt Rüstungspunkte und die 
	 * Rüstung sorgt dafür das der Spieler mehr 
	 * Kaelte aushalten kann.
	 * @param name
	 */
	public String ruestung(String name) {
		Gegenstand g = getGegenstandByName(name);
		if (this.waffen == null) {
			if (g instanceof Waffen) {
				this.waffen = (Waffen) g;
				return "Waffe wurde ausgerüstet!";
			}
		}
		if (this.helm == null) {
			if (g instanceof Helm) {
				this.helm = (Helm) g;
				this.ruestung += this.helm.getRuestung();
				this.auszuhaltendeKaelte -= 1;
				return "Rüstungsstück wurde ausgerüstet!";
			}
		}
		if (this.brust == null) {
			if (g instanceof Brust) {
				this.brust = (Brust) g;
				this.ruestung += this.brust.getRuestung();
				this.auszuhaltendeKaelte -= 4;
				return "Rüstungsstück wurde ausgerüstet!";
			}
		}
		if (this.hose == null) {
			if (g instanceof Hose) {
				this.hose = (Hose) g;
				this.ruestung += this.hose.getRuestung();
				this.auszuhaltendeKaelte -= 3;
				return "Rüstungsstück wurde ausgerüstet!";
			}
		}
		if (this.schuhe == null) {
			if (g instanceof Schuhe) {
				this.schuhe = (Schuhe) g;
				this.ruestung += this.schuhe.getRuestung();
				this.auszuhaltendeKaelte -= 2;
				return "Rüstungsstück wurde ausgerüstet!";
			}
		}
		return "Du hast bereits einen Gegenstand ausgerüstet oder es gibt diese Waffe nicht!";
	}

	/**
	 * Die Methode sorgt dafür das man
	 * die Rüstung ausziehen kann.
	 * @param name
	 * @return
	 */
	public String entrüsten(String name) {
		for(Gegenstand g: this.gegenstaende) {
			if(g.getName().equalsIgnoreCase(name)) {
				this.gegenstaende.remove(g);
				this.aktuellerRaum.gegenstandAblegen(g);
				
					if(g instanceof Waffen) {
						this.schaden -= ((Waffen)g).getSchaden();
						this.waffen = null;
						return "Waffe wurde entfernt";
					}
				if(g instanceof Helm) {
					this.ruestung -= ((Rüstung)g).getRuestung();
					this.helm = null;
					this.auszuhaltendeKaelte += 1;
					return "Helm ausgezogen, es wird kälter...";
				} 
				if(g instanceof Brust) {
					this.ruestung -= ((Rüstung)g).getRuestung();
					this.brust = null;
					this.auszuhaltendeKaelte += 4;
					return "Brust ausgezogen, es wird kälter...";
				} 
				if(g instanceof Hose) {
					this.ruestung -= ((Rüstung)g).getRuestung();
					this.hose = null;
					this.auszuhaltendeKaelte += 3;
					return "Hose ausgezogen, es wird kälter...";
				} 
				if(g instanceof Schuhe) {
					this.ruestung -= ((Rüstung)g).getRuestung();
					this.schuhe = null;
					this.auszuhaltendeKaelte += 2;
					return "Schuhe ausgezogen, es wird kälter...";
				} 
			}
		}
		return "Diesen Gegenstand gibt es nicht!";
	}

	public int getLeben() {
		return (int) lebenspunkte;
	}

	public int getSchaden() {
		return schaden;
	}

	public String reduziereLeben(int schaden) {
		if (ruestung >= 10) {
			if (schaden - 2.5 >= 0) {
				lebenspunkte -= (schaden - 2.5);
				return "";
			} else {
				lebenspunkte -= 0.25;
			}
		}
		if (ruestung >= 9) {
			if (schaden - 2.25 >= 0) {
				lebenspunkte -= (schaden - 2.25);
				return "";
			} else {
				lebenspunkte -= 0.25;
			}
		}
		if (ruestung >= 8) {
			if (schaden - 2 >= 0) {
				lebenspunkte -= (schaden - 2);
				return "";
			} else {
				lebenspunkte -= 0.25;
			}
		}
		if (ruestung >= 7) {
			if (schaden - 1.75 >= 0) {
				lebenspunkte -= (schaden - 1.75);
				return "";
			} else {
				lebenspunkte -= 0.25;
			}
		}
		if (ruestung >= 6) {
			if (schaden - 1.5 >= 0) {
				lebenspunkte -= (schaden - 1.5);
				return "";
			} else {
				lebenspunkte -= 0.25;
			}
		}
		if (ruestung >= 5) {
			if (schaden - 1.25 >= 0) {
				lebenspunkte -= (schaden - 1.25);
				return "";
			} else {
				lebenspunkte -= 0.25;
			}
		}
		if (ruestung >= 4) {
			if (schaden - 1 >= 0) {
				lebenspunkte -= (schaden - 1);
				return "";
			} else {
				lebenspunkte -= 0.25;
			}
		}
		if (ruestung >= 3) {
			lebenspunkte -= (schaden - 0.75);
			return "";
		}
		if (ruestung >= 2) {
			lebenspunkte -= (schaden - 0.5);
			return "";
		}
		return "Sie haben nicht genug Rüstungspunkte";
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<Gegenstand> getGegenstand() {
		return this.gegenstaende;
	}

	public int getTragkraft() {
		return this.tragkraft;
	}

	public int getGoldtaler() {
		return this.goldtaler;
	}

}