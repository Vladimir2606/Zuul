package zuul.commands;

import zuul.Befehl;
import zuul.Spieler;

public class TraderCommand implements CommandFunction {
	private Spieler spieler;

	public TraderCommand(Spieler spieler) {
		this.spieler = spieler;
	}

	@Override
	public void execute(Befehl befehl) {
		
	}
	
	private void gegenstandAufnehmen(Befehl befehl) {

        boolean geklappt=this.spieler.gegenstandAufnehmen(befehl.gibZweitesWort());
        if(geklappt) {
            System.out.println("Gegenstand aufgenommen\n");
        } else {
            System.out.println("Gegenstand konnte nicht aufgenommen werden\n");
        }
    }
}
