package Fenetres;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Environnement.ImageCarte;

/**
 * Fenetre d'interaction avec l'utilisateur : demande du chemin absolu a l'image a utiliser pour la simulation.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 06/01/2018
 *
 */
@SuppressWarnings("serial")
public class FenetreImageInput extends JFrame {
	private JLabel lFichierInput ;				// Label du champ texte contenant le chemin absolu a l'image a utiliser pour la simulation.
	private JTextField fichierInput ;			// Champ texte contenant le chemin absolu.
	private JButton apercu ;					// Bouton permettant une observation de l'image convertie en Terrain.
	private JButton continuer ;					// Passage a l'etape suivante de l'initialisation de la simulation
	private ImageCarte fichier ;				// Image issue du chemin absolu indiquee.
	
	/**
	 * Constructeur par defaut de la fenetre.
	 */
	public FenetreImageInput() {
		this.setTitle("Entrer chemin image");
		this.setLocation(100, 100);
		
		JPanel p = new JPanel() ;				// Panel NORTH contenant le label et le champs texte relatif au chemin absolu.
		this.lFichierInput = new JLabel() ;				// Initialisation du label
		this.fichierInput = new JTextField() ;			// Initialisation du champs texte.
		
		this.lFichierInput.setText("Image a convertir : ");				// init. des caracteristiques du champ texte.
		this.fichierInput.setColumns(20);								// Choix de "20" arbitraire.
		
		p.setLayout(new FlowLayout());							// Creation du panneau du haut ( NORTH )
		p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		p.add(this.lFichierInput);
		p.add(this.fichierInput);
		
		this.add(p, BorderLayout.NORTH) ;				// Ajout du panneau du haut.
		
		this.setSize(400, 100);								// Fixer la dimension de la fenetre. ( en fonction des panneaux contenus )
		
		this.apercu = new JButton("Appercu") ;				// Initialisation des boutons
		this.continuer = new JButton("Continuer") ;
		
		this.apercu.addActionListener(new ApercuAction());	// Association avec les actions associees.
		this.continuer.addActionListener(new ContinuerAction());
		
		JPanel pSouth = new JPanel() ;						// Creation du panneau du bas ( SOUTH )
		
		pSouth.add(this.apercu, BorderLayout.WEST) ;				// Formation de ce panneau 
		pSouth.add(Box.createHorizontalStrut(50), BorderLayout.CENTER) ;
		pSouth.add(this.continuer, BorderLayout.EAST) ;
		
		this.add(pSouth, BorderLayout.SOUTH);				// Ajout a la fenetre.
		
		this.setVisible(true) ;
	}
	
	/**
	 * Action liee au bouton "Apercu" de la fenetre.
	 * 
	 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 06/01/2018
	 * 
	 * @version 0.0.1
	 */
	private class ApercuAction implements ActionListener {
		/**
		 * Au clic : recuperation de l'image et du terrain associe puis ouverture de la fenetre d'apercu.
		 */
		public void actionPerformed(ActionEvent e) {
			String path = fichierInput.getText() ;
			
			fichier = new ImageCarte(path) ;
			fichier.toTerrain();
			
			FenetreApercu f = new FenetreApercu() ;
		}
	}

	/**
	 * Action liee au bouton "Continuer" de la fenetre.
	 * 
	 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 06/01/2018
	 * 
	 * @version 0.0.1
	 */
	private class ContinuerAction implements ActionListener {
		/**
		 * Au clic : fermeture de la fenetre actuelle et ouverture de la fenetre d'ajout de case / d'ateur ( FenetreCreationTerrain )
		 */
		public void actionPerformed(ActionEvent e) {
			String path = fichierInput.getText() ;				// Recuperation de l'image
			
			fichier = new ImageCarte(path) ;					// Analyse et obtention du terrain associe.
			fichier.toTerrain();
			
			dispose() ;
			
			FenetreCreationTerrain f = new FenetreCreationTerrain() ;
		}
	}
	
	public static void main(String[] args) {
		FenetreImageInput f = new FenetreImageInput() ;
	}
}
