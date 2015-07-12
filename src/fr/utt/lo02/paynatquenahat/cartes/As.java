package fr.utt.lo02.paynatquenahat.cartes;

import fr.utt.lo02.paynatquenahat.paquet.Tas;
import fr.utt.lo02.paynatquenahat.partie.*;
import fr.utt.lo02.paynatquenahat.joueurs.*;

/**
 * Classe héritant de Carte qui représente l'AS
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class As extends Carte {

	/**
	 * Constructeur de la classe
	 * 
	 * @param enseigne
	 *            L'enseigne de la carte
	 */
	public As(String enseigne) {
		super(14, "As", enseigne);
	}

	/*
	 * JOUER LA CARTE
	 */
	/**
	 * L'As permet d'envoyer tout le tas de cartes au joueur de notre choix
	 * 
	 * @param partie
	 *            La partie en cours
	 * @param joueur
	 *            Le joueur correspondant
	 */
	public void jouer(Partie partie, Joueur joueur) {
		// l'as n'est pas mis dans le tas lorsqu'on le joue
		if (Tas.getInstance().getNbCartes() > 0) {
			joueur.envoyerTasAAdversaire();
		}
	}

}
