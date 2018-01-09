package Exceptions;

/**
 * Exception : Access a un acteur non initialise.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
@SuppressWarnings("serial")
public class NoActeurType extends TerrainException {
	/**
	 * Constructeur de base.
	 */
	public NoActeurType() {
		super("Erreur pas d'acteur type trouve.") ;
	}
}
