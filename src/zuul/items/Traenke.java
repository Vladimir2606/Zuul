package zuul.items;

public class Traenke extends Gegenstand{

	protected int bonus;
	
	public Traenke(String name, String beschreibung, int gewicht, int bonus) {
		super(name, beschreibung, gewicht);
		this.bonus = bonus;
	}
}
