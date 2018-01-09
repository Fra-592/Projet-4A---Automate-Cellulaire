package Comportemental;

/**
 * 
 * @author 	ALLAM Jonathan - <thomasallam@outlook.fr> - 29/12/2017
 *
 * @version 0.0.1
 */
public interface Comportement {
	/**
	 * Action principale de l'acteur, realise apres son mouvement.
	 * 
	 * @param x Abscisse de l'acteur concernee.
	 * @param y Ordonnee de l'acteur concerne.
	 *
	 */
	public void action(int x, int y) ;
	
	/**
	 * Mouvement de l'acteur, realisee avant son action principale.
	 * 
	 * @param x Abscisse de l'acteur.
	 * @param y Ordonnee de l'acteur.
	 */
	public void mouvement(int x, int y) ;
	
	/**
	 * Action a la mort naturelle de l'acteur.
	 * 
	 * @param x Abscisse de l'acteur.
	 * @param y Ordonnee de l'acteur.
	 */
	public void actionMort(int x, int y) ;
	
	/**
	 * Action a la mort non naturelle de l'acteur.
	 * 
	 * @param x Abscisse de l'acteur.
	 * @param y Ordonnee de l'acteur.
	 */
	public void mort(int x, int y) ;
}
