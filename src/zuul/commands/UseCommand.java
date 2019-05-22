package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class UseCommand implements CommandFunction {
	private Spieler spieler;

	public UseCommand(Spieler spieler) {
		this.spieler = spieler;
	}

	@Override
	public void execute(Befehl befehl) {
		this.spieler.benutzen(befehl.gibZweitesWort());
	}
}
