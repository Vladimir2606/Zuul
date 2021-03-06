package zuul.commands;

import zuul.Befehl;
import zuul.Kampf;
import zuul.Raum;
import zuul.Spiel;
import zuul.Monster;
import zuul.Spieler;

public class GoCommand implements CommandFunction {

	private Spieler spieler;
	private Spiel spiel;

	public GoCommand(Spieler spieler, Spiel spiel) {
		this.spieler = spieler;
		this.spiel = spiel;
	}

	@Override
	public String execute(Befehl befehl) {
		return wechsleRaum(befehl);
	}

	private String wechsleRaum(Befehl befehl)
	{
		String ergKampf = "";
		Monster erg;
		Kampf kampf;
		if(!befehl.hatZweitesWort()) {
			// Gibt es kein zweites Wort, wissen wir nicht, wohin...
			return "Wohin m�chten Sie gehen?\n";
		}

		String richtung = befehl.gibZweitesWort();

		// Wir versuchen den Raum zu verlassen.
		Raum naechsterRaum = this.spieler.getAktuellerRaum().getAusgang(richtung);

		if (naechsterRaum == null) {
			System.out.println("Dort ist kein Weg!\n");
		}
		else {
			
			this.spieler.geheZu(naechsterRaum);
			raumInfoAusgeben();
			erg = naechsterRaum.sucheMonster();
			if(erg != null) {
				if(erg.testAgro()) {
					
					kampf = new Kampf(spieler, erg, naechsterRaum);
					ergKampf += kampf.kaempfen();
				}
			}
			
			}
			spieler.hungern();
			spieler.frieren();
			spiel.handelPassivSetzen();
		return ergKampf;

}

	private void raumInfoAusgeben() {
		System.out.println(this.spieler.getAktuellerRaum().getLangeBeschreibung());
	}


}


