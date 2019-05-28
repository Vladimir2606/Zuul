package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class TakeCommand implements CommandFunction {
    private Spieler spieler;

    public TakeCommand(Spieler spieler) {
        this.spieler = spieler;
    }

    @Override
    public String execute(Befehl befehl) {
       return gegenstandAufnehmen(befehl);
    }

    private String gegenstandAufnehmen(Befehl befehl) {

        boolean geklappt=this.spieler.gegenstandAufnehmen(befehl.gibZweitesWort());
        if(geklappt) {
            return "Gegenstand aufgenommen\n";
        } else {
            return "Gegenstand konnte nicht aufgenommen werden\n";
        }
    }
}
