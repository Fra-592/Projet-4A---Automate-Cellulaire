package Exceptions;

/**
 * Exception lancee par l'acces a un acteur non initialise.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 */
@SuppressWarnings("serial")
public class NoActor extends TerrainException {
	/**
	 * Constructeur par defaut.
	 */
	public NoActor() {
		super("Cette Case ne contient pas d'acteur.") ;
	}
}
