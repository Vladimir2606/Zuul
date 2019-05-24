package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class DeequipeCommand implements CommandFunction {
	private Spieler spieler;
	
	public DeequipeCommand(Spieler spieler) {
        this.spieler = spieler;
    }
	
	@Override
	public void execute(Befehl befehl) {
		this.spieler.entrüsten(befehl.gibZweitesWort());
	}

}
