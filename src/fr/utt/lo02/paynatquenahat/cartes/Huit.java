package fr.utt.lo02.paynatquenahat.cartes;

import fr.utt.lo02.paynatquenahat.partie.*;
import fr.utt.lo02.paynatquenahat.joueurs.*;

/**
 * Classe héritant de Carte qui représente le 8
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Huit extends Carte {

	/**
	 * Constructeur de la classe
	 * 
	 * @param enseigne
	 *            L'enseigne de la carte
	 */
	public Huit(String enseigne) {
		super(8, "8", enseigne);
	}

	/*
	 * JOUER LA CARTE
	 */

	/**
	 * Le 8 fait passer son tour au joueur suivant
	 * 
	 * @param partie
	 *            La partie en cours
	 * @param joueur
	 *            Le joueur correspondant
	 */
	public void jouer(Partie partie, Joueur joueur) {
		partie.prochainJoueurPasseSonTour();
		this.poser();
	}

}
