package zuul;

public class TeleporterRaum extends Raum {

	private WorldGenerator worldGenerator;

	public TeleporterRaum(String beschreibung, int temperatur, int raumgruppe, WorldGenerator worldGenerator) {
		super(beschreibung, temperatur, raumgruppe);
		this.worldGenerator = worldGenerator;
	}


	public void setAusgang(String richtung, Raum nachbar) {
		do {
			int zufaelligeRaumZahl = (int) ((Math.random()) * worldGenerator.alleRaume.size());
			nachbar = (Raum)worldGenerator.alleRaume.values().toArray()[zufaelligeRaumZahl];
		} while (nachbar == worldGenerator.alleRaume.get("teleporter"));
			this.ausgaenge.put(richtung, nachbar);
		}

	}
