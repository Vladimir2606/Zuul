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
	/**
	 * In dieser Methode wird geguckt, ob ein Handel aktiv ist,
	 * welchen Gegenstand der Spieler kaufen möchte,
	 * ob es gewünschten Gegenstand beim Händler gibt
	 * und ob der Spieler genug GoldTaler hat.
	 * 
	 * @param befehl um auf gibZweitesWort() zugreifen zu können
	 * 
	 * @return gib einen String wieder ob der Kauf geklappt hat, oder der Spieler nicht genügend GoldTaler hat usw..
	 */
	private String gegenstandKaufen(Befehl befehl) {
		if (spiel.istHandelAktiv()) {
			if (befehl.hatZweitesWort()) {

				for(HandelsWaren hw: this.spieler.getAktuellerRaum().getHaendler().getVerkaufsGegenstaende()) {
					if(hw.getName().equalsIgnoreCase(befehl.gibZweitesWort())) {
						//spieler.getAktuellerRaum().getHaendler().sucheVerkaufsGegenstand(befehl.gibZweitesWort()) *falls kaufsgegenstand nicht geht*
						HandelsWaren kaufsgegenstand = spieler.getAktuellerRaum().getHaendler().sucheVerkaufsGegenstand(befehl.gibZweitesWort());
						String kaufsgegenstandsName = kaufsgegenstand.getName();
						int kaufsgegenstandsPreis = kaufsgegenstand.getPreis();

						String ausgabe = "";
						if (befehl.hatZweitesWort()) {
							boolean geklappt=this.spieler.gegenstandKaufen(befehl.gibZweitesWort());
							if(geklappt) {
								ausgabe = kaufsgegenstandsName+" für "+kaufsgegenstandsPreis+" Goldtaler gekauft\n";
								ausgabe += "Du hast noch: "+spieler.getGoldtaler()+" Goldtaler übrig";
							} else {
								if(this.spieler.ermittleGewicht()+kaufsgegenstand.getGewicht() > spieler.getTragkraft()) {
									ausgabe = "Du kannst nicht so viel tragen!";
								} else if(this.spieler.getGoldtaler() < kaufsgegenstand.getPreis()) {
									ausgabe = "Du hast nicht genug Goldtaler!";

								} else {
									ausgabe = "Gegenstand konnte nicht gekauft werden!\n";
								}
							}
						}
						return ausgabe;
					}
				}
				return "Diesen Gegenstand gibt es hier nicht";
			}
			return "Was soll möchtest du kaufen?";
		}
		return "Kein Handel aktiv";
	}
}
