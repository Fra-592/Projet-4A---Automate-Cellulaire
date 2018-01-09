package Comportemental;

import java.util.Random;

import Environnement.Case;
import Exceptions.*;

/**
 * Comportement de l'ActeurType "Pompier", contient les actions concretes associees.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
public class CompPompier extends BaseComportement implements Comportement {
	/**
	 * Verifie si une case est en flamme dans un rayon de 3 cases. ( cad : contient un acteur "Feu" ) 
	 * 
	 * @param x l'abscisse du pompier.
	 * @param y Coordonnee du pompier.
	 * 
	 * @return Renvoie la position d'un feu si present dans un rayon de 3 case sinon (-1,-1).
	 */
	public int[] fireInRange(int x, int y) {
		int i, j ;				
		Case caseTemp ;
		boolean answer = false ;				// Indique si un feu est trouve ou non.
		int[] firePos = new int[2] ;			// Position du feu trouve ( -1, -1 si rien. )
		
		firePos[0] = -1 ;
		firePos[1] = -1 ;
		for (i = x-3; (i<=x+3) && !answer; i++) {
			for (j = y-3; (j<=y+3) && !answer; j++) {				// Pour toute case a 3 de distance et tant que pas de feu trouver ...
				try {
					caseTemp = this.getCase(i, j) ;
					
					if (caseTemp.getActeurType() == ActeurType.Feu) {			// Verifier si feu present.
						answer = true ;				// Feu trouve
						firePos[0] = i ;			// Enregistrement de la position du feu.
						firePos[1] = j ;
					}
				} catch (HorsLimite | NoActeurType e) {
					// Enregistrer dans un fichier log les erreurs.
				}
			}
		}
		
		return firePos ;				// Renvoyer les positions du feu (-1,-1) si rien.
	}
	
	/**
	 * Place un acteur pompier sur une case.
	 * 
	 * @param x Abscisse de la case cible.
	 * @param y Coordonnee de la case cible.
	 */
	public void toPompier(int x, int y) {
		try {
			Case casePompier = this.getCase(x, y) ;
		
			if ((casePompier.getAccessible() == true) && (casePompier.getActeurPresent() == false)) {					// Peut être Cahnger la place de la condition !!!!!!!!!!!!!!!!!!!!!!
				casePompier.ajoutActeur(ActeurType.Pompier);
			}
		} catch (HorsLimite e) {
															// Enregistrer dans un fichier log les erreurs.
		}
	}
	
	/**
	 * Action associe au Pompier : Si un feu a portee essayer de l'eteindre, sinon ne rien faire.
	 * 
	 * @param x Abscisse du pompier.
	 * @param y Coordonnee du pompier.
	 */
	public void action(int x, int y) {
		int[] posFire = fireInRange(x,y) ;
		//System.out.println("Action pompier en (" + x + "," + y + ") avec un feu en " + posFire[0] + " , " + posFire[1]) ;
		
		if (posFire[0] != -1) {
			//System.out.println("Pompier en (" + x + "," + y + ") tente d'eteindre un feu en (" + posFire[0] + "," + posFire[1] + ")");
			Random r = new Random() ;
			
			if (r.nextFloat() <= 0.5) {
				try {
					Case caseFeu = this.getCase(posFire[0], posFire[1]) ;
				
					caseFeu.setActeur(null);				// Extinction du feu sur la case.
				} catch (HorsLimite e) {
															// Enregistrer dans un fichier log les erreurs.
				}
			}
		}
	}
	
	/**
	 * Mouvement du Pompier : Si pas de feu a portee alors se deplace sur toute les cases adjacentes.
	 * 
	 * @param x Abscisse du pompier.
	 * @param y Coordonnee du pompier.
	 */
	public void mouvement(int x, int y) {
		int[] firePos ;
		
		firePos = this.fireInRange(x, y) ;
		
		if (firePos[0] == -1) {				// Sil il n'y a pas de feu a porter alors avancer.
			this.toPompier(x+2, y) ;				// Peut être mettre la condition ici !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			this.toPompier(x, y+2) ;
			this.toPompier(x, y-2) ;
			this.toPompier(x-2, y) ;
		}
		
		try {
			//this.getActeur(x, y).decVie(1);					// A REVOIR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		} catch (Exception e) {}
	}
	
	/**
	 * Action a la mort naturelle du Pompier : disparait de la case.
	 * 
	 * @param x Abscisse du pompier.
	 * @param y Coordonnee du pompier.
	 */
	public void actionMortSpecifique(int x, int y) {
		try {
			this.getCase(x, y).setActeur(null);
		} catch (HorsLimite e) {}
	}
	
	/**
	 * Action a la mort non naturelle du Pompier :  disparait de la case.
	 * 
	 * @param x Abscisse du pompier.
	 * @param y Coordonnee du pompier.
	 */
	public void mort(int x, int y) {
		try {
			this.getCase(x, y).setActeur(null);
		} catch (HorsLimite e) {}
	}
}
