package Fenetres;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Environnement.Terrain;
import Exceptions.FenetreErreurFatale;
import Exceptions.NoTerrain;

@SuppressWarnings("serial")
public class Simulation implements Runnable {
	private FenetreSimulation f ;
	public boolean running ;
    private final Object pauser = new Object();
	
	
	/**
	 * 
	 */
	public Simulation(FenetreSimulation fensim) {
		this.f = fensim;
		this.running = false;
	}
	
	/**
	 * 
	 */
	public void run() {
		Timer t = new Timer() ;
		
		t.schedule(new SimulationTask() , 0, 250);
	}
	
	public void pause()
	{
		this.running = false;
	}
	
	public void runback()
	{
		synchronized(pauser) {
			this.running = true;
			pauser.notifyAll();
		}
	}
	
	private class SimulationTask extends TimerTask {
		public void run() {
			try {
				if (!Terrain.getInstance().estTermine()) {
					synchronized (pauser) {
						if(running) {
						
							Terrain.getInstance().evolution() ;
							f.repaint();
						} else {
							try {
								pauser.wait();
							} catch (InterruptedException e) {}
						}
					}
				} else {
					FenetreStat f = new FenetreStat() ;
					
					this.cancel() ;
				}
			} catch (NoTerrain e) {
				new FenetreErreurFatale(e.toString()) ;
			}
		}
	}
}
