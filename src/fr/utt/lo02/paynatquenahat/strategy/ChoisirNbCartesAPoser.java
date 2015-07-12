package fr.utt.lo02.paynatquenahat.strategy;

import fr.utt.lo02.paynatquenahat.joueurs.*;

public class ChoisirNbCartesAPoser implements Strategy {

	// permet de decider combien de cartes identiques le joueur va poser
	public void faire(JoueurVirtuel j) {
		/*
		 * Dans notre strategie, nous decidons que le joueur virtuel jouera
		 * forcement le plus de cartes possibles pour se liberer du plus de
		 * cartes possible
		 */
		int max = j.getNbMaxCartesAPoser();
		max = (max > 3) ? 3 : max;
		j.setNbCartesAPoser(max);
	}

}