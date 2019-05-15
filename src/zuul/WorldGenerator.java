package zuul;

import zuul.r�stung.Helm;
import zuul.r�stung.Brust;
import zuul.r�stung.Hose;
import zuul.r�stung.Schuhe;

public class WorldGenerator {
    private Raum lichtung, waldstueck, taverne, hexenhaus, dorfplatz, kellerDerTaverne, geheimgang, taverneErsterStock,
    				piratenHoehle, teleporter;

    public WorldGenerator() {
        this.raeumeAnlegen();
        this.setzeAusgaenge();
        this.addGegenstaende();
        this.addMonster();
    }

    private void raeumeAnlegen() {
        lichtung = new Raum("auf einer Lichtung, umgeben von dunklen Tannen");
        waldstueck = new Raum("im dunklen Wald");
        taverne = new Raum("in der Taverne, mit zwielichten Gestalten an der Theke");
        hexenhaus = new Raum("im Hexenhaus, mit einem gr��em Symbol auf dem Boden");
        dorfplatz = new Raum("auf dem Dorfplatz");
        piratenHoehle = new Raum("in einer alten Piratenh�hle");
        kellerDerTaverne = new Raum("im Keller der Taverne");
        geheimgang = new Raum("in einem schmalen modrigen Geheimgang");
        taverneErsterStock=new Raum("bei den den G�stezimmern im ersten Stock der Taverne");
        teleporter = new Raum("in einem kleinem Raum mit einem gr��em Symbol auf dem Boden");
    }

    private void setzeAusgaenge() {

        // die Ausg�nge initialisieren
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
        taverne.gegenstandAblegen(new Gegenstand("Teller", "ein Teller mit deftigem Wildschweinfleisch mit So�e", 5 ));
        piratenHoehle.gegenstandAblegen(new Gegenstand("Schatztruhe", "eine mit Golf gef�llte Holzkiste", 40));
        piratenHoehle.gegenstandAblegen(new Gegenstand("Schwert", "das Schwert des alten Piratenkapitäns", 10));
        waldstueck.gegenstandAblegen(new Essen("Pilz", "ein seltsam aussehender Pilz", 1, 20, 2));
        lichtung.gegenstandAblegen(new Gegenstand("Korb", "ein Weidenkorb gef�llt mit Brot", 4));
        lichtung.gegenstandAblegen(new Essen("Muffin", "lecker lecker", 1, 5, 1));
        dorfplatz.gegenstandAblegen(new Essen("Steak", "f�gt 4 Hungerpunkte hinzu", 1, 0, 4));
        lichtung.gegenstandAblegen(new Waffen("Dolch", "ist ein spitzer Dolch", 3, 2));
        kellerDerTaverne.gegenstandAblegen(new Essen("Bier", "ein Glas Bier", 1, 0, 1));
        hexenhaus.gegenstandAblegen(new Essen("Fleisch", "ein vergiftetes St�ck Fleisch", 1, 0, -2));
        lichtung.gegenstandAblegen(new Helm("Holzhaube", "sch�tzt vor Angriffen", 3, 2));
        taverne.gegenstandAblegen(new Brust("Stahlbrust", "sch�tzt vor Angriffen", 10, 4));
        dorfplatz.gegenstandAblegen(new Hose("Stoffhose", "sch�tzt eher weniger vor Angriffen", 1, 1));
        piratenHoehle.gegenstandAblegen(new Schuhe("Stahlschuhe", "sch�tzt vor Angriffen", 6, 3));
        lichtung.gegenstandAblegen(new Helm("Stahlhelm", "sch�tzt vor Angriffen", 6, 3));

    }
    
    private void addMonster() {
    	waldstueck.setMonster(new Monster("Harald", "ist ein Org und besch�tzt das wladst�ck", 3, 1));
    }

    public Raum getStartRaum() {
        return this.lichtung;
    }
}
