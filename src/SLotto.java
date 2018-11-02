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
		ausgabe = LottoTippGenerator.ausgabe;
		speicher = new Speichern();
		lader = new Laden();
		logger.log(Level.INFO, "Arrays wurden inizialisiert");
		laden();
		logger.log(Level.INFO, "Super Konstruktor f�r " + modus() + " durchgelaufen.");
	}

	/**
	 * Gibt den Aktuellen Spielmodusals String zur�ck.
	 */
	public String modus() {
		return currentmode;
	}

	/**
	 * Funktion zum Laden der abgespeicherten Zahlen, welche nicht in den
	 * generierten Tipps vorkommen sollen.
	 */
	public void laden() {
		unglueckszahlenArray = lader.laden(modus());
		logger.log(Level.INFO, "Array mit ausgeschlossenen Zahlen wurde erfolgreich �bergeben.");
	}

	/**
	 * Funktion zum Speichern der ausgeschlossenen Zahlen.
	 */
	public void speichern() {
		speicher.speichern(unglueckszahlenArray, modus());
		logger.log(Level.INFO, "Gespeichert, SLotto speichern durchgelaufen.");
	}
}