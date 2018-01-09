package Fenetres;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;

import Environnement.Terrain;

@SuppressWarnings("serial")
public class PanneauCreationTerrainSouth extends JPanel {
	private JButton commencer ;
	private PanneauCreationTerrain p ;
	private FenetreCreationTerrain fContainer ;
	
	public PanneauCreationTerrainSouth(PanneauCreationTerrain p, FenetreCreationTerrain fContainer) {
		this.commencer = new JButton("Commencer") ;
		this.p = p ;
		this.fContainer = fContainer ;
		
		this.add(this.commencer);
		
		this.commencer.addActionListener(new BoutonAction());
	}

	private class BoutonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
											// Si pas de feu / pompier => fenetre erreur !
											// Ouvrir nouvelle FenetreSimulation
			fContainer.setVisible(false);
			fContainer.dispose() ;
			
			Terrain.getInstance().initialiser();
			
			Thread th = new Thread(new Simulation()) ;
											// Des que finit revenir a la fenetre PanneauCreationTerrain !		
			th.run() ;
		}
	}
}
