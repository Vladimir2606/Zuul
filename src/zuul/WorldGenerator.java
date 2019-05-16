
package zuul;

import zuul.rüstung.Helm;
import zuul.rüstung.Brust;
import zuul.rüstung.Hose;
import zuul.rüstung.Schuhe;

import java.util.HashMap;

public class WorldGenerator {

	HashMap<String, Raum> alleRaume;
	//private TeleporterRaum teleporterRaum;

	public WorldGenerator() {
		this.alleRaume = new HashMap<>();
		this.raeumeAnlegen();
		this.setzeAusgaenge();
		this.addGegenstaende();
		this.addMonster();
	}

	private Raum getZufaelligerRaum() {

		int zufaelligeRaumZahl = (int) ((Math.random()) * alleRaume.size() + 0);
		return (Raum)alleRaume.values().toArray()[zufaelligeRaumZahl];
	}

	/** legt die Räume an mit beschreibung (String) und kälte (int) und beem Teleporter noch den worldGenerator (this).
	 */
	private void raeumeAnlegen() {

		this.alleRaume.put("lichtung", new Raum("auf einer Lichtung, umgeben von dunklen Tannen", 12));
		this.alleRaume.put("waldstueck", new Raum("im dunklen Wald", 13));
		this.alleRaume.put("taverne", new Raum("in der Taverne, mit zwielichten Gestalten an der Theke", 20));
		this.alleRaume.put("hexenhaus", new TeleporterRaum("im Hexenhaus, mit einem größem Symbol auf dem Boden", 18, this));
		this.alleRaume.put("dorfplatz", new Raum("auf dem Dorfplatz", 15));
		this.alleRaume.put("piratenHoehle", new Raum("in einer kalten und nassen alten Piratenhöhle", 2));
		this.alleRaume.put("kellerDerTaverne", new Raum("im Keller der Taverne", 9));
		this.alleRaume.put("geheimgang", new Raum("in einem schmalen modrigen Geheimgang", 7));
		this.alleRaume.put("taverneErsterStock", new Raum("bei den den Gästezimmern im ersten Stock der Taverne", 20));
		this.alleRaume.put("teleporter", new TeleporterRaum("in einem kleinem kaltem Raum mit einem größem Symbol auf dem Boden", 3, this));
		this.alleRaume.put("trophäenHalle", new Raum("in einer großen Halle voller Trophäen", 21));

		/*
		 * lichtung = new Raum("auf einer Lichtung, umgeben von dunklen Tannen", 12);
		 * waldstueck = new Raum("im dunklen Wald", 13); taverne = new
		 * Raum("in der Taverne, mit zwielichten Gestalten an der Theke", 20); hexenhaus
		 * = new Raum("im Hexenhaus, mit einem größem Symbol auf dem Boden", 18);
		 * dorfplatz = new Raum("auf dem Dorfplatz", 15); piratenHoehle = new
		 * Raum("in einer kalten und nassen alten Piratenhöhle", 2); kellerDerTaverne =
		 * new Raum("im Keller der Taverne", 9); geheimgang = new
		 * Raum("in einem schmalen modrigen Geheimgang", 7); taverneErsterStock=new
		 * Raum("bei den den Gästezimmern im ersten Stock der Taverne", 20); teleporter
		 * = new
		 * Raum("in einem kleinem kaltem Raum mit einem größem Symbol auf dem Boden",
		 * 3);
		 */
	}

