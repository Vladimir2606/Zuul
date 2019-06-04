package zuul.commands;

import zuul.Befehl;
import zuul.Spiel;
import zuul.Spieler;

public class TradeCommand implements CommandFunction {
	private Spiel spiel;
	private Spieler spieler;

	public TradeCommand(Spiel spiel, Spieler spieler) {
		this.spiel = spiel;
		this.spieler = spieler;
	}

	@Override
	public String execute(Befehl befehl) {
		if(spieler.getAktuellerRaum().haendlerImRaum() == true) {
			return spiel.handelAnlegen();
		} else {
			return "Hier ist kein Händler";
		}
	}
}