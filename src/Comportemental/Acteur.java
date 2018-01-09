package Comportemental;

import java.awt.Color;

import Environnement.Terrain;
import Exceptions.NoTerrain;

/**
 * Decrit un acteur, origine d'actions decrites par un comportement specifique a chaqu'un.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 * 
 * @version 0.0.1
 * 
 * @see ActeurType
 * @see Comportement
 */
public class Acteur {
	private ActeurType type ;
	private Comportement comp ;
	private int posX, posY ;
	private int vie ;
	private boolean recente ;
	
	/**
	 * Constructeur d'un acteur.
	 * 
	 * @param nom Nom de l'acteur definit dans l'enumerateur ActeurType
	 * @param nbrVie La duree de vie de l'acteur.
	 * @param x La position en abscisse de l'acteur sur le plateau.
	 * @param y La position en ordonnee de l'acteur sur le plateau.
	 * 
	 * @see ActeurType
	 */
	public Acteur(ActeurType nom, int x, int y) {
		this.type = nom ;
		this.comp = nom.getComportement() ;
			
		this.vie = nom.getVie() ;
			
		this.posX = x ;
		this.posY = y ;
		
		this.recente = false ;
		
		try {
			Terrain t = Terrain.getInstance();				// Incrementation nombre creation de ce type d'acteur.
			t.stat.ajouterCreation(this);
		} catch (NoTerrain e) {
			
		}
		
		System.out.println("Nouveau " + nom.toString() + " en " + x + " " + y) ;	
						// else throw error.
	}

	/**
	 * Accesseur au type de l'acteur.
	 * 
	 * @return Renvoie le type de l'acteur.
	 * 
	 * @see ActeurType
	 */
	public ActeurType getType() {
		return this.type ;
	}
	
	/**
	 * Indique la couleur associee a cet acteur. ( selon son instanciation )
	 * 
	 * @return Renvoie la couleur de l'ActeurType associe.
	 */
	public Color getColor() {
		return this.getType().getColor() ;
	}
	
																				// METTRE (RECENT) DANS ACTEUR !!!! ????
	/**
	 * Indique si la case de l'acteur a ete recemment mise a jour.
	 * 
	 * @return Renvoie true si case recente et false sinon.
	 */
	public boolean getRecente() {
		return this.recente ;
	}
	
	/**
	 * Mes a true l'attribue "recente" de la case. Indique que la case a ete modifiee pendant ce tour.
	 */
	public void setRecente() {
		this.recente = true ;
	}
	
	/**
	 * Mes a false l'attribue "recente" de la case. Indique que la case n'a pas ete modifiee pendant ce tour.
	 */
	public void unsetRecente() {
		this.recente = false ;
	}
	
	/**
	 * Indique si l'acteur est mort ou non.
	 * 
	 * @return Renvoie true si l'acteur est mort, false sinon.
	 */
	public boolean estMort() {
		return this.vie == 0 ;
	}
	
	/**
	 * Reduit la vie de l'acteur d'un nombre entree en parametre.
	 * 
	 * @param nbr Le nombre de vie a retirer.
	 */
	public void decVie(int nbr) {
		this.vie = nbr < this.vie ? vie-nbr : 0 ;
		if (this.vie == 0) this.actionMortConcrete();
	}
	
	/**
	 * Action effectue par l'acteur selon le comportement definit a son initialisation.
	 * 
	 * @see Comportement
	 */
	public void actionConcrete() {
		this.comp.action(this.posX, this.posY);
	}
	
	/**
	 * Mouvement effectue par l'acteur selon le comportement definit a son initialisation.
	 */
	public void mouvementConcrete() {
		this.comp.mouvement(this.posX, this.posY);
	}
	
	/**
	 * Action effectue a la mort "naturelle" ( vie == 0 ) de l'acteur selon le comportement definit a son initialisation.
	 */
	public void actionMortConcrete() {
		this.comp.actionMort(posX, posY);
	}
	
	/**
	 * Action effectue a la mort prematuree de l'acteur ( vie != 0 ) selon le comportement definit a son initialisation.
	 */
	public void mortConcrete() {
		this.comp.mort(posX, posY) ;
	}
	
	/**
	 * Permettre l'affichage des informations fondamentales de l'acteur.
	 */
	public String toString() {
		return (this.type + " x : " + this.posX + " y : " + this.posY + " recente : " + this.getRecente()) ;
	}
}
