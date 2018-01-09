package Environnement;

import java.util.TreeMap;

import Comportemental.Acteur;
import Comportemental.ActeurType;
import Comportemental.StatActeur;

/**
 * Ensemble des resultats de la simulation. Unique pour chaque terrain, mise a jour dans "Case" et "BaseComportement".
 * L'ensemble des Cases et Acteurs a tracer sont modifiables a partir de CaseType et ActeurType.
 * 
 * @author ALLAM Jonathan - <thomasallam@outlook.fr> - 06/01/2018
 *
 * @version 0.0.1
 */
public class FeuilleRes {
	private String nomSimulation ;		// Nom de la simulation.
	private int nbrTour ;				// Nombre de tours de la simulation.
	private boolean feuEteint ;			// Indique si le feu a ete eteint.

	// private ? carteSimulation ;
	
	public TreeMap<String, StatActeur> ensStatActeur ;		// Ensemble des acteurs et de leur statistique.
	public TreeMap<String, StatCase> ensStatCase ;			// Ensemble des cases et de leur statistique.
	
	/**
	 * Constructeur par defaut.
	 */
	public FeuilleRes() {
		this.nomSimulation = "TEST" ;								// A CHANGER !!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.nbrTour = 0 ;
		
		this.ensStatActeur = new TreeMap<String, StatActeur>() ;	// Initialisation de l'ensemble des acteurs et cases.
		this.ensStatCase = new TreeMap<String, StatCase>() ;
		
		for (ActeurType a : ActeurType.values()) 				// Pour chaque acteur 
			if (a.getRecordStat())										// Si acteur a suivre
				this.ensStatActeur.put(a.toString(), new StatActeur(a)) ;		//	Rajouter un ensemble de statistiques
		
		for (CaseType c : CaseType.values()) 
			if (c.getRecordStat())
				this.ensStatCase.put(c.toString(), new StatCase(c)) ;
	}
	
	/**
	 * Incremente le nombre de tour de 1.
	 */
	public void incTour() {
		this.nbrTour ++ ;
	}
	
	/**
	 * Incremente le nombre de creation d'une case de 1.
	 * 
	 * @param c Case dont le nombre de creation doit etre incremente de 1.
	 */
	public void ajouterCreation(Case c) {
		CaseType ct = c.getCaseType() ;
		
		if (this.ensStatCase.containsKey(ct.toString())) {
			StatCase sc = this.ensStatCase.get(ct.toString()) ;
			
			sc.ajouterCreation();
		}
	}
	
	/**
	 * Incremente le nombre de creation d'un acteur de 1.
	 * 
	 * @param a Acteur dont le nombre de creation doit etre incremente de 1.
	 */
	public void ajouterCreation(Acteur a) {
		ActeurType at = a.getType() ;
		
		if (this.ensStatActeur.containsKey(at.toString())) {
			StatActeur sa = this.ensStatActeur.get(at.toString()) ;
			
			sa.ajouterCreation();
		}		
	}
	
	/**
	 * Increment le nombre de mort d'une case de 1.
	 * 
	 * @param c La case dont le nombre de mort doit etre incremente.
	 */
	public void ajouterMort(Case c) {
		CaseType ct = c.getCaseType() ;
		
		if (this.ensStatCase.containsKey(ct.toString())) {
			StatCase sc = this.ensStatCase.get(ct.toString()) ;
			
			sc.ajouterMort();
		}
	}
	
	/**
	 * Incremente le nombre de mort d'un acteur de 1.
	 * 
	 * @param a Acteur dont le nombre de mort doit etre incremente.
	 */
	public void ajouterMort(Acteur a) {
		ActeurType at = a.getType() ;
		
		if (this.ensStatActeur.containsKey(at.toString())) {
			StatActeur sa = this.ensStatActeur.get(at.toString()) ;
			
			sa.ajouterMort();
		}		
	}
	
	/**
	 * Renvoie un rendu de la simulation ( generale ) sous forme de String.
	 * 
	 * @return Renvoie le nom de la simulation et son nombre de tour total.
	 */
	public String toString() {
		String ans ;
		
		ans = "Nom simulation : " + this.nomSimulation ;
		ans += "\n        Nombre de tours : " + this.nbrTour ;
		
		return ans ;
	}
}
