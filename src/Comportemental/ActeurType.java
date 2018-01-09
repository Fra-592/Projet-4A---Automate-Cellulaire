package Comportemental;

import java.awt.Color;

/**
 * Enumeration des types d'acteurs instanciables.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
public enum ActeurType {
	Feu(new CompFeu(), Color.RED, 2, true),				// Le feu est rouge et a une duree de vie de 3
	Pompier(new CompPompier(), Color.YELLOW, 1, true) ;	// Le pompier est jaune et a une duree de vie de 1
	
	private Comportement comp ;
	private Color couleur ;
	private int nbrVie ;
	private boolean recordStat ;
	
	/**
	 * Constructeur d'un ActeurType, initialise son comportement, sa couleur et sa vie. Predefinies !
	 * 
	 * @param comp Comportement de l'ActeurType.
	 * @param couleur Couleur de l'ActeurType
	 * @param nbrVie Nombre de vie de l'ActeurType.
	 * @param recordStat Indique si l'acteur doit etre suivie. ( Construction de statistiques )
	 */
	private ActeurType(Comportement comp, Color couleur, int nbrVie, boolean recordStat) {
		this.comp = comp ;
		this.couleur = couleur ;
		this.nbrVie = nbrVie ;
		this.recordStat = recordStat ;
	}
	
	/**
	 * Indique si ce "nom" correspond a un type d'acteur ou non.
	 * 
	 * @param nom Le nom generique de l'acteur.
	 * 
	 * @return Renvoie true si correspond a un type d'acteur, false sinon.
	 */
	public static boolean contient(String nom) {
		return (ActeurType.valueOf(nom) != null) ;
	}
	
	/**
	 * Indique le comportement adopte par chaque type d'acteur.
	 * 
	 * @return Renvoie un Comportement approprie selon le type de l'acteur.
	 * 
	 * @see Acteur
	 * @see Comportement
	 */
	public Comportement getComportement() {
		return this.comp ;
	}
	
	/**
	 * Indique la couleur correspondante au type de l'acteur.
	 * 
	 * @return Renvoie la couleur de l'acteur.
	 */
	public Color getColor() {
		return this.couleur ;
	}
	
	/**
	 * Indique le nombre de vie associe a chaque acteur.
	 * 
	 * @return Renvoie la vie associe a l'acteur.
	 */
	public int getVie() {
		return this.nbrVie ;
	}
	
	/**
	 * Indique si les donnees de l'acteur doivent ou non etre observees.
	 * 
	 * @return Renvoie true si l'acteur doit etre suivie, false sinon.
	 */
	public boolean getRecordStat() {
		return this.recordStat ;
	}
}
