package Fenetres;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanneauSimulationSouth extends JPanel {
	private JButton pause ;
	private JButton suivant ;
	private JButton continuer ;
	private FenetreSimulation f ;
	
	public PanneauSimulationSouth(FenetreSimulation fenSimul) {
		super() ;
		
		this.f = fenSimul ;
		
		this.pause = new JButton("Pause") ;
		this.suivant = new JButton("Suivant") ;
		this.continuer = new JButton("Continuer") ;
		
		this.pause.addActionListener(new ActionPause()) ;
		this.suivant.addActionListener(new ActionSuivant());
		this.continuer.addActionListener(new ActionContinuer());
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		this.add(this.pause) ;
		this.add(this.suivant) ;
		this.add(this.continuer) ;
	}
	
	private class ActionPause implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Pause");
			f.toPause() ;
		}
	}
	
	private class ActionSuivant implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Suivant");
			f.toSuivant();
		}
	}
	
	private class ActionContinuer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Continuer");
			f.toContinuer();
		}
	}
}
