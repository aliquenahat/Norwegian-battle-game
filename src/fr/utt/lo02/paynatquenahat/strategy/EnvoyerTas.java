package fr.utt.lo02.paynatquenahat.strategy;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.lo02.paynatquenahat.joueurs.*;

public class EnvoyerTas implements Strategy {

	/*
	 * permet de definir a quel joueur le joueur virtuel envoie le tas lorsqu'il
	 * pose un AS
	 */
	public void faire(JoueurVirtuel j) {
		/*
		 * Dans notre strategie, nous decidons d'envoyer le tas au joueur qui a
		 * le moins de cartes
		 */
		ArrayList<Joueur> autres = j.getAutresJoueurs();
		int num_joueur = 0, nb_cartes = 5000, i = 0, nb_temp;
		Iterator<Joueur> it = autres.iterator();
		/*
		 * on regarde tous les autres joueurs pour voir celui qui a le plus de
		 * cartes
		 */
		while (it.hasNext()) {
			nb_temp = it.next().getNbCartes();
			if (nb_temp < nb_cartes) {
				num_joueur = i;
				nb_cartes = nb_temp;
			}
			i++;
		}
		j.setJoueurAQuiEnvoyerLeTas(autres.get(num_joueur));
	}

}