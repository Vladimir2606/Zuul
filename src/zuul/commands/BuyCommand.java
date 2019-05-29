package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;
import zuul.items.HandelsWaren;

public class BuyCommand implements CommandFunction {
	private Spieler spieler;

	public BuyCommand(Spieler spieler) {
		this.spieler = spieler;
	}

	@Override
	public void execute(Befehl befehl) {
		gegenstandKaufen(befehl);
	}

	private String gegenstandKaufen(Befehl befehl) {
		//spieler.getAktuellerRaum().getHaendler().sucheVerkaufsGegenstand(befehl.gibZweitesWort()) *falls kaufsgegenstand nicht geht*
		HandelsWaren kaufsgegenstand = spieler.getAktuellerRaum().getHaendler().sucheVerkaufsGegenstand(befehl.gibZweitesWort());
		String kaufsgegenstandsName = kaufsgegenstand.getName();
		int kaufsgegenstandsPreis = kaufsgegenstand.getPreis();

		String ausgabe = "";

		boolean geklappt=this.spieler.gegenstandKaufen(befehl.gibZweitesWort());
		if(geklappt) {
			ausgabe = kaufsgegenstandsName+" für "+kaufsgegenstandsPreis+"\n";
		} else {
			if(this.spieler.ermittleGewicht()+kaufsgegenstand.getGewicht() > spieler.getTragkraft()) {
				ausgabe = "Du kannst nicht so viel tragen!";
			} else if(this.spieler.getGoldtaler() < kaufsgegenstand.getPreis()) {
				ausgabe = "Du hast nicht genug Goldtaler!";

			} else {
				ausgabe = "Gegenstand konnte nicht gekauft werden werden\n!";
			}

		}
		return ausgabe;
	}
}
