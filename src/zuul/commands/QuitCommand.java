package zuul.commands;

import zuul.Befehl;
import zuul.Spiel;

public class QuitCommand implements CommandFunction {


    private Spiel spiel;

    public QuitCommand(Spiel spiel) {
        this.spiel = spiel;
    }

    @Override
    public String execute(Befehl befehl) {
        return beenden(befehl);

    }

    private String beenden(Befehl befehl)
    {
        if(befehl.hatZweitesWort()) {
            return "Was soll beendet werden?\n";
        }
        else {
            this.spiel.quit();
        }
		return null;
    }
}
