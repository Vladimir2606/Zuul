package zuul.items;

public class Schriftrolle  extends Gegenstand{

	private int bonusKraft;
	
	public Schriftrolle(String name, String beschreibung, int gewicht, int bonusKraft) {
		super(name, beschreibung, gewicht);
		this.bonusKraft = bonusKraft;
	}
	
	public int getBonusKraft() {
		return this.bonusKraft;
	}
}
