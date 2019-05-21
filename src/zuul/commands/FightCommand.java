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
	public void execute(Befehl befehl) {
		spiel.kampfAnlegen();
	}
}
