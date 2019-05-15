
package zuul;

public class Gegenstand {

    private String name;
    private String beschreibung;
    private int gewicht;

    public Gegenstand(String name, String beschreibung, int gewicht) {
        this.beschreibung=beschreibung;
        this.name=name;
        this.gewicht=gewicht;
    }
    
    //* @return gibt den namen, die beschreibung und das gewicht wieder
    @Override
    public String toString() {
        return name + ", " + this.beschreibung +", " + this.gewicht+"kg";
    }

    //* @return gibt das gewicht wieder
    public int getGewicht() {
        return this.gewicht;
    }

    public String getName() {
        return this.name;
    }
}
