package Environnement;

import java.awt.Color;

import Comportemental.Acteur;
import Comportemental.ActeurType;
import Exceptions.NoActeurType;
import Exceptions.NoActor;

/**
 * Element constitutif du plateau de jeu : une case n'est pas animee mais ses caracteristiques influent sur les actions des divers acteurs.
 *  
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
public class Case {
	private int posX, posY ;
	private CaseType type ;
	private boolean actPresent ;
	private Acteur act ;
	

	/**
	 * Constructeur d'une case : initialise sa position ainsi que le type de la case. ( qui influe sur ses caracteristiques. )
	 * 
	 * @param x Abscisse de la case
	 * @param y Ordonnee de la case
	 * @param type Type de case a creer
	 * 
	 * @see CaseType
	 */
	public Case(int x, int y, CaseType type) {
		this.type= type ;
		
		this.actPresent = false ;		// Pas d'acteur present.
		this.act = null ;
		
		this.posX = x ;				// Position en absisse
		this.posY = y ;				// Position en ordonnee
	}
	
	/**
	 * Constructeur d'une case : initialise sa position ainsi que le type de la case et le type d'acteur present dessus.
	 * 		( le type de la case influe sur les caracteristiques de celle-ci )
	 * 
	 * @param x Abscisse de la case
	 * @param y Ordonnee de la case
	 * @param type Type de case a creer
	 * @param nomActeur Type d'acteur present sur la case
	 */
	public Case(int x, int y, CaseType type, ActeurType nomActeur) {
		this(x,y,type) ;				// Appel au constructeur de base. 
		
		this.act = new Acteur(nomActeur, x, y) ;
		this.actPresent = true ;
		Terrain.getInstance().ajouter(this.act);
	}
	
	/**
	 * Accesseur au type de l'acteur.
	 * 
	 * @return Renvoie le type de l'acteur.
	 */
	public ActeurType getActeurType() throws NoActeurType {												// Throw exception si pas d'acteur !!!
		try {
			return this.getActeur().getType() ;
		} catch(NoActor e) {
			throw new NoActeurType() ;
		}
	}
	
	/**
	 * Accesseur a l'acteur present sur la case.
	 * 
	 * @return Renvoie l'acteur present sur la case.
	 * 
	 * @throws NoActor si la case ne contient pas d'acteur envoie une exception.
	 */
	public Acteur getActeur() throws NoActor {														// Throw exception si pas d'acteur !!!
		if (this.act == null) throw new NoActor() ;
		return this.act ;
	}
	
	/**
	 * Indique si un acteur est present sur la case.
	 * 
	 * @return Renvoie true si acteur present sinon false.
	 */
	public boolean getActeurPresent() {
		return this.actPresent ;
	}
	
	/**
	 * Indique la couleur de la case.
	 * Depend du type de l'acteur ou du type de la case, selon si un acteur est present ou non.
	 * 
	 * @return Renvoie la couleur associee a la case.
	 */
	public Color getColor() {
		if (this.actPresent == true)
			return this.act.getColor() ;
		else
			return this.type.getColor() ;
	}
	
	/**
	 * Indique si la case est accessible ou non. Soit, si un acteur peut l'occuper ou non.
	 * 
	 * @return Renvoie true si la case est accessible sinon false.
	 */
	public boolean getAccessible() {
		return this.type.getAccessible() ;
	}
	
	/**
	 * Indique si la case est inflammable ou non. Soit, si elle peut bruler ou non.
	 * 
	 * @return Renvoie true si la case est inflammable sinon false.
	 */
	public boolean getInflammable() {
		return this.type.getInflammable() ;
	}
	
	/**
	 * Accesseur au type de la case.
	 * 
	 * @return Renvoie le type de la case.
	 */
	public CaseType getCaseType() {
		return this.type ;
	}
	
	/**
	 * Ajouter un acteur sur une case a partir de son type.
	 * 
	 * @param nom Type de l'acteur a ajouter sur la case.
	 */
	public void ajoutActeur(ActeurType nom) {
		this.setActeur(new Acteur(nom, posX, posY)) ;
		System.out.println("ajoutActeur : x = " + posX + " y = " + posY + " type : " + nom.toString());
	}
	
	public void supprimerActeur() {
		try {
			if (this.getActeurPresent())								// Si acteur present, le supprimer des acteurs a faire jouer.
				Terrain.getInstance().supprimer(this.getActeur());
		} catch (NoActor e) {}
		
		this.actPresent = false ;				// Suppression de l'acteur de la case.
		this.act = null ;
	}
	
	/**
	 * Place un acteur, cree ulterieurement, sur la case.
	 * 
	 * @param newAct Acteur a placer sur la case.
	 */
	public void setActeur(Acteur newAct) {
		if (newAct != null) {
			if (this.actPresent)
				this.supprimerActeur() ;
			this.actPresent = true ;
			this.act = newAct ;
			Terrain.getInstance().ajouter(this.act) ;
		} else {
			this.supprimerActeur() ;
		}
	}
	
	/**
	 * Change le type de la case, modifie les caracteristiques de celle-ci.
	 * 
	 * @param t Nouveau type de la case.
	 */
	public void changeType(CaseType t) {
		this.type = t ;
	}
	
	/**
	 * Effectue l'action de l'acteur contenu sur la case.
	 * 
	 * @throws NoActor si la case ne contient pas d'acteur envoie une exception.
	 */
	public void actionActeur() throws NoActor {												// Throw exception si acteur non present !!!!
		if (this.act == null) throw new NoActor() ;
		else this.act.actionConcrete();	
	}
	
	/**
	 * Declenche le mouvement de l'acteur contenu sur la case.
	 * 
	 * @throws NoActor si la case ne contient pas d'acteur envoie une exception.
	 */
	public void mouvementActeur() throws NoActor {												// Throw exception si acteur non present !!!
		if (this.act == null) throw new NoActor() ;
		this.act.mouvementConcrete();
	}
}
