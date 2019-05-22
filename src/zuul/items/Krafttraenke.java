package zuul.items;

public class Krafttraenke extends Gegenstand {

	private int kraftbonus;

	public Krafttraenke(String name, String beschreibung, int gewicht, int kraftbonus) {
		super(name, beschreibung, gewicht);
		this.kraftbonus = kraftbonus;
	}
	
	/** @return gibt die kraftbonuspunkte wieder
	 */
	public int getKraftBonus() {
		return this.kraftbonus;
	}
	
}
