package zuul.items;

public class Heilungstraenke extends Gegenstand{

	private int heilungsbonus;
	
	public Heilungstraenke(String name, String beschreibung, int gewicht, int heilungsbonus) {
		super(name, beschreibung, gewicht);
		this.heilungsbonus = heilungsbonus;
	}
	
	/** @return gibt die Heilungsbonuspunkte wieder
	 */
    public int getHeilungsBonus() {
        return this.heilungsbonus;
    }

}
