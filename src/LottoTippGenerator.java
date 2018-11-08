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
	public static Ausgabe ausgabe;
	public static ProgramFlow programFlow;
	public static Benutzereingabe benutzereingabe;

	/**
	 * Main Methode bereitet das Loggen vor, erzeugt ein Objekt Ausgabe und
	 * ProgrammFlow. Wanderlt String[] args ind einen String um und übergibt diesen
	 * an die start() Methode von programmflow.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Logging();
		logger.log(Level.INFO, "Programm gestartet.");

		ausgabe = new Ausgabe();
		programFlow = new ProgramFlow();

		String befehl = "";
		befehl = Arrays.stream(args).collect(Collectors.joining(" "));
		if (befehl != null && befehl.length() > 0 && befehl.charAt(befehl.length() - 1) == ' ') {
			befehl = befehl.substring(0, befehl.length() - 1);
		}

		befehl = befehl.toUpperCase();
		programFlow.start(befehl);
	}
}
