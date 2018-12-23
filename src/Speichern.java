import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Diese Klasse enthält Methoden um die nötigen Daten persistent zu speichern.
 * 
 * @author Steffen Dworsky
 *
 */
public class Speichern extends SFileOperation {

	/**
	 * Im Konstruktor wird der super() konstruktor ausgeführt.
	 */
	public Speichern() {
		super();
		logger.log(Level.INFO, "Speichern Konstruktor durchgelaufen.");
	}

	/**
	 * Die Funktion speichern bekommt die ArrayList mit den ausgeschlossenen Zahlen
	 * übergeben und speichert das Array anschließend in einer Datei ab. Vor dem
	 * Abspeichern wird checkArray ausgeführt. Die Datei wird in dem aktuellen
	 * Verzeichniss gegebenenfalls erzeugt.
	 * 
	 * @param ungluecksZahlen
	 * @param mode
	 */
	public void speichern(ArrayList<Integer> ungluecksZahlen) {
		String filePath = currentDirectory().concat("\\TippGenerator.txt");
		File file = new File(filePath);
		if (!file.exists()) { // sollte es noch keine Datei zum abspeichern der unglückszahlen geben, so wird
								// eine erzeugt.
			try {
				createFile();
				logger.log(Level.INFO, "Datei wurde erzeugt (" + filePath + ").");
				ausgabe.dateiErstellt();
			} catch (IOException e) {
				logger.log(Level.WARNING, "Die Datei konnte nicht erstellt werden (" + filePath + ").", e);
				e.printStackTrace();
			}
		}
		try (PrintWriter out = new PrintWriter(file)) {
			ungluecksZahlen = checkArray(ungluecksZahlen); // ArrayList wird vor dem Abspeichern nochmals geprüft.
			out.write(ungluecksZahlen.toString());
			logger.log(Level.INFO, ungluecksZahlen + " wurde in die Datei geschrieben.");
			out.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "Es konnte keine Datei gefunden werden!", e);
			e.printStackTrace();
		}
	}
}
