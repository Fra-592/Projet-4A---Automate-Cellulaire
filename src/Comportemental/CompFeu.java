package Comportemental;

import Environnement.Terrain;
import Exceptions.*;
import Environnement.Case;
import Environnement.CaseType;

import java.util.Random;

/**
 * Comportement de l'ActeurType "Feu", contient les actions concretes associees.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
public class CompFeu extends BaseComportement implements Comportement {
	/**
	 * Enflamme une case a une position donee.
	 * 
	 * @param x Abscisse de la case a enflammer.
	 * @param y Ordonnee de la case a enflammer.
	 */
	public void toFire(int x, int y) {
		try {
			Terrain plateau = this.getTerrain() ;
			Case caseAdj = plateau.getCase(x, y) ;
		
			if (caseAdj.getInflammable()) {
				Acteur flamme = new Acteur(ActeurType.Feu, x, y) ;

				caseAdj.setActeur(flamme); ;
			}
		} catch (HorsLimite e) {
												// Enregistrer dans un fichier log les erreurs.			
		}
	}
	
	/**
	 * Action associee au feu : se consumme.
	 * 
	 * @param x Abscisse du feu.
	 * @param y Ordonnee du feu.
	 */
	public void action(int x, int y) {
		try {
			Acteur act = this.getActeur(x, y) ;				// Recuperation du feu correspondant.
		
			act.decVie(1) ;				// Reduire sa duree de vie
		} catch (HorsLimite | NoActor e) {
												// Enregistrer dans un fichier log les erreurs.
		}
	}
	
	/**
	 * Mouvement du feu : contamine les cases adjacentes avec une probabilite de 1/2. 
	 * 
	 * @param x Abscisse du feu.
	 * @param y Ordonnee du feu.
	 */
	public void mouvement(int x, int y) {				// Prendre en compte la meteo ( changer la description ! )
		Random r = new Random() ;
		
		if (r.nextFloat()<0.5)					// Si case inflammable alors ... !!!
			this.toFire(x+1, y);
		if (r.nextFloat()<0.5)
			this.toFire(x, y+1);
		if (r.nextFloat()<0.5)
			this.toFire(x-1, y);
		if (r.nextFloat()<0.5)
			this.toFire(x, y-1);
	}
	
	/**
	 * Action a la mort naturelle du feu : convertie la case en cendre.
	 * 
	 * @param x Abscisse du feu.
	 * @param y Ordonnee du feu.
	 */
	public void actionMortSpecifique(int x, int y) {
		try {
			this.getCase(x, y).changeType(CaseType.Cendre) ;				// La case devient de la cendre.
			this.getCase(x, y).setActeur(null);						// Elle ne contient plus d'acteur.
		} catch (HorsLimite e) {
												// Enregistrer dans un fichier log les erreurs.												
		}
	}
	

	/**
	 * Action a la mort non naturelle du feu : le feu disparait sans modifier la case.
	 * 
	 * @param x Abscisse du feu.
	 * @param y Ordonnee du feu.
	 */
	public void mort(int x, int y) {
		try {
			this.getCase(x, y).setActeur(null) ;
		} catch (HorsLimite e) {
												// Enregistrer dans un fichier log les erreurs !
		}
	}
}
