package zuul;

import java.util.List;
import java.util.ArrayList;

public class Monster {

	private String name;
	private String beschreibung;
	private int schaden;
	private int lebenspunkte;
	private int rüstung;
	private boolean agro;
	private List<Gegenstand> gegenstaende = new ArrayList<>();
	
	public Monster(String name, String beschreibung, int lebenspunkte, int rüstung, int schaden, boolean agro) {
		this.name=name;
		this.beschreibung=beschreibung;
		this.lebenspunkte=lebenspunkte;
		this.rüstung=rüstung;
		this.schaden=schaden;
		this.agro=agro;
		
		}
	
	@Override
    public String toString() {
        return name + ", " + this.beschreibung + ", " + this.schaden + "dmg" + ", " + this.lebenspunkte + " HP" + ", " + this.rüstung +" Rüstung";
    }
    
    public String getName(){
    	return this.name;
    }
    
    public boolean testAgro() {
    	return agro;
    }
    
    public int getLeben() {
    	return lebenspunkte;
    }
    
    public int getSchaden() {
    	return schaden;
    }
    
    public void reduziereLeben(int schaden) {
    	lebenspunkte-=schaden;
    }
    
    public void dropItem(Raum raum) {
    	for(int i = 0; i<= gegenstaende.size(); i++) {
    		raum.gegenstandAblegen(gegenstaende.get(i));
    		System.out.println("Das Monster hat ein/ne " + gegenstaende.get(i).getName() + " gedroppt ");
    		this.gegenstaende.remove(gegenstaende.get(i));
    	}
    }
    
    public void gegenstandAufnehmen(Gegenstand neuerGegenstand) {
		this.gegenstaende.add(neuerGegenstand);
	}
}
