import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Diese Klasse enth�lt Methoden um die n�tigen Daten persistent zu speichern.
 * 
 * @author Steffen Dworsky
 *
 */
public class Speichern extends SFileOperation {

	/**
	 * Im Konstruktor wird der super() konstruktor ausgef�hrt.
	 */
	public Speichern() {
		super();
		logger.log(Level.INFO, "Speichern Konstruktor durchgelaufen.");
	}

	/**
	 * Die Funktion speichern bekommt die ArrayList mit den ausgeschlossenen Zahlen
	 * �bergeben und speichert das Array anschlie�end in einer Datei ab. Vor dem
	 * Abspeichern wird checkArray ausgef�hrt. Die Datei wird in dem aktuellen
	 * Verzeichniss gegebenenfalls erzeugt.
	 * 
	 * @param ungluecksZahlen
	 * @param mode
	 */
	public static void speichern(ArrayList<Integer> ungluecksZahlen) {
		String filePath = currentDirectory().concat("\\TippGenerator.txt");
		File file = new File(filePath);
		if (!file.exists()) { // sollte es noch keine Datei zum abspeichern der ungl�ckszahlen geben, so wird
								// eine erzeugt.
			try {
				createFile();
				logger.log(Level.INFO, "Datei wurde erzeugt (" + filePath + ").");
				System.out.println(StringSammlung.dateiErstellt());
			} catch (IOException e) {
				logger.log(Level.WARNING, "Die Datei konnte nicht erstellt werden (" + filePath + ").", e);
				e.printStackTrace();
			}
		}
		try (PrintWriter out = new PrintWriter(file)) {
			ungluecksZahlen = checkArray(ungluecksZahlen); // ArrayList wird vor dem Abspeichern nochmals gepr�ft.
			out.write(ungluecksZahlen.toString());
			logger.log(Level.INFO, ungluecksZahlen + " wurde in die Datei geschrieben.");
			out.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "Es konnte keine Datei gefunden werden!", e);
			e.printStackTrace();
		}
	}
}
