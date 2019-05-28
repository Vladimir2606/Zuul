package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class StatusCommand implements CommandFunction {
    private Spieler spieler;

    public StatusCommand(Spieler spieler) {
        this.spieler = spieler;
    }

    @Override
    public String execute(Befehl befehl) {
       return status();
    }

    private String status() {
        return this.spieler.zeigeStatus()+"\n";
    }
}
