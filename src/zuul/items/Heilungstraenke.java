package zuul.items;

public class Heilungstraenke extends traenke{
	
	public Heilungstraenke(String name, String beschreibung, int gewicht, int bonus) {
		super(name, beschreibung, gewicht, bonus);
		
	}
	
	/** @return gibt die Heilungsbonuspunkte wieder
	 */
    public int getBonus() {
        return this.bonus;
    }
    
}
