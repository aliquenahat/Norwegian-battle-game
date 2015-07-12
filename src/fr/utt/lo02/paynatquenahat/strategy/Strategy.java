package fr.utt.lo02.paynatquenahat.strategy;

import fr.utt.lo02.paynatquenahat.joueurs.*;

public interface Strategy {

	/*
	 * L'interface Strategy possede une seule methode jouer() qui va etre
	 * implementee par differentes classes. La methode jouer() effectuera les
	 * actions pour le joueur virtuel concerne.
	 */

	public void faire(JoueurVirtuel j);

}