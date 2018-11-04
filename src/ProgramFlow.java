import java.util.ArrayList;
import java.util.Arrays;
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
	 * Methoden von Benutzereingabe zu erhalten. Erzeugt auch Tippgenerator Objekt
	 * um einige Funktionen immer gewährleisten zu können.
	 */
	public ProgramFlow() {
		benutzereingabe = new Benutzereingabe();
		tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
		logger.log(Level.INFO, "ProgramFlow Konstruktor durchgelaufen");
	}

	/**
	 * Begrüßt den User und führt waehleSpielmodus() aus.
	 * 
	 */
	public void start() {
		ausgabe.begruessung();
		befehlAusfuehren(benutzereingabe.erwarteBefehl());
	}

	/**
	 * Funktion bekommt einen Befehl übergeben. Sorgt für die Ausführung des Befehls
	 * an dem aktuellem TippGenerator Objekt.
	 * 
	 * @param befehl
	 */
	public void befehlAusfuehren(String befehl) {
		if (befehl.contains("TIPPGEN")) {
			befehlAusführenTippgen(befehl);
		} else {
			switch (befehl) {
			case "RESET":
				logger.log(Level.INFO, "Sammlung mit ausgeschlossenen Zahlen wird zurückgesetzt.");
				tippgenerator.reset();
				break;
			case "DELETE":
				int[] deleteZahlen = benutzereingabe.erfrageLottoZahlen();
				logger.log(Level.INFO, deleteZahlen + " als auszuschließende Zahlen übergeben.");
				tippgenerator.entferneZahlen(deleteZahlen);
				break;
			case "ADD":
				int[] addZahlen = benutzereingabe.erfrageLottoZahlen();
				logger.log(Level.INFO, addZahlen + " als Zahlen welche wieder hinzugefügt werden sollen übergeben.");
				tippgenerator.entferneUnglueckszahl(addZahlen);
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
		}
		befehlAusfuehren(benutzereingabe.erwarteBefehl());
	}

	private void befehlAusführenTippgen(String befehl) {
		ArrayList<String> tippgen = new ArrayList<String>(Arrays.asList(befehl.split(" ")));
		if (tippgen.contains("6AUS49")) {
			tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
			logger.log(Level.INFO, "6AUS49 wurde als Lottomodus gewählt.");
			if (isNumeric(tippgen.get(2))) {
				tippgenerator.generiereTipps(Integer.parseInt(tippgen.get(2)));
			}
			tippgenerator.generiereTipp();
		} else if (tippgen.contains("EURO")) {
			tippgenerator = new TippGenerator(new Eurojackpot());
			logger.log(Level.INFO, "EURO wurde als Lottomodus gewählt.");
			if (isNumeric(tippgen.get(2))) {
				tippgenerator.generiereTipps(Integer.parseInt(tippgen.get(2)));
			}

			tippgenerator.generiereTipp();
		} else {
			tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
			logger.log(Level.INFO, "Tippgenerator wurde als Befehl gewählt.");
			if (isNumeric(tippgen.get(1))) {
				tippgenerator.generiereTipps(Integer.parseInt(tippgen.get(2)));
			}
			tippgenerator.generiereTipp();
		}
	}

	private void quit() {
		benutzereingabe.quit();
		Logging.quit();
	}

	public static boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}
}
