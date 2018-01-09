package Comportemental;

/**
 * Regroupement des statistiques liees a un acteur.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
public class StatActeur {
	private ActeurType actType ;
	private int nbrCreation ;				// Nombre de fois ou l'acteur a ete cree.
	private int nbrMort ;					// Nombre de fois ou un acteur est mort.
	
	/**
	 * Constructeur par defaut.
	 */
	public StatActeur(ActeurType at) {
		this.nbrCreation = 0 ;
		this.nbrMort = 0 ;
		this.actType = at ;
	}
	
	/**
	 * Incrementation du nombre de creation de l'acteur.
	 */
	public void ajouterCreation() {
		this.nbrCreation ++ ;
	}
	
	/**
	 * Incrementation du nombre de mort de l'acteur.
	 */
	public void ajouterMort() {
		this.nbrMort ++ ;
	}
	
	/**
	 * Accesseur nombre de creation de l'acteur.
	 * 
	 * @return Renvoie nbrCreation.
	 */
	public int getNbrCreation() {
		return this.nbrCreation ;
	}
	
	/**
	 * Accesseur nombre de mort de l'acteur.
	 * 
	 * @return Renvoie nbrMort.
	 */
	public int getNbrMort() {
		return this.nbrMort ;
	}
	
	/**
	 * Renvoie le type de l'acteur sous forme d'une chaine de caractere.
	 * 
	 * @return Renvoie sous forme de String le type de l'acteur.
	 */
	public String getActeurType() {
		return this.actType.toString() ;
	}
}
