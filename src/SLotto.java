import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SLotto {

	protected ArrayList<Integer> tippzahlenArray;
	protected ArrayList<Integer> unglueckszahlenArray;
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
		logger.log(Level.INFO, "tippzahlenArray & unglueckszahlenArray wurden inizialisiert");
		laden();
		logger.log(Level.INFO, "Super Konstruktor f�r " + currentmode + " durchgelaufen.");
	}

	/**
	 * Funktion um eine Zahl welche ausgeschlossen wurde wieder zuzulassen. Daf�r
	 * muss die Zahl eine Zahl sein, in dem Array: unglueckszahlenArray vorhanden
	 * und in dem Array: sechsAusNeunundvierzig nicht vorhanden sein.
	 */
	// TODO StringBuilder verwenden um die r�ckgaben von hinzuf�gen/nicht m�glich
	// zusammen zu f�hren und diese dann zur�ckzugeben. Somit kann sowohl auf der
	// konsole, als auch in der GUI dies Methode verwendet werden.
	public void entferneUnglueckszahl(Integer[] zahlen) {
		for (int zahl : zahlen) { // durchl�uft das Array an zu l�schenden Zahlen
			if (unglueckszahlenArray.contains(zahl)) { // Ist die Aktuelle Zahl im ungl�cksArray, ...
				unglueckszahlenArray.remove((Integer) zahl); // ... so wird diese entfernt ...
				tippzahlenArray.add(zahl); // und dem tippzahlenAray hinzugef�gt.
				logger.log(Level.INFO,
						zahl + " wurde der Menge an m�glichen Zahlen zur Tippgenerierung wieder hinzugef�gt.");
				System.out.println(StringSammlung.erfolgreichWiederHinzugefuegt(zahl));
			} else { // Ist die aktuelle Zahl nicht unter den entfernen, so wird diese ignoriert.
				System.out.println(StringSammlung.hinzufuegenNichtMoeglich(zahl));
				logger.log(Level.INFO,
						zahl + " wurde nicht der Menge an m�glichen Zahlen zur Tippgenerierung wieder hinzugef�gt.");
			}
		}
		speichern(); // das aktualisierte Array der ungl�ckszahlen wird abgespeichert.
		logger.log(Level.INFO, "entferneUnglueckszahl() durchgelaufen");
	}

	/**
	 * Funktion zum Laden der abgespeicherten Zahlen, welche nicht in den
	 * generierten Tipps vorkommen sollen.
	 */
	public void laden() {
		unglueckszahlenArray = Laden.laden(); // F�gt die laden Funktion der Klasse LAden aus und speichert das
												// zur�ckgegebene Array ind ungkl�ckszahlenArray ab.
		logger.log(Level.INFO, "Array mit ausgeschlossenen Zahlen wurde erfolgreich �bergeben.");
	}

	/**
	 * Funktion zum Speichern der ausgeschlossenen Zahlen. Ruft die Methode
	 * speichern() der Klasse Seichern auf.
	 */
	public void speichern() {
		Speichern.speichern(unglueckszahlenArray); // speichert das aktuelle ungl�ckszahlenArray ab.
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
		unglueckszahlenArray = new ArrayList<Integer>(); // erzeugt eine Neue ArrayList und speichert diese in
															// ungl�ckszahlenArray ab.
		speichern();
	}

	/**
	 * Gibt den Aktuellen Spielmodus als String zur�ck.
	 */
	public String modus() {
		return currentmode;
	}
}