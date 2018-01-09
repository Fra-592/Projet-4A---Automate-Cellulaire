package Environnement;

import java.util.ArrayList;
import java.util.Iterator;

import Exceptions.HorsLimite;
import Exceptions.NoTerrain;
import Comportemental.Acteur;
import Comportemental.ActeurType;

/**
 * Plateau de la simulation, contient l'ensemble des "Case" et le mecanisme generale de simulation.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> -29/12/2017
 *
 */
public class Terrain {
	private Case[][] tableau ;						// Tableau de Case : etudiees pour la simulation.
	private int xMax ;								// Longueur du terrain
	private int yMax ;								// Hauteur du terrain
	private static Terrain uniqueInstance ;			// L'unique instance terrain. (Singleton)
	
	private ArrayList<Acteur> flammes ;				// Ensemble des flammes sur le terrain.
	private ArrayList<Acteur> pompiers ;			// Ensemble des pompiers sur le terrain.
	private ArrayList<Acteur> nouveauAct ;			// Ensemble des nouveaux acteurs a prendre en compte pour le prochain tour.
	private ArrayList<Acteur> acteurMort ;			// Ensemble des acteurs morts pendant ce tour.
	
	public FeuilleRes stat ;
	
	/**
	 * Constructeur par defaut du terrain.
	 */
	private Terrain() {
		this.flammes = new ArrayList<Acteur>() ;
		this.pompiers = new ArrayList<Acteur>() ;
		this.nouveauAct = new ArrayList<Acteur>() ;
		this.acteurMort = new ArrayList<Acteur>() ;
		
		this.stat = new FeuilleRes() ;
	}
	
	/**
	 * Constructeur du terrain, appelant le constructeur par defaut.
	 * Initialise la dimension du tableau de case.
	 * 
	 * @param xMax La longueur du tableau de cases.
	 * @param yMax La hauteur du tableau de cases.
	 */
	private Terrain(int xMax, int yMax) {
		this() ;
		
		this.tableau = new Case[yMax][xMax] ;
		
		int x, y ;
		for (x=0; x<xMax; x++) {
			for (y=0; y<yMax; y++) {
				this.tableau[y][x] = new Case(x, y, CaseType.Neutre) ; 
			}
		}
		
		this.xMax = xMax ;
		this.yMax = yMax ;
	}
	
	/**
	 * Fonction de creation du terrain seulement si pas encore cree. ( Singleton )
	 * 
	 * @param i Abscisse maximale du plateau.
	 * @param j	Ordonnee maximale du plateau
	 * @return Renvoie le terrain ainsi cree si aucun n'existe deja, l'ancien sinon.
	 */
	public static Terrain create(int i, int j) {
		if (Terrain.uniqueInstance == null)
			return Terrain.uniqueInstance = new Terrain(i, j) ;
		else return Terrain.uniqueInstance ;
	}
	
	/**
	 * Supprime les acteurs morts pendant ce tour a la simulation. ( les supprimes des listes flammes ou pompiers )
	 */
	private void supprimerActeurs() {
		Iterator<Acteur> it = this.acteurMort.iterator() ;				
		Acteur a ;				// Acteur a supprimer.
		
		while (it.hasNext()) {				// Tant que la liste contient des acteurs
			a = it.next();							// Le recuperer
			
			if (a.getType() == ActeurType.Feu)				// Si "Feu"
				this.flammes.remove(a) ;							// Le supprimer de la liste des flammes de la simulation
			if (a.getType() == ActeurType.Pompier)			// Si "Pompier"
				this.pompiers.remove(a) ;							// Le supprimer de la liste des pompiers de la simulation
			
			it.remove() ;							// Supprimer des mort a enlever.
		}
	}
	
	/**
	 * Ajoute les acteurs crees pendant ce tour a la simulation. ( les ajoute dans les liste flammes ou pompiers )
	 */
	private void ajouterActeurs() {
		Iterator<Acteur> it = this.nouveauAct.iterator() ;
		Acteur a ;
		
		while (it.hasNext()) {
			a = it.next();
			
			if (a.getType() == ActeurType.Feu)
				this.flammes.add(a) ;
			if (a.getType() == ActeurType.Pompier)
				this.pompiers.add(a) ;
			
			it.remove() ;
		}
	}
	
