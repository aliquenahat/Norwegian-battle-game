package fr.utt.lo02.paynatquenahat.cartes;

import fr.utt.lo02.paynatquenahat.paquet.*;
import fr.utt.lo02.paynatquenahat.partie.*;
import fr.utt.lo02.paynatquenahat.joueurs.*;
import fr.utt.lo02.paynatquenahat.affichage.*;

/**
 * Classe héritant de Carte qui représente le Dix
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Dix extends Carte {

	/**
	 * Constructeur de la classe
	 * 
	 * @param enseigne
	 *            L'enseigne de la carte
	 */
	public Dix(String enseigne) {
		super(10, "10", enseigne);
	}

	/*
	 * JOUER LA CARTE
	 */

	/**
	 * Le 10 efface le tas de cartes
	 * 
	 * @param partie
	 *            La partie en cours
	 * @param joueur
	 *            Le joueur correspondant
	 */
	public void jouer(Partie partie, Joueur joueur) {
		Ecran.TOUR_10pose();
		// le 10 n'a pas besoin d'etre mis dans le Tas car de toute faÃ§on on
		// supprime le Tas
		Tas.getInstance().viderTas();
	}

}
