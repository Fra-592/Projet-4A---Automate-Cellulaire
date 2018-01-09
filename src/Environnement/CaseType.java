package Environnement;

import java.awt.Color;

/**
 * Enumeration de l'ensemble des types de cases avec leurs caracteristiques :
 * 		accessible ( la case peut contenir un acteur ou non ), inflammable ( la case peut etre brulee ) et sa couleur.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 */
public enum CaseType {
	Arbre(true, true, new Color(40, 49, 30), true, true),						// Arbre : Accessible, Inflammable, Vert
	Eau(false, false, new Color (7, 18, 24), false, true),						// Eau : Non Accessible, Non Inflammable, Bleu
	Habitation(true, true, new Color(191,159, 138), true, true),		// Habitation : Accessible, Inflammable, marron
	Cendre(true, false, new Color(158,158,158), false, false),		// Cendre : Accessible, Non Inflammable, gris
	Neutre(true, true, Color.WHITE, false, false);					// Neutre : Accessible, Accessible, Blanc
	
	private boolean accessible ;				// Ce type de case peut contenir un acteur ou non.
	private boolean inflammable ;				// Ce type de case peut etre en feu ou non.
	private Color couleur ;						// Couleur de ce type de case.
	private boolean recordStat ;
	private boolean analysable ;
	
	/**
	 * Constructeur d'une CaseType.
	 * 
	 * @param accessible Indique si ce type de case est accessible. ( peut contenir un acteur )
	 * @param inflammable Indique si ce type de  case peu etre en feu.
	 * @param couleur La couleur de ce type de case.
	 * @param recordStat Indique si la case doit etre suivie. ( Construction de statistiques )
	 */
	private CaseType(boolean accessible, boolean inflammable, Color couleur, boolean recordStat, boolean analysable) {
		this.accessible = accessible ;
		this.inflammable = inflammable ;
		this.couleur = couleur ;
		this.recordStat = recordStat ;
		this.analysable = analysable ;
	}
	
	/**
	 * Renvoie l'accessibilite de ce type de case.
	 * 
	 * @return Renvoie true si ce type de case est accessible sinon false.
	 */
	public boolean getAccessible() {
		return this.accessible ;
	}
	
	/**
	 * Accesseur a l'attribut inflammable.
	 * 
	 * @return Renvoie true si ce type de case est inflammable sinon false.
	 */
	public boolean getInflammable() {
		return this.inflammable ;
	}
		
	/**
	 * Accesseur a la couleur de ce type de case.
	 * 
	 * @return Renvoie la couleur de ce type de case.
	 */
	public Color getColor() {
		return this.couleur ;
	}
	
	/**
	 * Indique si les donnees de la case doivent ou non etre observees.
	 * 
	 * @return Renvoie true si la case doit etre suivie, false sinon.
	 */
	public boolean getRecordStat() {
		return this.recordStat ;
	}
	
	/**
	 * Indique si la case peut etre reconnu sur une image ou non.
	 * 
	 * @return Renvoie true si la case peu etre analysee et false sinon.
	 */
	public boolean getAnalysable() {
		return this.analysable ;
	}
}
