package zuul;

import java.util.List;

import zuul.items.Gegenstand;

import java.util.ArrayList;

public class Monster {

	private String name;
	private String beschreibung;
	private int schaden;
	private int lebenspunkte;
	private int rüstung;
	private boolean agro;
	private List<Gegenstand> gegenstaende = new ArrayList<>();
	private int levelpunkte;

	public Monster(String name, String beschreibung, int lebenspunkte, int rüstung,
			int schaden, int levelpunkte, boolean agro) {
		this.name=name;
		this.beschreibung=beschreibung;
		this.lebenspunkte=lebenspunkte;
		this.rüstung=rüstung;
		this.schaden=schaden;
		this.agro=agro;
		this.levelpunkte = levelpunkte;
	}

	@Override
	public String toString() {
		return name + ", " + this.beschreibung + ", " + this.schaden + " dmg" + ", " + this.lebenspunkte + " HP" + ", " + this.rüstung +" Rüstung";
	}

	public String getName(){
		return this.name;
	}

	/**
	 * testet ob das Monster agressiv ist
	 * @return
	 * @author tiago
	 */
	public boolean testAgro() {
		return agro;
	}

	public double getLeben() {
		return this.lebenspunkte;
	}

	public int getSchaden() {
		return this.schaden;
	}

	/**
	 * Reduziert das leben vom Monster
	 * @param schaden
	 * @author tiago
	 */
	public void reduziereLeben(int schaden) {
		this.lebenspunkte-=schaden;
	}

	/** Droppt das item im Raum
	 * 
	 * @param raum: der Raum wird übergeben um das Item darin zu Droppen
	 * @author tiago
	 */
	public String dropItem(Raum raum) {
		String erg;
		for(int i = 0; i<= gegenstaende.size(); i++) {
			raum.gegenstandAblegen(gegenstaende.get(i));
			erg = "Das Monster hat ein/ne " + gegenstaende.get(i).getName() + " gedroppt ";
			this.gegenstaende.remove(gegenstaende.get(i));
			return erg;
		}
		return null;
	}

	/**
	 * fügt ein Gegenstand ins Monster
	 * @param neuerGegenstand
	 * @author tiago
	 */
	public void gegenstandAufnehmen(Gegenstand neuerGegenstand) {
		this.gegenstaende.add(neuerGegenstand);
	}
	
	public int getLevelpunkte() {
		return this.levelpunkte;
	}
}
