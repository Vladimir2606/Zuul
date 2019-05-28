package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class DropCommand implements CommandFunction{
    private Spieler spieler;

    public DropCommand(Spieler spieler) {
        this.spieler = spieler;
    }

    @Override
    public String execute(Befehl befehl) {
        return gegenstandAblegen(befehl);
    }

    private String gegenstandAblegen(Befehl befehl) {
        boolean geklappt=this.spieler.gegenstandAblegen(befehl.gibZweitesWort());
        if(geklappt) {
            return "Gegenstand abgelegt";
        } else {
            return "Gegenstand konnte nicht abgelegt werden";
        }
    }
}
