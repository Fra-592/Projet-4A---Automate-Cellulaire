package Exceptions;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Fenetre indiquant qu'une erreur est survenue.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 09/01/2017
 *
 */
@SuppressWarnings("serial")
public class FenetreException extends JFrame {
	private JLabel erreur ;
	
	/**
	 * Constructeur par defaut : affichage d'une fenetre avec un message d'erreur.
	 * 
	 * @param exception Message d'erreur a afficher.
	 */
	public FenetreException(String exception) {	
		Toolkit t = Toolkit.getDefaultToolkit() ;
		
		this.setTitle("Erreur");
		this.setLocation(500, 500) ;
		
		JPanel p = new JPanel() ;
		this.erreur = new JLabel(exception) ;
		
		p.add(this.erreur) ;
		
		this.add(p) ;
		
		this.setVisible(true);
		this.toFront();
	}
	
	/**
	 * Evenement de fermture de la fenetre.
	 * 
	 * @author Jonathan
	 *
	 */
	private class Fermeture extends WindowAdapter {
		/**
		 * Ferme la fenetre. Sans arreter le programme.
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			dispose() ;
		}
	}
}
