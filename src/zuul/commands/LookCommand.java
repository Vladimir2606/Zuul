package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class LookCommand implements CommandFunction {
    private Spieler spieler;

    public LookCommand(Spieler spieler) {
        this.spieler = spieler;
    }

    @Override
    public String execute(Befehl befehl) {
        return umsehen();
    }

    private String umsehen() {

        return this.spieler.getAktuellerRaum().getLangeBeschreibung()+"\n";
    }
}
