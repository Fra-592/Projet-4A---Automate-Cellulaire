package Fenetres;

import javax.swing.JFrame;

/**
 * Fenetre d'apercu du terrain actuel.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 06/01/2018
 *
 * @version 0.0.1
 */
@SuppressWarnings("serial")
public class FenetreApercu extends JFrame {
	private PanneauAffichageTerrain p ;				// Panneau dessinant le terrain.
	
	/**
	 * Constructeur par defaut.
	 */
	public FenetreApercu() {
		this.setTitle("Fenetre apercu terrain");
		this.setLocation(100, 100);
		
		this.p = new PanneauAffichageTerrain() ;
		
		this.setSize(this.p.getPreferredSize());
		
		System.out.println(" preferredSize : " + this.p.getPreferredSize());
		
		this.add(p) ;
		
		this.setVisible(true);
	}
}
