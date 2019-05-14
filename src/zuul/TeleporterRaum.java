package zuul;

public class TeleporterRaum extends Raum {

	private WorldGenerator WorldGenerator;
	public TeleporterRaum(String beschreibung, int temperatur) {
		super(beschreibung, temperatur);
	}


	public void setAusgang(String richtung, Raum nachbar) {

		int zufaelligeRaumZahl = (int) ((Math.random()) * WorldGenerator.alleRaume.size() + 1);
		nachbar = (Raum)WorldGenerator.alleRaume.values().toArray()[zufaelligeRaumZahl];

		this.ausgaenge.put(richtung, nachbar);
	}

}
