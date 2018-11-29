import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Klasse um das Logging für das komplette Programm zu verwalten. In den
 * einzelnen Classen muss lediglich das Feld private static final Logger logger
 * = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); existieren. Um probleme mit
 * zugriffen auf die LogDatei zu umgehen, locks zu löschen und anschließend den
 * zugriff auf die Datei wieder richtig und vollständig zu beenden.
 * 
 * @author Steffen Dworsky
 *
 */
public class Logging {

	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static FileHandler fileHandler;
	private SimpleFormatter formatter;
	private String pattern = "\\LottoTippGeneratorLog%g.log"; // Ein Name Pattern, um mehrere Log Dateien zu
																// ermöglichen.

	/**
	 * Im Konstruktor wird zunächst deleteLocks ausgeführt, danach wir versucht die
	 * Logdatei zu öffnen. es wird ein SimpleFormatter erzeugt und als formatter für
	 * den Filehandler gesetzt.
	 */
	public Logging() {
		deleteLocks(); // Sollte das Programm nicht richtig beendet worden sein, so werden zunächst
						// alles lock dateien gelöscht.
		try {
			fileHandler = new FileHandler(Paths.get("").toAbsolutePath().toString().concat(pattern), 100000, 10, true);
			// erlaubt 100000 byte pro datei, 10 Dateien und concat ist aktiviert.
		} catch (SecurityException | IOException e) {
			logger.log(Level.WARNING, "Auf log Datei konnte nich zugegriffen werden.", e);
			e.printStackTrace();
		}

		formatter = new SimpleFormatter(); // Formatter für die logdateien
		fileHandler.setFormatter(formatter);
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false); // Um zu verhindern das Logs auf der Konsole ausgegeben werden.
		logger.log(Level.INFO, "Logging gestartet.");

	}

	/**
	 * Funktion sucht im aktuellem Verzeichniss nach .lck dateien und verscuht diese
	 * zu löschen.
	 */
	private void deleteLocks() {
		File dir = new File(Paths.get("").toAbsolutePath().toString());
		File[] allFiles = dir.listFiles(); // Sucht alle Dateien im aktuellen verzeichniss raus

		for (File file : allFiles) {
			String lockFile = file.getName();
			if (lockFile.contains(".lck")) { // Enthält eine Datei die endung .lck so wird die datei gelöscht.
				file.delete();
				System.out.println("Das Programm wurde beim letzten mal nicht korrekt beendet! \n"
						+ " Sollte das Programm nicht korrekt starten, so müssen 'LottoTippGeneratorLog.lck' Datei(en) manuell gelöscht werden.");
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
