import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasse enthält Methoden um verschiedenes auf der Konsole auszugeben. Somit
 * werden nahezu alle System.out.println() entfernt.
 * 
 * @author Steffen Dworsky
 *
 */

// TODO Alles umbauen sodass Strings zurückgegeben werden., damit die Vorgefertigten ausgaben
// sowohl von der Konsolen version, als auch der GUI verwendet werden können
public class StringSammlung {

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public StringSammlung() {
		logger.setUseParentHandlers(false);
		logger.log(Level.INFO, "Ausgabe Konstruktor durchgelaufen.");
	}

	/**
	 * Eine kurze Willkommens Botschaft für den Nutzer.
	 */
	public static String begruessungConsole() {
		logger.log(Level.INFO, "Dem Nutzer wird die Begrüßungsnachricht über die Konsole ausgegeben.");
		return " ************************************** \n *Willkommen beim Lotto Tipp Generator* \n **************************************\n";
	}

	/**
	 * Da die Ausgabe auf der GUI scheinbar ein etwas anderes Format hat, musste die
	 * Begrüßung etwas angepasst werden. Gibt genau wie bei begruessungConsole eine
	 * begrüßungsnachricht als String zurück.
	 * 
	 * @return
	 */
	public static String begruessungGUI() {
		logger.log(Level.INFO, "Dem Nutzer wird die Begrüßungsnachricht über die GUI ausgegeben.");
		return " ********************************************* \n *Willkommen beim Lotto Tipp Generator* \n ********************************************* \n";
	}

	/**
	 * Bekommt einen Modus übergeben und fügt diesen in einen festen String ein.
	 * Gibt diesen String zurück.
	 * 
	 * @param modus
	 * @return
	 */
	public static String modusGewaehlt(String modus) {
		logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt, dass " + modus + " als Modus ausgewählt wurde.");
		return (modus + " wurde als Lottoart gewählt. \n ");

	}

	/**
	 * Gibt einen String zurück, in welchem dem Nutzer gesagt wird wie er die
	 * HIlfeausgabe auf der Konsole aufrufen kann.
	 * 
	 * @return
	 */
	public static String hilfeBefehl() {
		logger.log(Level.INFO, "Dem Nutzer wurde die Information wie die Hilfe aufgerufen wird ausgegeben.");
		return "'h' eingeben um eine Liste mit Befehlen ausgegeben zu bekommen.\n";

	}

	/**
	 * Gibt die einen String zurück, welcher die Aufforderung ein Spielmodus zu
	 * wählen enthält, sowie welche Spielmodi zur Auswahl stehen.
	 * 
	 * @return
	 */
	public static String waehleSpiel() {
		logger.log(Level.INFO, "Dem Nutzer wurden die Informationen zur Modusauswahl ausgegeben.");
		return "Bitte wählen Sie das gewünschte Lottospiel: \n 6aus49 \n Euro \n";
	}

	/**
	 * Gibt einen String zurück, welcher den Nutzer Informiert, das eine ungültige
	 * Eingabe getätigt wurde.
	 * 
	 * @return
	 */
	public static String ungueltigeEingabe(String eingabe) {
		logger.log(Level.INFO,
				"Der Nutzer wurde darauf hingewiesen, dass eine ungültige Eingabe getätigt wurde. Dem Nutzer wurde ein Hinweiß auf den Hilfebefehl gegeben.");
		return ("Ungültige Eingabe getätigt: " + eingabe + "\n" + hilfeAusgeben() + "\n");

	}

	/**
	 * Gibt einen String zurück, welcher die Information enthält, das die übergebene
	 * Zahl nicht aus der Menge an Zahlen gelöscht werden kann. Unterscheidet
	 * hierbei zwischen: Auserhalb des gültigen Zahlenbereiches und zahl entweder
	 * bereits entfernt oder die maximal 6 zulässigen zahlen wurden bereits
	 * entfernt.
	 * 
	 * @param zahl
	 */
	public static String nichtLoeschbar(int zahl) {
		if (zahl > 50 || zahl < 1) { // Für den Fall das die Zahl außerhalb des gültigen Bereichs liegt.
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im gültigem Bereich liegt.");
			return ("Löschen von " + zahl + " nicht möglich!\n "
					+ "Die zu löschende Zahl muss im Bereich [1,50] liegen.\n");

		} else { // Für alle anderen Fälle
			logger.log(Level.INFO,
					"Der Nutzer wurde benachrichtigt, dass das Löschen von " + zahl + " nicht möglich ist.");
			return ("Löschen von " + zahl + " nicht möglich!\n"
					+ "Entweder wurden bereits die Maximal 6 zulässigen Zahlen entfernt oder die gewünschte Zahl ist bereits entfernt.\n");
		}
	}

