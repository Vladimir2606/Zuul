package zuul;

public class Waffen extends Gegenstand {

	private int schaden;
	
	public Waffen(String name, String beschreibung, int gewicht, int schaden) {
		super(name, beschreibung, gewicht);
		this.schaden=schaden;
	}

}
