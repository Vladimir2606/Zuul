package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class UseCommand implements CommandFunction {
	private Spieler spieler;

	public UseCommand(Spieler spieler) {
		this.spieler = spieler;
	}

	@Override
	public String execute(Befehl befehl) {
<<<<<<< HEAD
		return null;
		//this.spieler.benutzen(befehl.gibZweitesWort());
=======
		return this.spieler.benutzen(befehl.gibZweitesWort());

>>>>>>> 92bda9d111bc5f89dc728b25a1c101f618a61afd
	}
}

