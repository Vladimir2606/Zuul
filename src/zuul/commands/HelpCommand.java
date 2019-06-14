
package zuul.commands;

import zuul.Befehl;
import zuul.Parser;

public class HelpCommand implements CommandFunction {
    private Parser parser;

    public HelpCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public String execute(Befehl befehl) {
        return hilfstextAusgeben();
    }

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlswörter.
     */
    private String hilfstextAusgeben() {
    	String erg = "";
        erg += "Sie haben sich verlaufen. Sie sind allein.";
        erg += "\nSie irren in der Welt von Zuul herum";
        erg += "\nAchtung kann jeder Spieler nur eine/n- Helm, Brustplatte, Hose, paar Schuhe und auch nur eine Waffe tragen!";
        erg += "\nAußerdem stehen Ihnen folgende Befehle zur Verfügung:";
        erg += "\n   " + this.parser.getAlleBefehle()+"\n";
        return erg;
    }
}
