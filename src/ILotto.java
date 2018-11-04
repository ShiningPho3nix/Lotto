/**
 * Interface Klasse f�r Lotto-Objekte. Da es mehrere Arten von Lotto gibt, ist
 * deren Implementation unterschiedlich, da jedoch die selben Methoden n�tig
 * sind bietet sich hier ein Interface an.
 * 
 * @author Steffen Dworsky
 *
 */
public interface ILotto {

	void laden();

	void speichern();

	void generiereTipp();

	void generiereTipps(int quicktipp);

	void erstelleCollection();

	void entferneZahlen(int[] zahl);

	void entferneUnglueckszahl(int[] zahl);

	String modus();

	void reset();

}
