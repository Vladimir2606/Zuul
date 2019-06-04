package zuul.commands;

import zuul.Befehl;
import zuul.Spiel;

public class TradeCommand implements CommandFunction {
	private Spiel spiel;

	public TradeCommand(Spiel spiel) {
		this.spiel = spiel;
	}

	@Override
	public String execute(Befehl befehl) {
		return spiel.handelAnlegen();
	}

}
