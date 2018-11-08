import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Die Klasse ProgrammFlow ist für den allgemeinen Programmablauf zuständig,
 * sowie für die Auswertung und weitergabe von eingegebenen Befehlen.
 * 
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
	 * Begrüßt den User und führt einen übergebenen Befehl mit befehlAusfuehren()
	 * aus.
	 * 
	 */
	public void start(String args) {
		ausgabe.begruessung();
		befehlAusfuehren(args);
	}

	/**
	 * Funktion bekommt einen Befehl übergeben. Sorgt für die Ausführung des Befehls
	 * an dem aktuellem TippGenerator Objekt. Gibt in einigen Fällen den Befehl
	 * weiter an einzelne Funktionen
	 * 
	 * @param befehl
	 */
	public void befehlAusfuehren(String befehl) {
		if (befehl.contains("TIPPGEN")) {
			befehlAusfuehrenTippgen(befehl);
		} else if (befehl.contains("DELETE")) {
			befehlAusfuehrenDelete(befehl);
		} else if (befehl.contains("READD")) {
			befehlAusfuehrenReadd(befehl);
		} else if (befehl.equals("")) {
			befehlAusfuehren(benutzereingabe.erwarteBefehl(new InputStreamReader(System.in)));
		} else {
			switch (befehl) {
			case "RESET":
				logger.log(Level.INFO, "Sammlung mit ausgeschlossenen Zahlen wird zurückgesetzt.");
				tippgenerator.reset();
				logger.log(Level.INFO, "Befehl 'reset' ausgeführt");
				break;
			case "LIST":
				ausgabe.list();
				if (tippgenerator.liste().isEmpty()) {
					System.out.println("Derzeit sind keine Zahlen von der Tippgenerierung ausgeschlossen.");
				} else {
					System.out.println(tippgenerator.liste().toString());
				}
				System.out.println("");
				logger.log(Level.INFO,
						tippgenerator.liste().toString() + " als ausgeschlossene Zahlen dem Nutzer ausgegeben.");
				logger.log(Level.INFO, "Befehl 'list' ausgeführt");
				break;
			case "H":
				ausgabe.hilfeAusgeben();
				logger.log(Level.INFO, "Befehl 'h' ausgeführt");
				break;
			case "QUIT":
				logger.log(Level.INFO, "Befehl 'quit' ausgeführt");
				quit();
				System.exit(0);
				break;
			default:
				logger.log(Level.INFO, "Ungültige Eingabe wurde als Befehl übergeben. Neuer Befehl wird angefragt.");
				ausgabe.ungueltigeEingabe(befehl);
				break;
			}
		}
		befehlAusfuehren(benutzereingabe.erwarteBefehl(new InputStreamReader(System.in)));
	}

	/**
	 * Methode zum Ausführen des Readd Befehls. Bekommt den befehl readd zusammen
	 * mit einer oder mehreren zahlen übergeben. Entfernt dann readd und packt alle
	 * zahlen im verbleibenden String in ein integer[]. Die zahlen im integer[]
	 * werden dann mithilfe der Methode entferneUnglueckszahlen von Tippgenerator
	 * aus dem unglückszahlenArray entfernt.
	 * 
	 * @param befehl
	 */
	private void befehlAusfuehrenReadd(String befehl) {
		String readd = befehl.replace("READD ", "");
		Integer[] addZahlen = benutzereingabe.erfrageLottoZahlen(new StringReader(readd));
		logger.log(Level.INFO, addZahlen + " als Zahlen welche wieder hinzugefügt werden sollen übergeben.");
		tippgenerator.entferneUnglueckszahl(addZahlen);
		logger.log(Level.INFO, "Befehl 'readd' ausgeführt");
		befehlAusfuehren("LIST");

	}

	/**
	 * Methode zum Ausführen des delete Befehls. Bekommt den befehl delete zusammen
	 * mit einer oder mehreren zahlen übergeben. Entfernt dann delete und packt alle
	 * zahlen im verbleibenden String in ein integer[]. Die zahlen im integer[]
	 * werden dann mithilfe der Methode entferneZahlen von Tippgenerator aus dem
	 * tippzahlenarray entfernt und in das unglückszahlenArray gepackt.
	 * 
	 * @param befehl
	 */
	private void befehlAusfuehrenDelete(String befehl) {
		String delete = befehl.replace("DELETE ", "");
		Integer[] deleteZahlen = benutzereingabe.erfrageLottoZahlen(new StringReader(delete));
		logger.log(Level.INFO, deleteZahlen + " als auszuschließende Zahlen übergeben.");
		tippgenerator.entferneZahlen(deleteZahlen);
		logger.log(Level.INFO, "Befehl 'delete' ausgeführt");
		befehlAusfuehren("LIST");

	}

	/**
	 * Funktion zur ausführung des befehls tippgen. Bekommt Tippgen mit dem
	 * optionalem String 6aus49, euro oder "" übergeben, sowie einer optionalen
	 * zahl. Entfernt den substring tippgen und erzeug je nach folgestring ein
	 * tippgeneratorobjekt. Dieser Folgestring wird dann auch entfernt. Anschlißend
	 * wird starteTippgen ausgeführt und bekommt den restlichen string übergeben.
	 * 
	 * @param befehl
	 */
	private void befehlAusfuehrenTippgen(String befehl) {
		ArrayList<String> tippgen = new ArrayList<String>(Arrays.asList(befehl.split(" ")));
		tippgen.remove("TIPPGEN");
		if (tippgen.contains("6AUS49")) {
			tippgen.remove("6AUS49");
			tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
			logger.log(Level.INFO, "6AUS49 wurde als Lottomodus gewählt.");
		} else if (tippgen.contains("EURO")) {
			tippgen.remove("EURO");
			tippgenerator = new TippGenerator(new Eurojackpot());
			logger.log(Level.INFO, "EURO wurde als Lottomodus gewählt.");
		} else if (tippgen.isEmpty()) {
			tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
			logger.log(Level.INFO, "Tippgenerator wurde als Befehl gewählt.");
		} else {
			ausgabe.ungueltigeModusEingabe(tippgen);
			befehlAusfuehren(benutzereingabe.erwarteBefehl(new InputStreamReader(System.in)));
			return;
		}
		starteTippgenerierung(tippgen);
	}

	/**
	 * bekommt einen String übergeben und prüft ob dieser leer ist. fals dieser leer
	 * ist wird generiereTipp am aktuellem tippgenerator objekt ausgeführt. ist der
	 * String nicht leer so wird geprüft ob dieser eine Zahl enthält. Enthält er
	 * eine Zahl so wird generiereTipps auseführt und bekommt die Zahl als int
	 * übergeben.
	 * 
	 * @param tippgen
	 */
	public void starteTippgenerierung(ArrayList<String> tippgen) {
		if (tippgen.isEmpty()) {
			tippgenerator.generiereTipp();
		} else {
			for (String string : tippgen) {
				if (istNumerisch(string)) {
					int quicktipp = Integer.parseInt(string);
					tippgenerator.generiereTipps(quicktipp);
				}
			}
		}
	}

	/**
	 * Prüft ob ein String nur aus Zahlen besteht. 'Whitespace' wird ignoriert.
	 * 
	 * @param String
	 * @return true wenn der übergebene String nur aus Zahlen besteht.
	 */
	public static boolean istNumerisch(String str) {
		for (char c : str.toCharArray()) {
			if (Character.isWhitespace(c)) {
				continue;
			}
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	/**
	 * Führt alle nötigen Schritte zum beenden vom Logging und des BufferedReader
	 * und benutzereingabe aus.
	 */
	private void quit() {
		benutzereingabe.quit();
		Logging.quit();
	}
}
