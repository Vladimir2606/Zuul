package zuul;

import zuul.r�stung.Helm;
import zuul.items.Essen;
import zuul.items.Gegenstand;
import zuul.items.Heilungstraenke;
import zuul.items.Krafttraenke;
import zuul.r�stung.Brust;
import zuul.r�stung.Hose;
import zuul.r�stung.Schuhe;


import java.util.HashMap;

public class WorldGenerator {

	HashMap<String, Raum> alleRaume;

	public WorldGenerator() {
		this.alleRaume = new HashMap<>();
		this.raeumeAnlegen();
		this.setzeAusgaenge();
		this.addGegenstaende();
		this.addEssen();
		this.addWaffen();
		this.addRuestungen();
		this.addMonster();
		addTraenke();
	}

	private Raum getZufaelligerRaum() {

		int zufaelligeRaumZahl = (int) ((Math.random()) * alleRaume.size() + 0);
		return (Raum)alleRaume.values().toArray()[zufaelligeRaumZahl];
	}

	/** legt die R�ume an mit beschreibung, k�lte und beim Teleporter noch den WorldGenerator
	 */
	private void raeumeAnlegen() {

		this.alleRaume.put("lichtung", new Raum("auf einer Lichtung, umgeben von dunklen Tannen", 12));
		this.alleRaume.put("waldstueck", new Raum("im dunklen Wald", 13));
		this.alleRaume.put("taverne", new Raum("in der Taverne, mit zwielichten Gestalten an der Theke", 20));
		this.alleRaume.put("hexenhaus", new Raum("im Hexenhaus, mit einem gr��em Symbol auf dem Boden", 18));
		this.alleRaume.put("dorfplatz", new Raum("auf dem Dorfplatz", 15));
		this.alleRaume.put("piratenHoehle", new Raum("in einer kalten und nassen alten Piratenh�hle", 4));
		this.alleRaume.put("kellerDerTaverne", new Raum("im Keller der Taverne", 9));
		this.alleRaume.put("geheimgang", new Raum("in einem schmalen modrigen Geheimgang", 7));
		this.alleRaume.put("taverneErsterStock", new Raum("bei den G�stezimmern im ersten Stock der Taverne", 20));
		this.alleRaume.put("teleporter", new TeleporterRaum("in einem kleinem kaltem Raum mit einem gr��em Symbol auf dem Boden", 3, this));
		this.alleRaume.put("troph�enHalle", new Raum("in einer gro�en Halle voller Troph�en", 21));
		this.alleRaume.put("gef�ngnis", new Raum("in dem Gef�ngnis von Zuul", 17));
		this.alleRaume.put("zelle1", new Raum("in einer leeren Zelle", 13));
		this.alleRaume.put("zelle2", new Raum("in einer kleine leeren Zelle", 13));
		this.alleRaume.put("gartenDerTaverne", new Raum("in einm kleinem Garten", 14));
		this.alleRaume.put("kirche", new Raum("in der heiligen Kirche", 19));
		this.alleRaume.put("hotel", new Raum("im Hotel des Dorfes", 20));
		this.alleRaume.put("strandweg", new Raum("auf einem Weg am Strand", 12));
		this.alleRaume.put("strand", new Raum("am Strand", 9));
		this.alleRaume.put("hotelErsterStock", new Raum("im erstem Stock des Hotels", 19));

		/*
		 * lichtung = new Raum("auf einer Lichtung, umgeben von dunklen Tannen", 12);
		 * waldstueck = new Raum("im dunklen Wald", 13); taverne = new
		 * Raum("in der Taverne, mit zwielichten Gestalten an der Theke", 20); hexenhaus
		 * = new Raum("im Hexenhaus, mit einem gr��em Symbol auf dem Boden", 18);
		 * dorfplatz = new Raum("auf dem Dorfplatz", 15); piratenHoehle = new
		 * Raum("in einer kalten und nassen alten Piratenh�hle", 2); kellerDerTaverne =
		 * new Raum("im Keller der Taverne", 9); geheimgang = new
		 * Raum("in einem schmalen modrigen Geheimgang", 7); taverneErsterStock=new
		 * Raum("bei den den G�stezimmern im ersten Stock der Taverne", 20); teleporter
		 * = new
		 * Raum("in einem kleinem kaltem Raum mit einem gr��em Symbol auf dem Boden",
		 * 3);
		 */
	}

