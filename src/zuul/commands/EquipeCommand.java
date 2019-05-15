package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class EquipeCommand implements CommandFunction {
	private Spieler spieler;
	
	public EquipeCommand(Spieler spieler) {
        this.spieler = spieler;
    }
	
	@Override
	public void execute(Befehl befehl) {
		this.spieler.ruestung(befehl.gibZweitesWort());
		this.spieler.schaden(befehl.gibZweitesWort());
		
	}

}
