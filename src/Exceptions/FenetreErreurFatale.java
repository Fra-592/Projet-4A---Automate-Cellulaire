package Exceptions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FenetreErreurFatale extends JFrame implements ActionListener {
	private JLabel erreur ;
	private JButton boutonFin ;
	
	public FenetreErreurFatale(String erreur) {
		this.erreur = new JLabel(erreur) ;
		
		this.setTitle("Erreur fatale");
		this.setBounds(300, 300, 300, 100) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel p = new JPanel() ;
		p.add(this.erreur) ;
		this.add(p) ;
		
		p = new JPanel() ;
		this.boutonFin = new JButton("Fermer l'application") ;
		this.boutonFin.addActionListener(this);
		p.add(this.boutonFin) ;
		this.add(p, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
