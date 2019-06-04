package zuul.items;

public class Krafttraenke extends Traenke {

	public Krafttraenke(String name, String beschreibung, int gewicht, int bonus) {
		super(name, beschreibung, gewicht, bonus);
		
	}
	
	/** @return gibt die kraftbonuspunkte wieder
	 */
	public int getBonus() {
		return this.bonus;
	}
	
}
