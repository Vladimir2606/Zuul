package zuul.commands;

import zuul.Befehl;
import zuul.Kampf;
import zuul.Spiel;

public class FightCommand implements CommandFunction {
	
	private Spiel spiel;
	
	public FightCommand(Spiel spiel) {
	this.spiel = spiel;	
    }

	@Override
	public String execute(Befehl befehl) {
		if (befehl.hatZweitesWort()) {
			return "Bitte nur -Fight- eingeben";
		}
		return spiel.kampfAnlegen();
	}
}