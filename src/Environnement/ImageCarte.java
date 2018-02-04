package Environnement;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Analyse d'une image rentree manuellement par l'utilisateur.
 * Transforme une image en un Terrain par discretisation et rapprochement aux CaseType implementees.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 06/01/2018
 *
 * @version 0.0.1
 */
public class ImageCarte {
	private BufferedImage image ;				// Image a analyser
	
	/**
	 * Constructeur par defaut.
	 */
	public ImageCarte() {
		try {
			this.image = ImageIO.read(new File("D:\\Informatique\\INFO4A\\Projet POO\\Images tests\\test01.jpg")) ;
		} catch (Exception e) {
			System.out.println("Erreur chargement image");
		}
	}
	
	/**
	 * Initialisation de l'image a partir d'un chemin absolu.
	 * 
	 * @param path Chemin absolu de l'image a analyser.
	 */
	public ImageCarte(String path) {
		try {
			this.image = ImageIO.read(new File(path)) ;
		} catch (Exception e) {
			System.out.println("Erreur chargement image");
		}		
	}
	
	/**
	 * Convertie l'image en un Terrain. ( 1 case = 1 carre de 4 pixels )
	 */
	public void toTerrain() {
		int h = this.image.getHeight() / 4 ;
		int w = this.image.getWidth() / 4 ;
		Terrain.create(h, w);
		
		for (int i = 0; i<h; i++) 								// Pour chaque case du terrain ...
			for (int j = 0; j<w; j++) 
				this.analyser(i, j) ;								// ... Analyser la case i, j du plateau.
	}
	
	/**
	 * Renvoie la couleur moyenne des couleurs indiquees. ( 4 )
	 * 
	 * @param couleurs Ensemble des couleurs a moyenner. ( 4 )
	 * 
	 * @return Renvoie la couleur moyenne de "couleurs"
	 */
	public int couleurMoyenne(int[] couleurs) {
		Color cTemp ;				// Couleur temporaire
		int r, g, b ;				// rouge, vert, bleu de la couleur moyenne
		
		r = b = g = 0 ;				// Au depart nuls
		
		for (int i=0; i<4; i++) {			// Pour l'ensemble des 4 couleurs indiquees.
			cTemp = new Color(couleurs[i]) ;		// Recuperer la couleur
			r += cTemp.getRed() ;					// Incrementer chaque couleur.
			g += cTemp.getGreen() ;
			b += cTemp.getBlue() ;
		}
			
		r /= 4 ;					// rouge moyen
		g /= 4 ;					// green moyen
		b /= 4 ;					// bleu moyen
		
		return new Color(r, g, b).getRGB() ;				// Renvoie de la couleur moyenne.
	}
	
	/**
	 * Renvoie le CaseType en fonction de la couleur moyenne indiquee.
	 * Cela par une comparaison a la couleur de chaque CaseType. ( utilisation d'une distance entre couleurs en fonction de chaque composante
	 *  rouge, bleu et vert. )
	 * 
	 * @param moyenne
	 * @return
	 */
	public CaseType getCaseType(Color moyenne) {
		int dMin, dTemp ;				// Distance minimale et Distance Temporaire
		int dr, dg, db ;				// Distance associee au rouge, vert et bleu.
		CaseType answer ;				// Reponse : couleur associee a la distance minimale (dMin)
		Color cTemp ;					// Couleur temporaire
		
		dMin = Integer.MAX_VALUE ;		// Inialisation de la distance Minimale.
		answer = null ;
		
		for (CaseType ct : CaseType.values()) {
			if (ct.getAnalysable()) {
				cTemp = ct.getColor() ;				// Couleur de CaseType actuel.
				
				dr = moyenne.getRed() - cTemp.getRed() ;				// Calcul distance selon rouge
				dr *= dr ;
				dr = (int) Math.pow(dr, 0.5) ;
				
				dg = moyenne.getGreen() - cTemp.getGreen() ;			// Calcul distance selon vert
				dg *= dg ;
				dg = (int) Math.pow(dg, 0.5) ;
				
				db = moyenne.getBlue() - cTemp.getBlue() ;				// Calcul distace selon bleu
				db *= db ;
				db = (int) Math.pow(db, 0.5) ;
				
								// Distance total entre la couleur moyenne et la couleur du CaseType actuel
				dTemp = dr + dg + db ;
				if (dTemp < dMin) {				// Si plus faible que la distance minimale connu
					dMin = dTemp ;						// Remplacer la distance minimale connu
					answer = ct ;						// Remplacer la couleur connu.
				}
			}
		}
		
		return answer ;				// Renvoie de la couleur trouvee.
	}
	
	/**
	 * Analyse de la case x, y du Terrain. Modifie son type a l'issu de cette fonction.
	 * 
	 * @param x Abscisse de la case a modifier.
	 * @param y Ordonnee de la case a modifier.
	 */
	public void analyser(int x, int y) {
		int[] couleurs ;				// RGB des 4 pixels constituant la futur case x, y du Terrain.
		
		couleurs = new int[4] ;
		
		for (int i=0; i < 2; i++) 								// Recuperation des couleurs des pixel (x,y) / (x+1,y)
			for (int j=0; j<2; j++) 								// (x,y+1) / (x+1, y+1)
				couleurs[i*2 + j] = this.image.getRGB(x*2+i, y*2+j) ;
		
		int moyenne ;
		
		moyenne = this.couleurMoyenne(couleurs) ;				// Recuperation de la couleur moyenne de l'ensemble.
		
		CaseType ct = this.getCaseType(new Color(moyenne)) ;
		
		try {
			if (ct != null)
				Terrain.getInstance().getCase(x, y).changeType(ct);
		} catch (Exception e) {
			System.out.println("Erreur analyse case.");
			e.printStackTrace();
		}
	}
}
