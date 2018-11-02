import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Klasse enthält Methoden zum laden der ausgeschlossenen Zahlen aus der
 * jeweiligen Datei.
 * 
 * @author Steffen Dworsky
 *
 */
public class Laden extends SFileOperation {

	/**
	 * Im Konstruktor werden die nötigen Objekte erzeugt.
	 */
	public Laden() {
		super();
		logger.log(Level.INFO, "Laden Konstruktor durchgelaufen.");
	}

	/**
	 * Die Funktion laden bekommt den aktuellen modus übergeben und lädt
	 * anschließend die ausgeschlossenen Zahlen aus der entsprechenden Datei. Die
	 * einzelnen Zahlen werden dann in ein lokales Array gelegt und anschließend
	 * übergeben. Sollte keine Datei existieren wird das Array leer übergeben.
	 * 
	 * @param mode
	 * @return
	 */
	public ArrayList<Integer> laden(String mode) {

		String filePath = currentDirectory().concat("\\TippGenerator" + mode + ".txt");
		File file = new File(filePath);
		String ladeString = "";
		ArrayList<Integer> ladeArray = new ArrayList<Integer>();

		if (!file.exists()) {
			try {
				createFile(mode);
				logger.log(Level.INFO, "Es wurde eine Datei für den Modus: " + mode + " erzeugt.");
				ausgabe.dateiErstellt(mode);
			} catch (IOException e) {
				logger.log(Level.WARNING, "Die Datei konnte nicht erstellt werden (" + filePath + ")", e);
				e.printStackTrace();
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			ladeString = br.readLine();
			if (ladeString == null) {
				logger.log(Level.INFO,
						"Keine ausgeschlossenen Zahlen in der Datei gefunden. Leeres Array wird übergeben.");
				br.close();
				return ladeArray;
			}
			ladeString = ladeString.replace("[", "").replace("]", "").replace(" ", "");
			logger.log(Level.INFO, ladeString + " wurden als ausgeschlossene Zahlen aus der Datei gelesen.");
			br.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, "Die Datei konnte nicht gelesen werden!", e);
			e.printStackTrace();
		}
		String[] partArray = ladeString.split(",");
		for (String string : partArray) {
			ladeArray.add(Integer.parseInt(string));
		}
		logger.log(Level.INFO,
				"Ausgeschlossene Zahlen wurden erfolgreich aus der Datei gelesen und werden nun als Array übergeben.");
		return ladeArray;
	}
}
