package fr.utt.lo02.paynatquenahat.configuration;

import fr.utt.lo02.paynatquenahat.affichage.*;

/**
 * Classe permettant de configuer l'application
 * 
 * @author Victor PAYNAT-SAUTIVET, Ali QUENAHAT
 * @version 1.0
 */
public class Config {

	/*
	 * VARIABLES A MODIFIER Permet de regler les parametres principaux de
	 * l'application.
	 * ============================================================
	 * ================
	 */
	private static final int NB_MAX_JOUEURS = 19;
	private static final String MODE_AFFICHAGE = "fenetre"; // console OU
															// fenetre

	/*
	 * ==========================================================================
	 * ==
	 */

	/*
	 * GETTERS
	 */
	/**
	 * Permet de récuperer le nombre max de joueurs pour la partie
	 * 
	 * @return Le nombre max de joueurs sous forme d'entier
	 */
	public static int NB_MAX_JOUEURS() {
		return NB_MAX_JOUEURS;
	}

	/**
	 * Retourne l'instance du mode d'affichage choisi (instance de Console ou de
	 * Fenetre, qui heritent d'Affichage)
	 * 
	 * @return l'instance d'Affichage
	 */
	public static Affichage getInstanceModeAffichage() {
		Affichage affichage = null;
		switch (MODE_AFFICHAGE) {
		case "console":
			affichage = Console.getInstance();
			break;
		case "fenetre":
			affichage = Fenetre.getInstance();
			break;
		default:
			Ecran.bug("Le mode d'affichage choisi est incorrect.");
			break;
		}
		return affichage;
	}

}