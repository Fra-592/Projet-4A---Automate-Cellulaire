package Fenetres;


import java.awt.BorderLayout;

import javax.swing.JFrame;

import Environnement.Terrain;

/**
 * Fenetre montrant le deroulement de la simulation.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 06/01/2018
 *
 * @version 0.0.1
 */
@SuppressWarnings("serial")
public class FenetreSimulation extends JFrame {
	public PanneauSimulation p ;
	private PanneauSimulationSouth pSouth ;
	private int mode ;												// A REVOIR !!!!!!!!!!!!!!!!!!!!! public ???
	
	/**
	 * Constructeur par defaut.
	 */
	public FenetreSimulation() {
		super() ;
		
		this.setTitle("Simulation");
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 							// A redefinir ( avec un message de confirmation etc ... !!! )
		
		this.p = new PanneauSimulation() ;
		this.pSouth = new PanneauSimulationSouth(this) ;
		
		int l, h ;
		l = this.p.getPreferredSize().width < this.pSouth.getPreferredSize().width ?  this.pSouth.getPreferredSize().width : this.p.getPreferredSize().width ;
		h = this.p.getPreferredSize().height + this.pSouth.getPreferredSize().height + 50 ;				// A revoir !!!!!!!!!!!!!!!!!!!!!!!!!!
		this.setSize(l+50, h);
		
		this.add(p) ;
		this.add(pSouth, BorderLayout.SOUTH) ;
		
		this.validate();
		
		this.mode = 1 ;								// Par defaut : simulation en pause.
		
		this.setVisible(true);
	}
	
	public void toPause() {
		this.mode = 0 ;
	}
	
	public void toSuivant() {
		this.mode = 2 ;
	}
	
	public void toContinuer() {
		this.mode = 1 ;
	}
	
	public void simulation() {
		Terrain t = Terrain.getInstance() ;
		
		do {
			t.evolution();
			this.update(this.getGraphics());;
			paint(getGraphics()) ;
		} while(true) ;
	}
}
