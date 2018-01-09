package Exceptions;

/**
 * Exception : aucun terrain initialise.
 * 
 * @author Jonathan
 *
 */
@SuppressWarnings("serial")
public class NoTerrain extends TerrainException {
	/**
	 * Constructeur par defaut.
	 */
	public NoTerrain() {
		super("Aucun terrain n'a ete initialise.") ;
	}
}
