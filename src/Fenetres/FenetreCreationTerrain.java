package Fenetres;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Environnement.Terrain;

/**
 * Fenetre de creation d'un terrain contient : un apercu du terrain actuel, un menu pour les elements a rajouter et un bouton pour commencer
 *  la simulation avec la carte ainsi creee.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
@SuppressWarnings("serial")
public class FenetreCreationTerrain extends JFrame {
	private PanneauCreationTerrain p ;				// Panneau central
	private PanneauCreationTerrainEast pEast ;		// Panneau de droite
	private PanneauCreationTerrainSouth pSouth ;	// Panneau du bas
	
	/**
	 * Constructeur par defaut d'un fenetre de creation de terrain.
	 */
	public FenetreCreationTerrain() {
		super() ;
										// Initialisation des caracteristiques principales.
		this.setTitle("Creation d'une nouvelle carte");				// titre
		this.setLocation(100, 100);									// Position
		
														// Ensemble des panneaux contenus
		this.pEast = new PanneauCreationTerrainEast() ;				// Panneau de droite
		this.p = new PanneauCreationTerrain(pEast) ;				// Panneau central
		this.pSouth = new PanneauCreationTerrainSouth(p, this) ;	// Panneau du bas
	
		
		int l, h ;								// longueur et hauteur de la fenetre
		
		l = this.p.getPreferredSize().width + this.pEast.getPreferredSize().width ;				// Calcul dimension fenetre
		h = this.p.getPreferredSize().height + this.pSouth.getPreferredSize().height ;
		
		this.setSize(l, h);						// Dimensionnement de la fenetre.
		
		this.add(p, BorderLayout.CENTER) ;				// Ajout des panneaux a la fenetre ( a l'emplacement correspondant )
		this.add(pEast, BorderLayout.EAST);
		this.add(pSouth, BorderLayout.SOUTH);
		
		this.setVisible(true);					// Afficher la fenetre.
	}
	
	public static void main(String[] args) {
		Terrain t = Terrain.create(50, 100) ;
		
		FenetreCreationTerrain f = new FenetreCreationTerrain() ;
	}
}
