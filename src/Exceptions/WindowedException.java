package Exceptions;

public class WindowedException extends Exception {
	private String erreur ;
	
	public WindowedException(String erreur) {
		this.erreur = erreur ;
		
		new FenetreException(this.erreur) ;
	}
}
