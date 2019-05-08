/**
 * Diese Klasse modelliert Rï¿½ume in der Welt von Zuul.
 *
 * Ein "Raum" reprï¿½sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen Rï¿½umen ï¿½ber Ausgï¿½nge verbunden.
 * Mï¿½gliche Ausgï¿½nge liegen im Norden, Osten, Sï¿½den und Westen.
 * Fï¿½r jede Richtung hï¿½lt ein Raum eine Referenz auf den 
 * benachbarten Raum.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
package zuul;

import java.util.ArrayList;
import java.util.HashMap;

public class Raum
{
    private String beschreibung;
    private HashMap<String, Raum> ausgaenge;
    private ArrayList<Gegenstand> gegenstaende;
    private int temperatur;

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausgänge.
     * @param beschreibung enthält eine Beschreibung in der Form
     *        "in einer Küche" oder "auf einem Sportplatz".
     * @param temperatur ist die Temperatur die jeder Raum hat.
     */
    public Raum(String beschreibung, int temperatur)
    {
        this.gegenstaende=new ArrayList<>();
        this.ausgaenge=new HashMap<>();
        this.beschreibung = beschreibung;
        this.temperatur = temperatur;
    }

    public void gegenstandAblegen(Gegenstand neuerGegenstand) {
        this.gegenstaende.add(neuerGegenstand);
    }

    public void setAusgang(String richtung, Raum nachbar) {
        this.ausgaenge.put(richtung, nachbar);
    }

    public Raum getAusgang(String name) {
        return this.ausgaenge.get(name);
    }

    public String getLangeBeschreibung() {
        String erg=  "Sie sind " + this.beschreibung + "\nAusgänge: " + this.ausgaengeToString();
        if(this.gegenstaende.size()>0) {
            erg+="\nGegenstände in diesem Umgebung:\n";
            for(Gegenstand g: this.gegenstaende) {
                erg+=" - " + g.toString() + "\n";
            }
            erg+="\nTemperatur in dieser Umgebung: "+this.temperatur+" grad";
        }
        return erg;
    }

    public String ausgaengeToString() {
        String erg="";
        for(String richtung: ausgaenge.keySet()) {
            erg+=richtung + " ";
        }
        return erg;
    }

    /**
     * @return Die Beschreibung dieses Raums.
     */
    public String gibBeschreibung()
    {
        return beschreibung;
    }

    public void entferneGegenstand(Gegenstand gesucht) {
        this.gegenstaende.remove(gesucht);
    }

    public Gegenstand sucheGegenstand(String name) {
        for(Gegenstand g: this.gegenstaende) {
            // if(g.getName() == name) --> funktioniert nicht,
            // da hier nur die Referenz auf Gleichheit geprüft wird
            // d.h. ob die im gleichen Speicher stehen
            if(g.getName().equalsIgnoreCase(name)) {
                return g;
                // Dieses return beendet die Methode
            }
        }
        // Falls diese Stelle erreicht wird, wurde kein
        // Gegenstand gefunden
        return null;
    }
}
