package Fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Comportemental.StatActeur;
import Environnement.StatCase;
import Environnement.Terrain;

/**
 * Fenetre montrant les informations sur la simulation terminee. ( cf. FeuilleRes )
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 06/01/2018
 *
 * @version 0.0.1
 * 
 * @see FeuilleRes
 */
@SuppressWarnings("serial")
public class FenetreStat extends JFrame {
	private JLabel infoGene ;				// Informations generales sur la simulation ( nom + nbr Tour )
	
	/**
	 * Constructeur par defaut.
	 */
	public FenetreStat() {
		this.setTitle("Fenetre resultats");							// Caracteristiques de la fenetre
		this.setBounds(100, 100, 500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		this.infoGene = new JLabel() ;								// Initialisation des informations generales ( Label )
		this.infoGene.setText(Terrain.getInstance().stat.toString());
		
		JPanel p = new JPanel() ;									// Panneau du haut contenant les informations generales.
		
		p.add(this.infoGene) ;
		
		this.add(p, BorderLayout.NORTH) ;								// Mise en forme du panneau du haut. ( informations generales )
		this.add(Box.createHorizontalStrut(50), BorderLayout.WEST);
		this.add(Box.createHorizontalStrut(50), BorderLayout.EAST);
		
		JPanel pCenter = new JPanel() ;								// Panneau central contenant informations sur chaque Case/Acteur.
		JPanel pTemp ;												// Panneau temporaire : information sur un element particulier ( mise en forme des infos )
		JLabel titre, chpCrea, chpMort ;							// Ensemble des infos relative a l'elt actuel. ( Nom / nbrCrea / nbrMort ) sous forme de texte.
		pCenter.setLayout(new GridLayout(0,2, 20, 20));
		pCenter.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		for (StatCase sc : Terrain.getInstance().stat.ensStatCase.values()) {				// Pour chaque acteur suivit pendant la simulation ...
			pTemp = new JPanel() ;																	// Creer un nouveau panneau
			pTemp.setLayout(new BoxLayout(pTemp, BoxLayout.PAGE_AXIS));								// Ajout d'un element ligne par ligne dans ce panneau.
			
			titre = new JLabel(sc.getCaseType(), JLabel.CENTER) ;
			chpCrea = new JLabel() ;
			chpMort = new JLabel() ;
			
			chpCrea.setText("Nombre de creation : " + sc.getNbrCreation());
			chpMort.setText("Nombre de mort : " + sc.getNbrCreation());
			
			pTemp.add(titre) ;
			pTemp.add(chpCrea) ;
			pTemp.add(chpMort) ;
			
			pTemp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pCenter.add(pTemp) ;
		}
		
		for (StatActeur sa : Terrain.getInstance().stat.ensStatActeur.values()) {			// Pour chaque Case suivit pendant la simulation
			pTemp = new JPanel() ;																	// Creer un nouveau panneau
			pTemp.setLayout(new BoxLayout(pTemp, BoxLayout.PAGE_AXIS));								// Ajout d'un element ligne par ligne dans ce panneau.
			
			titre = new JLabel(sa.getActeurType(), JLabel.CENTER) ;
			chpCrea = new JLabel() ;
			chpMort = new JLabel() ;
			
			chpCrea.setText("Nombre de creation : " + sa.getNbrCreation());
			chpMort.setText("Nombre de mort : " + sa.getNbrCreation());
			
			pTemp.add(titre) ;
			pTemp.add(chpCrea) ;
			pTemp.add(chpMort) ;
			
			pTemp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pCenter.add(pTemp) ;			
		}
		
		pCenter.add(Box.createHorizontalStrut(20));								// Delimination en bas.
		
		this.add(pCenter, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
}
