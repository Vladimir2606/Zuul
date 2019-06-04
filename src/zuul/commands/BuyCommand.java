package zuul.commands;

import zuul.Befehl;
import zuul.Spiel;
import zuul.Spieler;
import zuul.items.HandelsWaren;

public class BuyCommand implements CommandFunction {
	private Spieler spieler;
	private Spiel spiel;

	public BuyCommand(Spieler spieler, Spiel spiel) {
		this.spieler = spieler;
		this.spiel = spiel;
	}

	@Override
	public String execute(Befehl befehl) {
		return gegenstandKaufen(befehl);
	}

	private String gegenstandKaufen(Befehl befehl) {
		if (spiel.istHandelAktiv()) {
			if (befehl.hatZweitesWort()) {
			//spieler.getAktuellerRaum().getHaendler().sucheVerkaufsGegenstand(befehl.gibZweitesWort()) *falls kaufsgegenstand nicht geht*
			HandelsWaren kaufsgegenstand = spieler.getAktuellerRaum().getHaendler().sucheVerkaufsGegenstand(befehl.gibZweitesWort());
			String kaufsgegenstandsName = kaufsgegenstand.getName();
			int kaufsgegenstandsPreis = kaufsgegenstand.getPreis();

			String ausgabe = "";
			if (befehl.hatZweitesWort()) {
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
			}
			return ausgabe;
		}
			return "Was soll möchtest du kaufen?";
		}
		return "Kein Handel aktiv";
	}
}
