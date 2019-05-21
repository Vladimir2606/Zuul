package zuul;

import zuul.items.Gegenstand;

public class Waffen extends Gegenstand {

	private double schaden;
	
	public Waffen(String name, String beschreibung, int gewicht, double schaden) {
		super(name, beschreibung, gewicht);
		this.schaden=schaden;
	}

	public double getSchaden() {
		return this.schaden;
	}
}
