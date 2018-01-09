package Fenetres;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import Environnement.Case;
import Environnement.Terrain;
import Exceptions.HorsLimite;

/**
 * Panneau d'affichage du Terrain.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 06/01/2018
 *
 * @version 0.0.1
 */
@SuppressWarnings("serial")
public class PanneauAffichageTerrain extends JPanel {
	
	/**
	 * Dessin du terrain.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g) ;
		
		Graphics2D g2 = (Graphics2D) g ;
		Terrain plateau = Terrain.getInstance() ;				// Recuperer le plateau de la simulation.
		int xMax, yMax ;
		int x, y ;
		Case caseTemp ;
		Rectangle2D rectTemp ;
		
		xMax = plateau.getXMax() ; 
		yMax = plateau.getYMax() ;
		
		for (x=0; x<xMax; x++) {								// Pour chaque case du terrain
			for (y=0; y<yMax; y++) {								
				try {
					caseTemp = plateau.getCase(x, y) ;								// Recuperer la case
					g2.setPaint(caseTemp.getColor());								// Couleur de cette case
					
					rectTemp = new Rectangle2D.Double(10+4*x, 10+4*y, 4, 4) ;		// Dans un carre de 4 pixel a la position approprie
					g2.draw(rectTemp);												// Ajout  de la couleur de la case dans ce rectangle.
					g2.fill(rectTemp) ;
				} catch (HorsLimite e) {}
			}
		}
	}
	
	/**
	 * Changement de la dimension favorisee pour ce panneau sachant que 1 case = 1 carre de 4 pixels. ( l'ajout d'offset est arbitraire )
	 */
	public Dimension preferredSize() {
		Terrain t = Terrain.getInstance() ;
		int x, y ;
		
		x = t.getXMax()*4+4 + 50 ;
		y = t.getYMax()*4+4 + 75 ;
		
		return new Dimension(x, y) ;
	}
}
