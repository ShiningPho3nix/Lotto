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
	 * Der Konstruktor inizialisiert die beiden Arrays für die Menge an Zahlen zur
	 * Tippgenerierung, sowie für die entfernten Zahlen. Anschließend wird die
	 * Funktion laden() ausgeführt.
	 */
	public SLotto(String mode) {
		this.currentmode = mode;
		tippzahlenArray = new ArrayList<Integer>();
		unglueckszahlenArray = new ArrayList<Integer>();
		ausgabe = new Ausgabe();
		speicher = new Speichern();
		lader = new Laden();
		logger.log(Level.INFO, "tippzahlenArray & unglueckszahlenArray wurden inizialisiert");
		laden();
		logger.log(Level.INFO, "Super Konstruktor für " + currentmode + " durchgelaufen.");
	}

	/**
	 * Funktion um eine Zahl welche ausgeschlossen wurde wieder zuzulassen. Dafür
	 * muss die Zahl eine Zahl sein, in dem Array: unglueckszahlenArray vorhanden
	 * und in dem Array: sechsAusNeunundvierzig nicht vorhanden sein.
	 */
	public void entferneUnglueckszahl(Integer[] zahlen) {
		for (int zahl : zahlen) { // durchläuft das Array an zu löschenden Zahlen
			if (unglueckszahlenArray.contains(zahl)) { // Ist die Aktuelle Zahl im unglücksArray, ...
				unglueckszahlenArray.remove((Integer) zahl); // ... so wird diese entfernt ...
				tippzahlenArray.add(zahl); // und dem tippzahlenAray hinzugefügt.
				logger.log(Level.INFO,
						zahl + " wurde der Menge an möglichen Zahlen zur Tippgenerierung wieder hinzugefügt.");
				ausgabe.erfolgreichWiederHinzugefuegt(zahl);
			} else { // Ist die aktuelle Zahl nicht unter den entfernen, so wird diese ignoriert.
				ausgabe.hinzufuegenNichtMoeglich(zahl);
				logger.log(Level.INFO,
						zahl + " wurde nicht der Menge an möglichen Zahlen zur Tippgenerierung wieder hinzugefügt.");
			}
		}
		speichern(); // das aktualisierte Array der unglückszahlen wird abgespeichert.
		logger.log(Level.INFO, "entferneUnglueckszahl() durchgelaufen");
	}

	/**
	 * Funktion zum Laden der abgespeicherten Zahlen, welche nicht in den
	 * generierten Tipps vorkommen sollen.
	 */
	public void laden() {
		unglueckszahlenArray = lader.laden(); // Fügt die laden Funktion der Klasse LAden aus und speichert das
												// zurückgegebene Array ind ungklückszahlenArray ab.
		logger.log(Level.INFO, "Array mit ausgeschlossenen Zahlen wurde erfolgreich übergeben.");
	}

	/**
	 * Funktion zum Speichern der ausgeschlossenen Zahlen. Ruft die Methode
	 * speichern() der Klasse Seichern auf.
	 */
	public void speichern() {
		speicher.speichern(unglueckszahlenArray); // speichert das aktuelle unglückszahlenArray ab.
		logger.log(Level.INFO, "Gespeichert, SLotto speichern durchgelaufen.");
	}

	/**
	 * Gibt das unglueckszahlenArray als ArrayList zurück.
	 * 
	 * @return
	 */
	public ArrayList<Integer> liste() {
		return unglueckszahlenArray;
	}

	/**
	 * Funktion überschreibt das bestehende unglückszahlenArray mit einem neuen
	 * leeren Array und speichert dieses ab. Somit werden alles gesperrten Zahlen
	 * auf einmal gelöscht.
	 */
	public void reset() {
		unglueckszahlenArray = new ArrayList<Integer>(); // erzeugt eine Neue ArrayList und speichert diese in unglückszahlenArray ab.
		speichern();
	}

	/**
	 * Gibt den Aktuellen Spielmodus als String zurück.
	 */
	public String modus() {
		return currentmode;
	}
}