import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Klasse um das Logging für das komplette Programm zu verwalten.
 * 
 * @author Steffen Dworsky
 *
 */
public class Logging {

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static FileHandler fileHandler;
	private SimpleFormatter formatter;
	private String pattern = "\\LottoTippGeneratorLog%g.log";

	/**
	 * Im Konstrukrot wird zunächst deleteLocks ausgeführt , danach wir versucht die
	 * Logdatei zu öffnen. es wird ein SimpleFormatter erzeugt und als formatter für
	 * den Filehandler gesetzt.
	 */
	public Logging() {
		deleteLocks();
		try {
			fileHandler = new FileHandler(Paths.get("").toAbsolutePath().toString().concat(pattern), 100000, 10, true);
		} catch (SecurityException | IOException e) {
			logger.log(Level.WARNING, "Auf log Datei konnte nich zugegriffen werden.", e);
			e.printStackTrace();
		}

		formatter = new SimpleFormatter();
		fileHandler.setFormatter(formatter);
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
		logger.log(Level.INFO, "Logging gestartet.");

	}

	/**
	 * Funktion sucht im aktuellem Verzeichniss nach .lck dateien und verscuht diese
	 * zu löschen.
	 */
	private void deleteLocks() {
		File dir = new File(Paths.get("").toAbsolutePath().toString());
		File[] allFiles = dir.listFiles();

		for (File file : allFiles) {
			String lockFile = file.getName();
			if (lockFile.contains(".lck")) {
				file.delete();
				System.out.println(
						"Das Programm wurde beim letzten mal nicht korrekt beendet. Sollte das Programm nicht korrekt starten, so müssen 'LottoTippGeneratorLog.lck' Datei(en) manuell gelöscht werden.");
			}
		}
	}

	/**
	 * Funktion schlißt den FileHandler
	 */
	public static void quit() {
		logger.log(Level.INFO, "Logging wird jetzt Beendet.");
		fileHandler.close();
	}
}
