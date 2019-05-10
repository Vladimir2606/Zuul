package zuul;

public class Essen extends Gegenstand{

    private int bonus;
    private int essenspunkte;

    public Essen(String name, String beschreibung, int gewicht, int bonus, int essenspunkte) {

        super(name, beschreibung, gewicht);
        this.bonus = bonus;
        this.essenspunkte = essenspunkte;
    }
    
    //* @return gibt die bonuspunkte des Essens wieder
    public int getBonus() {
        return this.bonus;
    }
    
    //* @return gibt die essenspunkte des Essens wieder
    public int getEssen() {
    	return this.essenspunkte;
    }
}
