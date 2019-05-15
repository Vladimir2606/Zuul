package zuul;

public class Monster {

	private String name;
	private String beschreibung;
	private double schaden;
	private int lebenspunkte;
	private int r�stung;
	
	public Monster(String name, String beschreibung, int lebenspunkte, int r�stung, double schaden) {
		this.name=name;
		this.beschreibung=beschreibung;
		this.lebenspunkte=lebenspunkte;
		this.r�stung=r�stung;
		this.schaden=schaden;
		}
	
	@Override
    public String toString() {
        return name + ", " + this.beschreibung + ", " + this.schaden + "dmg" + this.lebenspunkte + " HP" + ", " + this.r�stung +" R�stung";
    }
    
    public String getName(){
    	return this.name;
    }
}