	/**
	 * Gibt einen String zurück welcher die Aufforderung zur Eingabe eines Befehls
	 * enthält. Führt in der nächstfolgenden Zeile hilfeBefehl() aus.
	 */
	public static String erwarteBefehl() {
		logger.log(Level.INFO, "Der Nutzer wurde zur Eingabe eines Befehls aufgefordert.");
		return ("Bitte einen Befehl eingeben:\n" + hilfeBefehl());
	}

	/**
	 * Gibt einen String mit der Aufforderung zur Eingabe von Zahlen zurück.
	 */
	public static String zahlEingeben() {
		return "Bitte die Zahlen mit leerzeichen getrennt eingeben:\n";
	}

	/**
	 * Gibt einen fertigen String mit allen Konsolen-Befehlen zurück.
	 */
	public static String hilfeAusgeben() {
		logger.log(Level.INFO, "List mit Befehlen ausgegeben..");
		return ("Die möglichen Befehle sind:\n"
				+ " 'tippgen <6aus49/euro> <Tippanzahl>': Erzeugt einen Tipp im angegebenen Modus, die angegebene Anzahl an Mal.\n"
				+ " Die Eingaben werden durch ein leerzeichen getrennt.\n"
				+ " 'reset': Sammlung mit ausgeschlossenen Zahlen wird zurückgesetzt.\n "
				+ "'delete <Zahlen>': Ermöglicht das Entfernen einer oder mehrerer Zahlen aus der Menge an Zahlen zur Tippgenerierung.\n"
				+ " Die Zahlen werden durch ein leerzeichen getrennt.\n "
				+ "'readd <Zahlen>': Ermöglicht das Entfernen einer oder mehrerer Zahlen aus der Menge an ausgeschlossenen Zahlen.\n"
				+ " Die Zahlen werden durch ein leerzeichen getrennt.\n"
				+ "'list': Gibt eine Liste mit allen aktuell ausgeschlossenen Zahlen aus.\n"
				+ "'quit': Beendet das Programm ordnungsgemäß.\n");

	}

	/**
	 * Gibt einen String zurück, dass die übergebene Zahl erfolgreich gelöscht
	 * wurde.
	 * 
	 * @param zahl
	 * @param string
	 * @return
	 */
	public static String erfolgreichEntfernt(int zahl) {
		logger.log(Level.INFO, "Über erfolgreiches entfernen der Zahl: " + zahl + " benachrichtigt.");
		return (zahl + " erfolgreich entfernt.\n");
	}

	/**
	 * Gibt einen String zurück, dass die übergebene Zahl erfolgreich wieder
	 * hinzugefügt wurde.
	 * 
	 * @param zahl
	 */
	public static String erfolgreichWiederHinzugefuegt(int zahl) {
		logger.log(Level.INFO, "Über erfolgreiches wieder hinzufügen der Zahl: " + zahl + " benachrichtigt.");
		return (zahl + " erfolgreich wieder hinzugefügt.\n");
	}

	/**
	 * Gibt einen String zurück, der enthält, das eine Datei zum abspeichern der
	 * entfernten Zahlen erzeugt wurde.
	 */
	public static String dateiErstellt() {
		logger.log(Level.INFO, "Über erfolgreiches erstellen einer neuen Datei benachrichtigt.");
		return "Es existierte noch keine Datei zum abspeichern der entfernten Zahlen. Diese Datei wurde mit dem Namen 'TippGenerator.txt' erstellt.\n";

	}

	/**
	 * Gibt einen String zurück, der darüber informiert, das die übergebene Zahl
	 * keine Zahl ist. Gibt dabei auch den Gültigen Zahlenbereich.
	 * 
	 * @param input
	 */
	public static String istKeineZahl(String input) {
		logger.log(Level.INFO, "Nachricht ausgegeben, dass " + input + " keine Zahl ist.");
		return (input + " ist keine Zahl und wird daher ignoriert.\n");
	}

