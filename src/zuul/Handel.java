package zuul;

public class Handel {

	private Haendler haendler;
	private Spieler spieler;
	private Raum raum;

	public Handel(Haendler haendler, Spieler spieler, Raum raum) {
		this.haendler = haendler;
		this.spieler = spieler;
		this.raum = raum;
	}

	public String handelAusgeben() {
		String erg = "";
		for (int i = 0; i < haendler.getVerkaufsGegenstaende().size(); i++) {
			erg = haendler.verkaufsGegenstaendeAusgeben();
		}
		return erg;
	}

}