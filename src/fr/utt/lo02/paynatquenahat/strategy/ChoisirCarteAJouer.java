package fr.utt.lo02.paynatquenahat.strategy;

import java.util.ArrayList;

import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.cartes.*;

public class ChoisirCarteAJouer implements Strategy {

	// permet de decider quelle carte joue le joueur virtuel
	public void faire(JoueurVirtuel j) {
		/*
		 * Dans notre strategie, nous decidons que le joueur virtuel joue
		 * automatiquement la carte de plus faible valeur qui peut etre jouee
		 * par dessus le tas
		 */
		ArrayList<Carte> cartes = j.getCartesJouables();
		j.setNomCarteChoisie(cartes.get(0).getNom());
	}

}