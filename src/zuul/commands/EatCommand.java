package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class EatCommand implements CommandFunction {
    private Spieler spieler;

    public EatCommand(Spieler spieler) {
        this.spieler = spieler;
    }

    @Override
    public String execute(Befehl befehl) {
        return this.spieler.essen(befehl.gibZweitesWort());
    }
}
