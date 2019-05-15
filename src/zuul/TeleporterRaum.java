package zuul;

public class TeleporterRaum extends Raum {

	private WorldGenerator worldGenerator;
	
	public TeleporterRaum(String beschreibung, int temperatur, WorldGenerator worldGenerator) {
		super(beschreibung, temperatur);
		this.worldGenerator = worldGenerator;
	}


	public void setAusgang(String richtung, Raum nachbar) {

		int zufaelligeRaumZahl = (int) ((Math.random()) * worldGenerator.alleRaume.size());
		nachbar = (Raum)worldGenerator.alleRaume.values().toArray()[zufaelligeRaumZahl];

		this.ausgaenge.put(richtung, nachbar);
	}

}
