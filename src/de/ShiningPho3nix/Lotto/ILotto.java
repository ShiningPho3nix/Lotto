package de.ShiningPho3nix.Lotto;

import java.util.ArrayList;

/**
 * Interface Klasse f�r Lotto-Objekte. Da es mehrere Arten von Lotto gibt, ist
 * deren Implementation unterschiedlich, da jedoch die selben Methoden n�tig
 * sind bietet sich hier ein Interface an.
 * 
 * @author Steffen Dworsky
 *
 */
public interface ILotto {

	/**
	 * L�dt dei Ungl�ckszahlen aus der Datei. Erzeugt eine neue, sollte keine
	 * existieren.
	 */
	void laden();

	/**
	 * Speichert die Ungl�ckszahlen in der Datei. Erzeugt eine neue, sollte keine
	 * existieren.
	 */
	void speichern();

	/**
	 * Generiert einen Tipp im aktuell gew�hlten Modus, bzw. in der Klasse, von
	 * welchem TippGenerator das Objekt h�lt.
	 * 
	 * @return Einen formatierten String, welcher den Tipp darstellt.
	 */
	String generiereTipp();

	/**
	 * F�hrt generiereTipp() die mit quicktipp angegebene Anzahl an mal aus.
	 * 
	 * @param quicktipp
	 * @return Einen formatierten String, welcher alle generierten Tipps darstellt.
	 */
	String generiereTipps(int quicktipp);

	/**
	 * Erstellt mithilfe der Ungl�ckszahlen die Tippzahlen ArrayLists f�r das Objekt
	 * in TippGenerator
	 */
	void erstelleCollection();

	/**
	 * Erm�glicht das ausschlie�en von Zahlen aus der Tippgenerierung.
	 * 
	 * @param deleteZahlen
	 * @return Einen formatierten String welcher f�r jede der �bergebenen Zahlen
	 *         eine Benachrichtigung enth�lt, ob die Aktion erfolgreich war oder
	 *         nicht. Relevant vorallem f�r die Ausgabe dieser Informationen an den
	 *         Nutzer �ber die GUI.
	 */
	String neueUnglueckszahlAusschliessen(Integer[] deleteZahlen);

	/**
	 * Erm�glicht es Zahlen welche von der Tippgenerierung ausgeschlossen sind
	 * wieder zuzulassen.
	 * 
	 * @param addZahlen
	 * @return Einen formatierten String welcher f�r jede der �bergebenen Zahlen
	 *         eine Benachrichtigung enth�lt, ob die Aktion erfolgreich war oder
	 *         nicht. Relevant vorallem f�r die Ausgabe dieser Informationen an den
	 *         Nutzer �ber die GUI.
	 */
	String unglueckszahlWiederZulassen(Integer[] addZahlen);

	/**
	 * Gibt den aktuellen Modus, also den Typ des Objektes welches von TippGenerator
	 * gehalten wird als String zur�ck.
	 * 
	 * @return 'Euro' f�r Eurojackpot oder '6aus49' f�r SechsAusNeunundvierzig.
	 */
	String modus();

	/**
	 * L�scht alle Ungl�ckszahlen und erzeugt die Tippzahlen ArrayLists neu.
	 */
	void reset();

	/**
	 * Gibt eine Liste mit den Ungl�ckszahlen zur�ck.
	 * 
	 * @return ArrayList der Ungl�ckszahlen.
	 */
	ArrayList<Integer> liste();

}
