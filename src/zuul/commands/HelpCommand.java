package zuul.commands;

import zuul.Befehl;
import zuul.Parser;

public class HelpCommand implements CommandFunction {
    private Parser parser;

    public HelpCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(Befehl befehl) {
        hilfstextAusgeben();
    }

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlswörter.
     */
    private void hilfstextAusgeben()
    {
    	String erg = "";
        erg += "Sie haben sich verlaufen. Sie sind allein.";
        erg += "\nSie irren in der Welt von Zuul herum";
        erg += "\nIhnen stehen folgende Befehle zur Verfügung:";
        erg += "\n   " + this.parser.getAlleBefehle();
       
        System.out.println(erg);
    }
}
