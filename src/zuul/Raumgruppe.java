package zuul;

import java.util.ArrayList;

public class Raumgruppe {
	
	private ArrayList<Raum> raeume;
	public Raumgruppe(ArrayList<Raum> raeume, Ausgang ausgang, Raum eingang) {
		super();
		this.raeume = raeume;
		this.ausgang = ausgang;
		this.eingang = eingang;
	}
	private Ausgang ausgang;
	private Raum eingang;

}
