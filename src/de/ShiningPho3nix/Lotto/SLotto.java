package de.ShiningPho3nix.Lotto;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ShiningPho3nix.Lotto.GUI.LottoTippGeneratorGUI;

public abstract class SLotto {

	public ArrayList<Integer> tippzahlenArray;
	public ArrayList<Integer> zweiAusZehnTippzahlenArray;
	public ArrayList<Integer> unglueckszahlenArray;
	private final static Logger logger = LogManager.getLogger(LottoTippGeneratorGUI.class);

	/**
	 * Der Konstruktor inizialisiert die beiden Arrays f�r die Menge an Zahlen zur
	 * Tippgenerierung, sowie f�r die entfernten Zahlen. Anschlie�end wird die
	 * Funktion laden() ausgef�hrt.
	 */
	public SLotto() {
		tippzahlenArray = new ArrayList<Integer>();
		zweiAusZehnTippzahlenArray = new ArrayList<Integer>();
		unglueckszahlenArray = new ArrayList<Integer>();
		logger.info("tippzahlenArrays & unglueckszahlenArray wurden inizialisiert");
		laden();
		erstelleCollection();
		logger.info("Konstruktor durchgelaufen.");
	}

	/**
	 * Methode zum entfernen von Zahlen aus der Menge an Zahlen f�r die
	 * Tippgenerierung. L�schen einer Zahl ist nur m�glich wenn noch nicht 6 Zahlen
	 * entfernt wurden und die Zahl selbst noch nicht entfernt worden ist.
	 * Anschli�end wird entferneAusTippzahlen ausgef�hrt.
	 */
	public String neueUnglueckszahlAusschliessen(Integer[] zahlen) {
		StringBuilder sb = new StringBuilder();
		for (int zahl : zahlen) {
			if (unglueckszahlenArray.size() < 6 && !unglueckszahlenArray.contains(zahl) && 0 < zahl && zahl < 51
					&& tippzahlenArray.contains(zahl)) {
				// Pr�ft ob noch nicht 6 ungl�ckszahlen gew�hlt sind, ob die aktuelle Zahl noch
				// keine unl�ckszahl ist und ob die Zahl im g�ltigen Bereich liegt.
				unglueckszahlenArray.add(zahl); // F�gt die Zahl der ungl�ckszahlen ArrayList hinzu.
				tippzahlenArray.remove((Integer) zahl);
				if (zweiAusZehnTippzahlenArray.contains(zahl)) {
					zweiAusZehnTippzahlenArray.remove((Integer) zahl); // entfernt die Aktuelle Zahl aus der 2aus10
					// ArrayList.
					logger.info(zahl + " wurde auch aus dem zweiAusZehnTippzahlenArray entfernt.");
				}
				logger.info(zahl + " wurde aus der Menge an m�glichen Zahlen f�r die " + " Tippgenerierung entfernt.");
				logger.info("ungl�ckszahlenArray gespeichert.");
				sb.append(StringSammlung.erfolgreichEntfernt(zahl) + "\n");
				speichern();
			} else {
				sb.append(StringSammlung.nichtLoeschbar(zahl) + "\n"); // Sind die Bedingungen an eine der Zahlen
				// nicht erf�llt, so wird
				// auf der
				// Konsole ausgegeben das diese Zahl nicht gel�scht werden kann. Es wird
				// mit der n�chsten Zahl weiter gemacht.
				logger.info(zahl + " wurde nicht aus der Menge an m�glichen Zahlen f�r die "
						+ " Tippgenerierung entfernt.");
			}
		}
		logger.info("entferneZahlen() durchgelaufen");
		return sb.toString();
	}

	/**
	 * Funktion um eine Zahl welche ausgeschlossen wurde wieder zuzulassen. Daf�r
	 * muss die Zahl eine Zahl sein, in dem Array: unglueckszahlenArray vorhanden
	 * und in dem Array: sechsAusNeunundvierzig nicht vorhanden sein.
	 * 
	 * @return
	 */
	public String unglueckszahlWiederZulassen(Integer[] zahlen) {
		StringBuilder sb = new StringBuilder();
		for (int zahl : zahlen) { // durchl�uft das Array an zu l�schenden Zahlen
			if (unglueckszahlenArray.contains(zahl)) { // Ist die Aktuelle Zahl im ungl�cksArray, ...
				unglueckszahlenArray.remove((Integer) zahl); // ... so wird diese entfernt ...
				tippzahlenArray.add(zahl); // und dem tippzahlenAray hinzugef�gt.
				if (zahl <= 10) {
					zweiAusZehnTippzahlenArray.add(zahl);
				}
				logger.info(zahl + " wurde der Menge an m�glichen Zahlen zur Tippgenerierung wieder hinzugef�gt.");
				speichern(); // das aktualisierte Array der ungl�ckszahlen wird abgespeichert.
				logger.info("entferneUnglueckszahl() erfolgreich durchgelaufen");
				sb.append(StringSammlung.erfolgreichWiederHinzugefuegt(zahl) + "\n");
			} else { // Ist die aktuelle Zahl nicht unter den entfernen, so wird diese ignoriert.
				logger.info(
						zahl + " wurde nicht der Menge an m�glichen Zahlen zur Tippgenerierung wieder hinzugef�gt.");
				sb.append(StringSammlung.hinzufuegenNichtMoeglich(zahl) + "\n");
			}
		}
		return sb.toString();
	}

