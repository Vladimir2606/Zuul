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
        lichtung = new Raum("auf einer Lichtung, umgeben von dunklen Tannen");
        waldstueck = new Raum("im dunklen Wald");
        taverne = new Raum("in der Taverne, mit zwielichten Gestalten an der Theke");
        hexenhaus = new Raum("im Hexenhaus, mit einem größem Symbol auf dem Boden");
        dorfplatz = new Raum("auf dem Dorfplatz");
        piratenHoehle = new Raum("in einer alten Piratenhöhle");
        kellerDerTaverne = new Raum("im Keller der Taverne");
        geheimgang = new Raum("in einem schmalen modrigen Geheimgang");
        taverneErsterStock=new Raum("bei den den Gästezimmern im ersten Stock der Taverne");
        teleporter = new Raum("in einem kleinem Raum mit einem größem Symbol auf dem Boden");
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
<<<<<<< HEAD
        waldstueck.gegenstandAblegen(new Essen("Pilz", "ein seltsam aussehender Pilz", 1, 20, 0));
        lichtung.gegenstandAblegen(new Gegenstand("Korb", "ein Weidenkorb gefÃ¼llt mit Brot", 4));
        lichtung.gegenstandAblegen(new Essen("Muffin", "lecker lecker", 1, 5, 0));
        dorfplatz.gegenstandAblegen(new Essen("Steak", "fügt 4 Hungerpunkte hinzu", 1, 0, 4));
=======
        waldstueck.gegenstandAblegen(new Essen("Pilz", "ein seltsam aussehender Pilz", 1, 20));
        lichtung.gegenstandAblegen(new Gegenstand("Korb", "ein Weidenkorb gefüllt mit Brot", 4));
        lichtung.gegenstandAblegen(new Essen("Muffin", "lecker lecker", 1, 5));
>>>>>>> fafc4e4495a5a47c8c5812726b235ac371b75f22
    }

    public Raum getStartRaum() {
        return this.lichtung;
    }


}
