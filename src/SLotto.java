import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SLotto {

	protected ArrayList<Integer> tippzahlenArray;
	protected ArrayList<Integer> unglueckszahlenArray;
	protected Ausgabe ausgabe;
	protected Speichern speicher;
	protected Laden lader;
	protected static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private String currentmode;

	/**
	 * Der Konstruktor inizialisiert die beiden Arrays f�r die Menge an Zahlen zur
	 * Tippgenerierung, sowie f�r die entfernten Zahlen. Anschlie�end wird die
	 * Funktion laden() ausgef�hrt.
	 */
	public SLotto(String mode) {
		this.currentmode = mode;
		tippzahlenArray = new ArrayList<Integer>();
		unglueckszahlenArray = new ArrayList<Integer>();
		ausgabe = new Ausgabe();
		speicher = new Speichern();
		lader = new Laden();
		logger.log(Level.INFO, "Arrays wurden inizialisiert");
		laden();
		logger.log(Level.INFO, "Super Konstruktor f�r " + currentmode + " durchgelaufen.");
	}

	/**
	 * Funktion um eine Zahl welche ausgeschlossen wurde wieder zuzulassen. Daf�r
	 * muss die Zahl eine Zahl sein, in dem Array: unglueckszahlenArray vorhanden
	 * und in dem Array: sechsAusNeunundvierzig nicht vorhanden sein.
	 */
	public void entferneUnglueckszahl(Integer[] zahlen) {
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.contains(zahl)) {
				unglueckszahlenArray.remove((Integer) zahl);
				tippzahlenArray.add(zahl);
				logger.log(Level.INFO,
						zahl + " wurde der Menge an m�glichen Zahlen zur Tippgenerierung wieder hinzugef�gt.");
				ausgabe.erfolgreichWiederHinzugefuegt(zahl);
			} else {
				ausgabe.hinzufuegenNichtMoeglich(zahl);
				logger.log(Level.INFO,
						zahl + " wurde nicht der Menge an m�glichen Zahlen zur Tippgenerierung wieder hinzugef�gt.");
			}
		}
		speichern();
		logger.log(Level.INFO, "entferneUnglueckszahl() durchgelaufen");
	}

	/**
	 * Funktion zum Laden der abgespeicherten Zahlen, welche nicht in den
	 * generierten Tipps vorkommen sollen.
	 */
	public void laden() {
		unglueckszahlenArray = lader.laden();
		logger.log(Level.INFO, "Array mit ausgeschlossenen Zahlen wurde erfolgreich �bergeben.");
	}

	/**
	 * Funktion zum Speichern der ausgeschlossenen Zahlen. Ruft die Methode
	 * speichern() der Klasse Seichern auf.
	 */
	public void speichern() {
		speicher.speichern(unglueckszahlenArray);
		logger.log(Level.INFO, "Gespeichert, SLotto speichern durchgelaufen.");
	}

	/**
	 * Gibt das unglueckszahlenArray als ArrayList zur�ck.
	 * 
	 * @return
	 */
	public ArrayList<Integer> liste() {
		return unglueckszahlenArray;
	}

	/**
	 * Funktion �berschreibt das bestehende ungl�ckszahlenArray mit einem neuen
	 * leeren Array und speichert dieses ab. Somit werden alles gesperrten Zahlen
	 * auf einmal gel�scht.
	 */
	public void reset() {
		unglueckszahlenArray = new ArrayList<Integer>();
		speichern();
	}

	/**
	 * Gibt den Aktuellen Spielmodus als String zur�ck.
	 */
	public String modus() {
		return currentmode;
	}
}