package zuul;

public class Monster {

	private String name;
	private String beschreibung;
	private double schaden;
	private int lebenspunkte;
	private int rüstung;
	
	public Monster(String name, String beschreibung, int lebenspunkte, int rüstung, double schaden) {
		this.name=name;
		this.beschreibung=beschreibung;
		this.lebenspunkte=lebenspunkte;
		this.rüstung=rüstung;
		this.schaden=schaden;
		}
	
	@Override
    public String toString() {
        return name + ", " + this.beschreibung + ", " + this.schaden + "dmg" + this.lebenspunkte + " HP" + ", " + this.rüstung +" Rüstung";
    }
    
    public String getName(){
    	return this.name;
    }
}
