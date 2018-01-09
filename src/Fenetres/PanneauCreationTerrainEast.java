package Fenetres;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Comportemental.ActeurType;
import Environnement.CaseType;

@SuppressWarnings("serial")
public class PanneauCreationTerrainEast extends JPanel {
	public JComboBox<String> choixAction ;
	public JComboBox<Object> listeType ;
	
	public PanneauCreationTerrainEast() {
		JPanel p1, p2 ;
		JLabel l1, l2 ;
		
		p1 = new JPanel() ;
		p2 = new JPanel() ;
		l1 = new JLabel() ;
		l2 = new JLabel() ;
		
		l1.setText("Choix de l'element a rajouter :");
		l2.setText("Choix du type de l'element :");
		
		String[] s = {"Case", "Acteur"} ;
		this.choixAction = new JComboBox<String>(s) ;
		this.listeType = new JComboBox<Object>(CaseType.values()) ;
		
		p1.add(l1);
		p1.add(choixAction) ;
		p1.setBackground(Color.GRAY);
		
		p2.add(l2) ;
		p2.add(listeType);
		p2.setBackground(Color.GRAY);
		
		this.choixAction.addItemListener(new ListeChangeAction());
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(p1) ;
		this.add(Box.createVerticalStrut(25));
		this.add(p2);
	}
	
	private class ListeChangeAction implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getItem() != null) {
				if (e.getItem() == "Case") {
					listeType.removeAllItems();
					for (CaseType c : CaseType.values()) 
						listeType.addItem(c) ;
				} else {
					listeType.removeAllItems();
					for (ActeurType t : ActeurType.values()) 
						listeType.addItem(t);
				}
			}
		}
	}
}
