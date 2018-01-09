package Environnement;

/**
 * Regroupement des statistiques liees a une case.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2018
 *
 */
public class StatCase {
	private CaseType caseType ;
	private int nbrCreation ;				// Nombre de fois ou la case a ete cree.
	private int nbrDetruite ;				// Nombre de fois ou la case a ete detruite
	
	/**
	 * Constructeur par defaut.
	 */
	public StatCase(CaseType ct) {
		this.nbrCreation = 0 ;
		this.nbrDetruite = 0 ;
		this.caseType = ct ;
	}
	
	/**
	 * Incrementation du nombre de creation de la case
	 */
	public void ajouterCreation() {
		this.nbrCreation ++ ;
	}
	
	/**
	 * Incrementation du nombre de destruction de la case.
	 */
	public void ajouterMort() {
		this.nbrDetruite ++ ;
	}
	
	/**
	 * Accesseur nombre de creation de la case.
	 * 
	 * @return Renvoie nbrCreation.
	 */
	public int getNbrCreation() {
		return this.nbrCreation ;
	}
	
	/**
	 * Accesseur nombre de destruction de la case.
	 * 
	 * @return Renvoie nbrMort.
	 */
	public int getNbrDetruite() {
		return this.nbrDetruite ;
	}
	
	public String getCaseType() {
		return this.caseType.toString();
	}
}