	/**
	 * Ajoute un acteur a la liste acteurMort.
	 * 
	 * @param a Acteur a supprimer pour la suite de la simulation.
	 */
	public void supprimer(Acteur a) {
		this.acteurMort.add(a) ;
	}
	
	/**
	 * Ajoute un acteur a la liste nouveauAct.
	 * 
	 * @param a Acteur a ajouter pour la suite de la simulation.
	 */
	public void ajouter(Acteur a) {
		System.out.println("ajout d'un feu ? " + (a.getType() == ActeurType.Feu));
		this.nouveauAct.add(a) ;
	}
	
	/**
	 * Accesseur a l'abscisse maximale du plateau.
	 * 
	 * @return Renvoie l'abscisse maximale du plateau de simulation.
	 */
	public int getXMax() {
		return this.xMax ;
	}
	/**
	 * Accesseur a l'ordonnee maximale du plateau.
	 * 
	 * @return Renvoie l'ordonnee maximale du plateau de simulation.
	 */
	public int getYMax() {
		return this.yMax ;
	}
	
	/**
	 * Indique l'unique instance de terrain.
	 * 
	 * @return Renvoie le terrain actuel.
	 */
	public static Terrain getInstance() throws NoTerrain {
		if (Terrain.uniqueInstance == null) throw new NoTerrain() ;
		return Terrain.uniqueInstance ;
	}
	
	/**
	 * Renvoie la case aux coordonnees indiquees. Une exception est envoyee si la case n'existe pas ( hors des limite du plateau )
	 * 
	 * @param x L'abscisse de la case ( origine en "haut a gauche" quand observation du plateau sur une fenetre de simulation )
	 * @param y L'ordonnee de la case ( origine en "haut a gauche" quand observation du plateau sur une fenetre de simulation )
	 * 
	 * @return Renvoie la case au coordonnees indiquees si la case existe.
	 * 
	 * @throws HorsLimite La case n'existe pas, est hors des limites du plateau.
	 */
	public Case getCase(int x, int y) throws HorsLimite {
		if ((xMax<=x) || (yMax<=y)) throw new HorsLimite(x, y, xMax, yMax) ;
		if ((x<0) || (y<0)) throw new HorsLimite(x, y, xMax, yMax) ;
		return this.tableau[y][x] ;
	}
	
	/**
	 * Initialisation de la simulation. Ajouter tous les acteurs mis sur le plateau de simulation.
	 */
	public void initialiser() {
		this.ajouterActeurs();
		this.supprimerActeurs();
	}
	
	/**
	 * Deroule un tour de la simulation :
	 * 		realiser les actions du "Feu" puis des "Pompiers"
	 * 		"actions" = Acteur.actionConcrete() puis Acteur.mouvementConcret()
	 * 		Puis ajout des acteurs crees pendant ce tour.
	 * 		Enfin suppression des acteurs mort pendant ce tour.
	 */
	public void evolution() {
		this.stat.incTour(); 
		
		Iterator<Acteur> aIterator = this.flammes.iterator() ;
		Acteur f ;
		
		while (aIterator.hasNext()) {
			f = aIterator.next() ;
			
			System.out.println("Tour de : " + f);
			f.actionConcrete();
			f.mouvementConcrete();
		}
		
		aIterator = this.pompiers.iterator() ;
		Acteur p ;
		
		while (aIterator.hasNext()) {
			p = aIterator.next();
			p.actionConcrete();
			p.mouvementConcrete();
		}
		
		this.ajouterActeurs();
		this.supprimerActeurs();
	}
	
	/**
	 * Indique si la simulation est achevee.
	 * 
	 * @return Renvoie true si simulation achevee sinon false.
	 */
	public boolean estTermine() {
		return (this.flammes.isEmpty() || this.pompiers.isEmpty()) ;				// Si plus de flammes ou plus de pompiers alors simulation terminee.
	}
	
	/**
	 * Indique si le feu a ete eteint ou non.
	 * 
	 * @return Renvoie true si le feu a ete eteint sinon false.
	 */
	public boolean feuEteint() {
		return this.flammes.isEmpty() ;
	}
}
