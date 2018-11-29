import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Klasse enth�lt Methoden zum laden der ausgeschlossenen Zahlen aus der Datei.
 * 
 * @author Steffen Dworsky
 *
 */
public class Laden extends SFileOperation {

	/**
	 * Im Konstruktor wird lediglich der super() Konstruktor ausgef�hrt.
	 */
	public Laden() {
		super();
		logger.log(Level.INFO, "Laden Konstruktor durchgelaufen.");
	}

	/**
	 * Die Funktion laden l�dt die ausgeschlossenen Zahlen aus der Datei. Die
	 * einzelnen Zahlen werden dann in ein lokales Array gelegt und anschlie�end
	 * �bergeben. Sollte keine Datei existieren wird eine neue Datei erzeugt und das
	 * Array leer �bergeben. Befor das Array �bergeben wird, wird es noch auf
	 * korrektheit gepr�ft mit checkArray
	 * 
	 * @return
	 */
	public ArrayList<Integer> laden() {

		String filePath = currentDirectory().concat("\\TippGenerator.txt"); // Durch currentDirectory mit angeh�ngtem
																			// Namen der Datei wird hier der Absolute
																			// Path als String festgelegt.
		File file = new File(filePath); // Der oben gefundene filepath wird hier zum erzeugen eines neune file objektes
										// genutzt.
		String ladeString = ""; // sollten keine Zahlen geladen werden, so wird der String unver�ndert returned,
								// daher wird dieser als leerer String inizialisiert.
		ArrayList<Integer> ladeArrayList = new ArrayList<Integer>(); // Um die geladenen Zahlen zwischen zu speichern
																		// und zu
																		// returnen

		if (!file.exists()) { // sollte unter dem path weiter oben noch keine datei mit dem angegebenen namen
								// existieren, so wird hier die Datei erzeugt, bzw. createFile() ausgef�hrt.
			try {
				createFile();
				logger.log(Level.INFO, "Es wurde eine Datei f�r zum Speichern der Ungl�ckszahlen erzeugt.");
				ausgabe.dateiErstellt();
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
																// leer zur�ckgegeben.
				logger.log(Level.INFO,
						"Keine ausgeschlossenen Zahlen in der Datei gefunden. Leeres Array wird �bergeben.");
				br.close();
				return ladeArrayList;
			}
			ladeString = ladeString.replace("[", "").replace("]", "").replace(" ", ""); // Da die ArrayList beim
																						// Abspeichern direkt in die
																						// Datei geschrieben wird, hat
																						// sie das Format [1, 2, 3,].
																						// Die unn�tigen zeichen werden
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
				"Ausgeschlossene Zahlen wurden erfolgreich aus der Datei gelesen und werden nun als Array �bergeben.");
		return checkArray(ladeArrayList); // Zum abschluss werden noch einige anforderungen gepr�ft und die ArrayList
											// dann zur�ckgegeben.
	}
}
