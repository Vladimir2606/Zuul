package zuul.commands;

import zuul.Befehl;
import zuul.Spiel;

public class TradeCommand implements CommandFunction {
	private Spiel spiel;

	public TradeCommand(Spiel spiel) {
		this.spiel = spiel;
	}

	@Override
	public void execute(Befehl befehl) {
		System.out.println(spiel.handelAnlegen());
	}

}
