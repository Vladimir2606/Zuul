package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class EquipeCommand implements CommandFunction {
	private Spieler spieler;
	
	public EquipeCommand(Spieler spieler) {
        this.spieler = spieler;
    }
	
	@Override
	public String execute(Befehl befehl) {
		return this.spieler.ruestung(befehl.gibZweitesWort());
	}

}