	/** die Ausg�nge initialisieren/ den R�umen ein befehlswort zum raumwechsel
	 * und den ausgang (Raum) zuweisen
	 */ 
	private void setzeAusgaenge() {
		Raum zufaelligerRaum = getZufaelligerRaum();

		this.alleRaume.get("lichtung").setAusgang("down", this.alleRaume.get("piratenHoehle"));
		this.alleRaume.get("lichtung").setAusgang("east", this.alleRaume.get("waldstueck"));
		this.alleRaume.get("lichtung").setAusgang("north", this.alleRaume.get("kirche"));
		this.alleRaume.get("lichtung").setAusgang("west", this.alleRaume.get("hotel"));
		this.alleRaume.get("waldstueck").setAusgang("west", this.alleRaume.get("lichtung"));
		this.alleRaume.get("waldstueck").setAusgang("south", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("dorfplatz").setAusgang("west", this.alleRaume.get("hexenhaus"));
		this.alleRaume.get("dorfplatz").setAusgang("north", this.alleRaume.get("waldstueck"));
		this.alleRaume.get("dorfplatz").setAusgang("south", this.alleRaume.get("taverne"));
		this.alleRaume.get("dorfplatz").setAusgang("east", this.alleRaume.get("troph�enHalle"));
		this.alleRaume.get("hexenhaus").setAusgang("east", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("hexenhaus").setAusgang("south", this.alleRaume.get("gef�ngnis"));
		this.alleRaume.get("hexenhaus").setAusgang("teleport", zufaelligerRaum);
		this.alleRaume.get("taverne").setAusgang("north", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("taverne").setAusgang("up", this.alleRaume.get("taverneErsterStock"));
		this.alleRaume.get("taverne").setAusgang("down", this.alleRaume.get("kellerDerTaverne"));
		this.alleRaume.get("taverne").setAusgang("hintert�r", this.alleRaume.get("gartenDerTaverne"));
		this.alleRaume.get("taverneErsterStock").setAusgang("down", this.alleRaume.get("taverne"));
		this.alleRaume.get("taverneErsterStock").setAusgang("window", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("kellerDerTaverne").setAusgang("up", this.alleRaume.get("taverne"));
		this.alleRaume.get("kellerDerTaverne").setAusgang("north", this.alleRaume.get("geheimgang"));
		this.alleRaume.get("gartenDerTaverne").setAusgang("hintert�r", this.alleRaume.get("taverne"));
		this.alleRaume.get("geheimgang").setAusgang("south", this.alleRaume.get("kellerDerTaverne"));
		this.alleRaume.get("geheimgang").setAusgang("east", this.alleRaume.get("piratenHoehle"));
		this.alleRaume.get("geheimgang").setAusgang("north", this.alleRaume.get("teleporter"));
		this.alleRaume.get("piratenHoehle").setAusgang("west",this.alleRaume.get("geheimgang"));
		this.alleRaume.get("piratenHoehle").setAusgang("up", this.alleRaume.get("lichtung"));
		this.alleRaume.get("teleporter").setAusgang("south", this.alleRaume.get("geheimgang"));
		this.alleRaume.get("teleporter").setAusgang("teleport", zufaelligerRaum);
		this.alleRaume.get("troph�enHalle").setAusgang("west", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("gef�ngnis").setAusgang("north", this.alleRaume.get("hexenhaus"));
		this.alleRaume.get("gef�ngnis").setAusgang("zelle1", this.alleRaume.get("zelle1"));
		this.alleRaume.get("gef�ngnis").setAusgang("zelle2", this.alleRaume.get("zelle2"));
		this.alleRaume.get("zelle1").setAusgang("vorraum", this.alleRaume.get("gef�ngnis"));
		this.alleRaume.get("zelle2").setAusgang("vorraum", this.alleRaume.get("gef�ngnis"));
		this.alleRaume.get("kirche").setAusgang("south", this.alleRaume.get("lichtung"));
		this.alleRaume.get("kirche").setAusgang("north", this.alleRaume.get("strandweg"));
		this.alleRaume.get("strandweg").setAusgang("south", this.alleRaume.get("kirche"));
		this.alleRaume.get("strandweg").setAusgang("east", this.alleRaume.get("strand"));
		this.alleRaume.get("strand").setAusgang("west", this.alleRaume.get("strandweg"));
		this.alleRaume.get("hotel").setAusgang("east", this.alleRaume.get("lichtung"));
		this.alleRaume.get("hotel").setAusgang("up", this.alleRaume.get("hotelErsterStock"));
		this.alleRaume.get("hotelErsterStock").setAusgang("down", this.alleRaume.get("hotel"));

	}

	/** erstellt Gegenstaende und legt sie in einem Raum ab
	 */
	private void addGegenstaende() {

		this.alleRaume.get("taverne").gegenstandAblegen(new Gegenstand("Bierkrug", "ein leckeres dunkles Pils in einem edlen Krug", 2));
		this.alleRaume.get("taverne").gegenstandAblegen(new Gegenstand("Teller", "ein Teller mit deftigem Wildschweinfleisch mit So�e", 5));
		this.alleRaume.get("strand").gegenstandAblegen(new Gegenstand("Schatztruhe", "eine mit Gold gef�llte Holzkiste", 40));
		this.alleRaume.get("piratenHoehle").gegenstandAblegen(new Gegenstand("Schwert", "das Schwert des alten Piratenkapitäns", 10));
		this.alleRaume.get("lichtung").gegenstandAblegen(new Gegenstand("Korb", "ein Weidenkorb gef�llt mit Brot", 4));
		this.alleRaume.get("kirche").gegenstandAblegen(new Gegenstand("Kerzenst�nder", "ein mit Gold verzierter Kerzenst�nder", 5));
	}

	/** erstellt Essen und legt sie in einem Raum ab
	 */
	private void addEssen() {

		this.alleRaume.get("waldstueck").gegenstandAblegen(new Essen("Pilz", "ein seltsam-magisch aussehender Pilz", 1, 20, 0));
		this.alleRaume.get("lichtung").gegenstandAblegen(new Essen("Muffin", "lecker lecker", 1, 5, 20));
		this.alleRaume.get("dorfplatz").gegenstandAblegen(new Essen("Steak", "f�gt 4 Hungerpunkte hinzu", 1, 0, 4));
		this.alleRaume.get("kellerDerTaverne").gegenstandAblegen(new Essen("Bier", "ein Glas Bier", 1, 0, 1));
		this.alleRaume.get("hexenhaus").gegenstandAblegen(new Essen("Fleisch", "ein vergiftetes St�ck Fleisch", 1, 0, -2));
		this.alleRaume.get("zelle1").gegenstandAblegen(new Essen("Kackhaufen", "ein gro�er gl�nzender haufen Kacke", 5, 0, -5));
		this.alleRaume.get("gartenDerTaverne").gegenstandAblegen(new Essen("Gurke", "eine leckere Gurke", 2, 0, 6));
	}

	private void addTraenke() {

		this.alleRaume.get("kirche").gegenstandAblegen(new Heilungstraenke("Heilungstrank", "ein kleiner Trank der das leben ein wenig auff�llt", 1, 3));
		this.alleRaume.get("taverneErsterStock").gegenstandAblegen(new Heilungstraenke("Heilungstrank", "ein kleiner Trank der das leben ein wenig auff�llt", 1, 3));
		this.alleRaume.get("hexenhaus").gegenstandAblegen(new Heilungstraenke("Heilungstrank", "ein kleiner Trank der das leben ein wenig auff�llt", 1, 3));
		this.alleRaume.get("strandweg").gegenstandAblegen(new Heilungstraenke("Heilungstrank", "ein mittlerer Trank der das leben auff�llt", 2, 5));
		this.alleRaume.get("gef�ngnis").gegenstandAblegen(new Heilungstraenke("Heilungstrank", "ein mittlerer Trank der das leben auff�llt", 2, 5));
		this.alleRaume.get("hotelErsterStock").gegenstandAblegen(new Heilungstraenke("Heilungstrank", "ein gro�er Trank der das leben viel auff�llt", 5, 10));
		this.alleRaume.get("zelle2").gegenstandAblegen(new Heilungstraenke("Heilungstrank", "ein gro�er Trank der das leben viel auff�llt", 5, 10));
		this.alleRaume.get("hotelErsterStock").gegenstandAblegen(new Krafttraenke("Krafttrank", "ein kleiner Trank der schwerer tragen l�sst", 2, 5));
		this.alleRaume.get("strand").gegenstandAblegen(new Krafttraenke("Krafttrank", "ein kleiner Trank der schwerer tragen l�sst", 2, 5));
		this.alleRaume.get("geheimgang").gegenstandAblegen(new Krafttraenke("Krafttrank", "ein gro�er Trank der schwerer tragen l�sst", 5, 10));
		this.alleRaume.get("lichtung").gegenstandAblegen(new Krafttraenke("Krafttrank", "ein gro�er Trank der schwerer tragen l�sst", 5, 10));
	}

	/** erstellt Waffen und legt sie in einem Raum ab
	 */
	private void addWaffen() {

		this.alleRaume.get("lichtung").gegenstandAblegen(new Waffen("Dolch", "ein spitzer Dolch", 3, 3));
		this.alleRaume.get("hexenhaus").gegenstandAblegen(new Waffen("Pizzaroller", "ein gef�hlicher und t�tlicher Pizzaroller", 5, 7));
		this.alleRaume.get("troph�enHalle").gegenstandAblegen(new Waffen("ThorsHammer", "Der m�chtige Hammer Thors durchstr�mt von Blitzen", 25, 15));
		this.alleRaume.get("taverneErsterStock").gegenstandAblegen(new Waffen("Langschwert", "Ein gro�es Schwert aus Metall", 12, 13));
		this.alleRaume.get("strandweg").gegenstandAblegen(new Waffen("Messer", "Ein altes verrostetes Messer", 3, 2));
	}

	/** erstellt R�stungen und legt sie in einem Raum ab
	 */
	private void addRuestungen() {

		this.alleRaume.get("lichtung").gegenstandAblegen(new Helm("Stahlhelm", "ein massiver Stahlhelm", 6, 3));
		this.alleRaume.get("hotel").gegenstandAblegen(new Helm("Holzhaube", "einfach aber sch�tzend", 3, 2));
		this.alleRaume.get("troph�enHalle").gegenstandAblegen(new Helm("LokisHelm", "ein magisch gl�nzender Helm aus Plastahl", 9, 7));

		this.alleRaume.get("taverne").gegenstandAblegen(new Brust("Stahlbrustplatte", "aus dicken Stahlplatten", 10, 8));
		this.alleRaume.get("strandweg").gegenstandAblegen(new Brust("Plastahlbrustplatte", "aus geh�rtetem Plastahl", 16, 12));

		this.alleRaume.get("dorfplatz").gegenstandAblegen(new Hose("Stoffhose", "sch�tzt ein wenig vor Angriffen", 1, 1));
		this.alleRaume.get("kellerDerTaverne").gegenstandAblegen(new Hose("Kettenhemdhose", "eine Kettenhemdhose, wie die Soldaten sie tragen", 4, 5));

		this.alleRaume.get("piratenHoehle").gegenstandAblegen(new Schuhe("Stahlschuhe", "massive Schuhe mit eingen�hten Stahlplatten", 6, 4));
		this.alleRaume.get("geheimgang").gegenstandAblegen(new Schuhe("Lederschuhe", "ein paar Schuhe aus Rinderleder", 3, 2));
	}

	/** @retrun gibt den Startraum wieder
	 */
	public Raum getStartRaum() {
		return this.alleRaume.get("lichtung");
	}

	private void addMonster() {
		this.alleRaume.get("waldstueck").setMonster(new Monster("Harald", "ist ein Org und besch�tzt das wladst�ck", 3, 1, 0.5));
	}

}
