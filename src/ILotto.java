/**
 * Interface Klasse für Lotto-Objekte. Da es mehrere Arten von Lotto gibt, ist
 * deren Implementation unterschiedlich, da jedoch die selben Methoden nötig
 * sind bietet sich hier ein Interface an.
 * 
 * @author Steffen Dworsky
 *
 */
public interface ILotto {

	void laden();

	void speichern();

	void generiereTipp();

	void erstelleCollection();

	void entferneZahlen(int zahl);

	void entferneUnglueckszahl(int zahl);

	String modus();
	
	void reset();
}
