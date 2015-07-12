package fr.utt.lo02.paynatquenahat.cartes;

import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.partie.*;
import fr.utt.lo02.paynatquenahat.affichage.*;

/**
 * Classe héritant de Carte qui représente le 7
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Sept extends Carte {

	/**
	 * Constructeur de la classe
	 * 
	 * @param enseigne
	 *            L'enseigne de la carte
	 */
	public Sept(String enseigne) {
		super(7, "7", enseigne);
	}

	/*
	 * JOUER LA CARTE
	 */

	/**
	 * Sur un 7, on ne peut poser qu'une carte de valeur inférieure
	 * 
	 * @param carte
	 *            La carte jouée par dessus
	 * @return vrai si la carte est jouable par dessus
	 */
	public boolean jouableParDessus(Carte carte) {
		if (carte.getValeur() <= this.getValeur())
			return true;

		return false;
	}

	/**
	 * Affichage du message
	 * 
	 */
	public void jouer(Partie partie, Joueur joueur) {
		Ecran.TOUR_7pose();
		this.poser();
	}

}
