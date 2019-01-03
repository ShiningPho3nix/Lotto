import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Klasse enthält Methoden zum laden der ausgeschlossenen Zahlen aus der Datei.
 * 
 * @author Steffen Dworsky
 *
 */
public class Laden extends SFileOperation {

	/**
	 * Im Konstruktor wird lediglich der super() Konstruktor ausgeführt.
	 */
	public Laden() {
		super();
		logger.log(Level.INFO, "Laden Konstruktor durchgelaufen.");
	}

	/**
	 * Die Funktion laden lädt die ausgeschlossenen Zahlen aus der Datei. Die
	 * einzelnen Zahlen werden dann in ein lokales Array gelegt und anschließend
	 * übergeben. Sollte keine Datei existieren wird eine neue Datei erzeugt und das
	 * Array leer übergeben. Befor das Array übergeben wird, wird es noch auf
	 * korrektheit geprüft mit checkArray
	 * 
	 * @return
	 */
	public static ArrayList<Integer> laden() {

		String filePath = currentDirectory().concat("\\TippGenerator.txt"); // Durch currentDirectory mit angehängtem
																			// Namen der Datei wird hier der Absolute
																			// Path als String festgelegt.
		File file = new File(filePath); // Der oben gefundene filepath wird hier zum erzeugen eines neune file objektes
										// genutzt.
		String ladeString = ""; // sollten keine Zahlen geladen werden, so wird der String unverändert returned,
								// daher wird dieser als leerer String inizialisiert.
		ArrayList<Integer> ladeArrayList = new ArrayList<Integer>(); // Um die geladenen Zahlen zwischen zu speichern
																		// und zu
																		// returnen

		if (!file.exists()) { // sollte unter dem path weiter oben noch keine datei mit dem angegebenen namen
								// existieren, so wird hier die Datei erzeugt, bzw. createFile() ausgeführt.
			try {
				createFile();
				logger.log(Level.INFO, "Es wurde eine Datei für zum Speichern der Unglückszahlen erzeugt.");
				System.out.println(StringSammlung.dateiErstellt());
			} catch (IOException e) {
				logger.log(Level.WARNING, "Die Datei konnte nicht erstellt werden (" + filePath + ")", e);
				e.printStackTrace();
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); // Zum einlesen der Informationen aus der
																			// Datei wird eine BufferedReader verwendet.
			ladeString = br.readLine();
			if (ladeString == null || ladeString.equals("")) { // Sollte nichts eingelesen werden, wird die ArrayListe
																// leer zurückgegeben.
				logger.log(Level.INFO,
						"Keine ausgeschlossenen Zahlen in der Datei gefunden. Leeres Array wird übergeben.");
				br.close();
				return ladeArrayList;
			}
			ladeString = ladeString.replace("[", "").replace("]", "").replace(" ", ""); // Da die ArrayList beim
																						// Abspeichern direkt in die
																						// Datei geschrieben wird, hat
																						// sie das Format [1, 2, 3,].
																						// Die unnötigen zeichen werden
																						// daher entfernt.
			logger.log(Level.INFO, ladeString + " wurden als ausgeschlossene Zahlen aus der Datei gelesen.");
			br.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, "Die Datei konnte nicht gelesen werden!", e);
			e.printStackTrace();
		}
		String[] partArray = ladeString.split(","); // Die Kommas bleiben und werden hier zum trennen der Zahlen
													// verwendet.
		for (String string : partArray) {
			if (string.equals("")) {
				continue;
			}
			try {
				ladeArrayList.add(Integer.parseInt(string)); // Sollte die Datei Manipuliert worden sein oder auf
																// irgendeinem anderen wege nicht-Zahlen in die
																// Textdatei geraten sein, so werden diese hier
																// rausgefiltert.
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, string + " ist keine Zahl und wird daher ignoriert", e);
				continue;
			}
		}
		logger.log(Level.INFO,
				"Ausgeschlossene Zahlen wurden erfolgreich aus der Datei gelesen und werden nun als Array übergeben.");
		return checkArray(ladeArrayList); // Zum abschluss werden noch einige anforderungen geprüft und die ArrayList
											// dann zurückgegeben.
	}
}
