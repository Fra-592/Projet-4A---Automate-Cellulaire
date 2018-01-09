package Fenetres;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Environnement.Terrain;

@SuppressWarnings("serial")
public class Simulation implements Runnable {
	private FenetreSimulation f ;
	public int mode ;
	
	/**
	 * 
	 */
	public Simulation() {
		this.f = new FenetreSimulation() ;
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.mode = 0 ;
	}
	
	/**
	 * 
	 */
	public void run() {
		Timer t = new Timer() ;
		
		t.schedule(new SimulationTask() , 0, 250);
	}
	
	private class SimulationTask extends TimerTask {
		public void run() {
			if (!Terrain.getInstance().estTermine()) {
				Terrain.getInstance().evolution() ;
				f.repaint();
			} else {
				FenetreStat f = new FenetreStat() ;
				
				this.cancel() ;
			}
		}
	}
}
