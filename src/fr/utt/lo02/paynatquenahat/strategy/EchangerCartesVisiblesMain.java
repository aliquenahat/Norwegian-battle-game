package fr.utt.lo02.paynatquenahat.strategy;

import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.affichage.*;

public class EchangerCartesVisiblesMain implements Strategy {

	/*
	 * permet de decider si le joueur echange ses cartes visibles avec les
	 * cartes de sa main au debut
	 */
	public void faire(JoueurVirtuel j) {
		/*
		 * Dans notre strategie, nous decidons de ne pas echanger les cartes du
		 * joueur au debut de la partie. Il jouera donc en premier avec les
		 * cartes distribuees dans sa main, et ses cartes disposees face visible
		 * seront pour apres.
		 */
		Ecran.infosEchangerCartesDebut_non(j);
	}

}