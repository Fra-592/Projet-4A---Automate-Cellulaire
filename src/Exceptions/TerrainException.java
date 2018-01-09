package Exceptions;

/**
 * Exception type.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
@SuppressWarnings("serial")
public class TerrainException extends Exception {
	private String message ;		// Me
	
	/**
	 * Constructeur d'une exception.
	 * 
	 * @param message Description de l'exception.
	 */
	public TerrainException(String message) {
		super() ;
		this.message = message ;
	}
	
	/**
	 * Permettre l'affichage d'une exception.
	 */
	@Override 
	public String toString() {
		return this.message ;
	}
}