	/**
	 * Funktion zur Erstellung einer Liste mit zul�ssigen Zahlen f�r die Tipp
	 * Generierung. Die ausgeschlossenen Zahlen werden hierbei nicht mit
	 * aufgenommen. Die Zul�ssigen Zahlen werden dem tippzahlenArray hinzugef�gt.
	 */
	public void erstelleCollection() {
		logger.error("�berladen der Methode erstelleCollection hat nicht korrekt funktioniert.");
	}

	public void erstelleZweiAusZehnCollection() {
		for (int i = 1; i < 11; i++) { // F�r die Zahlen 1-10
			if (unglueckszahlenArray.contains((Integer) i)) {
				logger.info(Integer.toString(i)
						+ " als Zahl aus der Menge an m�glichen Zahlen zur Tippgenerierung ausgeschlossen.");
				continue; // Wenn die aktuelle Zahl unter den ausgeschlossenen Zahlen ist, wird mit der
							// n�chsten Zahl weiter gemacht.
			} else {
				zweiAusZehnTippzahlenArray.add(i); // Ist die Zahl nicht im ungl�cksArray so wird die Zahl dem
													// zweiAusZehntippzahlenArray hinzugef�gt.
			}
		}
		logger.info("zweiAusZehntippzahlenArray gef�llt.");
	}

	/**
	 * Diese Methode wird in den jeweiligen Klassen Eurojackpot und 6aus49
	 * �berschrieben und dort ausgef�hrt. F�hrt dem entsprechend nat�rlich auch die
	 * korrekte version basierend auf dem jeweiligem Objekt in TippGenerator (euro
	 * oder 6aus49) aus.
	 * 
	 * @return
	 */
	public String generiereTipp() {
		logger.error("�berladen der Methode generiereTipp() hat nicht korrekt funktioniert.");
		// generiereTipp wird in den jeweiligen Klassen Eurojackpot und 6aus49
		// ausgef�hrt.
		return null;
	}

	/**
	 * Hat der Nutzer den Wunsch mehrere Tipps zu generieren, so wird diese Methode
	 * verwendet, welche generiereTipp() die gew�nschte anzahl an mal ausf�hrt und
	 * somit die gew�nschte anzahl an Tipps generiert werden.
	 */
	public String generiereTipps(int quicktipp) {
		StringBuilder sb = new StringBuilder();
		if (quicktipp < 1) {
			quicktipp = 1;
		}
		for (int i = 1; i <= quicktipp; i++) {
			sb.append("Tipp#" + i + System.lineSeparator()); // F�r die �bergeben Anzahl an mal wird Tipp mit
																// fortlaufender Zahl
			// ausgegeben...
			sb.append(generiereTipp() + System.lineSeparator()); // ...und anschlie�end ein Tipp generiert.
		}
		logger.info("Es wurden " + quicktipp + " Tipps generiert.");
		return sb.toString();
	}

	/**
	 * Funktion zum Laden der abgespeicherten Zahlen, welche nicht in den
	 * generierten Tipps vorkommen sollen.
	 */
	public void laden() {
		unglueckszahlenArray = FileOperation.laden(); // F�gt die laden Funktion der Klasse LAden aus und speichert das
		// zur�ckgegebene Array ind ungkl�ckszahlenArray ab.
		logger.info("Array mit ausgeschlossenen Zahlen wurde erfolgreich �bergeben.");
	}

	/**
	 * Funktion zum Speichern der ausgeschlossenen Zahlen. Ruft die Methode
	 * speichern() der Klasse Seichern auf.
	 */
	public void speichern() {
		FileOperation.speichern(unglueckszahlenArray, "Unglueckszahlen.txt"); // speichert das aktuelle
																				// ungl�ckszahlenArray ab.
		logger.info("Gespeichert, SLotto speichern durchgelaufen.");
	}

	/**
	 * Gibt das unglueckszahlenArray als ArrayList zur�ck.
	 * 
	 * @return
	 */
	public ArrayList<Integer> liste() {
		logger.info("ungl�ckszahlenArray �ber Methode liste zur�ckgegeben");
		return unglueckszahlenArray;
	}

	/**
	 * Funktion �berschreibt das bestehende ungl�ckszahlenArray mit einem neuen
	 * leeren Array und speichert dieses ab. Somit werden alles gesperrten Zahlen
	 * auf einmal gel�scht.
	 */
	public void reset() {
		unglueckszahlenArray = new ArrayList<Integer>(); // erzeugt eine Neue ArrayList und speichert diese in
															// ungl�ckszahlenArray ab.
		speichern();
	}

	/**
	 * Gibt den Aktuellen Spielmodus als String zur�ck.
	 */
	public String getModus() {
		logger.error("�berladen der Methode getModus() hat nicht funktioniert.");
		return null;
	}
}