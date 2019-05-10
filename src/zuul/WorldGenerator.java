package zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class WorldGenerator {
    private Raum lichtung, waldstueck, taverne, hexenhaus, dorfplatz, kellerDerTaverne, geheimgang, taverneErsterStock,
    				piratenHoehle, teleporter;
    
    private HashMap<String, Raum> alleRaume;
    
    
    public WorldGenerator() {
    	this.alleRaume = new HashMap<>();
    	this.raeumeAnlegen();
        this.setzeAusgaenge();
        this.addGegenstaende();
    }
    
    private Raum getZufaelligerRaum() {
    	Raum zufaelligerRaum = null;
    	Random r = new Random(alleRaume.size()); // Random r gib eine -... zahl zurück.. ?!*************************
    	System.out.println(r.nextInt()); // Diese Zeile weg..
    	zufaelligerRaum = new ArrayList<Raum>(alleRaume.values()).get(r.nextInt());
    	
    	return zufaelligerRaum;
    }
    
    //** legt die Räume an mit beschreibung (String) und kälte (int)
    private void raeumeAnlegen() {
    	
    	this.alleRaume.put("lichtung", new Raum("auf einer Lichtung, umgeben von dunklen Tannen", 12));
    	this.alleRaume.put("waldstueck", new Raum("im dunklen Wald", 13));
    	this.alleRaume.put("taverne", new Raum("in der Taverne, mit zwielichten Gestalten an der Theke", 20));
    	this.alleRaume.put("hexenhaus", new Raum("im Hexenhaus, mit einem größem Symbol auf dem Boden", 18));
    	this.alleRaume.put("dorfplatz", new Raum("auf dem Dorfplatz", 15));
    	this.alleRaume.put("piratenHoehle", new Raum("in einer kalten und nassen alten Piratenhöhle", 2));
    	this.alleRaume.put("kellerDerTaverne", new Raum("im Keller der Taverne", 9));
    	this.alleRaume.put("geheimgang", new Raum("in einem schmalen modrigen Geheimgang", 7));
    	this.alleRaume.put("taverneErsterStock", new Raum("bei den den Gästezimmern im ersten Stock der Taverne", 20));
    	this.alleRaume.put("teleporter", new Raum("in einem kleinem kaltem Raum mit einem größem Symbol auf dem Boden", 3));
    	
    	
  /*     
    	lichtung = new Raum("auf einer Lichtung, umgeben von dunklen Tannen", 12);
    	waldstueck = new Raum("im dunklen Wald", 13);
        taverne = new Raum("in der Taverne, mit zwielichten Gestalten an der Theke", 20);
        hexenhaus = new Raum("im Hexenhaus, mit einem größem Symbol auf dem Boden", 18);
        dorfplatz = new Raum("auf dem Dorfplatz", 15);
        piratenHoehle = new Raum("in einer kalten und nassen alten Piratenhöhle", 2);
        kellerDerTaverne = new Raum("im Keller der Taverne", 9);
        geheimgang = new Raum("in einem schmalen modrigen Geheimgang", 7);
        taverneErsterStock=new Raum("bei den den Gästezimmern im ersten Stock der Taverne", 20);
        teleporter = new Raum("in einem kleinem kaltem Raum mit einem größem Symbol auf dem Boden", 3);
   */
    }
    
//** die Ausgänge initialisieren/ den Räumen ein befehlswort zum raumwechsel und den ausgang (Raum) zuweisen
    private void setzeAusgaenge() {
    	Raum zufaelligerRaum = getZufaelligerRaum();
    	
        lichtung.setAusgang("down", piratenHoehle);
        lichtung.setAusgang("east", waldstueck);
        waldstueck.setAusgang("west", lichtung);
        waldstueck.setAusgang("south", dorfplatz);
        dorfplatz.setAusgang("west", hexenhaus);
        dorfplatz.setAusgang("north", waldstueck);
        dorfplatz.setAusgang("south", taverne);
        hexenhaus.setAusgang("east", dorfplatz);
        hexenhaus.setAusgang("teleport", zufaelligerRaum);
        taverne.setAusgang("north", dorfplatz);
        taverne.setAusgang("up", taverneErsterStock);
        taverne.setAusgang("down", kellerDerTaverne);
        taverneErsterStock.setAusgang("down", taverne);
        taverneErsterStock.setAusgang("window", dorfplatz);
        kellerDerTaverne.setAusgang("up", taverne);
        kellerDerTaverne.setAusgang("north", geheimgang);
        geheimgang.setAusgang("south", kellerDerTaverne);
        geheimgang.setAusgang("east", piratenHoehle);
        geheimgang.setAusgang("north", teleporter);
        piratenHoehle.setAusgang("west", geheimgang);
        piratenHoehle.setAusgang("up", lichtung);
        teleporter.setAusgang("south", geheimgang);
        teleporter.setAusgang("teleport", zufaelligerRaum);

    }

    	//** legt fest welche Gegenstaende mit Name (string), Beschreibung (String) und Gewicht (int)
    	//* und bei Essen zusätzlich noch einen Bonuswert (int) und Essenswert (int) zum satt werden
    private void addGegenstaende() {
        taverne.gegenstandAblegen(new Gegenstand("Bierkrug", "ein leckeres dunkles Pils in einem edlen Krug", 2));
        taverne.gegenstandAblegen(new Gegenstand("Teller", "ein Teller mit deftigem Wildschweinfleisch mit Soße", 5 ));
        piratenHoehle.gegenstandAblegen(new Gegenstand("Schatztruhe", "eine mit Gold gefüllte Holzkiste", 40));
        piratenHoehle.gegenstandAblegen(new Gegenstand("Schwert", "das Schwert des alten PiratenkapitÃ¤ns", 10));
        waldstueck.gegenstandAblegen(new Essen("Pilz", "ein seltsam aussehender Pilz", 1, 20, 0));
        lichtung.gegenstandAblegen(new Gegenstand("Korb", "ein Weidenkorb gefüllt mit Brot", 4));
        lichtung.gegenstandAblegen(new Essen("Muffin", "lecker lecker", 1, 5, 0));
        dorfplatz.gegenstandAblegen(new Essen("Steak", "fügt 4 Hungerpunkte hinzu", 1, 0, 4));


    }

    //* @retrun gibt den Startraum wieder
    public Raum getStartRaum() {
        return this.lichtung;
    }


}
