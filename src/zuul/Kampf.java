package zuul;

public class Kampf {
	
	private Spieler spieler;
	private Monster monster;
	boolean angreifer = true;
	private Raum raum;
	
	public Kampf(Spieler spieler, Monster monster, Raum raum) {
		this.spieler = spieler;
		this.monster = monster;
		this.raum = raum;
	}
	
	/**
	 * 
	 * @author tiago
	 */
	public String kaempfen() {
		String erg = "Kampf beginnt";
		erg += "Leben vom Spieler: "+spieler.getLeben()+"	Leben vom Monster: "+monster.getLeben();
		if(monster.testAgro()) {
			angriff();
			angreifer = false;
		}
		while(beideAmLeben()){
			angriff();
			angreifer = !angreifer;
		}
		nachDemKampf();
		spieler.nochAmLeben();
		return erg;
	}
	
	/**
	 * 
	 * @author tiago
	 */
	public void angriff() {
		if(angreifer) {
			monster.reduziereLeben(spieler.getSchaden());
		} else {
			spieler.reduziereLeben(monster.getSchaden());
		}
	}
	
	/**
	 * prüft ob Spieler und Monster am leben sind
	 * @return
	 * @author tiago
	 */
	public boolean beideAmLeben() {
		if(spieler.getLeben()>0 && monster.getLeben()>0) {
			return true;
		}
		return false;
	}

	public void nachDemKampf() {
		monster.dropItem(raum);
		spieler.setLevelpunkte(monster.getLevelpunkte());
		raum.monsterEntfernen(monster); 
	}
	
}
