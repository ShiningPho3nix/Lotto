import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Die Klasse ProgrammFlow ist f�r den allgemeinen Programmablauf zust�ndig,
 * sowie f�r die Auswertung und weitergabe von eingegebenen Befehlen.
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
	 * Konstruktor erzeugt eine neue Benutzereingabe um Eingaben vom Nutzer �ber die
	 * Methoden von Benutzereingabe zu erhalten. Erzeugt auch Tippgenerator Objekt
	 * um einige Funktionen immer gew�hrleisten zu k�nnen.
	 */
	public ProgramFlow() {
		benutzereingabe = new Benutzereingabe();
		tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
		logger.log(Level.INFO, "ProgramFlow Konstruktor durchgelaufen");
	}

	/**
	 * Begr��t den User und f�hrt einen �bergebenen Befehl mit befehlAusfuehren()
	 * aus.
	 * 
	 */
	public void start(String args) {
		ausgabe.begruessung();
		befehlAusfuehren(args);
	}

	/**
	 * Funktion bekommt einen Befehl �bergeben. Sorgt f�r die Ausf�hrung des Befehls
	 * an dem aktuellem TippGenerator Objekt. Gibt in einigen F�llen den Befehl
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
				logger.log(Level.INFO, "Sammlung mit ausgeschlossenen Zahlen wird zur�ckgesetzt.");
				tippgenerator.reset();
				logger.log(Level.INFO, "Befehl 'reset' ausgef�hrt");
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
				logger.log(Level.INFO, "Befehl 'list' ausgef�hrt");
				break;
			case "H":
				ausgabe.hilfeAusgeben();
				logger.log(Level.INFO, "Befehl 'h' ausgef�hrt");
				break;
			case "QUIT":
				logger.log(Level.INFO, "Befehl 'quit' ausgef�hrt");
				quit();
				System.exit(0);
				break;
			default:
				logger.log(Level.INFO, "Ung�ltige Eingabe wurde als Befehl �bergeben. Neuer Befehl wird angefragt.");
				ausgabe.ungueltigeEingabe(befehl);
				break;
			}
		}
		befehlAusfuehren(benutzereingabe.erwarteBefehl(new InputStreamReader(System.in)));
	}

	/**
	 * Methode zum Ausf�hren des Readd Befehls. Bekommt den befehl readd zusammen
	 * mit einer oder mehreren zahlen �bergeben. Entfernt dann readd und packt alle
	 * zahlen im verbleibenden String in ein integer[]. Die zahlen im integer[]
	 * werden dann mithilfe der Methode entferneUnglueckszahlen von Tippgenerator
	 * aus dem ungl�ckszahlenArray entfernt.
	 * 
	 * @param befehl
	 */
	private void befehlAusfuehrenReadd(String befehl) {
		String readd = befehl.replace("READD ", "");
		Integer[] addZahlen = benutzereingabe.erfrageLottoZahlen(new StringReader(readd));
		logger.log(Level.INFO, addZahlen + " als Zahlen welche wieder hinzugef�gt werden sollen �bergeben.");
		tippgenerator.entferneUnglueckszahl(addZahlen);
		logger.log(Level.INFO, "Befehl 'readd' ausgef�hrt");
		befehlAusfuehren("LIST");

	}

	/**
	 * Methode zum Ausf�hren des delete Befehls. Bekommt den befehl delete zusammen
	 * mit einer oder mehreren zahlen �bergeben. Entfernt dann delete und packt alle
	 * zahlen im verbleibenden String in ein integer[]. Die zahlen im integer[]
	 * werden dann mithilfe der Methode entferneZahlen von Tippgenerator aus dem
	 * tippzahlenarray entfernt und in das ungl�ckszahlenArray gepackt.
	 * 
	 * @param befehl
	 */
	private void befehlAusfuehrenDelete(String befehl) {
		String delete = befehl.replace("DELETE ", "");
		Integer[] deleteZahlen = benutzereingabe.erfrageLottoZahlen(new StringReader(delete));
		logger.log(Level.INFO, deleteZahlen + " als auszuschlie�ende Zahlen �bergeben.");
		tippgenerator.entferneZahlen(deleteZahlen);
		logger.log(Level.INFO, "Befehl 'delete' ausgef�hrt");
		befehlAusfuehren("LIST");

	}

	/**
	 * Funktion zur ausf�hrung des befehls tippgen. Bekommt Tippgen mit dem
	 * optionalem String 6aus49, euro oder "" �bergeben, sowie einer optionalen
	 * zahl. Entfernt den substring tippgen und erzeug je nach folgestring ein
	 * tippgeneratorobjekt. Dieser Folgestring wird dann auch entfernt. Anschli�end
	 * wird starteTippgen ausgef�hrt und bekommt den restlichen string �bergeben.
	 * 
	 * @param befehl
	 */
	private void befehlAusfuehrenTippgen(String befehl) {
		ArrayList<String> tippgen = new ArrayList<String>(Arrays.asList(befehl.split(" ")));
		tippgen.remove("TIPPGEN");
		if (tippgen.contains("6AUS49")) {
			tippgen.remove("6AUS49");
			tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
			logger.log(Level.INFO, "6AUS49 wurde als Lottomodus gew�hlt.");
		} else if (tippgen.contains("EURO")) {
			tippgen.remove("EURO");
			tippgenerator = new TippGenerator(new Eurojackpot());
			logger.log(Level.INFO, "EURO wurde als Lottomodus gew�hlt.");
		} else if (tippgen.isEmpty()) {
			tippgenerator = new TippGenerator(new SechsAusNeunundvierzig());
			logger.log(Level.INFO, "Tippgenerator wurde als Befehl gew�hlt.");
		} else {
			ausgabe.ungueltigeModusEingabe(tippgen);
			befehlAusfuehren(benutzereingabe.erwarteBefehl(new InputStreamReader(System.in)));
			return;
		}
		starteTippgenerierung(tippgen);
	}

	/**
	 * bekommt einen String �bergeben und pr�ft ob dieser leer ist. fals dieser leer
	 * ist wird generiereTipp am aktuellem tippgenerator objekt ausgef�hrt. ist der
	 * String nicht leer so wird gepr�ft ob dieser eine Zahl enth�lt. Enth�lt er
	 * eine Zahl so wird generiereTipps ausef�hrt und bekommt die Zahl als int
	 * �bergeben.
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
	 * Pr�ft ob ein String nur aus Zahlen besteht. 'Whitespace' wird ignoriert.
	 * 
	 * @param String
	 * @return true wenn der �bergebene String nur aus Zahlen besteht.
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
	 * F�hrt alle n�tigen Schritte zum beenden vom Logging und des BufferedReader
	 * und benutzereingabe aus.
	 */
	private void quit() {
		benutzereingabe.quit();
		Logging.quit();
	}
}
