package Exceptions;

/**
 * Exception indicatrice d'un acces à un element memoire non valide. ( typiquement : une case du Terrain hors des bornes )
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
@SuppressWarnings("serial")
public class HorsLimite extends TerrainException {
	/**
	 * Constructeur de l'exception.
	 * 
	 * @param x Abscisse de la case a l'origine de l'exception
	 * @param y Ordonnee de la case a l'origine de l'exception
	 * @param xmax Abscisse maximale possible.
	 * @param ymax Ordonne maximale possible.
	 */
	public HorsLimite(int x, int y, int xmax, int ymax) {
		super("Coordonnees " + x + " " + y + " hors limites. ( xmax = " + xmax + " , ymax = " + ymax + " )") ;
	}
}
