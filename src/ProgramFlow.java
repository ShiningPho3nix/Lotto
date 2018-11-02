import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */

/**
 * @author Steffen Dworsky
 *
 */
public class ProgramFlow {

	private Ausgabe ausgabe = LottoTippGenerator.ausgabe;
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Benutzereingabe benutzereingabe;
	public TippGenerator tippgenerator = null;

	/**
	 * Konstruktor erzeugt eine neue Benutzereingabe um Eingaben vom Nutzer über die
	 * Methoden von Benutzereingabe zu erhalten.
	 */
	public ProgramFlow() {
		benutzereingabe = new Benutzereingabe();
		logger.log(Level.INFO, "ProgramFlow Konstruktor durchgelaufen");
	}

	/**
	 * Begrüßt den User und führt waehleSpielmodus() aus.
	 * 
	 */
	public void start() {
		ausgabe.begruessung();
		setSpielModus(benutzereingabe.erfrageSpielModus());
	}

	/**
	 * Bekommt einen String (1,2) übergeben und erzeugt je nach Eingabe für 1: new
	 * TippGenerator(new SechsAusNeunundvierzig()), für 2: new TippGenerator(new
	 * Eurojackpot()), oder aber
	 * 
	 * @param spielmodus
	 */
	public void setSpielModus(String spielmodus) {
		switch (spielmodus) {
		case "1":
			tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
			logger.log(Level.INFO, tippgenerator.lottoModus() + " wurde als Lottomodus gewählt");
			break;
		case "2":
			tippgenerator = new TippGenerator(new Eurojackpot());
			logger.log(Level.INFO, tippgenerator.lottoModus() + " wurde als Lottomodus gewählt");
			break;
		default:
			logger.log(Level.INFO,
					"Ungültige Eingabe wurde als Spielmodus übergeben. Spielmodus wird erneut angefragt.");
			ausgabe.ungueltigeEingabe();
			setSpielModus(benutzereingabe.erfrageSpielModus());
			break;
		}
		befehlAusfuehren(benutzereingabe.erwarteBefehl(tippgenerator.lottoModus()));
	}

	/**
	 * Funktion bekommt einen Befehl übergeben. Sorgt für die Ausführung des Befehls
	 * an dem aktuellem TippGenerator Objekt.
	 * 
	 * @param befehl
	 */
	public void befehlAusfuehren(String befehl) {
		befehl = befehl.toUpperCase();
		switch (befehl) {
		case "TIPPGEN":
			logger.log(Level.INFO, "Tippgenerator wurde als Befehl gewählt.");
			tippgenerator.generiereTipp();
			break;
		case "RESET":
			logger.log(Level.INFO, "Für den aktuellen Modus wurde reset gewählt.");
			tippgenerator.reset();
			break;
		case "DELETE":
			int zahl = benutzereingabe.erfrageLottoZahl();
			logger.log(Level.INFO, zahl + " als zu löschende Zahl eingegeben.");
			tippgenerator.entferneZahlen(zahl);
			break;
		case "CHANGE":
			System.out.println("Change gewählt.");
			break;
		case "H":
			ausgabe.hilfeAusgeben();
			break;
		case "QUIT":
			quit();
			return;
		default:
			logger.log(Level.INFO,
					"Ungültige Eingabe wurde als Befehl übergeben. Befehlsausführung wird abgebrochen, neuer Befehl wird angefragt.");
			ausgabe.ungueltigeEingabe();
			break;
		}
		befehlAusfuehren(benutzereingabe.erwarteBefehl(tippgenerator.lottoModus()));
	}

	private void quit() {
		benutzereingabe.quit();
		Logging.quit();
	}
}
