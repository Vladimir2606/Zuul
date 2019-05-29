package zuul.items;

public class traenke extends Gegenstand{

	protected int bonus;
	
	public traenke(String name, String beschreibung, int gewicht, int bonus) {
		super(name, beschreibung, gewicht);
		this.bonus = bonus;
	}
}
