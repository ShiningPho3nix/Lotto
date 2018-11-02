import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main Klasse zum Starten des Programms
 * 
 * @author Steffen Dworsky
 *
 */
public class LottoTippGenerator {

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	static Ausgabe ausgabe;
	public static ProgramFlow programFlow;

	/**
	 * Main Methode, bereitet das Loggen vor, erzeugt ein Objekt Ausgabe, welches
	 * von den anderen Klassen später verwendet wird und ruft die Funktion start()
	 * auf.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Logging();
		logger.log(Level.INFO, "Programm gestartet.");
		ausgabe = new Ausgabe();
		programFlow = new ProgramFlow();
		programFlow.start();
	}
}
