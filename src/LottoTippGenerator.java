import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Main Klasse zum Starten des Programms
 * 
 * @author Steffen Dworsky
 *
 */
public class LottoTippGenerator {

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static StringSammlung ausgabe;
	public static ProgramFlow programFlow;
	public static Benutzereingabe benutzereingabe;

	/**
	 * Main Methode bereitet das Loggen vor, erzeugt ein Objekt Ausgabe und
	 * ProgrammFlow. Wanderlt String[] args ind einen String um und ¸bergibt diesen
	 * an die start() Methode von programmflow.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Logging(); // Startet das Logging
		logger.log(Level.INFO, "Programm gestartet.");

		ausgabe = new StringSammlung();
		programFlow = new ProgramFlow();

		String befehl = "";
		befehl = Arrays.stream(args).collect(Collectors.joining(" ")); // Erzeugt aus dem String Array args einen
																		// String, die einzelnen Eintr‰ge des Arrays
																		// werden durch ein Leerzeichen getrennt
		if (befehl != null && befehl.length() > 0 && befehl.charAt(befehl.length() - 1) == ' ') {
			befehl = befehl.substring(0, befehl.length() - 1); // Da die Eintr‰ge des Array gejoind werden mit einem
																// Leerzeichen, hat der resultierende String am Ende
																// immer ein Leerzeichen. Dies wird durch diese Zeile
																// entfernt.
		}

		befehl = befehl.toUpperCase(); // Nahezu alle eingaben werden zu Uppercase umgewandelt, um
										// groﬂ-/kleinschreibung
										// duch den nutzer zu ignorieren.
		programFlow.start(befehl);
	}
}