	/** die Ausgänge initialisieren/ den Räumen ein befehlswort zum raumwechsel
	 * und den ausgang (Raum) zuweisen
	 */ 
	private void setzeAusgaenge() {
		Raum zufaelligerRaum = getZufaelligerRaum();

		this.alleRaume.get("lichtung").setAusgang("down", this.alleRaume.get("piratenhoehle"));
		this.alleRaume.get("lichtung").setAusgang("east", this.alleRaume.get("waldstueck"));
		this.alleRaume.get("waldstueck").setAusgang("west", this.alleRaume.get("lichtung"));
		this.alleRaume.get("waldstueck").setAusgang("south", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("dorfplatz").setAusgang("west", this.alleRaume.get("hexenhaus"));
		this.alleRaume.get("dorfplatz").setAusgang("north", this.alleRaume.get("waldstueck"));
		this.alleRaume.get("dorfplatz").setAusgang("south", this.alleRaume.get("taverne"));
		this.alleRaume.get("dorfplatz").setAusgang("east", this.alleRaume.get("trophäenHalle"));
		this.alleRaume.get("hexenhaus").setAusgang("east", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("hexenhaus").setAusgang("teleport", zufaelligerRaum);
		this.alleRaume.get("taverne").setAusgang("north", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("taverne").setAusgang("up", this.alleRaume.get("taverneErsterStock"));
		this.alleRaume.get("taverne").setAusgang("down", this.alleRaume.get("kellerDerTaverne"));
		this.alleRaume.get("taverneErsterStock").setAusgang("down", this.alleRaume.get("taverne"));
		this.alleRaume.get("taverneErsterStock").setAusgang("window", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("kellerDerTaverne").setAusgang("up", this.alleRaume.get("taverne"));
		this.alleRaume.get("kellerDerTaverne").setAusgang("north", this.alleRaume.get("geheimgang"));
		this.alleRaume.get("geheimgang").setAusgang("south", this.alleRaume.get("kellerDerTaverne"));
		this.alleRaume.get("geheimgang").setAusgang("east", this.alleRaume.get("piratenHoehle"));
		this.alleRaume.get("geheimgang").setAusgang("north", this.alleRaume.get("teleporter"));
		this.alleRaume.get("piratenHoehle").setAusgang("west",this.alleRaume.get("geheimgang"));
		this.alleRaume.get("piratenHoehle").setAusgang("up", this.alleRaume.get("lichtung"));
		this.alleRaume.get("teleporter").setAusgang("south", this.alleRaume.get("geheimgang"));
		this.alleRaume.get("teleporter").setAusgang("teleport", zufaelligerRaum);
		this.alleRaume.get("trophäenHalle").setAusgang("west", this.alleRaume.get("dorfplatz"));

	}

	/** legt fest welche Gegenstaende mit Name (string), Beschreibung (String) und
	 * Gewicht (int)
	 * und bei Essen zusätzlich noch einen Bonuswert (int) und Essenswert (int)
	 * zum satt werden
	 */
	private void addGegenstaende() {

		this.alleRaume.get("taverne").gegenstandAblegen(new Gegenstand("Bierkrug", "ein leckeres dunkles Pils in einem edlen Krug", 2));
		this.alleRaume.get("taverne").gegenstandAblegen(new Gegenstand("Teller", "ein Teller mit deftigem Wildschweinfleisch mit Soße", 5));
		this.alleRaume.get("piratenHoehle").gegenstandAblegen(new Gegenstand("Schatztruhe", "eine mit Gold gefüllte Holzkiste", 40));
		this.alleRaume.get("piratenHoehle").gegenstandAblegen(new Gegenstand("Schwert", "das Schwert des alten PiratenkapitÃ¤ns", 10));
		this.alleRaume.get("lichtung").gegenstandAblegen(new Gegenstand("Korb", "ein Weidenkorb gefüllt mit Brot", 4));
		
		this.alleRaume.get("waldstueck").gegenstandAblegen(new Essen("Pilz", "ein seltsam aussehender Pilz", 1, 20, 0));
		this.alleRaume.get("lichtung").gegenstandAblegen(new Essen("Muffin", "lecker lecker", 1, 5, 0));
		this.alleRaume.get("dorfplatz").gegenstandAblegen(new Essen("Steak", "fügt 4 Hungerpunkte hinzu", 1, 0, 4));
		this.alleRaume.get("kellerDerTaverne").gegenstandAblegen(new Essen("Bier", "ein Glas Bier", 1, 0, 1));
		this.alleRaume.get("hexenhaus").gegenstandAblegen(new Essen("Fleisch", "ein vergiftetes Stück Fleisch", 1, 0, -2));
		this.alleRaume.get("trophäenHalle").gegenstandAblegen(new Essen("Kackhaufen", "ein großer glänzender haufen Kacke", 5, 0, -5));
		
		this.alleRaume.get("lichtung").gegenstandAblegen(new Waffen("Dolch", "ist ein spitzer Dolch", 3, 2));
		this.alleRaume.get("hexenhaus").gegenstandAblegen(new Waffen("Pizzaroller", "ein gefählicher und tötlicher Pizzaroller", 5, 7));
		this.alleRaume.get("trophäenHalle").gegenstandAblegen(new Waffen("Thors Hammer", "Der mächtige Hammer Thors durchströmt von Blitzen", 25, 15));
		
		this.alleRaume.get("lichtung").gegenstandAblegen(new Helm("Stahlhelm", "schützt vor Angriffen", 6, 3));
		this.alleRaume.get("lichtung").gegenstandAblegen(new Helm("Holzhaube", "schützt vor Angriffen", 3, 2));
		
		this.alleRaume.get("taverne").gegenstandAblegen(new Brust("Stahlbrust", "schützt vor Angriffen", 10, 4));
		
		this.alleRaume.get("dorfplatz").gegenstandAblegen(new Hose("Stoffhose", "schützt eher weniger vor Angriffen", 1, 1));
		
		this.alleRaume.get("piratenHoehle").gegenstandAblegen(new Schuhe("Stahlschuhe", "schützt vor Angriffen", 6, 3));
	}

	// * @retrun gibt den Startraum wieder
	public Raum getStartRaum() {
		return this.alleRaume.get("lichtung");
	}

	private void addMonster() {
		this.alleRaume.get("waldstueck").setMonster(new Monster("Harald", "ist ein Org und beschützt das wladstück", 3, 1));
	}

}