	/**
	 * Gibt einen String zurück, welcherden Nutzer informiert, dass eine der
	 * eingegebenen Zahlen nicht wieder hinzugefügt werden konnte.
	 * 
	 * @param zahl
	 */
	public static String hinzufuegenNichtMoeglich(int zahl) {
		if (zahl > 50 || zahl < 1) {
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt das " + zahl + " nicht im gültigem Bereich liegt.");
			return (zahl + " konnte nicht wieder hinzugefügt werden.\n"
					+ "Die eingegebene Zahl muss im Bereich [1,50] liegen.\n");
		} else {
			logger.log(Level.INFO, "Der Nutzer wurde benachrichtigt, dass das Löschen von " + zahl
					+ " nicht möglich ist. Da " + zahl + " keine der aktuell ausgeschlossenen Zahlen ist.");
			return (zahl + " konnte nicht wieder hinzugefügt werden.\n"
					+ " Die Zahl ist aktuell nicht unter den ausgeschlossenen Zahlen.\n");
		}
	}

	/**
	 * Gibt eine Mittteilung aus, das die folgenden Zahlen ausgeschlossene Zahlen
	 * sind.
	 * 
	 * @return
	 */
	public static String list() {
		logger.log(Level.INFO, "list() ausgeführt.");
		return "Die derzeitig ausgeschlossenen Zahlen sind:\n";
	}

	/**
	 * Gibt einen String zurück, welcher den Nutzer darauf hinweist das ungültige
	 * Eingaben getätig wurden. Nennt dem Nutzer auch diese unglültigen Eingaben und
	 * gibt dem Nutzer die Hilfe ausgabe aus.
	 * 
	 * @return
	 */
	public static String ungueltigeEingabe(ArrayList<String> tippgen) {
		StringBuilder sb = new StringBuilder();
		for (String string : tippgen) {
			if (ProgramFlow.istNumerisch(string)) {
				continue;
			} else {
				if (sb.equals(null)) {
					sb.append("Ungültige Eingaben:\n" + string + "\n");
				} else {
					sb.append(string + "\n");
				}
			}
			sb.append(hilfeAusgeben());
		}
		logger.log(Level.INFO, "Der Nutzer wurde darauf hingewiesen, dass eine ungültige Eingabe getätigt wurde."
				+ " Dem Nutzer wurde ein Hinweiß auf den Hilfebefehl gegeben.");
		return sb.toString();
	}

	/**
	 * Methode zur Ausgabe einer Benachrichtigung an der Nutzer das ein übergebener
	 * Befehl ungültige Strings enthielt und das diese ignoriert wurden
	 * 
	 * @param tippgen
	 */
	public static String ignorierterBefehl(ArrayList<String> tippgen) {
		StringBuilder sb = new StringBuilder();
		sb.append("Der Eingegebene Befehl enthielt ungültige Teile, welche ignoriert werden.\n");
		for (String string : tippgen) {
			sb.append(string + " ");
		}
		return sb.toString();
	}

	/**
	 * Gibt einen String zurück, dass derzeit keine Zahlen von der Tippgenerierung
	 * ausgeschlossen sind.
	 * 
	 * @return
	 */
	public static String keineZahlenAusgeschlossen() {
		return "Derzeit sind keine Zahlen von der Tippgenerierung ausgeschlossen.\n";
	}

	/**
	 * Methode um einen String mit der aktuellen Liste an ausgeschlossenen Zahlen zu
	 * erzeugen.
	 * 
	 * @param tippgenerator
	 */
	public static String ausgeschlosseneZahlenListe(TippGenerator tippgenerator) {
		return tippgenerator.liste().toString();
	}

	/**
	 * Methode um einen 6aus49 Tipp als String zu erhalten.
	 * 
	 * @param tipp
	 */
	public static String sechsAusNeunundvierzigTipp(ArrayList<Integer> tipp) {
		return ("6aus49:\n" + tipp + "\n");
	}

	/**
	 * Methode um einen Eurojackpot Tipp als String zu erhalten.
	 * 
	 * @param tipp
	 * @param zweiAusZehnTipp
	 */
	public static String eurojackpotTipp(ArrayList<Integer> tipp, ArrayList<Integer> zweiAusZehnTipp) {
		return ("5aus50:\n" + tipp + "\n 2aus10:\n" + zweiAusZehnTipp + "\n");
	}
}
