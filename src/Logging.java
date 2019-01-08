import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Klasse um das Logging f�r das komplette Programm zu verwalten. In den
 * einzelnen Classen muss lediglich das Feld private static final Logger logger
 * = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); existieren. Um probleme mit
 * zugriffen auf die LogDatei zu umgehen, locks zu l�schen und anschlie�end den
 * zugriff auf die Datei wieder richtig und vollst�ndig zu beenden.
 * 
 * @author Steffen Dworsky
 *
 */
public class Logging {

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static FileHandler fileHandler;
	private SimpleFormatter formatter;
	private String path = Paths.get("").toAbsolutePath().toString().concat("\\LottoTippGenFiles\\logs");
	private String fileName = "\\LottoTippGeneratorLog%g.log";

	/**
	 * Im Konstruktor wird zun�chst deleteLocks ausgef�hrt, danach wir versucht die
	 * Logdatei zu �ffnen. es wird ein SimpleFormatter erzeugt und als formatter f�r
	 * den Filehandler gesetzt.
	 */
	public Logging() {
		try {
			File file = new File(path.concat(fileName));
			file.getParentFile().mkdirs();
			fileHandler = new FileHandler(path.concat(fileName), 100000, 10, true);
			// erlaubt 100000 byte pro datei, 10 Dateien und concat ist aktiviert.
			deleteLocks(); // Sollte das Programm nicht richtig beendet worden sein, so werden alle lock
							// Dateien gel�scht.
		} catch (SecurityException | IOException e) {
			logger.log(Level.WARNING, "Auf log Datei konnte nich zugegriffen werden.", e);
			e.printStackTrace();
		}

		formatter = new SimpleFormatter(); // Formatter f�r die logdateien
		fileHandler.setFormatter(formatter);
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false); // Um zu verhindern das Logs auf der Konsole ausgegeben werden.
		logger.log(Level.INFO, "Logging gestartet.");

	}

	/**
	 * Funktion sucht im aktuellem Verzeichniss nach .lck dateien und verscuht diese
	 * zu l�schen.
	 */
	private void deleteLocks() {
		File file = new File(path);
		File[] allFiles = file.listFiles(); // Sucht alle Dateien im aktuellen verzeichniss raus
		if (allFiles.length == 0) {
			return;
		}

		for (File files : allFiles) {
			String lockFile = files.getName();
			if (lockFile.contains(".lck")) { // Enth�lt eine Datei die endung .lck so wird die datei gel�scht.
				files.delete();
				System.out.println(StringSammlung.nichtKorrektBeendet());
			}
		}
	}

	/**
	 * Funktion schli�t den FileHandler
	 */
	public static void quit() {
		logger.log(Level.INFO, "Logging wird jetzt Beendet.");
		fileHandler.close();
	}
}
