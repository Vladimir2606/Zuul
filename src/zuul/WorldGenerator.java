package zuul;

import zuul.r�stung.Helm;
import zuul.items.Essen;
import zuul.items.Gegenstand;
import zuul.items.HandelsWaren;
import zuul.items.Heilungstraenke;
import zuul.items.Krafttraenke;
import zuul.items.Schriftrolle;
import zuul.r�stung.Brust;
import zuul.r�stung.Hose;
import zuul.r�stung.Schuhe;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldGenerator {

	HashMap<String, Raum> alleRaume;

	private Monster harald, hexe1, piratenSkellet, gefallenerGefangener, untoterW�chter, priester, clown, vergiftetePutzfrau, gollum, riese;
	private Haendler willi, hexe, marian;

	public WorldGenerator() {
		this.alleRaume = new HashMap<>();
		this.raeumeAnlegen();
		this.setzeAusgaenge();
		this.addGegenstaende();
		this.addEssen();
		this.addWaffen();
		this.addRuestungen();
		this.addMonster();
		this.addTraenke();
		this.addSchriftrollen();
		addHaendler();
	}

	private Raum getZufaelligerRaum() {

		int zufaelligeRaumZahl = (int) ((Math.random()) * alleRaume.size() + 0);
		return (Raum)alleRaume.values().toArray()[zufaelligeRaumZahl];
	}

	/** legt die R�ume an mit beschreibung, k�lte und beim Teleporter noch den WorldGenerator
	 */
	private void raeumeAnlegen() {

		this.alleRaume.put("lichtung", new Raum("auf einer Lichtung, umgeben von dunklen Tannen", 12, 1));
		this.alleRaume.put("waldstueck", new Raum("im dunklen Wald", 13, 2));
		this.alleRaume.put("taverne", new Raum("in der Taverne, mit zwielichten Gestalten an der Theke", 20, 3));
		this.alleRaume.put("hexenhaus", new Raum("im Hexenhaus, mit einem gr��em Symbol auf dem Boden", 18, 2));
		this.alleRaume.put("dorfplatz", new Raum("auf dem Dorfplatz", 15, 2));
		this.alleRaume.put("piratenHoehle", new Raum("in einer kalten und nassen alten Piratenh�hle", 4, 1));
		this.alleRaume.put("kellerDerTaverne", new Raum("im Keller der Taverne", 9, 3));
		this.alleRaume.put("geheimgang", new Raum("in einem schmalen modrigen Geheimgang", 7, 1));
		this.alleRaume.put("taverneErsterStock", new Raum("bei den G�stezimmern im ersten Stock der Taverne", 20, 3));
		this.alleRaume.put("teleporter", new TeleporterRaum("in einem kleinem kaltem Raum mit einem gr��em Symbol auf dem Boden", 3, 1, this));
		this.alleRaume.put("troph�enHalle", new Raum("in einer gro�en Halle voller Troph�en", 21, 6));
		this.alleRaume.put("gef�ngnis", new Raum("in dem Gef�ngnis von Zuul", 17, 4));
		this.alleRaume.put("zelle1", new Raum("in einer leeren Zelle", 13, 4));
		this.alleRaume.put("zelle2", new Raum("in einer kleinen leeren Zelle", 13, 4));
		this.alleRaume.put("gartenDerTaverne", new Raum("in einm kleinem Garten", 14, 3));
		this.alleRaume.put("kirche", new Raum("in der heiligen Kirche", 19, 5));
		this.alleRaume.put("hotel", new Raum("im Hotel des Dorfes", 20, 6));
		this.alleRaume.put("strandweg", new Raum("auf einem Weg am Strand", 12, 5));
		this.alleRaume.put("strand", new Raum("am Strand", 9, 5));
		this.alleRaume.put("hotelErsterStock", new Raum("im erstem Stock des Hotels", 19, 6));


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
	 * Die "Raumgruppen" werden zuf�llig mit einander verbunden
	 */ 
	private void setzeAusgaenge() {
		Raum zufaelligerRaum = getZufaelligerRaum();
		// Benutzte Gruppen-verbindungsw�rter:	Steinbr�cke, Wanderweg, Steinweg, Tannenpfad, Wanderpfad, Seeweg, Feldweg,
		//										Holzbr�cke, H�ngebr�cke, Waldweg, Tunnel,

		// Raumgruppe 1
		Raumgruppe raumgruppe1 = new Raumgruppe();

		raumgruppe1.addEinAusgang(new EinUndAusgang(this.alleRaume.get("lichtung"), "Steinbr�cke"));
		raumgruppe1.addEinAusgang(new EinUndAusgang(this.alleRaume.get("lichtung"), "Wanderweg"));

		this.alleRaume.get("lichtung").setAusgang("down", this.alleRaume.get("piratenHoehle"));
		this.alleRaume.get("piratenHoehle").setAusgang("up", this.alleRaume.get("lichtung"));
		this.alleRaume.get("piratenHoehle").setAusgang("west",this.alleRaume.get("geheimgang"));
		this.alleRaume.get("geheimgang").setAusgang("east", this.alleRaume.get("piratenHoehle"));
		this.alleRaume.get("geheimgang").setAusgang("north", this.alleRaume.get("teleporter"));
		this.alleRaume.get("geheimgang").setAusgang("south", this.alleRaume.get("kellerDerTaverne"));
		this.alleRaume.get("teleporter").setAusgang("south", this.alleRaume.get("geheimgang"));
		this.alleRaume.get("teleporter").setAusgang("teleport", zufaelligerRaum);


		// Raumgruppe 2
		Raumgruppe raumgruppe2 = new Raumgruppe();

		raumgruppe2.addEinAusgang(new EinUndAusgang(this.alleRaume.get("waldstueck"), "Steinweg"));
		raumgruppe2.addEinAusgang(new EinUndAusgang(this.alleRaume.get("hexenhaus"), "Tannenpfad"));

		this.alleRaume.get("dorfplatz").setAusgang("west", this.alleRaume.get("hexenhaus"));
		this.alleRaume.get("dorfplatz").setAusgang("north", this.alleRaume.get("waldstueck"));
		this.alleRaume.get("hexenhaus").setAusgang("east", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("hexenhaus").setAusgang("teleport", zufaelligerRaum);
		this.alleRaume.get("waldstueck").setAusgang("south", this.alleRaume.get("dorfplatz"));


		// Raumgruppe 3
		Raumgruppe raumgruppe3 = new Raumgruppe();

		raumgruppe3.addEinAusgang(new EinUndAusgang(this.alleRaume.get("gartenDerTaverne"), "Wanderpfad"));
		raumgruppe3.addEinAusgang(new EinUndAusgang(this.alleRaume.get("taverne"), "Seeweg"));

		this.alleRaume.get("taverneErsterStock").setAusgang("down", this.alleRaume.get("taverne"));
		this.alleRaume.get("taverneErsterStock").setAusgang("window", this.alleRaume.get("dorfplatz"));
		this.alleRaume.get("kellerDerTaverne").setAusgang("up", this.alleRaume.get("taverne"));
		this.alleRaume.get("kellerDerTaverne").setAusgang("north", this.alleRaume.get("geheimgang"));
		this.alleRaume.get("gartenDerTaverne").setAusgang("hintert�r", this.alleRaume.get("taverne"));
		this.alleRaume.get("taverne").setAusgang("up", this.alleRaume.get("taverneErsterStock"));
		this.alleRaume.get("taverne").setAusgang("down", this.alleRaume.get("kellerDerTaverne"));
		this.alleRaume.get("taverne").setAusgang("hintert�r", this.alleRaume.get("gartenDerTaverne"));


		// Raumgruppe 4
		Raumgruppe raumgruppe4 = new Raumgruppe();

		raumgruppe4.addEinAusgang(new EinUndAusgang(this.alleRaume.get("gef�ngnis"), "Feldweg"));

		this.alleRaume.get("gef�ngnis").setAusgang("zelle1", this.alleRaume.get("zelle1"));
		this.alleRaume.get("gef�ngnis").setAusgang("zelle2", this.alleRaume.get("zelle2"));
		this.alleRaume.get("zelle1").setAusgang("vorraum", this.alleRaume.get("gef�ngnis"));
		this.alleRaume.get("zelle2").setAusgang("vorraum", this.alleRaume.get("gef�ngnis"));


		// Raumgruppe 5
		Raumgruppe raumgruppe5 = new Raumgruppe();

		raumgruppe5.addEinAusgang(new EinUndAusgang(this.alleRaume.get("kirche"), "Holzbr�cke"));
		raumgruppe5.addEinAusgang(new EinUndAusgang(this.alleRaume.get("strand"), "H�ngebr�cke"));

		this.alleRaume.get("strandweg").setAusgang("east", this.alleRaume.get("strand"));
		this.alleRaume.get("strandweg").setAusgang("south", this.alleRaume.get("kirche"));
		this.alleRaume.get("strand").setAusgang("west", this.alleRaume.get("strandweg"));
		this.alleRaume.get("kirche").setAusgang("north", this.alleRaume.get("strandweg"));


		// Raumgruppe 6
		Raumgruppe raumgruppe6 = new Raumgruppe();

		raumgruppe6.addEinAusgang(new EinUndAusgang(this.alleRaume.get("hotel"), "Waldweg"));
		raumgruppe6.addEinAusgang(new EinUndAusgang(this.alleRaume.get("troph�enHalle"), "Tunnel"));

		this.alleRaume.get("hotel").setAusgang("up", this.alleRaume.get("hotelErsterStock"));
		this.alleRaume.get("hotel").setAusgang("south", this.alleRaume.get("troph�enHalle"));
		this.alleRaume.get("hotelErsterStock").setAusgang("down", this.alleRaume.get("hotel"));
		this.alleRaume.get("troph�enHalle").setAusgang("west", this.alleRaume.get("hotel"));

		ArrayList<Raumgruppe> gruppen=new ArrayList<Raumgruppe>();
		gruppen.add(raumgruppe1);
		gruppen.add(raumgruppe2);
		gruppen.add(raumgruppe3);
		gruppen.add(raumgruppe4);
		gruppen.add(raumgruppe5);
		gruppen.add(raumgruppe6);

		//Das muss hier bleiben, damit der Startraum zu 100% verbunden ist

		EinUndAusgang ea1=gruppen.get(0).getRandomEinAusgang();
		EinUndAusgang eaZ=gruppen.get((int)(Math.random()*gruppen.size())).getRandomEinAusgang();
		String weg=Math.random()<0.5?ea1.getBezeichnung():eaZ.getBezeichnung();
		ea1.getRaum().setAusgang(weg, eaZ.getRaum());
		eaZ.getRaum().setAusgang(weg, ea1.getRaum());

		//Hier die Schleife f�r die Raumverbindungen


		for (int i = 0; i < gruppen.size(); i++) {
			EinUndAusgang eaX = gruppen.get((int) (Math.random()*gruppen.size())).getRandomEinAusgang();
			EinUndAusgang eaY = gruppen.get((int) (Math.random()*gruppen.size())).getRandomEinAusgang();

			if(eaX != eaY&& eaX.getRaum().getRaumgruppe() != eaY.getRaum().getRaumgruppe()) {

				eaX=gruppen.get((int)(Math.random()*gruppen.size())).getRandomEinAusgang();
				eaY=gruppen.get((int)(Math.random()*gruppen.size())).getRandomEinAusgang();
				String wege=Math.random()<0.5?eaX.getBezeichnung():eaY.getBezeichnung();
				eaX.getRaum().setAusgang(wege, eaY.getRaum());
				eaY.getRaum().setAusgang(wege, eaX.getRaum());
			}

		}
	}

	/** erstellt Gegenstaende und legt sie in einem Raum ab
	 */
	private void addGegenstaende() {
		this.alleRaume.get("taverne").gegenstandAblegen(new Gegenstand("Bierkrug", "ein leckeres dunkles Pils in einem edlen Krug", 2));
		this.alleRaume.get("taverne").gegenstandAblegen(new Gegenstand("Teller", "ein Teller mit deftigem Wildschweinfleisch mit So�e", 5));
		this.alleRaume.get("strand").gegenstandAblegen(new Gegenstand("Schatztruhe", "eine mit Gold gef�llte Holzkiste", 40));
		this.alleRaume.get("piratenHoehle").gegenstandAblegen(new Gegenstand("Deko-Schwert", "das Schwert des alten Piratenkapitäns", 10));
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
		this.alleRaume.get("kirche").gegenstandAblegen(new Heilungstraenke("Heilungstrank-K", "ein kleiner Trank der das leben ein wenig auff�llt", 1, 3));
		this.alleRaume.get("taverneErsterStock").gegenstandAblegen(new Heilungstraenke("Heilungstrank-K", "ein kleiner Trank der das leben ein wenig auff�llt", 1, 3));
		this.alleRaume.get("hexenhaus").gegenstandAblegen(new Heilungstraenke("Heilungstrank-K", "ein kleiner Trank der das leben ein wenig auff�llt", 1, 3));
		this.alleRaume.get("strandweg").gegenstandAblegen(new Heilungstraenke("Heilungstrank-M", "ein mittlerer Trank der das leben auff�llt", 2, 5));
		this.alleRaume.get("gef�ngnis").gegenstandAblegen(new Heilungstraenke("Heilungstrank-M", "ein mittlerer Trank der das leben auff�llt", 2, 5));
		this.alleRaume.get("hotelErsterStock").gegenstandAblegen(new Heilungstraenke("Heilungstrank-G", "ein gro�er Trank der das leben viel auff�llt", 5, 10));
		this.alleRaume.get("zelle2").gegenstandAblegen(new Heilungstraenke("Heilungstrank-G", "ein gro�er Trank der das leben viel auff�llt", 5, 10));
		this.alleRaume.get("hotelErsterStock").gegenstandAblegen(new Krafttraenke("Krafttrank-K", "ein kleiner Trank der schwerer tragen l�sst", 2, 5));
		this.alleRaume.get("strand").gegenstandAblegen(new Krafttraenke("Krafttrank-K", "ein kleiner Trank der schwerer tragen l�sst", 2, 5));
		this.alleRaume.get("geheimgang").gegenstandAblegen(new Krafttraenke("Krafttrank-G", "ein gro�er Trank der schwerer tragen l�sst", 5, 10));
		this.alleRaume.get("lichtung").gegenstandAblegen(new Krafttraenke("Krafttrank-G", "ein gro�er Trank der schwerer tragen l�sst", 5, 10));
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

	private void addSchriftrollen() {
		this.alleRaume.get("zelle2").gegenstandAblegen(new Schriftrolle("Schriftrolle", "eine Schriftrolle die dein Schaden erh�ht", 1, 3));
		this.alleRaume.get("hotelErsterStock").gegenstandAblegen(new Schriftrolle("Schriftrolle", "eine Schriftrolle die dein Schaden erh�ht", 1, 3));
	}

	/** @retrun gibt den Startraum wieder
	 */
	public Raum getStartRaum() {
		return this.alleRaume.get("lichtung");
	}


	/**
	 * erstellt Monster und legt sie in einem Raum ab
	 */
	private void addMonster() {
		this.harald = new Monster("Harald", "ist ein Org und besch�tzt das wladst�ck", 3, 1, 2, true);
		this.alleRaume.get("waldstueck").setMonster(harald);
		this.harald.gegenstandAufnehmen(new Gegenstand("Ring", "des b�sen Orgs Harald", 1));

		this.gollum = new Monster("Gollum", "ist ein kleier h�sslicher und agressiever Gnom", 1, 1, 1, true);
		this.alleRaume.get("geheimgang").setMonster(gollum);
		this.gollum.gegenstandAufnehmen(new Gegenstand("Goldst�ck", "ein goldener Taler", 1));

		this.riese = new Monster("Riese", "ein gef�hrlich aussehender Riese", 1, 1, 1, false);
		this.alleRaume.get("strand").setMonster(riese);
		this.riese.gegenstandAufnehmen(new Waffen("Pizzaroller", "ein gef�hlicher und t�tlicher Pizzaroller", 5, 7));

		this.hexe1 = new Monster("Hexe1", "die ihr hexenhaus besch�tzt", 7, 2, 9, true);
		this.alleRaume.get("hexenhaus").setMonster(hexe1);
		this.hexe1.gegenstandAufnehmen(new Waffen("Hexenstab", "ist ein alter Zauberstab der nicht mehr funktioniert", 3, 5));

		this.piratenSkellet = new Monster("Piratenskellet", "besch�tzt das besch�digte Pirateschiff in der H�hle", 4, 4, 6, true);
		this.alleRaume.get("piratenHoehle").setMonster(piratenSkellet);
		this.piratenSkellet.gegenstandAufnehmen(new Brust("Skelletpanzer", "ist der Oberk�rper des Skellettes", 6, 8));

		this.gefallenerGefangener = new Monster("Gefallener Gefangener", "er ist ein gefangener der schon seit vielen jahren tot ist und noch die zelle besch�tzt", 8, 3, 8, false);
		this.alleRaume.get("zelle1").setMonster(gefallenerGefangener);
		this.gefallenerGefangener.gegenstandAufnehmen(new Hose("Hose", "des Gefallenen Gefangenden ", 3, 3));

		this.untoterW�chter = new Monster("Untoterw�chter", "er ist der w�chter des gef�ngnisses und hat sich selbst in der zelle engespert", 5, 3, 7, false);
		this.alleRaume.get("zelle2").setMonster(untoterW�chter);
		this.untoterW�chter.gegenstandAufnehmen(new Waffen("Schlagstock", "vom Untotenw�chter", 4, 8));

		this.priester = new Monster("Priester", "bewacht die gl�ubigen in dieser Welt", 12, 5, 15, false);
		this.alleRaume.get("kirche").setMonster(priester);
		this.priester.gegenstandAufnehmen(new Helm("Priesterhut", "wird dich besch�tzen wenn du ihn aufgesetzt hast", 2, 5));

		this.clown = new Monster("Clown", "ist in dem Hotel gestorben und geistert seit dem im Hotel herrum", 10, 4, 11, false);
		this.alleRaume.get("hotel").setMonster(clown);
		this.clown.gegenstandAufnehmen(new Schuhe("Clownschuhe", "sind dir viel zu gro�", 2, 3));

		this.vergiftetePutzfrau = new Monster("Vergiftete Putzfrau", "wurde von einem Gast vergiftet", 13, 5, 12, true);
		this.alleRaume.get("hotelErsterStock").setMonster(vergiftetePutzfrau);
		this.vergiftetePutzfrau.gegenstandAufnehmen(new Waffen("Besen", "damit wurde die Vergiftete Putzfrau geschlagen", 4, 6));
	}


	private void addHaendler() {
		this.willi = new Haendler("Willi", "\n   Waffenshop\n   Hier kannst du gegen Goldtaler Dinge kaufen");
		this.alleRaume.get("lichtung").setHaendler(willi);
		this.willi.gegenstandAufnehmen(new HandelsWaren(new Waffen("Messer", "Ein altes verrostetes Messer", 3, 2), 5));
		this.willi.gegenstandAufnehmen(new HandelsWaren(new Gegenstand("L�ffel", "Ein alte L�ffel", 1), 1));
		this.willi.gegenstandAufnehmen(new HandelsWaren(new Hose("Stoffhose", "sch�tzt ein wenig vor Angriffen", 1, 1), 5));
		this.willi.gegenstandAufnehmen(new HandelsWaren(new Heilungstraenke("Heilungstrank-G", "ein gro�er Trank der das leben viel auff�llt", 5, 10), 5));

		this.hexe = new Haendler("Hexe", "\n   Traekeshop\n   Hier kannst du gegen Goldtaler Dinge kaufen");
		this.alleRaume.get("hexenhaus").setHaendler(hexe);
		this.hexe.gegenstandAufnehmen(new HandelsWaren(new Heilungstraenke("Heilungstrank-K", "ein kleiner Trank der das leben ein wenig auff�llt", 1, 3), 2));
		this.hexe.gegenstandAufnehmen(new HandelsWaren(new Heilungstraenke("Heilungstrank-M", "ein mittlerer Trank der das leben auff�llt", 2, 5), 4));
		this.hexe.gegenstandAufnehmen(new HandelsWaren(new Heilungstraenke("Heilungstrank-G", "ein gro�er Trank der das leben viel auff�llt", 4, 10), 8));
		this.hexe.gegenstandAufnehmen(new HandelsWaren( new Krafttraenke("Krafttrank-K", "ein kleiner Trank der schwerer tragen l�sst", 2, 5), 4));
		this.hexe.gegenstandAufnehmen(new HandelsWaren( new Krafttraenke("Krafttrank-G", "ein gro�er Trank der schwerer tragen l�sst", 5, 10), 8));

		this.marian = new Haendler("Marian", "\n   Schriftrollenshop\n   Hier kannst du gegen Goldtaler Dinge kaufen");
		this.alleRaume.get("kirche").setHaendler(marian);
		this.marian.gegenstandAufnehmen(new HandelsWaren(new Schriftrolle("Schriftrolle-K", "eine Schriftrolle die dein Schaden ein wenig erh�ht", 1, 3), 10));
		this.marian.gegenstandAufnehmen(new HandelsWaren(new Schriftrolle("Schriftrolle-G", "eine Schriftrolle die dein Schaden erh�ht", 1, 8), 25));
	}

}	

