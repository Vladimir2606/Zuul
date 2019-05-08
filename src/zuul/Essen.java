package zuul;

public class Essen extends Gegenstand{

    private int bonus;
    private int essen;

    public Essen(String name, String beschreibung, int gewicht, int bonus, int essenspunkte) {

        super(name, beschreibung, gewicht);
        this.bonus = bonus;
        this.essen = essen;
    }
    
    public int getBonus() {
        return this.bonus;
    }
    
    public int getEssen() {
    	return this.essen;
    }
}
