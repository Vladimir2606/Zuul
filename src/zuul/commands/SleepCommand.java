
package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class SleepCommand implements CommandFunction{

    private Spieler spieler;

    public SleepCommand(Spieler spieler) {
        this.spieler = spieler;
    }

    @Override
    public String execute(Befehl befehl) {
        this.spieler.sleep();
        return "Ist auch schon spät, geh schlafen!\n";
    }
}

