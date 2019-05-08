package zuul;

public class Essen extends Gegenstand{

    private int bonus;
    private int essenspunkte;

    public Essen(String name, String beschreibung, int gewicht, int bonus, int essenspunkte) {

        super(name, beschreibung, gewicht);
        this.bonus = bonus;
        this.essenspunkte = essenspunkte;
    }
    
    public int getBonus() {
        return this.bonus;
    }
    
    public int getEssen() {
    	return this.essenspunkte;
    }
}
