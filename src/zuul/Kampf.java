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
	
	public void kaempfen() {
		System.out.println("Kampf beginnt");
		System.out.println("Leben vom Spieler: " + spieler.getLeben() + "	Leben vom Monster: " + monster.getLeben());
		if(monster.testAgro()) {
			angreifer = false;
		}
		while(beideAmLeben()){
			angriff();
			angreifer = !angreifer;
		}
		System.out.println("Leben vom Spieler: " + spieler.getLeben());
		nachDemKampf();
	}
	
	public void angriff() {
		if(angreifer) {
			monster.reduziereLeben(spieler.getSchaden());
		} else {
			spieler.reduziereLeben(monster.getSchaden());
		}
	}
	
	public boolean beideAmLeben() {
		if(spieler.getLeben()>0 && monster.getLeben()>0) {
			return true;
		}
		return false;
	}
	
	public void nachDemKampf() {
		monster.dropItem(raum);
		raum.monsterEntfernen(monster);
	}
	
}
