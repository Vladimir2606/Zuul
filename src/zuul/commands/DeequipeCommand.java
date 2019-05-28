package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class DeequipeCommand implements CommandFunction {
	private Spieler spieler;
	
	public DeequipeCommand(Spieler spieler) {
        this.spieler = spieler;
    }
	
	@Override
	public String execute(Befehl befehl) {
		return this.spieler.entrüsten(befehl.gibZweitesWort());
	}

}
