package zuul;

public class WorldGenerator {
    private Raum lichtung, waldstueck, taverne, hexenhaus, dorfplatz, kellerDerTaverne, geheimgang, taverneErsterStock,
    				piratenHoehle, teleporter;

    public WorldGenerator() {
        this.raeumeAnlegen();
        this.setzeAusgaenge();
        this.addGegenstaende();
    }

    private void raeumeAnlegen() {
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
    }

    private void setzeAusgaenge() {

        // die Ausgänge initialisieren
        lichtung.setAusgang("down", piratenHoehle);
        lichtung.setAusgang("east", waldstueck);
        waldstueck.setAusgang("west", lichtung);
        waldstueck.setAusgang("south", dorfplatz);
        dorfplatz.setAusgang("west", hexenhaus);
        dorfplatz.setAusgang("north", waldstueck);
        dorfplatz.setAusgang("south", taverne);
        hexenhaus.setAusgang("east", dorfplatz);
        hexenhaus.setAusgang("teleport", teleporter);
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
        teleporter.setAusgang("teleport", hexenhaus);

    }


    private void addGegenstaende() {
        taverne.gegenstandAblegen(new Gegenstand("Bierkrug", "ein leckeres dunkles Pils in einem edlen Krug", 2));
        taverne.gegenstandAblegen(new Gegenstand("Teller", "ein Teller mit deftigem Wildschweinfleisch mit Soße", 5 ));
        piratenHoehle.gegenstandAblegen(new Gegenstand("Schatztruhe", "eine mit Golf gefüllte Holzkiste", 40));
        piratenHoehle.gegenstandAblegen(new Gegenstand("Schwert", "das Schwert des alten PiratenkapitÃ¤ns", 10));
        waldstueck.gegenstandAblegen(new Essen("Pilz", "ein seltsam aussehender Pilz", 1, 20, 0));
        lichtung.gegenstandAblegen(new Gegenstand("Korb", "ein Weidenkorb gefüllt mit Brot", 4));
        lichtung.gegenstandAblegen(new Essen("Muffin", "lecker lecker", 1, 5, 0));
        dorfplatz.gegenstandAblegen(new Essen("Steak", "fügt 4 Hungerpunkte hinzu", 1, 0, 4));


    }

    public Raum getStartRaum() {
        return this.lichtung;
    }


}
