package zuul.items;

public class HandelsWaren {

	private int preis;
	private Gegenstand gegenstand;

	public HandelsWaren(Gegenstand gegenstand, int preis) {
		this.preis = preis;
		this.gegenstand = gegenstand;
	}

	public int getPreis() {
		return this.preis;
	}

	public String getName() {
		return this.gegenstand.getName();
	}
	
	public int getGewicht() {
		return this.gegenstand.getGewicht();
	}

	public Gegenstand getGegenstand() {
		return gegenstand;
	}
}
