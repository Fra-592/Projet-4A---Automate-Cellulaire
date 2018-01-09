package Fenetres;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import Comportemental.ActeurType;
import Environnement.Case;
import Environnement.CaseType;
import Environnement.Terrain;
import Exceptions.FenetreErreurFatale;
import Exceptions.HorsLimite;
import Exceptions.NoTerrain;

@SuppressWarnings("serial")
public class PanneauCreationTerrain extends PanneauAffichageTerrain {
	private PanneauCreationTerrainEast p ;
	
	public PanneauCreationTerrain(PanneauCreationTerrainEast p) {
		super() ;
		
		this.setSize(100, 100);
		this.setFocusable(true);
		this.addMouseListener(new MouseAction());
		
		this.p = p ;
	}
	
	private class MouseAction extends MouseAdapter {
		public boolean inPlateau(MouseEvent e) throws NoTerrain {
			Terrain t = Terrain.getInstance();
			
			if ((10 <= e.getX()) && (e.getX() <= 10+4*(t.getXMax()+1))) 
				if ((10 <= e.getY()) && (e.getY() <= 10+4*(t.getYMax()+1))) 
					return true ;
			return false ;
		}
		
		public Case getCase(MouseEvent e) throws NoTerrain {
			try {
				Terrain t = Terrain.getInstance() ;
				int x = e.getX();
				int y = e.getY();
				int i, j ;
			
				i = (x-10)/4 ;
				j = (y-10)/4 ;
			
				System.out.println("creation case en : x = " + i + " et y = " + j ) ;
			
				return t.getCase(i,j) ;
			} catch (HorsLimite h) {
				return null ;
			} 
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			int bouton = e.getButton() ;
			
			if (bouton == MouseEvent.BUTTON1) {				// Si clic gauche ...
				try {
					if (this.inPlateau(e)) {
						Case c = this.getCase(e) ;
						if (c != null) {
							if (p.choixAction.getSelectedItem() == "Case") {
								c.changeType((CaseType) p.listeType.getSelectedItem());
								repaint() ;
							} else {
								c.ajoutActeur((ActeurType) p.listeType.getSelectedItem());
								repaint() ;
							}
						}
					}
				} catch (NoTerrain exception) {
					new FenetreErreurFatale(exception.toString()) ;
				}
			}
		}
	}
}
