package zuul.commands;

import zuul.Befehl;
import zuul.Spiel;

public class FightCommand implements CommandFunction {
	
	private Spiel spiel;
	
	public FightCommand(Spiel spiel) {
	this.spiel = spiel;	
    }

	@Override
	public String execute(Befehl befehl) {
		return spiel.kampfAnlegen();
	}
}
