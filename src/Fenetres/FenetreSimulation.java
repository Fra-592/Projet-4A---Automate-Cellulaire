package Fenetres;


import java.awt.BorderLayout;

import javax.swing.JFrame;

import Environnement.Terrain;
import Exceptions.FenetreErreurFatale;
import Exceptions.NoTerrain;

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
	private Simulation simulation;
	private Thread thrsimul;
	
	/**
	 * Constructeur par defaut.
	 */
	public FenetreSimulation() {
		super() ;
		
		this.setTitle("Simulation");
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.p = new PanneauSimulation() ;
		this.pSouth = new PanneauSimulationSouth(this) ;
		
		int l, h ;
		l = this.p.getPreferredSize().width < this.pSouth.getPreferredSize().width ?  this.pSouth.getPreferredSize().width : this.p.getPreferredSize().width ;
		h = this.p.getPreferredSize().height + this.pSouth.getPreferredSize().height + 50 ;				// A revoir !!!!!!!!!!!!!!!!!!!!!!!!!!
		this.setSize(l+50, h);
		
		this.add(p) ;
		this.add(pSouth, BorderLayout.SOUTH) ;
		
		this.validate();
		this.simulation = new Simulation(this);
		this.thrsimul = new Thread(this.simulation);
		
		this.setVisible(true);
		thrsimul.run();
	}
	
	public void toPause() {
		simulation.pause();
	}
	
	public void toSuivant() {
		//simulation.step();
	}
	
	public void toContinuer() {
		simulation.runback();
	}
}
