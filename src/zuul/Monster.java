package zuul;

public class Monster {

	private String name;
	private String beschreibung;
	private int lebenspunkte;
	private int r�stung;
	
	public Monster(String name, String beschreibung, int lebenspunkte, int r�stung) {
		this.name=name;
		this.beschreibung=beschreibung;
		this.lebenspunkte=lebenspunkte;
		this.r�stung=r�stung;
		}
	
	@Override
    public String toString() {
        return name + ", " + this.beschreibung + ", " + this.lebenspunkte + " HP" + ", " + this.r�stung +" R�stung";
    }
    
    public String getName(){
    	return this.name;
    }
}
